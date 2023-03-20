<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

	<jsp:include page="../../user/include/common_js.jsp" />
	<jsp:include page="../include/user_list_js.jsp" />

</head>
<body>
	
	<jsp:include page="../include/header.jsp" />
	
	<section>
		<div id="section_wrap">
			
			<div>
				
				<div class="word">
					USER LIST
				</div>
				
				<div class="user_list">
					<table>
						<thead>
							<tr>
								<td>m_no</td>
								<td>m_id</td>
								<td>m_pw</td>
								<td>m_mail</td>
								<td>m_phone</td>
								<td>ma_able</td>
								<td>ma_comment</td>
								<td>m_reg_date</td>
								<td>m_mod_date</td>
							</tr>
						</thead>
					</table>
				</div>
				
			</div>
			
		</div>
	</section>
	
	<jsp:include page="../../user/include/footer.jsp" />
	
</body>
</html>

<template id="user_list_item">
<tbody>
	<tr>
		<td class="m_no">m_no</td>
		<td class="m_id">m_id</td>
		<td class="m_pw">m_pw</td>
		<td class="m_mail">
			<input type="text" name="m_mail"><a class="m_mail_btn" href="#none">변경</a>
		</td>
		<td class="m_phone">
			<input type="text" name="m_phone"><a class="m_phone_btn" href="#none">변경</a>
		</td>
		<td class="ma_able">
			<select name="ma_able">
				<option value="0">0</option>
				<option value="1">1</option>
			</select>
		</td>
		<td class="ma_comment">ma_comment</td>
		<td class="m_reg_date">m_reg_date</td>
		<td class="m_mod_date">m_mod_date</td>
	</tr>
</tbody>
</template>

