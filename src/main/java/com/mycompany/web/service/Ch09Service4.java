package com.mycompany.web.service;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.web.dao.Ch09Dao4;

import org.slf4j.Logger;

@Service
public class Ch09Service4 {
	private static final Logger logger = LoggerFactory.getLogger(Ch09Service4.class);

	@Autowired
	private Ch09Dao4 ch09Dao4;
	
	public void method4() {
		logger.debug("실행");
		ch09Dao4.insert();
	}

}
