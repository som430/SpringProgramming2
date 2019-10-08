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
	<p>
		<a href="join?mid=fall&mname=홍길동&mpassword=12345&mage=25&mbirth=1995-12-25"
			class="btn btn-primary">get 방식 테스트</a>
		<a href="join2?mid=fall&mname=홍길동&mpassword=12345&mage=25&mbirth=1995-12-25"
			class="btn btn-primary">get 방식 테스트(객체로 받기)</a>	
	</p>
	
	<p><%--<form method="post" action="join">--%>
	<form id="joinForm" name="joinForm" method="post" action="join2">
		<div class="input-group mb-3">
			<div class="input-group-prepend">
				<span class="input-group-text" id="basic-addon1">아이디</span>
			</div>
			<input id="mid" name="mid" value="fall" type="text" class="form-control" placeholder="Username"
				aria-label="Username" aria-describedby="basic-addon1">
		</div>

		<div class="input-group mb-3">
			<div class="input-group-prepend">
				<span class="input-group-text" id="basic-addon1">이름</span>
			</div>
			<input id="mname" name="mname" value="홍길동" type="text" class="form-control" placeholder="Username"
				aria-label="Username" aria-describedby="basic-addon1">
		</div>
		
		<div class="input-group mb-3">
			<div class="input-group-prepend">
				<span class="input-group-text" id="basic-addon1">비밀번호</span>
			</div>
			<input id="mpassword" name="mpassword" value="12345" type="password" class="form-control" placeholder="Username"
				aria-label="Username" aria-describedby="basic-addon1">
		</div>
		
		<div class="input-group mb-3">
			<div class="input-group-prepend">
				<span class="input-group-text" id="basic-addon1">나이</span>
			</div>
			<input id="mage" name="mage" value="25" type="number" class="form-control" placeholder="Username"
				aria-label="Username" aria-describedby="basic-addon1">
		</div>
		
		<div class="input-group mb-3">
			<div class="input-group-prepend">
				<span class="input-group-text" id="basic-addon1">생년월일</span>
			</div>
			<input id="mbirth" name="mbirth" value="1995-12-25" type="date" class="form-control" placeholder="Username"
				aria-label="Username" aria-describedby="basic-addon1">
		</div>
		<input type="submit" value="회원가입" class="btn btn-info">
	</form></p>
	<p>
		<script type="text/javascript">
			function join(){
				//how1
				/*var mid = $("#mid").val();
				if(mid == "") return; //유효성 검사 
				var mname = $("#mname").val();
				var mpassword = $("#mpassword").val();
				var mage = $("#mage").val();
				var mbirth = $("#mbirth").val();
				var params ="";
				params += "mid=" + mid + "&";
				params += "mname=" + mname + "&";
				params += "mpassword=" + mpassword + "&";
				params += "mage=" + mage + "&";
				params += "mbirth=" + mbirth;
				
				location.href="join2?" + params;*/
				
				//how2
				/*var joinForm = document.querySelector("#joinForm");
				//var joinForm = document.joinForm; //같은 코드 
				joinForm.submit();*/
				
				//how3
				document.joinForm.submit(); // joinForm은 name
			}
		</script>
		<button onclick="join()" class="btn btn-info">자바스크립트를 이용해서 데이터 전달</button>
	</p>
	<p>
		<script type="text/javascript">
			function joinAjax(){
				$.ajax({
					url: "join3",
					//data: "mid=fall&mname=홍길동",
					data: {mid:"fall", mname:"홍길동"},
					method: "post",
					success:function(data){
						var html = "<span>mid: " +data.mid+ "</span> <br/>";
						html += "<span>mname: " +data.mname+ "</span>";
						$("#resultDiv").html(html);	
					}
				});
			}
		</script>
		<button onclick="joinAjax()" class="btn btn-info">AJAX를 통한 데이터 전달</button>
		<div id="resultDiv">
		</div>
	</p>
	</body>
</html>