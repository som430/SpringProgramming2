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
		<script type="text/javascript">
			var ws = null;
			//다 해석하고 준비시켜놓고 실행해라
			function connect() {
				ws = new WebSocket("ws://" + location.host + "<%=application.getContextPath()%>/websocket/temperature");
				ws.onopen = onConnect;
				ws.onmessage = onMessage;
				ws.onclose = onDisconnet;
			};
			
			function disconnect() {
				if(ws != null){
					ws.close();
					ws = null;
				}
			}
			function onConnect() {
				console.log("WebSocket 연결 성공");
			}
			
			function onDisconnect() {
				console.log("WebSocket 연결 끊김");
			}
			
			function onMessage(event) {
				var message = event.data;
				$("#receiveDiv").append(message + "<br/>");
			}
			
			function sendMessage() {
				var data = {"msgid":"msg1", "value":"30"};
				var strJson = JSON.stringify(data);
				ws.send(strJson);
			}
		</script>
	</head>
	<body>
		<h5>연결 및 끊기</h5>
		<button class="btn btn-info" onclick="connect()">연결</button>
		<button class="btn btn-info" onclick="disconnect()">끊기</button>
		
		<h5>WebSocket 메세지 보내기</h5>
		<button class="btn btn-info" onclick="sendMessage()">보내기</button>
		
		<h5>WebSocket 메세지 수신</h5>
		<div id="receiveDiv"></div>
	</body>
</html>