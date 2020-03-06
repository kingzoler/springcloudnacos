package test.provider.controller;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import test.scin.HelloWorld2;

@RestController
@Service
@Transactional
@Slf4j
public class HelloWorldImpl2 implements HelloWorld2 {

	public String test2(String id) {
		log.info("dd");
		return null;
	}

	@Override
	public String test3(String id) {
		log.info("path参数：{}", id);
		return null;
	}
}