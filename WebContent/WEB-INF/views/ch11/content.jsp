<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<script type="text/javascript"
			src="<%=application.getContextPath()%>/resources/js/jquery-3.4.1.min.js"></script>
		<link rel="stylesheet" type="text/css"
			href="<%=application.getContextPath()%>/resources/bootstrap-4.3.1-dist/css/bootstrap.min.css">
		<script type="text/javascript"
			src="<%=application.getContextPath()%>/resources/bootstrap-4.3.1-dist/js/bootstrap.min.js"></script>
			<script type="text/javascript"
			src="<%=application.getContextPath()%>/resources/js/paho-mqtt-min.js"></script>
		<script type="text/javascript">
			$(function(){
				//MQTT Broker와 연결하기 
				client = new Paho.MQTT.Client(location.hostname, 61624, "clientId"+new Date().getTime());
				client.onMessageArrived = onMessageArrived;
				
				client.connect({onSuccess:onConnect}); //연결 성공할 때 onConnect실행
			});
			
			//연결이 완료되었을 때 자동으로 실행(콜백)되는 함수 
			function onConnect() {
				  client.subscribe("/drone/fc/pub"); // /drone/pub : 토픽 이름 //메세지 도착하면 onMess~ 실행
			}
			
			//메세지를 수신했을 때 자동으로 실행(콜백)되는 함수
			function onMessageArrived(message){
				$("#divReceive").append(message.payloadString + "<br/>");
			}

			function sendMessage(){
				var data = $("#inputData").val();
				var message = new Paho.MQTT.Message(data);
				message.destinationName = "/drone/fc/sub";
				client.send(message);
			}
		</script>
	</head>
	<body>
		<input id="inputData" type="text" />
		<button onclick="sendMessage()">보내기</button>
		<h5>수신된 메세지</h5>
		<div id="divReceive"></div>
	</body>
</html>