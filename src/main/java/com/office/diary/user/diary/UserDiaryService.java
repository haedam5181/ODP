package com.office.diary.user.diary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.office.diary.user.diary.page.Criteria;
import com.office.diary.user.diary.page.PageMakerVo;
import com.office.diary.user.member.UserMemberVo;

@Service
public class UserDiaryService {

	@Autowired
	UserDiaryDao userDiaryDao;
	
	public int diary_write_confirm(UserDiaryVo userDiaryVo) {
		System.out.println("[UserDiaryService] diary_write_confirm() CALLED!!");
		
		int result = userDiaryDao.diary_write_confirm(userDiaryVo);
		
		if (result <= 0)
			System.out.println("[UserDiaryService] diary_write FAILED!!");
		else
			System.out.println("[UserDiaryService] diary_write SUCCESS!!");
		
		return result;
	}

	public Map<String, Object> getDiaryItems(Map<String, String> msgMap) {
		System.out.println("[UserDiaryService] getDiaryItems() CALLED!!");
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		Criteria criteria = new Criteria(
										Integer.parseInt(msgMap.get("pageNum")), 
										Integer.parseInt(msgMap.get("amount"))
										);
		
		List<UserDiaryVo> userDiaryVos = userDiaryDao.getDiaryItems(msgMap, criteria);
		
		int totalCnt = userDiaryDao.getTotalCnt(msgMap);
		PageMakerVo pageMakerVo = new PageMakerVo(criteria, totalCnt);
		
		map.put("userDiaryVos", userDiaryVos);
		map.put("pageMakerVo", pageMakerVo);
		
		return map;
		
	}

	public Map<String, Object> deleteDiaryItem(Map<String, String> msgMap) {
		System.out.println("[UserDiaryService] deleteDiaryItem() CALLED!!");
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		int result = userDiaryDao.deleteDiaryItem(msgMap);
		map.put("result", result);
		
		return map;
	}

	public UserDiaryVo diary_modify(UserDiaryVo userDiaryVo) {
		System.out.println("[UserDiaryService] diary_modify() CALLED!!");
		
		userDiaryVo = userDiaryDao.diary_modify(userDiaryVo);
		
		return userDiaryVo;
	}

	public int diary_modify_confirm(UserDiaryVo userDiaryVo) {
		System.out.println("[UserDiaryService] diary_modify_confirm() CALLED!!");
		
		int result = userDiaryDao.diary_modify_confirm(userDiaryVo);
		
		return result;
	}

	public Map<String, Object> getDiaryItem(Map<String, String> msgMap) {
		System.out.println("[UserDiaryService] getDiaryItem() CALLED!!");
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		UserDiaryVo userDiaryVo = userDiaryDao.getDiaryItem(msgMap);
		
		map.put("userDiaryVo", userDiaryVo);
		
		return map;
	}

}
