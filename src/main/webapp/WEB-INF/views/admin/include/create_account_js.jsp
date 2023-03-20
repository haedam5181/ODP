<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">

function adminCreateAccountForm() {
	console.log('adminCreateAccountForm() CALLED!!');
	
	let form = document.create_account_form;
	if (form.am_id.value == '') {
		alert('INPUT ADMIN ID');
		form.am_id.focus();
		
	} else if (form.am_pw.value == '') {
		alert('INPUT ADMIN PW');
		form.am_pw.focus();
		
	} else if (form.am_mail.value == '') {
		alert('INPUT ADMIN MAIL');
		form.am_mail.focus();
		
	} else {
		form.submit();
		
	}
	
}

</script>