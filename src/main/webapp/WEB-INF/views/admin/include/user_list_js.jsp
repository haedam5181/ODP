<%@page import="com.office.diary.user.member.UserMemberVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">


$(document).ready(function(){
	console.log('DOCUMENT READY!!');
	
	ajax_getUserItems();
	
});

function ajax_getUserItems() {
	console.log('ajax_getUserItems() CALLED!!');
	
	var msg = {
		
	};
	$.ajax({
		url: '<c:url value="/admin/manage/getUserItems" />', 
		type: 'POST', 
		data: JSON.stringify(msg), 
		contentType: 'application/json; charset=utf-8;', 
		dataType: 'json',  
		success: function(data) {
			console.log('AJAX SUCCESS - ajax_getUserItems()');
			
			let userMemberVos = data.userMemberVos;
			for (let i = 0; i < userMemberVos.length; i++) {
				
				let userMemberVo = userMemberVos[i];
				let m_no = userMemberVo.m_no;
				let m_id = userMemberVo.m_id;
				let m_pw = userMemberVo.m_pw;
				let m_mail = userMemberVo.m_mail;
				let m_phone = userMemberVo.m_phone;
				let ma_able = userMemberVo.ma_able;
				let ma_comment = userMemberVo.ma_comment;
				let m_reg_date = userMemberVo.m_reg_date;
				let m_mod_date = userMemberVo.m_mod_date;
				
				console.log('userMemberVo---------------' + userMemberVo);
				
				/* TEMPLATE COPY */
				let t = document.querySelector('#user_list_item');
				let clone = document.importNode(t.content, true);
				
				/* ADD ID */
				let clone_tbody = clone.querySelector('tbody');
				$(clone_tbody).attr('id', m_no);
				
				/* UI */
				$('div.user_list table').append(clone);
				
				$('#' + m_no + ' td.m_no').text(m_no);
				$('#' + m_no + ' td.m_id').text(m_id);
				$('#' + m_no + ' td.m_pw').text('*******');
				$('#' + m_no + ' input[name="m_mail"]').val(m_mail);
				$('#' + m_no + ' input[name="m_phone"]').val(m_phone);
				$('#' + m_no + ' select[name="ma_able"]').val(ma_able).prop('selected', true);
				$('#' + m_no + ' td.ma_comment').text(ma_comment);
				$('#' + m_no + ' td.m_reg_date').text(m_reg_date);
				$('#' + m_no + ' td.m_mod_date').text(m_mod_date);
				
				/* DATA */
				$('#' + m_no + ' a.m_mail_btn').data('m_no', m_no);
				$('#' + m_no + ' a.m_mail_btn').data('cur_m_mail', m_mail);
				
				$('#' + m_no + ' a.m_phone_btn').data('m_no', m_no);
				$('#' + m_no + ' a.m_phone_btn').data('cur_m_phone', m_phone);
				
				$('#' + m_no + ' select[name="ma_able"]').data('m_no', m_no);
				$('#' + m_no + ' select[name="ma_able"]').data('cur_ma_able', ma_able);
				
			}
			
			add_events();
			
			
		},
		error: function(data) {
			console.log('AJAX FAIL - ajax_getUserItems()');
			
		}
		
	});
	
}

function add_events() {
	console.log('add_events() CALLED!!');
	
	/* 메일 변경 */
	$('a.m_mail_btn').click(function(){
		console.log('m_mail_btn CLICK HANDLER!!');
		
		console.log($(this).data('m_no'));
		console.log($(this).data('cur_m_mail'));
		
		let m_no = $(this).data('m_no');
		let cur_m_mail = $(this).data('cur_m_mail');
		let mod_m_mail = $(this).siblings('input[name="m_mail"]').val();
		
		ajax_updateM_mail(m_no, cur_m_mail, mod_m_mail);
		
	});
	
	/* 연락처 변경 */
	$('a.m_phone_btn').click(function(){
		console.log('m_phone_btn CLICK HANDLER!!');
		
		console.log($(this).data('m_no'));
		console.log($(this).data('cur_m_phone'));
		
		let m_no = $(this).data('m_no');
		let cur_m_phone = $(this).data('cur_m_phone');
		let mod_m_phone = $(this).siblings('input[name="m_phone"]').val();
		
		ajax_updateM_phone(m_no, cur_m_phone, mod_m_phone);
		
	});
	
	/* 휴면 상태 변경 */
	$('select[name="ma_able"]').change(function(){
		console.log('ma_able CHANGE HANDLER!!');
		
		console.log($(this).data('m_no'));
		console.log($(this).data('cur_ma_able'));
		
		let m_no = $(this).data('m_no');
		let cur_ma_able = $(this).data('cur_ma_able');
		let mod_ma_able = $(this).val();
		
		let result = confirm('정말 휴면 상태를 변경(' + mod_ma_able + ')하시겠습니까?');
		if(result) {
			ajax_updateMa_able(m_no, cur_ma_able, mod_ma_able);
			
		} else {
			$(this).val(cur_ma_able).prop('selected', true);
			
		}
		
	});
	
}

function ajax_updateM_mail(no, curMail, modMail) {
	console.log('ajax_updateM_mail() CALLED!!');
	
	var msg = {
		'm_no'		: no,
		'm_mail'	: modMail
	};
	$.ajax({
		url: '<c:url value="/admin/manage/updateM_mail" />', 
		type: 'POST', 
		data: JSON.stringify(msg), 
		contentType: 'application/json; charset=utf-8;', 
		dataType: 'json',  
		success: function(data) {
			console.log('AJAX SUCCESS - ajax_updateM_mail()');
			
			let result = data.result;
			if (result > 0) {
				alert('메일 업데이트에 성공 했습니다.');
				
			} else {
				alert('메일 업데이트에 실패 했습니다');
				$('#' + no + ' input[name="m_mail"]').val(curMail);
				
			}
			
		},
		error: function(data) {
			console.log('AJAX FAIL - ajax_updateM_mail()');
			
			alert('메일 업데이트에 실패 했습니다.');
			$('#' + no + ' input[name="m_mail"]').val(curMail);
			
		}
	});
	
}

function ajax_updateM_phone(no, curPhone, modPhone) {
	console.log('ajax_updateM_phone() CALLED!!');
	
	var msg = {
		'm_no'		: no,
		'm_phone'	: modPhone
	};
	$.ajax({
		url: '<c:url value="/admin/manage/updateM_phone" />', 
		type: 'POST', 
		data: JSON.stringify(msg), 
		contentType: 'application/json; charset=utf-8;', 
		dataType: 'json',  
		success: function(data) {
			console.log('AJAX SUCCESS - ajax_updateM_phone()');
			
			let result = data.result;
			if (result > 0) {
				alert('연락처 업데이트에 성공 했습니다.');
				
			} else {
				alert('연락처 업데이트에 실패 했습니다');
				$('#' + no + ' input[name="m_phone"]').val(curPhone);
				
			}
			
		},
		error: function(data) {
			console.log('AJAX FAIL - ajax_updateM_phone()');
			
			alert('연락처 업데이트에 실패 했습니다.');
			$('#' + no + ' input[name="m_phone"]').val(curPhone);
			
		}
	});
		
}

function ajax_updateMa_able(no, curAble, modAble) {
	console.log('ajax_updateMa_able() CALLED!!');
	
	var msg = {
		'm_no'		: no,
		'ma_able'	: modAble
	};
	$.ajax({
		url: '<c:url value="/admin/manage/updateMa_able" />', 
		type: 'POST', 
		data: JSON.stringify(msg), 
		contentType: 'application/json; charset=utf-8;', 
		dataType: 'json',  
		success: function(data) {
			console.log('AJAX SUCCESS - ajax_updateMa_able()');
			
			let result = data.result;
			if (result > 0) {
				alert('휴면 상태 업데이트에 성공 했습니다.');
				
				if (modAble > 0)
					$('#' + no + ' td.ma_comment').text('로그인 가능');
				else
					$('#' + no + ' td.ma_comment').text('로그인 불가능');
				
			} else {
				alert('휴면 상태 업데이트에 실패 했습니다');
				$('#' + no + ' select[name="ma_able"]').val(curAble).prop('selected', true);
				
			}
			
		},
		error: function(data) {
			console.log('AJAX FAIL - ajax_updateMa_able()');
			
			alert('휴면 상태 업데이트에 실패 했습니다');
			$('#' + no + ' select[name="ma_able"]').val(curAble).prop('selected', true);
			
		}
	});
		
}

</script>