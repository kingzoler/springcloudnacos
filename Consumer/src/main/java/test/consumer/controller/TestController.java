package test.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import test.scin.HelloWorld;
import test.scin.TestHello;

@RestController
@Slf4j
public class TestController {
	
	@Autowired
	private HelloWorld helloWorld;
	@Autowired
	private TestHello testHello;
	
	@GetMapping("/test")
	public String test(String t) {
		return helloWorld.test("123");
	}
	
	@GetMapping("/test3")
	public String test2(String t) {
		return testHello.test("123");
	}
}
