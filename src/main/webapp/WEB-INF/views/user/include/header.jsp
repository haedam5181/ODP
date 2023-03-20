<%@page import="com.office.diary.user.member.UserMemberVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link href="<c:url value='/resources/user/css/header.css' />" rel="stylesheet" type="text/css">

<header>
	<div id="header_wrap">
		
		<div class="logo">
			한줄로 표현하는 <span>나의 하루</span>
		</div>
		
		<div class="gnb">
		
			<% 
				UserMemberVo userMemberVo =	(UserMemberVo) session.getAttribute("logined_userMemberVo");
			%>
			
			<%
				if (userMemberVo == null) {
			%>
			
			<a href="<c:url value='/' />">처음화면</a>&nbsp;&nbsp; | &nbsp;&nbsp;
			<a href="<c:url value='/user/member/login_form' />">로그인</a>&nbsp;&nbsp;
			
			<%
                } else {
            %>
            
			<a href="<c:url value='/' />">처음화면</a>&nbsp;&nbsp; | &nbsp;&nbsp;
			<a href="<c:url value='/user/member/logout_confirm' />">로그아웃</a>&nbsp;&nbsp; | &nbsp;&nbsp;
			<a href="<c:url value='/user/member/my_page' />">내정보</a>&nbsp;&nbsp; | &nbsp;&nbsp;
			<a href="<c:url value='/user/diary/diary_list' />">일기장</a>&nbsp;&nbsp;
			
			<%
                }
            %>
		</div>
		
	</div>
</header>