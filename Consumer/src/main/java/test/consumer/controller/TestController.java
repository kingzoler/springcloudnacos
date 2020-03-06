package test.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import test.scin.HelloWorld;

@RestController
@Slf4j
public class TestController {
	
	@Autowired
	private HelloWorld helloWorld;
	
	@GetMapping("/test")
	public String test(String t) {
		return helloWorld.test("123");
	}
}
