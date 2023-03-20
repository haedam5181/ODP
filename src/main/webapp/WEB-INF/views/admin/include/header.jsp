<%@page import="com.office.diary.admin.member.AdminMemberVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
	<div id="header_wrap">
	
		<div>
		
		<%
			AdminMemberVo adminMemberVo = (AdminMemberVo) session.getAttribute("logined_adminMemberVo");
		%>
		
		<%
			if (adminMemberVo == null) {
		%>
			<a href="<c:url value="/admin"/>">admin home</a> &nbsp; | &nbsp;
			<a href="<c:url value="/"/>" target="_blank">user home</a>  &nbsp; | &nbsp;
			<a href="<c:url value="/admin"/>">login</a>
		<%
			} else {
		%>
			<a href="<c:url value="/admin/manage/user_list"/>">admin home</a> &nbsp; | &nbsp;
			<a href="<c:url value="/"/>" target="_blank">user home</a>  &nbsp; | &nbsp;
			<a href="<c:url value="/admin/member/logout_confirm"/>">logout</a>
		<%
			}
		%>
		
		</div>
		
	</div>
	
</header>