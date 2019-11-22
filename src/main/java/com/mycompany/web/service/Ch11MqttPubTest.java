package com.mycompany.web.service;

import java.util.Date;
import java.util.Random;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.json.JSONArray;
import org.json.JSONObject;

public class Ch11MqttPubTest {

	public static void main(String[] args) throws Exception {
		MqttClient client = new MqttClient("tcp://localhost:1884", MqttClient.generateClientId(), null);
		client.connect();
		Random random = new Random();
		while(true) {
			JSONArray jsonArray = new JSONArray();
			jsonArray.put(new Date().getTime());
			jsonArray.put(random.nextInt(30)); //0~29사이의 임의의 정수값
			String json = jsonArray.toString(); //[0101020303, 28]
			client.publish("/drone/chart/pub", json.getBytes(), 0, false);
			Thread.sleep(1000);
		}
	}

}
