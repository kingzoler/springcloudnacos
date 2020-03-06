package test.provider;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import feign.RequestInterceptor;
import feign.codec.ErrorDecoder;

@Configuration
public class FeignConfiguration {
	@Bean
	public ErrorDecoder errorDecoder(){
		return new UserErrorDecoder();
	}
	
	@Bean
	public RequestInterceptor requestInterceptor(){
		return new FeignRequestInterceptor();
	}
	
//	@Bean
//    public WebMvcRegistrations feignWebRegistrations() {
//        return new WebMvcRegistrations() {
//            @Override
//            public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
//                return new FeignRequestMappingHandlerMapping();
//            }
//        };
//    }
//
//    private static class FeignRequestMappingHandlerMapping extends RequestMappingHandlerMapping {
//        @Override
//        protected boolean isHandler(Class<?> beanType) {
//            return super.isHandler(beanType) &&
//                    !AnnotatedElementUtils.hasAnnotation(beanType, FeignClient.class);
//        }
//    }
}
