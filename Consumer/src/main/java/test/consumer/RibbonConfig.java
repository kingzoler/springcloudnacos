package test.consumer;

import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@RibbonClients(defaultConfiguration = RibbonRuleConfig.class)
public class RibbonConfig {
}