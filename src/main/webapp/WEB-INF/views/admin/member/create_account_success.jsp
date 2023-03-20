<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
	
	<jsp:include page="../include/header.jsp" />
	
	<section>
		<div id="section_wrap">
			
			<div>
				
				<div class="word">
					CREATE ADMIN ACCOUNT SUCCESS
				</div>
				
				<div>
					<a href="<c:url value="/admin"/>">ADMIN LOG IN</a>
				</div>
				
			</div>
			
		</div>
	</section>
	
	<jsp:include page="../../user/include/footer.jsp" />
	
</body>
</html>