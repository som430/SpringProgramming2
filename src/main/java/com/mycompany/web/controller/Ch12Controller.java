package com.mycompany.web.controller;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.web.service.Ch12MqttService;

@Controller
@RequestMapping("/ch12")
public class Ch12Controller {
	private static final Logger logger = LoggerFactory.getLogger(Ch12Controller.class);
	
	@Autowired
	private Ch12MqttService mqttService;
	
	@RequestMapping("/content")
	public String content() {
		return "ch12/content";
	}
	
	@RequestMapping("/sendMqtt")
	public String sendMqtt(String msgid, String param1, String param2) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("msgid", msgid);
		jsonObject.put("param1", param1);
		jsonObject.put("param2", param2);
		String json = jsonObject.toString();
		mqttService.sendMessage("/drone/mqttservice/pub", json);
		return "ch12/content";
	}
}
