<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">

$(document).ready(function(){
	console.log('DOCUMENT READY!!');
	
	setInterval(setNowDateTime, 1000);
	
});

function setNowDateTime() {
	console.log('setNowDateTime() CALLED!');
	
	let days = ['일', '월', '화', '수', '목', '금', '토'];
	
	let now = new Date();
	let year = now.getFullYear();
	let month = now.getMonth();
	let date = now.getDate();
	let day = now.getDay();
	let hours = now.getHours();
	let minutes = now.getMinutes();
	let seconds = now.getSeconds();
	
	/* UI */
	let date_time = year + '년 ' + (month + 1) + '월 ' + date + '일(' + days[day] + '요일) ';
	    date_time += hours + '시 ' + minutes + '분 ' + seconds + '초';
	
	$('#section_wrap .now_datetime .today_txt').text(date_time);
	
	/* DATA - SET */
	setD_write_full_year(year);
	setD_write_month(month);
	setD_write_date(date);
	setD_write_day(day);
	setD_write_hours(hours);
	setD_write_minutes(minutes);
	setD_write_seconds(seconds);
	
}

function diaryWriteForm() {
	console.log('diaryWriteForm() CALLED!!');
	
	let form = document.diary_write_form;
	if (form.d_txt.value == '') {
		alert('Please enter your diary content!!');
		form.d_txt.focus();
		
	} else if (form.d_img_file.value == '') {
		alert('Please enter your diary image!!');
		form.d_img_file.focus();
		
	} else {
		
		form.d_write_full_year.value = getD_write_full_year();
		form.d_write_month.value = getD_write_month();
		form.d_write_date.value = getD_write_date();
		form.d_write_day.value = getD_write_day();
		form.d_write_hours.value = getD_write_hours();
		form.d_write_minutes.value = getD_write_minutes();
		form.d_write_seconds.value = getD_write_seconds();
		
		form.submit();
	}
	
}

</script>