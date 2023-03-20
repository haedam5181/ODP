package com.office.diary.user.member;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMemberDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public UserMemberVo login_confirm(UserMemberVo userMemberVo) {
		System.out.println("[UserMemberDao] login_confirm() CALLED!!");
		
		List<UserMemberVo> userMemberVos = new ArrayList<UserMemberVo>();
		
		String sql = "SELECT * FROM tbl_member WHERE m_id = ?";
		
		try {
			
			userMemberVos = jdbcTemplate.query(sql, new RowMapper<UserMemberVo>() {

				@Override
				public UserMemberVo mapRow(ResultSet rs, int rowNum) throws SQLException {
					
					UserMemberVo userMemberVo = new UserMemberVo();
					userMemberVo.setM_no(rs.getInt("m_no"));
					userMemberVo.setM_id(rs.getString("m_id"));
					userMemberVo.setM_pw(rs.getString("m_pw"));
					userMemberVo.setM_mail(rs.getString("m_mail"));
					userMemberVo.setM_phone(rs.getString("m_phone"));
					userMemberVo.setMa_able(rs.getInt("ma_able"));
					userMemberVo.setM_reg_date(rs.getString("m_reg_date"));
					userMemberVo.setM_mod_date(rs.getString("m_mod_date"));
					
					return userMemberVo;
				}
				
			}, userMemberVo.getM_id());
			
			if (!passwordEncoder.matches(userMemberVo.getM_pw(), userMemberVos.get(0).getM_pw()))
				userMemberVos.clear();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return userMemberVos.size() > 0 ? userMemberVos.get(0) : null;
		
	}

	public boolean isMember(UserMemberVo userMemberVo) {
		System.out.println("[UserMemberDao] isMember() CALLED!!");
		
		String sql = "SELECT COUNT(*) FROM tbl_member WHERE m_id = ?";
		int result = 0;
		
		try {
			
			result = jdbcTemplate.queryForObject(sql, Integer.class, userMemberVo.getM_id());
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}		
		
		return result > 0 ? true : false;
	}

	public int create_account_confirm(UserMemberVo userMemberVo) {
		System.out.println("[UserMemberDao] create_account_confirm() CALLED!!");
		
		int result = UserMemberService.CREATE_ACCOUNT_FAIL;
		
		String sql = "INSERT INTO tbl_member("
						+ "m_id, "
						+ "m_pw, "
						+ "m_mail, "
						+ "m_phone, "
						+ "m_reg_date, "
						+ "m_mod_date) "
					+ "VALUES(?, ?, ?, ?, NOW(), NOW())";
		
		try {
			
			result = jdbcTemplate.update(sql, 
										 userMemberVo.getM_id(), 
										 passwordEncoder.encode(userMemberVo.getM_pw()),
										 userMemberVo.getM_mail(), 
										 userMemberVo.getM_phone());
			
			result = UserMemberService.CREATE_ACCOUNT_SUCCESS;
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		
		return result;
	}

	public UserMemberVo my_page(String m_id) {
		System.out.println("[UserMemberDao] my_page() CALLED!!");
		
		String sql = "SELECT "
						+ "m.m_no 			no, "
						+ "m.m_id 			id, "
						+ "m.m_pw 			pw, "
						+ "m.m_mail 		mail, "
						+ "m.m_phone 		phone, "
						+ "m.m_reg_date 	reg_date, "
						+ "m.m_mod_date 	mod_date, "
						+ "ma.ma_able 		able, "
						+ "ma.ma_comment 	comment "
				
				   + "FROM tbl_member m "
				
				   + "JOIN tbl_member_able ma "
				   + "ON m.ma_able = ma.ma_able "
				
				   + "WHERE m.m_id = ?";
		
		List<UserMemberVo> userMemberVos = null;
		
		userMemberVos = jdbcTemplate.query(sql, new RowMapper<UserMemberVo>() {

			@Override
			public UserMemberVo mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				UserMemberVo userMemberVo = new UserMemberVo();
				userMemberVo.setM_no(rs.getInt("no"));
				userMemberVo.setM_id(rs.getString("id"));
				userMemberVo.setM_pw(rs.getString("pw"));
				userMemberVo.setM_mail(rs.getString("mail"));
				userMemberVo.setM_phone(rs.getString("phone"));
				userMemberVo.setMa_able(rs.getInt("able"));
				userMemberVo.setMa_comment(rs.getString("comment"));
				userMemberVo.setM_reg_date(rs.getString("reg_date"));
				userMemberVo.setM_mod_date(rs.getString("mod_date"));
				
				return userMemberVo;
				
			}
			
		}, m_id);
		
		System.out.println("userMemberVos: " + userMemberVos);
		
		return userMemberVos != null ? userMemberVos.get(0) : null;
		
	}

	public int my_page_confirm(UserMemberVo userMemberVo) {
		System.out.println("[UserMemberDao] my_page_confirm() CALLED!!");
		
		String sql = "UPDATE tbl_member SET m_mail = ?, m_phone = ? WHERE m_no = ?";
		
		int result = 0;
		
		try {
			
			result = jdbcTemplate.update(sql, 
										 userMemberVo.getM_mail(), 
										 userMemberVo.getM_phone(), 
										 userMemberVo.getM_no());
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return result;
	}

	public int pw_modify_confirm(UserMemberVo userMemberVo) {
		System.out.println("[UserMemberDao] my_page_confirm() CALLED!!");
		
		String sql = "UPDATE tbl_member SET m_pw = ? WHERE m_id = ?";
		
		int result = 0;
		
		try {
			
			result = jdbcTemplate.update(sql, 
										 passwordEncoder.encode(userMemberVo.getM_pw()), 
										 userMemberVo.getM_id());
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return result;
	}

}
