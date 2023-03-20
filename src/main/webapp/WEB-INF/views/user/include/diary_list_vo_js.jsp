<%@page import="com.office.diary.user.member.UserMemberVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">

/* GLOBAL VARIABLE */
var pageNum;		// 현재 페이지 또는 희망하는 페이지 번호
var amount;			// 현재 페이지에 출력하려는 아이템 개수

var full_year;		// 현재 년도
var month;			// 현재 월
var date;			// 현재 일

function getPageNum() {
	
	if (pageNum == null || pageNum == undefined || pageNum == '' || pageNum == ' ')
		pageNum = 1;
	
	return pageNum;
	
}

function setPageNum(p) {
	pageNum = p;
}

function getAmount() {
	
	if (amount == null || amount == undefined || amount == '' || amount == ' ')
		amount = 5;
	
	return amount;
	
}

function setAmount(a) {
	amount = a;
}

function getFull_year() {
	
	if (full_year == null || full_year == '' || full_year == ' ' || full_year == undefined) {
		let now = new Date();
		setFull_year(now.getFullYear());
	}
	
	return full_year;
	
}

function setFull_year(y) {
	
	full_year = y;
	
}

function getMonth() {
	
	if (month == null || month == '' || month == ' ' || month == undefined) {
		let now = new Date();
		setMonth(now.getMonth() + 1);
	}
	
	return month;
	
}

function setMonth(m) {
	
	month = m;
	
}

function getDate() {
	
	if (date == null || date == '' || date == ' ' || date == undefined) {
		let now = new Date();
		setDate(now.getDate());
	}
	
	return date;
	
}

function setDate(d) {
	
	date = d;
	
}

</script>