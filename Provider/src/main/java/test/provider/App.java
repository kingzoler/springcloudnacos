package test.provider;

import java.lang.reflect.Field;
import java.util.Map;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.alibaba.druid.util.JdbcConstants;

import io.seata.rm.datasource.undo.UndoLogManager;
import io.seata.rm.datasource.undo.UndoLogManagerFactory;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@MapperScan("test.provider.mapper")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "test.scin")
public class App {
	public static void main(String[] args)
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {

		Field field = UndoLogManagerFactory.class.getDeclaredField("UNDO_LOG_MANAGER_MAP");
		field.setAccessible(true);
		Map<String, UndoLogManager> map = (Map<String, UndoLogManager>) field.get(UndoLogManagerFactory.class);
		map.put(JdbcConstants.MYSQL, new MySQLUndoLogManager());

		SpringApplication.run(App.class, args);
	}
}
