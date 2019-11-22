package com.mycompany.web.websocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PreDestroy;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class WebSocketTemperature extends TextWebSocketHandler{
	private static final Logger logger = LoggerFactory.getLogger(WebSocketTemperature.class);  
	
	private List<WebSocketSession> list = new ArrayList<>();
	
	private int count = 0;
	
	/*public WebSocketTemperature() {
		Thread thread = new Thread() {
			@Override
			public void run() {
				while(true) {
					try {
						JSONObject jsonObject = new JSONObject();
						jsonObject.put("msgid", "temperature");
						jsonObject.put("value", count++);
						String json = jsonObject.toString();
						//for문이 끝나기전에 list 값이 바뀔 수 있기 때문에 cloned 사용 
						List<WebSocketSession> cloned = new ArrayList<>(list);
						for(WebSocketSession wss: cloned) {
							TextMessage message = new TextMessage(json);
							wss.sendMessage(message);
						}
						Thread.sleep(1000);
					}catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		};
		thread.start();
	}*/
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		//session에 client 정보 담겨있음 
		list.add(session);
		logger.debug(session.getId()+ "가 접속되었습니다.");
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		logger.debug(session.getId()+ "의 접속이 끊어졌습니다.");
		list.remove(session);
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String json = message.getPayload();
		logger.debug(json);
	}
	
	//-------------------------------------------------------------------
	
	public WebSocketTemperature() {
		mqttConnect();
	}
	
	private MqttClient client;
	
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
	
	private void receiveMessage() throws MqttException {
		client.setCallback(new MqttCallback() {
			@Override
			public void messageArrived(String topic, MqttMessage message) throws Exception {
				byte[] bytes = message.getPayload();
				String json = new String(bytes);
				List<WebSocketSession> cloned = new ArrayList<>(list);
				for(WebSocketSession wss: cloned) {
					TextMessage msg = new TextMessage(json);
					wss.sendMessage(msg);
				}
				/*//문제점 : subscribe Thread는 publish를 할 수 없습니다.
				//client.publish(...) (X)
				//해결책 : 새로운 스레드로 publish는 가능.
				Thread thread = new Thread() {
					@Override
					public void run() {
						//client.publish(...) (O)
					}
				};
				thread.start();*/
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
		
		client.subscribe("drone/websocket/sub");
	}
	
	public void sendMessage(String topic, String message) {
		try {
			client.publish(topic, message.getBytes(), 0, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
