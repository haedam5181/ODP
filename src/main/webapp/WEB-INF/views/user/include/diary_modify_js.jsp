<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">

var isOldThumnail = true;

$(document).ready(function(){
	console.log('DOCUMENT READY!!');
	
	add_events();
	
});

function diaryModifyForm() {
	console.log('diaryModifyForm() CALLED!!');
	
	let form = document.diary_modify_form;
	if (form.d_txt.value == '') {
		alert('Please enter your diary content!!');
		form.d_txt.focus();
		
	} else if (!isOldThumnail && form.d_img_file.value == '') {
		alert('Please enter your diary image!!');
		form.d_img_file.focus();
		
	} else {
		
		form.submit();
	}
	
}

function add_events() {
	console.log('add_events() CALLED!!');
	
	$('#section_wrap .diary_modify .thumnail_delete').click(function(){
		console.log('thumnail_delete CLICK EVENT HANDLER!!');
		
		/* UI */
		$('#section_wrap .diary_modify .thumnail').parent().css('display', 'none');
		$('#section_wrap .diary_modify .thumnail_delete').parent().css('display', 'none');
		$('#section_wrap .diary_modify input[name="d_img_file"]').css('display', 'inline-block');
		
		/* DATA */
		isOldThumnail = false;
		
	});
	
}

function diaryModifyFormReset() {
	console.log('diaryModifyFormReset() CALLED!!');
	
	/* UI */
	$('#section_wrap .diary_modify .thumnail').parent().css('display', 'block');
	$('#section_wrap .diary_modify .thumnail_delete').parent().css('display', 'block');
	$('#section_wrap .diary_modify input[name="d_img_file"]').css('display', 'none');
	
	/* DATA */
	isOldThumnail = true;
	
}


</script>