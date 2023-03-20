<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

	<link href="<c:url value='/resources/user/css/common.css' />" rel="stylesheet" type="text/css">
	<link href="<c:url value='/resources/user/css/login_form.css' />" rel="stylesheet" type="text/css">
	
	<script src="<c:url value='/resources/user/js/login_form.js' />" type="text/javascript"></script>
	
</head>
<body>
	
	<jsp:include page="../include/header.jsp"/>
	
	<section>
		<div id="section_wrap">
			
			<div class="word">
				
			</div>
			
			<div class="login_form">
				
				<form action="<c:url value='/user/member/login_confirm' />" name="login_form" method="post">
				
					<input type="text" name="m_id" placeholder="INPUT YOUR ID">
					<br>
					<input type="password" name="m_pw" placeholder="INPUT YOUR PW">
					<br>
					<input type="button" value="로그인" onclick="loginForm();">
					<input type="reset" value="RESET">
				
				</form>
				
			</div>
			
			<div class="create_account">
				<a href="<%=request.getContextPath()%>/user/member/create_account_form">계정생성</a>
			</div>
			
		</div>
	</section>
	
	<jsp:include page="../include/footer.jsp" />
	
</body>
</html>