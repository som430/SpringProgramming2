package com.mycompany.web.service;

import javax.annotation.PreDestroy;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class Ch12MqttService {
	
	private static final Logger logger = LoggerFactory.getLogger(Ch12MqttService.class);
	
	private MqttClient client;
	
	public Ch12MqttService() {
		mqttConnect();
	}
	
	private void mqttConnect() {
		try {
			client = new MqttClient("tcp://localhost:1884", MqttClient.generateClientId(), null);
			client.connect();
			receiveMessage();
			logger.debug("Mqtt Broker에 연결 성공: tcp://localhost:1884");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void mqttDisconnect() {
		try {
			client.disconnectForcibly(1);
			client.close(true);
			logger.debug("Mqtt Broker 강제로 연결 끊기 성공");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	@PreDestroy
	public void destory() {
		logger.debug("Mqtt 종료 시키기");
		mqttDisconnect();
	}
	
	public void sendMessage(String topic, String message) {
		try {
			client.publish(topic, message.getBytes(), 0, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void receiveMessage() throws MqttException {
		client.setCallback(new MqttCallback() {
			@Override
			public void messageArrived(String topic, MqttMessage message) throws Exception {
				byte[] bytes = message.getPayload();
				String json = new String(bytes);
				logger.debug(json);
				//문제점 : subscribe Thread는 publish를 할 수 없습니다.
				//client.publish(...) (X)
				//해결책 : 새로운 스레드로 publish는 가능.
				Thread thread = new Thread() {
					@Override
					public void run() {
						//client.publish(...) (O)
					}
				};
				thread.start();
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
		
		client.subscribe("drone/mqttservice/sub");
	}

}
