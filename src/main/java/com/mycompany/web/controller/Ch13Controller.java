package com.mycompany.web.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ch13")
public class Ch13Controller {
	private static final Logger logger = LoggerFactory.getLogger(Ch13Controller.class);
	
	@RequestMapping("/content")
	public String content() {
		return "ch13/content";
	}
	
}
