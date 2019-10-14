package com.mycompany.web.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mycompany.web.dao.Ch09Dao2;

@Component
public class Ch09Service2 {
	private static final Logger logger = LoggerFactory.getLogger(Ch09Service2.class);

	@Autowired
	private Ch09Dao2 ch09Dao2;

	public void method2() {
		logger.debug("실행");
		ch09Dao2.insert();
	}
}
