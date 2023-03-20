function loginForm() {
	console.log('loginForm() CALLED!!');
	
	var form = document.login_form;
	
	if (form.m_id.value == '') {
		alert('INPUT YOUR ID');
		form.m_id.focus();
		
	} else if (form.m_pw.value == '') {
		alert('INPUT YOUR PW');
		form.m_pw.focus();
		
	} else {
		form.submit();
		
	}
	
}