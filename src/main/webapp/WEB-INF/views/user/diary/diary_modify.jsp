<%@page import="com.office.diary.user.diary.UserDiaryVo"%>
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
	<link href="<c:url value='/resources/user/css/diary_modify.css' />" rel="stylesheet" type="text/css">
	
	<jsp:include page="../include/common_js.jsp" />
	<jsp:include page="../include/diary_modify_js.jsp"/>
	
</head>
<body>
	
	<jsp:include page="../include/header.jsp"/>
	
	<section>
		<div id="section_wrap">
			
			<div class="word">
				일기확인 및 변경
			</div>
			
			<div class="diary_modify">
				
				<form action="<c:url value="/user/diary/diary_modify_confirm" />" 
					  name="diary_modify_form" 
					  method="post" 
					  enctype="multipart/form-data">
					
					<input type="hidden" name="d_no" value="${userDiaryVo.d_no}">
					
					<input type="text" name="d_txt" value="${userDiaryVo.d_txt}" placeholder="오늘 가장 인상 깊었던 사건은?">
					<br>
					<input type="file" name="d_img_file">
					<br>
					<div>
						<img class="thumnail" src="<c:url value='/diaryUploadImg/${userDiaryVo.m_id}/${userDiaryVo.d_img_name}'/>">
					</div>
					<div>
						<a class="thumnail_delete" href="#none">이미지 삭제</a>
					</div>
					<input type="button" value="정보변경" onclick="diaryModifyForm();">
					<input type="reset" value="RESET" onclick="diaryModifyFormReset();">
					
				</form>
				
			</div>
			
		</div>
	</section>
	
	<jsp:include page="../include/footer.jsp" />
	
</body>
</html>