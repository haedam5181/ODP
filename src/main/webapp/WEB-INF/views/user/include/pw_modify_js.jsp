<%@page import="com.office.diary.user.member.UserMemberVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">

$(document).ready(function(){
	console.log('ready!!');
	
	add_events();
	
});

function add_events() {
	console.log('add_events CALLED!!');
	
	$('#section_wrap table input[value="CANCEL"]').click(function() {
		console.log('CANCEL CLICK HANDLER!!');
		
		location.href="<c:url value='/'/>";
		
	});
}

function pwModifyForm() {
	console.log('pwModifyForm() CALLED!!');
	
	let form = document.pw_modify_form;
	if (form.m_pw.value == '') {
		alert('INPUT YOUR PASSWORD!!');
		form.m_pw.focus();
		
	} else {
		form.submit();
		
	}
	
}


</script>