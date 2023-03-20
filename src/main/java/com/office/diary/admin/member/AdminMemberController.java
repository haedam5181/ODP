package com.office.diary.admin.member;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin/member")
public class AdminMemberController {

	@Autowired
	AdminMemberService adminMemberService;
	
	@RequestMapping(value = "/login_confirm", method = RequestMethod.POST)
	public String login_confirm(AdminMemberVo adminMemberVo, HttpSession session) {
		System.out.println("[AdminMemberController] login_confirm() CALLED!!");
		
		String nextPage = "admin/member/login_success";
		
		AdminMemberVo logined_adminMemberVo = adminMemberService.login_confirm(adminMemberVo);
		if (logined_adminMemberVo != null) {
			session.setAttribute("logined_adminMemberVo", logined_adminMemberVo);
			session.setMaxInactiveInterval(60 * 30);
			
		} else {
			nextPage = "admin/member/login_fail";
			
		}
		
		return nextPage;
		
	}
	
	/*
	 * create admin account
	 */
	@RequestMapping(value = "/create_account", method = RequestMethod.GET)
	public String create_account() {
		System.out.println("[AdminMemberController] create_account() CALLED!!");
		
		String nextPage = "admin/member/create_account";
		
		return nextPage;
		
	}
	
	/*
	 * create admin account confirm
	 */
	@RequestMapping(value = "/create_account_confirm", method = RequestMethod.POST)
	public String create_account_confirm(AdminMemberVo adminMemberVo) {
		System.out.println("[AdminMemberController] create_account_confirm() CALLED!!");
		
		String nextPage = "admin/member/create_account_success";
		
		AdminMemberEnum result = adminMemberService.create_account_confirm(adminMemberVo);
		
		if (result.equals(AdminMemberEnum.ADMIN_MEMBER_ACCOUNT_FAIL)) 
			nextPage = "admin/member/create_account_fail";
		
		return nextPage;
	}
	
	/*
	 * admin logout confirm
	 */
	@RequestMapping(value = "/logout_confirm", method = RequestMethod.GET)
	public String logout_confirm(HttpSession session) {
		System.out.println("[AdminMemberController] logout_confirm() CALLED!!");
		
		// session.invalidate();
		session.removeAttribute("logined_adminMemberVo");
		
		return "redirect:/admin";
	}
	
}
