package com.office.diary.user.member;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user/member")
public class UserMemberController {
	
	@Autowired
	UserMemberService userMemberService;
	
	/*
	 * login form
	 */
	@RequestMapping(value = "/login_form", method = RequestMethod.GET)
	public String login_form() {
		System.out.println("[UserMemberController] login_form() CALLED!!");
		
		String nextPage = "user/member/login_form";
		
		return nextPage;
	}
	
	/*
	 * login confirm
	 */
	@RequestMapping(value = "/login_confirm", method = RequestMethod.POST)
	public String login_confirm(UserMemberVo userMemberVo, HttpSession session) {
		System.out.println("[UserMemberController] login_confirm() CALLED!!");
		
		String nextPage = "user/member/login_success";
		
		UserMemberVo logined_userMemberVo = userMemberService.login_confirm(userMemberVo);
		System.out.println(logined_userMemberVo);
		
		if (logined_userMemberVo == null) {
			nextPage = "user/member/login_fail";
			
		} else {
			session.setAttribute("logined_userMemberVo", logined_userMemberVo);
			session.setMaxInactiveInterval(60 * 30);
			
		}
		
		return nextPage;
		
	}
	
	/*
	 * create account form
	 */
	@RequestMapping(value="/create_account_form", method = RequestMethod.GET)
	public String create_account_form() {
		System.out.println("[UserMemberController] create_account_form() CALLED!!");
		
		String nextPage = "user/member/create_account_form";
		
		return nextPage;		
		
	}
	
	/*
	 * create account confirm
	 */
	@RequestMapping(value = "/create_account_confirm", method = RequestMethod.POST)
	public String create_account_confirm(UserMemberVo userMemberVo) {
		System.out.println("[UserMemberController] create_account_confirm() CALLED!!");
		
		String nextPage = "user/member/create_account_success";
		
		int result = userMemberService.create_account_confirm(userMemberVo);
		
		if (result == UserMemberService.IS_MEMBER || 
				result == UserMemberService.CREATE_ACCOUNT_FAIL) 
			nextPage = "user/member/create_account_fail";
		
		return nextPage;
		
	}
	
	/*
	 * logout confirm
	 */
	@RequestMapping(value = "/logout_confirm", method = RequestMethod.GET)
	public String logout_confirm(HttpSession session) {
		System.out.println("[UserMemberController] logout_confirm() CALLED!!");
		
		String nextPage = "redirect:/";
		
		// session.invalidate();
		session.removeAttribute("logined_userMemberVo");
		
		return nextPage;
		
	}
	
	/*
	 * my page(modify)
	 */
	@RequestMapping(value = "/my_page", method = RequestMethod.GET)
	public String my_page(HttpSession session, Model model) {
		System.out.println("[UserMemberController] my_page() CALLED!!");
		
		String nextPage = "user/member/my_page";
		
		UserMemberVo logined_userMemberVo = (UserMemberVo) session.getAttribute("logined_userMemberVo");
		if (logined_userMemberVo == null)
			return "redirect:/";
		
		UserMemberVo userMemberVo = userMemberService.my_page(logined_userMemberVo.getM_id());
		model.addAttribute("userMemberVo", userMemberVo);
		
		return nextPage;
		
	}
	
	/*
	 * my_page_confirm(modify)
	 */
	@RequestMapping(value = "/my_page_confirm", method = RequestMethod.POST)
	public String my_page_confirm(HttpSession session, UserMemberVo userMemberVo) {
		System.out.println("[UserMemberController] my_page_confirm() CALLED!!");
		
		String nextPage = "user/member/my_page_success";
		
		UserMemberVo logined_userMemberVo = (UserMemberVo) session.getAttribute("logined_userMemberVo");
		if (logined_userMemberVo == null)
			return "redirect:/";
		
		int result = userMemberService.my_page_confirm(userMemberVo);
		if (result <= 0)
			nextPage = "user/member/my_page_fail";
		
		return nextPage;
		
	}
	
	@RequestMapping(value = "/pw_modify", method = RequestMethod.GET)
	public String pw_modify(HttpSession session, Model model) {
		System.out.println("[UserMemberController] pw_modify() CALLED!!");
		
		String nextPage = "/user/member/pw_modify";
		
		UserMemberVo logined_userMemberVo = (UserMemberVo) session.getAttribute("logined_userMemberVo");
		if (logined_userMemberVo == null)
			return "redirect:/";
		
		model.addAttribute("m_id", logined_userMemberVo.getM_id());
		
		return nextPage;
		
	}
	
	@RequestMapping(value = "/pw_modify_confirm", method = RequestMethod.POST)
	public String pw_modify_confirm(UserMemberVo userMemberVo, HttpSession session) {
		System.out.println("[UserMemberController] pw_modify_confirm() CALLED!!");
		
		boolean modify_confirm = userMemberService.pw_modify_confirm(userMemberVo);
		
		if (modify_confirm)
			session.invalidate();
		
		return "redirect:/";
		
	}
	
}
