package com.office.diary.admin.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminMemberService {

	@Autowired
	AdminMemberDao adminMemberDao;
	
	public AdminMemberVo login_confirm(AdminMemberVo adminMemberVo) {
		System.out.println("[AdminMemberService] login_confirm() CALLED!!");
		
		AdminMemberVo logined_adminMemberVo = adminMemberDao.login_confirm(adminMemberVo);
		
		if (logined_adminMemberVo == null)
			System.out.println("ADMIN LOGIN FAIL!!");
		else
			System.out.println("ADMIN LOGIN SUCCESS!!");
		
		return logined_adminMemberVo;
	}

	public AdminMemberEnum create_account_confirm(AdminMemberVo adminMemberVo) {
		System.out.println("[AdminMemberService] create_account_confirm() CALLED!!");
		
		AdminMemberEnum result = adminMemberDao.isMember(adminMemberVo);
		
		if (result == AdminMemberEnum.IS_ADMIN_MEMBER_ACCOUNT)
			return AdminMemberEnum.IS_ADMIN_MEMBER_ACCOUNT;
		
		result = adminMemberDao.create_account_confirm(adminMemberVo);
		
		if (result.equals(AdminMemberEnum.ADMIN_MEMBER_ACCOUNT_SUCCESS))
			System.out.println("ADMIN CREATE ACCOUNT SUCCESS!!");
		else
			System.out.println("ADMIN CREATE ACCOUNT FAIL!!");
		
		return result;
		
	}

}
