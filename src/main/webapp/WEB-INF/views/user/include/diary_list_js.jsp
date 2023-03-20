<%@page import="com.office.diary.user.member.UserMemberVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">

<%
	UserMemberVo logined_userMemberVo = 
				(UserMemberVo) session.getAttribute("logined_userMemberVo");
%>

var days_kr = ['일', '월', '화', '수', '목', '금', '토'];

$(document).ready(function(){
	console.log('DOCUMENT READY!!');
	
	init_year_month_date();
	
	ajax_getDiaryItems();
	
	setDocumentHeight();
	
	init_main_event();
	
});

function setSelectDateOptions() {
	console.log('setSelectDateOptions() CALLED!!');
	
	let last = new Date(parseInt(getFull_year()), parseInt(getMonth()), 0);
	
	/* UI */
	$('select[name="diary_list_date_select"]').children().remove();
	$('select[name="diary_list_date_select"]').append('<option value="-1">-- 선택 --</option>');
	for (let i = 1; i <= last.getDate(); i++) {
		$('select[name="diary_list_date_select"]').append('<option value="' + i + '">' + i + '</option>');
	}
	
}

function init_year_month_date() {
	console.log('init_year_month_date() CALLED!!');
	
	$('select[name="diary_list_year_select"]').val(getFull_year()).prop('selected', true);
	$('select[name="diary_list_month_select"]').val(getMonth()).prop('selected', true);
	
	setSelectDateOptions();
	$('select[name="diary_list_date_select"]').val(getDate()).prop('selected', true);
	
}

function init_main_event() {
	console.log('init_main_event CALLED!!');
	
	// 페이지 번호 클릭 시
	$(document).on('click', '#section_wrap > div.page_wrap > div.page_number a', function() {
		console.log('page_number CLICK HANDLER!!');
		
		let pageNum = $(this).data('pagenum');
		setPageNum(pageNum);
		
		ajax_getDiaryItems();
		
	});
	
	// 페이지 번호 입력 시
	$(document).on('click', '#section_wrap > div.page_wrap > div.page_goto a.goto_page_btn', function() {
		console.log('page_goto BUTTON CLICK HANDLER!!');
		
		let targetPageNum = $('#section_wrap > div.page_wrap > div.page_goto input[name="page_target_num"]').val();
		let totalPageNum = $('#section_wrap > div.page_wrap > div.page_goto span.page_total_num').text();
		
		if (targetPageNum == '' || targetPageNum == ' ' || targetPageNum == null) {
			alert('이동하고자 하는 페이지 숫자를 입력하세요!!');
			$('#section_wrap > div.page_wrap > div.page_goto input[name="page_target_num"]').focus();
			return;
			
		} else if (parseInt(targetPageNum) < 1) {
			alert('1페이지 미만은 존재하지 않습니다!!');
			$('#section_wrap > div.page_wrap > div.page_goto input[name="page_target_num"]').val('');
			$('#section_wrap > div.page_wrap > div.page_goto input[name="page_target_num"]').focus();
			return;
			
		} else if (parseInt(targetPageNum) > parseInt(totalPageNum.replaceAll(',', ''))) {
			alert(totalPageNum + '을(를) 초과 할수 없습니다!!');
			$('#section_wrap > div.page_wrap > div.page_goto input[name="page_target_num"]').val('');
			$('#section_wrap > div.page_wrap > div.page_goto input[name="page_target_num"]').focus();
			return;
			
		}
		
		setPageNum(targetPageNum);
		
		ajax_getDiaryItems();
		
		$('#section_wrap > div.page_wrap > div.page_goto input[name="page_target_num"]').val('');
		
	});
	
	
	// 년도 설정
	$(document).on('change', 'select[name="diary_list_year_select"]', function() {
		console.log('year_select CHANGE HANDLER!!');
		
		/* UI */
		$('select[name="diary_list_month_select"]').val(-1).prop('selected', true);
		
		$('select[name="diary_list_date_select"]').children().remove();
		$('select[name="diary_list_date_select"]').append('<option value="-1">-- 선택 --</option>');
		$('select[name="diary_list_date_select"]').val(-1).prop('selected', true);
		
		/* DATA */
		setFull_year($('select[name="diary_list_year_select"]').val());
		setMonth($('select[name="diary_list_month_select"]').val());
		setDate($('select[name="diary_list_date_select"]').val());
		
		/* get items */
		ajax_getDiaryItems();
		
	});
	
	// 월 설정
	$(document).on('change', 'select[name="diary_list_month_select"]', function() {
		console.log('month_select CHANGE HANDLER!!');

		/* UI */
		$('select[name="diary_list_date_select"]').val(-1).prop('selected', true);
		
		/* DATA */
		setMonth($('select[name="diary_list_month_select"]').val());
		setDate($('select[name="diary_list_date_select"]').val());
		
		setSelectDateOptions();
		
		/* get items */
		ajax_getDiaryItems();
		
	});
	
	// 일 설정
	$(document).on('change', 'select[name="diary_list_date_select"]', function() {
		console.log('date_select CHANGE HANDLER!!');
		
		/* DATA */
		setDate($('select[name="diary_list_date_select"]').val());
		
		/* get items */
		ajax_getDiaryItems();
		
	});
	
}

function ajax_getDiaryItems() {
	console.log('ajax_getDiaryItems() CALLED!!');
	
	console.log('year: ' + getFull_year());
	console.log('month: ' + getMonth());
	console.log('date: ' + getDate());
	
	var msg = {
		'm_id'		: '${logined_userMemberVo.getM_id()}',
		'pageNum'	: getPageNum(),
		'amount'	: getAmount(),
		'full_year'	: getFull_year(), 
		'month'		: getMonth(), 
		'date'		: getDate()
	};
	$.ajax({
		url: '<c:url value="/user/diary/getDiaryItems" />', 
		type: 'POST', 
		data: JSON.stringify(msg), 
		contentType: 'application/json; charset=utf-8;', 
		dataType: 'json',  
		success: function(data) {
			console.log('AJAX SUCCESS - ajax_getDiaryItems()');
			
			$('#section_wrap .diary_list table').remove();
			
			let userDiaryVos = data.userDiaryVos;
			let pageMakerVo = data.pageMakerVo;
			
			for (let i = 0; i < userDiaryVos.length; i++) {
				
				let userDiaryVo = userDiaryVos[i];
				let d_no = userDiaryVo.d_no;
				let m_id = userDiaryVo.m_id;
				let d_txt = userDiaryVo.d_txt;
				let d_img_name = userDiaryVo.d_img_name;
				let d_write_full_year = userDiaryVo.d_write_full_year;
				let d_write_month = userDiaryVo.d_write_month;
				let d_write_date = userDiaryVo.d_write_date;
				let d_write_day = userDiaryVo.d_write_day;
				let d_write_hours = userDiaryVo.d_write_hours;
				let d_write_minutes = userDiaryVo.d_write_minutes;
				let d_write_seconds = userDiaryVo.d_write_seconds;
				let d_reg_date = userDiaryVo.d_reg_date;
				let d_mod_date = userDiaryVo.d_mod_date;
				
				let t = document.querySelector('#diary_list_item');
				let clone = document.importNode(t.content, true);
				
				let clone_table = clone.querySelector("table");
				$(clone_table).attr('id', d_no);
				
				$('div.diary_list').append(clone);
				
				/* UI */
				
				$('#' + d_no + ' td.date span.year').text(d_write_full_year);
				$('#' + d_no + ' td.date span.month').text(d_write_month);
				$('#' + d_no + ' td.date span.date').text(d_write_date);
				$('#' + d_no + ' td.date span.day').text(days_kr[d_write_day] + '요일');
				$('#' + d_no + ' td.txt a').text(d_txt);
				//$('#' + d_no + ' td.thumnail img').attr('src', '<c:url value="/diaryUploadImg/"/>' + m_id + '/' + d_img_name);
				$('#' + d_no + ' td.thumnail img').attr('src', '<c:url value="/diaryUploadImg/${logined_userMemberVo.getM_id()}/"/>' + d_img_name);
				
				/* DATA */
				$('#' + d_no + ' td.txt a').data('d_no', d_no);
				$('#' + d_no + ' td.thumnail a').data('d_no', d_no);
				$('#' + d_no + ' td.modify_delete a').data('d_no', d_no);
				$('#' + d_no + ' td.modify_delete a').data('d_txt', d_txt);
				
				/* PAGE UI */
				$('#section_wrap > div.page_wrap > div.page_number').children().remove();
				
				// 이전 페이지
				if (pageMakerVo.prev) {
					$('#section_wrap > div.page_wrap > div.page_number').append('<a href="#none" data-pagenum="' + (pageMakerVo.startPage - 1) + '">PRE</a>');
				}
				
				// 처음 페이지
				if (getPageNum() > 10) {
					$('#section_wrap > div.page_wrap > div.page_number').append('<a href="#none" data-pagenum="1">1</a><span>...</span>');
				}
				
				// 페이지 넘버링
				for (let p = parseInt(pageMakerVo.startPage); p <= parseInt(pageMakerVo.endPage); p++) {
					$('#section_wrap > div.page_wrap > div.page_number').append('<a href="#none" data-pagenum="' + p + '">' + p + '</a>');
				}
				// 페이지 하이라이트
				$('#section_wrap > div.page_wrap > div.page_number a[data-pagenum=' + getPageNum() + ']').addClass('selectedPageNum');
				
				// 다음 페이지
				if (pageMakerVo.next) {
					$('#section_wrap > div.page_wrap > div.page_number').append('<a href="#none" data-pagenum="' + (pageMakerVo.endPage + 1) + '">NEX</a>');
				}
				
				// 전체 페이지
				$('#section_wrap > div.page_wrap > div.page_goto > span.page_total_num').text(addComma(pageMakerVo.totalPage.toString()));
				
			}
			
			add_events();
			
		},
		error: function(data) {
			console.log('AJAX FAIL - ajax_getDiaryItems()');
		}
	});
	
}

function add_events() {
	console.log('add_events() CALLED!!');
	
	$('td.txt a').click(function(){
		console.log('txt CLICK EVENT HANDLER!!');
		console.log($(this));
		
		showDetail($(this).data('d_no'));
		
	});
	
	$('td.thumnail a').click(function(){
		console.log('thumnail CLICK EVENT HANDLER!!');
		console.log($(this));
		
		showDetail($(this).data('d_no'));
		
	});
	
	$('td.modify_delete a.modify').click(function(){
		console.log('modify CLICK EVENT HANDLER!!');
		console.log($(this));
		
		location.href = '<c:url value="/user/diary/diary_modify?d_no="/>' + $(this).data('d_no');
		
	});
	
	$('td.modify_delete a.delete').click(function(){
		console.log('delete CLICK EVENT HANDLER!!');
		console.log($(this));
		
		let word = '정말 일기(' + $(this).data('d_txt') + ')를 삭제 하시겠습니까?';
		let result = confirm(word);
		if (result)
			ajax_deleteDiaryItem($(this).data('d_no'));
		
	});
	
	$('#diary_detail_wrap div.close_download a.close').click(function() {
		console.log('close CLICK HANDLER!!');
		
		closeDetail();
		
	});
	
	$('#diary_detail_wrap div.close_download a.download').click(function() {
		console.log('download CLICK HANDLER!!');
		
		console.log($(this));
		
		location.href="<c:url value='/user/diary/download_img?m_id='/>" + $(this).data('m_id') + '&d_img_name=' + $(this).data('d_img_name');
		
	});
	
}

function ajax_deleteDiaryItem(no) {
	console.log('ajax_deleteDiaryItem CALLED!!');
	
	var msg = {
		'd_no': no
	};
	$.ajax({
		url: '<c:url value="/user/diary/deleteDiaryItem" />', 
		type: 'POST', 
		data: JSON.stringify(msg), 
		contentType: 'application/json; charset=utf-8;', 
		dataType: 'json',  
		success: function(data) {
			console.log('AJAX SUCCESS - ajax_deleteDiaryItem()');
			
			if (data.result > 0)
				ajax_getDiaryItems();
			
		},
		error: function(data) {
			console.log('AJAX ERROR - ajax_deleteDiaryItem()');
			
		}
	});
	
}

function setDocumentHeight() {
	console.log('setDocumentHeight() CALLED!!');
	
	let doc_height = $(document).height();
	$('#diary_detail_wrap').css('height', doc_height);
	
}

function closeDetail() {
	console.log('closeDetail() CALLED!!');
	
	$('#diary_detail_wrap').css('display', 'none');
	
}

function showDetail(no) {
	console.log('showDetail() CALLED!!');
	
	$('#diary_detail_wrap').css('display', 'block');
	
	ajax_getDiaryItem(no);
	
}

function ajax_getDiaryItem(no) {
	console.log('ajax_getDiaryItem() CALLED!!');
	
	var msg = {
		'd_no': no
	};
	$.ajax({
		url: '<c:url value="/user/diary/getDiaryItem" />', 
		type: 'POST', 
		data: JSON.stringify(msg), 
		contentType: 'application/json; charset=utf-8;', 
		dataType: 'json',  
		success: function(data) {
			console.log('AJAX SUCCESS - ajax_getDiaryItem()');
			
			let userDiaryVo = data.userDiaryVo;
			let d_no = userDiaryVo.d_no;
			let m_id = userDiaryVo.m_id;
			let d_txt = userDiaryVo.d_txt;
			let d_img_name = userDiaryVo.d_img_name;
			let d_write_full_year = userDiaryVo.d_write_full_year;
			let d_write_month = userDiaryVo.d_write_month;
			let d_write_date = userDiaryVo.d_write_date;
			let d_write_day = userDiaryVo.d_write_day;
			let d_write_hours = userDiaryVo.d_write_hours;
			let d_write_minutes = userDiaryVo.d_write_minutes;
			let d_write_seconds = userDiaryVo.d_write_seconds;
			let d_reg_date = userDiaryVo.d_reg_date;
			let d_mod_date = userDiaryVo.d_mod_date;
			
			/* UI */
			$('#diary_detail_wrap span.date').text(d_write_full_year + '년 ' + d_write_month + '월 ' + d_write_date + '일(' + days_kr[d_write_day] + '요일)');
			$('#diary_detail_wrap span.time').text(d_write_hours + '시 ' + d_write_minutes + '분 ' + d_write_seconds + '초');
			$('#diary_detail_wrap div.write_img img').attr('src', '<c:url value="/diaryUploadImg/"/>' + m_id + '/' + d_img_name);
			$('#diary_detail_wrap div.write_txt span').text(d_txt);
			
			/* DATA */
			$('#diary_detail_wrap a.download').data('m_id', m_id);
			$('#diary_detail_wrap a.download').data('d_img_name', d_img_name);
			
		},
		error: function(data) {
			console.log('AJAX ERROR - ajax_getDiaryItem()');
			
		}
	});
	
}

function addComma(value) {
	console.log('addComma CALLED!!');
	
	if (value == null)
		return null;
	
	return value.replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

</script>