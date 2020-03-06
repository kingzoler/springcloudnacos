package test.consumer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class Test2Controller {
	
	@GetMapping("/test2")
	public String test2() {
		return "test2";
	}
}
