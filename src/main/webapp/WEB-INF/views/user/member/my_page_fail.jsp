<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

	<link href="<c:url value='/resources/user/css/common.css' />" rel="stylesheet" type="text/css">
	<link href="<c:url value='/resources/user/css/my_page_fail.css' />" rel="stylesheet" type="text/css">
	
</head>
<body>
	
	<jsp:include page="../include/header.jsp"/>
	
	<section>
		<div id="section_wrap">
			
			<div class="word">
				다시 시도해 주세요~
			</div>
			
		</div>
	</section>
	
	<jsp:include page="../include/footer.jsp" />
	
</body>
</html>