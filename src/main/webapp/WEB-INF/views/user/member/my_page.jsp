<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

	<link href="<c:url value='/resources/user/css/common.css' />" rel="stylesheet" type="text/css">
	<link href="<c:url value='/resources/user/css/my_page.css' />" rel="stylesheet" type="text/css">
	
	<jsp:include page="../include/common_js.jsp" />
	<jsp:include page="../include/my_page_js.jsp"/>
	
</head>
<body>
	
	<jsp:include page="../include/header.jsp"/>
	
	<section>
		<div id="section_wrap">
			
			<div class="word">
				나의 정보
			</div>
			
			<div class="my_page">
				
				<form action="<c:url value='/user/member/my_page_confirm'/>" name="my_page_form" method="post">
					
					<input type="hidden" name="m_no" value="${userMemberVo.m_no}">
					
					<table>
						<tr>
							<td>회원번호</td>
							<td>${userMemberVo.m_no}</td>
						</tr>
						<tr>
							<td>아이디</td>
							<td>${userMemberVo.m_id}</td>
						</tr>
						<tr>
							<td>비밀번호</td>
							<td>********* <a href="<c:url value='/user/member/pw_modify'/>">비밀번호 변경</a></td>
						</tr>
						<tr>
							<td>메일</td>
							<td><input type="text" name="m_mail" value="${userMemberVo.m_mail}"></td>
						</tr>
						<tr>
							<td>연락처</td>
							<td><input type="text" name="m_phone" value="${userMemberVo.m_phone}"></td>
						</tr>
						<tr>
							<td>로그인 상태</td>
							<td>${userMemberVo.ma_able}</td>
						</tr>
						<tr>
							<td>상태</td>
							<td>${userMemberVo.ma_comment}</td>
						</tr>
						<tr>
							<td>등록일</td>
							<td>${userMemberVo.m_reg_date}</td>
						</tr>
						<tr>
							<td>수정일</td>
							<td>${userMemberVo.m_mod_date}</td>
						</tr>
						<tr>
							<td colspan="2">
								<input type="button" value="정보변경" onclick="myPageForm();">
								<input type="reset" value="RESET">
								<input type="button" value="취소">
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