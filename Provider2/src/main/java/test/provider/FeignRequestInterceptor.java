package test.provider;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import io.seata.core.context.RootContext;

public class FeignRequestInterceptor implements RequestInterceptor {
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header("logUUID", MDC.get("uuid"));
        
        String xid = RootContext.getXID();
		if (StringUtils.isNotBlank(xid)) {
			requestTemplate.header(RootContext.KEY_XID, xid);
		}
    }
}