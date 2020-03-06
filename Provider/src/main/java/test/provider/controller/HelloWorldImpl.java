package test.provider.controller;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import test.provider.mapper.TestMapper;
import test.scin.HelloWorld;
import test.scin.TestHello;

@RestController
@Service
@Transactional
@Slf4j
public class HelloWorldImpl implements HelloWorld {

	@Autowired
	private TestHello testHello;

	@Autowired
	private TestMapper testMapper;

	@GlobalTransactional
	public String test(String id) {
		// throw new TestException("123");
		log.info("test");

		testMapper.insertTest(RandomStringUtils.randomAlphabetic(10));

		System.out.println(testHello.test("456"));

		//System.out.println(1/0);

		testMapper.insertTest(RandomStringUtils.randomAlphabetic(10));
		
		return "hello" + id.toString();
	}
}