package test.consumer;

import org.slf4j.MDC;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class FeignRequestInterceptor implements RequestInterceptor {
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header("logUUID", MDC.get("uuid"));
    }
}