package com.mycompany.web.service;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class Ch12MqttServiceSubTest {

	public static void main(String[] args) throws Exception {
		MqttClient client = new MqttClient("tcp://localhost:1884", MqttClient.generateClientId(), null);
		client.connect();
		client.setCallback(new MqttCallback() {
			@Override
			public void messageArrived(String topic, MqttMessage message) throws Exception {
				String json = new String(message.getPayload());
				System.out.println(json);

			}
			
			@Override
			public void deliveryComplete(IMqttDeliveryToken token) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void connectionLost(Throwable cause) {
				// TODO Auto-generated method stub
				
			}
		});
		client.subscribe("/drone/mqttservice/pub");
	}

}
