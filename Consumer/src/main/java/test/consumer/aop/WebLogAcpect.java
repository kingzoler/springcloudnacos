package test.consumer.aop;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.MDC;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
@Order(Ordered.HIGHEST_PRECEDENCE + 2)
public class WebLogAcpect {
	@Pointcut("execution(public * test.consumer.controller..*.*(..))")
	public void webLog() {
	}

	@Before("webLog()")
	public void doBefore(JoinPoint joinPoint) throws Throwable {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		String name = MyThreadLocal.AOP_POINT.get();
		if (name == null) {
			MyThreadLocal.AOP_POINT.set(joinPoint.getSignature().toLongString());
		}
		if (MDC.get("uuid") == null) {
			String uuid = request.getHeader("logUUID");
			if (uuid == null) {
				uuid = UUID.randomUUID().toString();
			}
			MDC.put("uuid", uuid);
		}
	}

	@After("webLog()")
	public void doAfter(JoinPoint joinPoint) throws Throwable {
		String name = joinPoint.getSignature().toLongString();
		if (name.equals(MyThreadLocal.AOP_POINT.get())) {
			MDC.remove("uuid");
			MyThreadLocal.AOP_POINT.remove();
		}
	}
}
