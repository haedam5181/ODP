<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

	<jsp:include page="./include/home_js.jsp" />

</head>
<body>
	
	<jsp:include page="./include/header.jsp" />
	
	<section>
		<div id="section_wrap">
			
			<div>
				
				<div>
					<form action="<c:url value="/admin/member/login_confirm"/>" name="login_form" method="post">
						<input type="text" name="am_id" placeholder="INPUT ADMIN ID">
						<br>
						<input type="password" name="am_pw" placeholder="INPUT ADMIN PW">
						<br>
						<input type="button" value="ADMIN LOGIN" onclick="adminLoginForm();">
						<input type="reset" value="RESET">
					</form>
				</div>
				
				<div>
					<a href="<c:url value="/admin/member/create_account"/>">CREATE ADMIN ACCOUNT</a>
				</div>
				
			</div>
			
		</div>
	</section>
	
	<jsp:include page="../user/include/footer.jsp" />
	
</body>
</html>