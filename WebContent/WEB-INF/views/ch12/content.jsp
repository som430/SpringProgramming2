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
		
	</head>
	<body>
		<h5>MQTT Publishing 요청</h5>
		<a href="sendMqtt?msgid=msg1&param1=value1&param2=value2" > JSON 메시지 전송</a>
	</body>
</html>