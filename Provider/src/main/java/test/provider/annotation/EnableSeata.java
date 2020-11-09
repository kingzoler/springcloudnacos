package test.provider.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

import io.seata.spring.boot.autoconfigure.HttpAutoConfiguration;
import test.provider.FeignConfiguration;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import({FeignConfiguration.class, HttpAutoConfiguration.class})
public @interface EnableSeata {

}
