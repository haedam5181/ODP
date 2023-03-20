<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

	<link href="<c:url value='/resources/user/css/common.css' />" rel="stylesheet" type="text/css">
	<link href="<c:url value='/resources/user/css/pw_modify.css' />" rel="stylesheet" type="text/css">
	
	<jsp:include page="../include/common_js.jsp" />
	<jsp:include page="../include/pw_modify_js.jsp"/>
	
</head>
<body>
	
	<jsp:include page="../include/header.jsp"/>
	
	<section>
		<div id="section_wrap">
			
			<div class="word">
				User Password Modify
			</div>
			
			<div class="pw_modify">
				
				<form action="<c:url value='/user/member/pw_modify_confirm'/>" name="pw_modify_form" method="post">
					
					<input type="hidden" name="m_id" value="${m_id}">
					
					<table>
						<tr>
							<td>
								new password : <input type="password" name="m_pw" placeholder="INPUT NEW PASSWORD">
							</td>
						</tr>
						
						<tr>
							<td>
								<input type="button" value="MODIFY" onclick="pwModifyForm();">
								<input type="reset" value="RESET">
								<input type="button" value="CANCEL">
							</td>
						</tr>
					</table>
					
				</form>
				
			</div>
			
		</div>
	</section>
	
	<jsp:include page="../include/footer.jsp" />
	
</body>
</html>