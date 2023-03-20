function createAccountForm() {
	console.log('createAccountForm() CALLED!!');
	
	var form = document.create_account_form;
	
	if (form.m_id.value == '') {
		alert('INPUT YOUR ID');
		form.m_id.focus();
		
	} else if (form.m_pw.value == '') {
		alert('INPUT YOUR PW');
		form.m_pw.focus();
		
	} else if (form.m_mail.value == '') {
		alert('INPUT YOUR MAIL ADDRESS');
		form.m_mail.focus();
		
	} else if (form.m_phone.value == '') {
		alert('INPUT YOUR PHONE NUMBER');
		form.m_phone.focus();
		
	} else {
		form.submit();
		
	}
	
}