<%@page import="com.office.diary.user.member.UserMemberVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

	<link href="<c:url value='/resources/user/css/common.css' />" rel="stylesheet" type="text/css">
	<link href="<c:url value='/resources/user/css/diary_modify_fail.css' />" rel="stylesheet" type="text/css">
	
</head>
<body>
	
	<jsp:include page="../include/header.jsp"/>
	
	<section>
		<div id="section_wrap">
			
			<div class="word">
				DIARY MODIFY FAIL!!
			</div>
			
			<div class="diary_list_write">
				<a href="<c:url value="/user/diary/diary_list"/>">diary_list</a> &nbsp;&nbsp; | &nbsp;&nbsp;
				<a href="<c:url value="/user/diary/diary_write"/>">diary_write</a>
			</div>
			
		</div>
	</section>
	
	<jsp:include page="../include/footer.jsp" />
	
</body>
</html>