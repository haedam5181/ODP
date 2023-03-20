<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">

function adminLoginForm() {
	console.log('adminLoginForm() CALLED!!');
	
	let form = document.login_form;
	if (form.am_id.value == '') {
		alert('INPUT ADMIN ID');
		form.am_id.focus();
		
	} else if (form.am_pw.value == '') {
		alert('INPUT ADMIN PW');
		form.am_pw.focus();
		
	} else {
		form.submit();
		
	}
}

</script>