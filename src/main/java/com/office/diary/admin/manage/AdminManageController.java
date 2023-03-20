package com.office.diary.admin.manage;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.office.diary.admin.member.AdminMemberVo;

@Controller
@RequestMapping("/admin/manage")
public class AdminManageController {
	
	@Autowired
	AdminManageService adminManageService;
	
	/*
	 * user_list
	 */
	@RequestMapping(value = "/user_list", method = RequestMethod.GET)
	public String user_list(HttpSession session) {
		System.out.println("[AdminManageController] user_list() CALLED!!");
		
		String nextPage = "admin/manage/user_list";
		
		AdminMemberVo logined_adminMemberVo =
				(AdminMemberVo) session.getAttribute("logined_adminMemberVo");
		if(logined_adminMemberVo == null)
			return "redirect:/admin";
		
		return nextPage;
		
	}
	
	/*
	 * user items
	 */
	@RequestMapping(value = "/getUserItems", method = RequestMethod.POST)
	@ResponseBody
	public Object getUserItems(@RequestBody Map<String, String> msgMap) {
		System.out.println("[AdminManageController] getUserItems() CALLED!!");
		
		Map<String, Object> map = adminManageService.getUserItems(msgMap);
		
		return map;
		
	}
	
	/*
	 * update m_mail
	 */
	@RequestMapping(value = "/updateM_mail", method = RequestMethod.POST)
	@ResponseBody
	public Object updateM_mail(@RequestBody Map<String, String> msgMap) {
		System.out.println("[AdminManageController] updateM_mail() CALLED!!");
		
		Map<String, Object> map = adminManageService.updateM_mail(msgMap);
		
		return map;
	}
	
	/*
	 * update m_phone
	 */
	@RequestMapping(value = "/updateM_phone", method = RequestMethod.POST)
	@ResponseBody
	public Object updateM_phone(@RequestBody Map<String, String> msgMap) {
		System.out.println("[AdminManageController] updateM_phone() CALLED!!");
		
		Map<String, Object> map = adminManageService.updateM_phone(msgMap);
		
		return map;
	}
	
	/*
	 * update ma_able
	 */
	@RequestMapping(value = "/updateMa_able", method = RequestMethod.POST)
	@ResponseBody
	public Object updateMa_able(@RequestBody Map<String, String> msgMap) {
		System.out.println("[AdminManageController] updateMa_able() CALLED!!");
		
		Map<String, Object> map = adminManageService.updateMa_able(msgMap);
		
		return map;
		
	}
	
}
