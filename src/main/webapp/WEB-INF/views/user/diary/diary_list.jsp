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
	<link href="<c:url value='/resources/user/css/diary_list.css' />" rel="stylesheet" type="text/css">
	
	<jsp:include page="../include/common_js.jsp" />
	<jsp:include page="../include/diary_list_vo_js.jsp"/>
	<jsp:include page="../include/diary_list_js.jsp"/>
	
</head>
<body>
	
	<jsp:include page="../include/header.jsp"/>
	
	<section>
		<div id="section_wrap">
			
			<%
			UserMemberVo userMemberVo =	(UserMemberVo) session.getAttribute("logined_userMemberVo");
			%>
			
			<div class="word">
				<span class="m_id"><%=userMemberVo.getM_id()%></span> 님의 일기장
			</div>
			
			<div class="diary_list_date_write">
				
				<select name="diary_list_year_select">
					<option value="2020">2020</option>
					<option value="2021">2021</option>
					<option value="2022">2022</option>
					<option value="2023">2023</option>
					<option value="2024">2024</option>
				</select>
				년
				<select name="diary_list_month_select">
					<option value="-1">-- 선택 --</option>
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
					<option value="5">5</option>
					<option value="6">6</option>
					<option value="7">7</option>
					<option value="8">8</option>
					<option value="9">9</option>
					<option value="10">10</option>
					<option value="11">11</option>
					<option value="12">12</option>
				</select>
				월
				<select name="diary_list_date_select">

				</select>
				일
				
				<a class="diary_write" href="<c:url value="/user/diary/diary_write"/>">쓰기</a>
				
			</div>
			
			<!-- 목록(Template 이용) 시작 -->
			<div class="diary_list">
				
			</div>
			<!-- 목록(Template 이용) 끝 -->
			
			<!-- 페이지 시작 -->
			<div class="page_wrap">
				
				<div class="page_number">
				
				</div>
				
				<div class="page_goto">
					<input type="number" name="page_target_num">&nbsp; &#47; <span class="page_total_num"></span>
					<a class="goto_page_btn" href="#none">페이지로 가기</a>
				</div>
				
			</div>
			<!-- 페이지 끝 -->
			
			<!-- 디테일 뷰 시작 -->
			<div id="diary_detail_wrap">
				
				<div class="diary_detail">
					
					<ul>
						<li>
							<a href="#none">&#60;</a>
						</li>
						<li>
							<div class="write_date">
								<span class="date"></span> <span class="time"></span>
							</div>
							<div class="write_img">
								<img>
							</div>
							<div class="write_txt">
								<span></span>
							</div>
							<div class="close_download">
								<a class="close" href="#none">CLOSE</a>&nbsp;&nbsp; | &nbsp;&nbsp;
								<a class="download" href="#none">DOWNLOAD</a>
							</div>
						</li>
						<li>
							<a href="#none">&#62;</a>
						</li>
					</ul>
					
				</div>
				
			</div>
			<!-- 디테일 뷰 끝 -->
			
		</div>
	</section>
	
	<jsp:include page="../include/footer.jsp" />
	
</body>
</html>

<template id="diary_list_item">
<table>
	<tr>
		<td class="date">
			<div><span class="year">2022</span></div>
			<div><span class="month">3</span>/<span class="date">20</span></div>
			<div><span class="day">수요일</span></div>
		</td>
		<td class="txt">
			<a href="#none">내용</a>
		</td>
		<td class="thumnail">
			<a href="#none"><img src="<c:url value="/resources/user/img/intro.jpg" />"></a>
		</td>
		<td class="modify_delete">
			<a class="modify" href="#none">수정</a>
			<br>
			<a class="delete" href="#none">삭제</a>
		</td>
	</tr>
</table>
</template>
