package com.office.diary.user.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserMemberService {
	
	final static int IS_MEMBER = 0;
	final static int IS_NOT_MEMBER = 1;
	final static int CREATE_ACCOUNT_SUCCESS = 2;
	final static int CREATE_ACCOUNT_FAIL = 3;
	
	@Autowired
	UserMemberDao userMemberDao;
	
	public UserMemberVo login_confirm(UserMemberVo userMemberVo) {
		System.out.println("[UserMemberService] login_confirm() CALLED!!");
		
		UserMemberVo logined_userMemberVo = userMemberDao.login_confirm(userMemberVo);
		
		if (logined_userMemberVo != null)
			System.out.println("LOG-IN SUCCESSED!!");
		else
			System.out.println("LOG-IN FAIL!!");
		
		return logined_userMemberVo;
	}

	public int create_account_confirm(UserMemberVo userMemberVo) {
		System.out.println("[UserMemberService] create_account_confirm() CALLED!!");
		
		int result = 0;
		boolean isMember = userMemberDao.isMember(userMemberVo);
		
		if (isMember) {
			result = IS_MEMBER;
			
		} else {
			
			result = userMemberDao.create_account_confirm(userMemberVo);
			
			if (result == CREATE_ACCOUNT_FAIL) {
				System.out.println("CREATE ACCOUNT FAIL");
				
			} else {
				System.out.println("CREATE ACCOUNT SUCCESS");
				
			}
			
		}
		
		return result;
	}

	public UserMemberVo my_page(String m_id) {
		System.out.println("[UserMemberService] my_page() CALLED!!");
		
		UserMemberVo userMemberVo = userMemberDao.my_page(m_id);
		
		return userMemberVo;
	}

	public int my_page_confirm(UserMemberVo userMemberVo) {
		System.out.println("[UserMemberService] my_page() CALLED!!");
		
		int result = userMemberDao.my_page_confirm(userMemberVo);
		
		if (result <= 0)
			System.out.println("USER INFO UPDATE FAIL!!");
		else
			System.out.println("USER INFO UPDATE SUCCESS!!");

		return result;
	}

	public boolean pw_modify_confirm(UserMemberVo userMemberVo) {
		System.out.println("[UserMemberService] my_page() CALLED!!");
		
		int result = userMemberDao.pw_modify_confirm(userMemberVo);
		
		if (result <= 0)
			return false;
		else 
			return true;
		
	}

}
