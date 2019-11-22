package com.mycompany.web.service;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.json.JSONObject;

public class Ch12MqttServicePubTest {

	public static void main(String[] args) throws Exception {
		MqttClient client = new MqttClient("tcp://localhost:1884", MqttClient.generateClientId(), null);
		client.connect();
		int count = 0;
		while(true) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("msgid", "temperature");
			jsonObject.put("value", count++);
			String json = jsonObject.toString();
			client.publish("drone/mqttservice/sub", json.getBytes(), 0, false);
			Thread.sleep(1000);
		}
	}

}
