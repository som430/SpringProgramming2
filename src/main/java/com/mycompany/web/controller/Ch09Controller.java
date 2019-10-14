package com.mycompany.web.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.web.service.Ch09MemberService;
import com.mycompany.web.service.Ch09MemberService1;
import com.mycompany.web.service.Ch09MemberService2;
import com.mycompany.web.service.Ch09Service;
import com.mycompany.web.service.Ch09Service2;
import com.mycompany.web.service.Ch09Service3;
import com.mycompany.web.service.Ch09Service4;

@Controller
@RequestMapping("/ch09")
public class Ch09Controller {
	private static final Logger logger = LoggerFactory.getLogger(Ch09Controller.class);
	
	@Autowired
	private Ch09Service ch09Service;	
	@Autowired
	private Ch09Service2 ch09Service2;	
	@Autowired
	private Ch09Service3 ch09Service3;	
	@Autowired
	private Ch09Service4 ch09Service4;
	
	/*@Autowired
	private Ch09MemberService1 ch09MemberService1;
	@Autowired
	private Ch09MemberService2 ch09MemberService2;*/
	
	@Resource(name="ch09MemberService")
	private Ch09MemberService ch09MemberService;

	@RequestMapping("/content")
	public String content() {
		return "ch09/content";
	}		
	
	@RequestMapping("/method1")
	public String method1() {
		logger.debug("실행");
		ch09Service.method1();
		return "redirect:/ch09/content";
	}
	
	@RequestMapping("/method2")
	public String method2() {
		logger.debug("실행");
		ch09Service2.method2();
		return "redirect:/ch09/content";
	}
	
	@RequestMapping("/method3")
	public String method3() {
		logger.debug("실행");
		ch09Service3.method3();
		return "redirect:/ch09/content";
	}	
	
	@RequestMapping("/method4")
	public String method4() {
		logger.debug("실행");
		ch09Service4.method4();
		return "redirect:/ch09/content";
	}	
	
}
