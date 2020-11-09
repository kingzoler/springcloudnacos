package test.provider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import test.provider.annotation.EnableSeata;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@MapperScan("test.provider.mapper")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "test.scin")
@EnableSeata
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
