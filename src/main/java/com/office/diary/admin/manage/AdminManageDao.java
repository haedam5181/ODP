package com.office.diary.admin.manage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.office.diary.user.member.UserMemberVo;

@Component
public class AdminManageDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public List<UserMemberVo> getUserItems(Map<String, String> msgMap) {
		System.out.println("[AdminManageDao] getUserItems() CALLED!!");
		
		String sql = "SELECT "
				
						+ "m.m_no			no, "
						+ "m.m_id			id, "
						+ "m.m_pw			pw, "
						+ "m.m_mail			mail, "
						+ "m.m_phone		phone, "
						+ "ma.ma_able		able, "
						+ "ma.ma_comment	comment, "
						+ "m.m_reg_date		reg_date, "
						+ "m.m_mod_date 	mod_date "
						
					+ "FROM tbl_member m "
					
					+ "JOIN tbl_member_able ma "
					+ "ON m.ma_able = ma.ma_able "
					
					+ "ORDER BY m.m_no DESC";
		
		List<UserMemberVo> userMemberVos = null;
		
		try {
			
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
				
			});
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return userMemberVos;
	}

	public int updateM_mail(Map<String, String> msgMap) {
		System.out.println("[AdminManageDao] updateM_mail() CALLED!!");
		
		String sql = "UPDATE tbl_member SET m_mail = ? WHERE m_no = ?";
		
		int result = 0;
		
		try {
			
			result = jdbcTemplate.update(sql, 
										 msgMap.get("m_mail"), 
										 msgMap.get("m_no"));
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return result;
		
	}

	public int updateM_phone(Map<String, String> msgMap) {
		System.out.println("[AdminManageDao] updateM_phone() CALLED!!");
		
		String sql = "UPDATE tbl_member SET m_phone = ? WHERE m_no = ?";
		
		int result = 0;
		
		try {
			
			result = jdbcTemplate.update(sql, 
										 msgMap.get("m_phone"), 
										 msgMap.get("m_no"));
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return result;
		
	}

	public int updateMa_able(Map<String, String> msgMap) {
		System.out.println("[AdminManageDao] updateMa_able() CALLED!!");
		
		String sql = "UPDATE tbl_member SET ma_able = ? WHERE m_no = ?";
		
		int result = 0;
		
		try {
			
			result = jdbcTemplate.update(sql, 
										 msgMap.get("ma_able"), 
										 msgMap.get("m_no"));
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return result;
		
	}

}
