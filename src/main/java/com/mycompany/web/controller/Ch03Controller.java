package com.mycompany.web.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mycompany.web.dto.Ch03Dto;

@Controller
@RequestMapping("/ch03")
public class Ch03Controller {
	private static final Logger logger = LoggerFactory.getLogger(Ch03Controller.class);
	
	@RequestMapping("/content")
	public String content() {
		return "ch03/content";
	}
	
	@RequestMapping("/join")
	public String join(String mid, String mname, String mpassword, @RequestParam(defaultValue="0")int mage, @DateTimeFormat(pattern="yyyy-MM-dd")Date mbirth) {
		logger.debug("mid: "+mid);
		logger.debug("mname: "+mname);
		logger.debug("mpassword: "+mpassword);
		logger.debug("mage: "+mage);
		logger.debug("mbirth: "+mbirth);
		return "ch03/content";
	}
	
	@RequestMapping("/join2")
	public String join2(Ch03Dto member) {
		logger.debug("mid: "+member.getMid());
		logger.debug("mname: "+member.getMname());
		logger.debug("mpassword: "+member.getMpassword());
		logger.debug("mage: "+member.getMage());
		logger.debug("mbirth: "+member.getMbirth());
		return "ch03/content";
	}
	
	@RequestMapping("/join3")
	public String join3(Ch03Dto member, HttpServletRequest request) {
		logger.debug("mid: "+member.getMid());
		logger.debug("mname: "+member.getMname());
		
		request.setAttribute("mid", member.getMid());
		request.setAttribute("mname", member.getMname());
		return "ch03/join3";
	}
	
}
