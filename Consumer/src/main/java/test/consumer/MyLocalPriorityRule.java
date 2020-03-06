package test.consumer;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

/**
 * 本地优先策略 只连接本地和服务器的服务
 * 
 * @author WX
 *
 */
public class MyLocalPriorityRule extends AbstractLoadBalancerRule {

	private List<String> localAddr;
	private static final boolean AVAILABLE_ONLY_SERVERS = true;
	private static final boolean ALL_SERVERS = false;
	@Value("${serverIP}")
	private String serverIP;
	private List<String> serverList;

	private static Logger log = LoggerFactory.getLogger(MyLocalPriorityRule.class);

	public MyLocalPriorityRule() {
		localAddr = new ArrayList<>();
		// 获取本地网卡IP
		Enumeration<NetworkInterface> netInterfaces;
		try {
			netInterfaces = NetworkInterface.getNetworkInterfaces();
			InetAddress ip;
			while (netInterfaces.hasMoreElements()) {
				NetworkInterface ni = netInterfaces.nextElement();
				Enumeration<InetAddress> addresses = ni.getInetAddresses();
				while (addresses.hasMoreElements()) {
					ip = addresses.nextElement();
					if (!ip.isLoopbackAddress() && ip.getHostAddress().indexOf(':') == -1) {
						localAddr.add(ip.getHostAddress());
					}
				}
			}
		} catch (Exception e) {
			log.error("get local addr error", e);
		}
	}

	public MyLocalPriorityRule(ILoadBalancer lb) {
		this();
		setLoadBalancer(lb);
	}

	@PostConstruct
	private void init() {
		serverList = Arrays.asList(serverIP.split(","));
		System.out.println(serverList);
	}

	public Server choose(ILoadBalancer lb, Object key) {
		if (lb == null) {
			log.warn("no load balancer");
			return null;
		}

		List<Server> reachableServers = lb.getReachableServers();
		List<Server> allServers = lb.getAllServers();

		int upCount = reachableServers.size();
		int serverCount = allServers.size();

		if ((upCount == 0) || (serverCount == 0)) {
			log.warn("No up servers available from load balancer: " + lb);
			return null;
		}

		Server server = null;

		for (Server s : allServers) {
			if (s == null) {
				/* Transient. */
				Thread.yield();
				continue;
			}

			String addr = s.getHost();

			if (s.isAlive() && (s.isReadyToServe())) {
				// 本地的服务
				if (localAddr.contains(addr)) {
					server = s;
					return server;
				}
				// 服务器上的服务
				if (serverIP.contains(addr)) {
					server = s;
				}
			}
		}

		if (server == null) {
			log.warn("No available alive servers from load balancer: " + lb);
		}
		return server;
	}

	@Override
	public Server choose(Object key) {
		return choose(getLoadBalancer(), key);
	}

	@Override
	public void initWithNiwsConfig(IClientConfig clientConfig) {
	}
}