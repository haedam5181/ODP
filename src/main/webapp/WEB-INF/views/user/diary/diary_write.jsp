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
	<link href="<c:url value='/resources/user/css/diary_write.css' />" rel="stylesheet" type="text/css">
	
	<jsp:include page="../include/common_js.jsp" />
	<jsp:include page="../include/diary_write_vo_js.jsp"/>
	<jsp:include page="../include/diary_write_js.jsp"/>
	
</head>
<body>
	
	<jsp:include page="../include/header.jsp"/>
	
	<section>
		<div id="section_wrap">
			
			<div class="word">
				오늘의 정리
			</div>
	
			<div class="now_datetime">
				Today : <span class="today_txt">&nbsp;</span>
			</div>
	
			<div class="diary_write">
				
				<form action="<c:url value="/user/diary/diary_write_confirm" />" 
					  name="diary_write_form" 
					  method="post" 
					  enctype="multipart/form-data">
					
					<input type="hidden" name="d_write_full_year">
					<input type="hidden" name="d_write_month">
					<input type="hidden" name="d_write_date">
					<input type="hidden" name="d_write_day">
					<input type="hidden" name="d_write_hours">
					<input type="hidden" name="d_write_minutes">
					<input type="hidden" name="d_write_seconds">
					
					<input type="text" name="d_txt" placeholder="오늘 가장 인상 깊었던 사건은?">
					<br>
					<input type="file" name="d_img_file">
					<br>
					<input type="button" value="쓰기" onclick="diaryWriteForm();">
					<input type="reset" value="RESET">
					
				</form>
				
			</div>
			
		</div>
	</section>
	
	<jsp:include page="../include/footer.jsp" />
	
</body>
</html>