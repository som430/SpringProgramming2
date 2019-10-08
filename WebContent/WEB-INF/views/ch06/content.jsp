<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
			function btnLogin(){
				if($("#mid").val() == "")return false;
				if($("#mpassword").val() == "")return false;
				return true;
			}
			function btnLogout(){
				location.href = "logout";
			}
		</script>
	</head>
	<body>
		<h5>[content.jsp]</h5>
		<div>
			<c:if test="${loginResult != 'success'}"><%-- test값이 true이면 실행 --%>
				<form id="loginForm" method="post" action="login">
				  <div class="form-group">
				    <label for="mid">아이디</label>
				    <input type="text" class="form-control" id="mid" name="mid">
				    <c:if test="${loginResult == 'WrongMid'}">
				    	<span style="color:red;">login 아이디가 없습니다.</span>
				    </c:if>
				  </div>
				  <div class="form-group">
				    <label for="mpassword">패스워드</label>
				    <input type="password" class="form-control" id="mpassword" name="mpassword">
				    <c:if test="${loginResult == 'wrongMpassword'}">
				    	<span style="color:red;">비밀번호가 틀립니다.</span>
				  	</c:if>
				  </div>
				  <input onclick="return btnLogin()" type="submit" class="btn btn-primary" value="로그인" />
				  <%-- return값에 따라 submit 제어 --%>
				</form>
			</c:if> 
			<c:if test="${loginResult == 'success'}">
				<div id="logoutDiv">
					<button onclick="btnLogout()" class="btn btn-danger">로그아웃</button>
				</div>
			</c:if>
		</div>
	</body>
</html>