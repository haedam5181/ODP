package com.office.diary.admin.manage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.office.diary.user.member.UserMemberVo;

@Service
public class AdminManageService {

	@Autowired
	AdminManageDao adminManageDao;
	
	public Map<String, Object> getUserItems(Map<String, String> msgMap) {
		System.out.println("[AdminManageService] getUserItems() CALLED!!");
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<UserMemberVo> userMemberVos = adminManageDao.getUserItems(msgMap);
		
		map.put("userMemberVos", userMemberVos);
		
		return map;
	}

	public Map<String, Object> updateM_mail(Map<String, String> msgMap) {
		System.out.println("[AdminManageService] updateM_mail() CALLED!!");
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		int result = adminManageDao.updateM_mail(msgMap);
		map.put("result", result);
		
		return map;
	}

	public Map<String, Object> updateM_phone(Map<String, String> msgMap) {
		System.out.println("[AdminManageService] updateM_phone() CALLED!!");
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		int result = adminManageDao.updateM_phone(msgMap);
		map.put("result", result);
		
		return map;
	}

	public Map<String, Object> updateMa_able(Map<String, String> msgMap) {
		System.out.println("[AdminManageService] updateMa_able() CALLED!!");
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		int result = adminManageDao.updateMa_able(msgMap);
		map.put("result", result);
		
		return map;
	}

}
