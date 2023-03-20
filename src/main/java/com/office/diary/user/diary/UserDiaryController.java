package com.office.diary.user.diary;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.office.diary.user.member.UserMemberVo;

@Controller
@RequestMapping("/user/diary")
public class UserDiaryController {

	@Autowired
	UserDiaryService userDiaryService;
	
	@RequestMapping(value = "/diary_list", method = RequestMethod.GET)
	public String diary_list(HttpSession session) {
		System.out.println("[UserDiaryController] diary_list() CALLED!!");
		
		String nextPage = "user/diary/diary_list";
		
		if (session.getAttribute("logined_userMemberVo") == null)
			return "redirect:/";
			
		return nextPage;
		
	}
	
	@RequestMapping(value = "/diary_write", method = RequestMethod.GET)
	public String diary_write(HttpSession session) {
		System.out.println("[UserDiaryController] diary_write() CALLED!!");
		
		String nextPage = "user/diary/diary_write";
		
		if (session.getAttribute("logined_userMemberVo") == null) 
			return "redirect:/";
		
		return nextPage;
		
	}
	
	@RequestMapping(value = "/diary_write_confirm", method = RequestMethod.POST)
	public String diary_write_confirm(HttpSession session, 
									  UserDiaryVo userDiaryVo, 
									  @RequestParam("d_img_file") MultipartFile d_img_file ) {
		System.out.println("[UserDiaryController] diary_write_confirm() CALLED!!");
		
		String nextPage = "user/diary/diary_write_success";
		
		// 로그인 인증
		UserMemberVo logined_userMemberVo = 
				(UserMemberVo) session.getAttribute("logined_userMemberVo");
		if (logined_userMemberVo == null)
			return "redirec:/";
		
		// 로그인 계정 SET
		userDiaryVo.setM_id(logined_userMemberVo.getM_id());
		
		// file 처리(저장)
		String fileOriName = d_img_file.getOriginalFilename();		// 사용자가 업로드한 file 이름
		String uploadDir = "c:\\diary\\upload\\" + userDiaryVo.getM_id();
		
		String fileExtension = 
				fileOriName.substring(fileOriName.lastIndexOf("."), fileOriName.length()); // file 확장자
		
		// long fileSize = d_img_file.getSize();
		
		UUID uuid = UUID.randomUUID();
		String uniqueName = uuid.toString().replaceAll("-", "");
		
		File savefile = new File(uploadDir + "\\" + uniqueName + fileExtension);
		
		try {
			
			d_img_file.transferTo(savefile);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		// DB 작업
		userDiaryVo.setD_img_name(uniqueName + fileExtension);
		
		int result = userDiaryService.diary_write_confirm(userDiaryVo);
		if (result <= 0)
			nextPage = "user/diary/diary_write_fail";
		
		return nextPage;
		
	}
	
	@RequestMapping(value = "/getDiaryItems", method = RequestMethod.POST)
	@ResponseBody
	public Object getDiaryItems(@RequestBody Map<String, String> msgMap) {
		System.out.println("[UserDiaryController] getDiaryItems() CALLED!!");
		
		Map<String, Object> map = userDiaryService.getDiaryItems(msgMap);
		
		return map;
	}
	
	@RequestMapping(value = "/deleteDiaryItem", method = RequestMethod.POST)
	@ResponseBody
	public Object deleteDiaryItem(@RequestBody Map<String, String> msgMap) {
		System.out.println("[UserDiaryController] deleteDiaryItem() CALLED!!");
		
		Map<String, Object> map = userDiaryService.deleteDiaryItem(msgMap);
		
		return map;
		
	}
	
	@RequestMapping(value = "/diary_modify", method = RequestMethod.GET)
	public String diary_modify(HttpSession session, UserDiaryVo userDiaryVo, Model model) {
		System.out.println("[UserDiaryController] diary_modify() CALLED!!");
		
		String nextPage = "user/diary/diary_modify";
		
		if (session.getAttribute("logined_userMemberVo") == null) 
			return "redirect:/";
		
		userDiaryVo = userDiaryService.diary_modify(userDiaryVo);
		model.addAttribute("userDiaryVo", userDiaryVo);
		
		return nextPage;
		
	}
	
	@RequestMapping(value = "/diary_modify_confirm", method = RequestMethod.POST)
	public String diary_modify_confirm(HttpSession session, 
									   UserDiaryVo userDiaryVo, 
									   @RequestParam("d_img_file") MultipartFile d_img_file) {
		System.out.println("[UserDiaryController] diary_modify_confirm() CALLED!!");
		
		String nextPage = "user/diary/diary_modify_success";
		
		// 로그인 인증
		UserMemberVo logined_userMemberVo = 
				(UserMemberVo) session.getAttribute("logined_userMemberVo");
		if (logined_userMemberVo == null)
			return "redirec:/";
		
		// 아이디(m_id) SET
		userDiaryVo.setM_id(logined_userMemberVo.getM_id());
		
		// file 처리(저장)
		String fileOriName = d_img_file.getOriginalFilename();		// 사용자가 업로드한 file 이름
		
		if (!fileOriName.equals("")) {
			//long fileSize = d_img_file.getSize();
			
			String uploadDir = "c:\\diary\\upload\\" + userDiaryVo.getM_id();
			
			String fileExtension = 
					fileOriName.substring(fileOriName.lastIndexOf("."), fileOriName.length()); // file 확장자
			
			UUID uuid = UUID.randomUUID();
			String uniqueName = uuid.toString().replaceAll("-", "");
			
			File savefile = new File(uploadDir + "\\" + uniqueName + fileExtension);
			
			try {
				
				d_img_file.transferTo(savefile);
				
			} catch (Exception e) {
				e.printStackTrace();
				
			}
			
			userDiaryVo.setD_img_name(uniqueName + fileExtension);
			
		}
		
		int result = userDiaryService.diary_modify_confirm(userDiaryVo);
		if (result <= 0)
			nextPage = "user/diary/diary_modify_fail";
		
		return nextPage;
		
	}
	
	@RequestMapping(value = "/getDiaryItem", method = RequestMethod.POST)
	@ResponseBody
	public Object getDiaryItem(@RequestBody Map<String, String> msgMap) {
		System.out.println("[UserDiaryController] getDiaryItem() CALLED!!");
		
		Map<String, Object> map = userDiaryService.getDiaryItem(msgMap);
		
		return map;
		
	}
	
	@RequestMapping(value = "/download_img", method = RequestMethod.GET)
	public void download_img(UserDiaryVo userDiaryVo, HttpServletResponse response) {
		System.out.println("[UserDiaryController] download_img() CALLED!!");
		
		System.out.println("id: " + userDiaryVo.getM_id());
		System.out.println("img_name: " + userDiaryVo.getD_img_name());
		
		FileInputStream fileInputStream = null;
		
		try {
			
			String imgPath = "C:\\diary\\upload\\" + userDiaryVo.getM_id() + '\\' + userDiaryVo.getD_img_name();
			
			// 헤더 설정(for 다운로드)
			File file = new File(imgPath);
			response.setHeader(imgPath, imgPath);
			response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
			
			// 파일 읽기(input) & 쓰기(output): (file --> FileInputStream --> buffer --> OutputStream)
			fileInputStream = new FileInputStream(imgPath);
			OutputStream outputStream = response.getOutputStream();
			
			int read = 0;
			byte[] buffer = new byte[1024];
			while ((read = fileInputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, read);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			
			try {
				
				if (fileInputStream != null)
						fileInputStream.close();
				
			} catch (Exception e2) {
				e2.printStackTrace();
				
			}
			
		}
		
	}
	
}
