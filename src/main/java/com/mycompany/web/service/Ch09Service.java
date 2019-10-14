package com.mycompany.web.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.mycompany.web.dao.Ch09Dao;

@Service
public class Ch09Service {
	
	private static final Logger logger = LoggerFactory.getLogger(Ch09Service.class);	
	
	@Autowired
	private Ch09Dao ch09Dao;
	
	public void method1() {//method1 실행되기 전에 set먼저 실행되야함 
		logger.debug("실행"); 
		ch09Dao.insert(); //안그러면 NullPointException 발생
	}
}
