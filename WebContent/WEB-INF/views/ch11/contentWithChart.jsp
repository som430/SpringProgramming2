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
		<script type="text/javascript" 
			src="<%=application.getContextPath()%>/resources/Highcharts-7.2.1/code/highcharts.js"></script>
		<script type="text/javascript" 
			src="<%=application.getContextPath()%>/resources/Highcharts-7.2.1/code/themes/dark-unica.js"></script>
		<script type="text/javascript">
			$(function(){
				//MQTT Broker와 연결하기 
				client = new Paho.MQTT.Client(location.hostname, 61624, "clientId"+new Date().getTime());
				client.onMessageArrived = onMessageArrived;
				
				client.connect({onSuccess:onConnect}); //연결 성공할 때 onConnect실행
			});
			
			//연결이 완료되었을 때 자동으로 실행(콜백)되는 함수 
			function onConnect() {
				  client.subscribe("/drone/chart/pub"); 
			}
			
			//메세지를 수신했을 때 자동으로 실행(콜백)되는 함수
			function onMessageArrived(message){
				var json = JSON.parse(message.payloadString); //[x,y]
				var series = chart.series[0];
				var shift = series.data.length > 300;
				series.addPoint(json, true, shift);
			}
			
			var chart = null;
			$(function() {
				chart = new Highcharts.Chart({
					chart: {
						renderTo: "divChart",
						type: "areaspline"
					},
					title: {
						text: "Temperature Chart"
					},
					xAxis: {
						type: "datetime",
						tickPixelInterval: 100,
						maxZoom: 20000
					},
					yAxis: {
						minPadding: 0.2,
						maxPadding: 0.2
					},
					series: [
						{type: 'area', name:"temperature", data:[]}
					]
				});
			});
		</script>
	</head>
	<body>
		<h5>온도 차트</h5>
		<div id="divChart" style="width:100%; height:400px;"></div>
	</body>
</html>