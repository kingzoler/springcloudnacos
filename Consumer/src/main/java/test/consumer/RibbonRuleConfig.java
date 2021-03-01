package test.consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.loadbalancer.IRule;

@Configuration
public class RibbonRuleConfig {
    @Bean
    public IRule ribbonRulr() {
        return new MyLocalPriorityRule();
    }
}