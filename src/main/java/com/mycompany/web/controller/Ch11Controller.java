package com.mycompany.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ch11")
public class Ch11Controller {
	private static final Logger logger = LoggerFactory.getLogger(Ch11Controller.class);
	
	@RequestMapping("/content")
	public String content() {
		//return "ch11/content";
		return "ch11/contentWithChart";
	}
}
