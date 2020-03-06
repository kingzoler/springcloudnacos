package test.provider.controller;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import test.provider.mapper.Test2Mapper;
import test.scin.TestHello;

@RestController
@Service
@Transactional
@Slf4j
public class TestHelloImpl implements TestHello {

	@Autowired
	private Test2Mapper test2Mapper;

	@GlobalTransactional
	public String test(String id) {
		log.info(id);
		test2Mapper.insertTest2(RandomStringUtils.randomAlphabetic(10));
		return "Test" + id.toString();
	}
}