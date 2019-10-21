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
			function btn1Click(){
				$.ajax({
					url:"connTest",
					success: function(data){
						if(data.result) {
							$("#div1").html("success");
						}else {
							$("#div1").html("fail");
						}
					}
				});
			}
			function btn2Click(){
				$.ajax({
					url:"getMember",
					data: {mid:"fall"}, //매개변수 
					success: function(data){
						$("#div2").html(data);
					}
				});
			}
		</script>
	</head>
	<body>
		<p>
			<button id="btn1" onclick="btn1Click()" type="button" class="btn btn-info">연결 테스트</button>
			<div id="div1"></div>
		</p>
		<p>
			<button id="btn2" onclick="btn2Click()" type="button" class="btn btn-info">멤버 정보 가져오기</button>
			<div id="div2"></div>
		</p>
		<p>
			<a href="boardList?pageNo=1" class="btn btn-info">게시판</a>
		</p>
	</body>
</html>