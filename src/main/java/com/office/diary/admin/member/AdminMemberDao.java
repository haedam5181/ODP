package com.office.diary.admin.member;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminMemberDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public AdminMemberVo login_confirm(AdminMemberVo adminMemberVo) {
		System.out.println("[AdminMemberDao] login_confirm() CALLED!!");
		
		String sql = "SELECT * FROM tbl_admin_member WHERE am_id = ?";
		
		List<AdminMemberVo> adminMemberVos = null;
		
		try {
			
			adminMemberVos = jdbcTemplate.query(sql, new RowMapper<AdminMemberVo>() {

				@Override
				public AdminMemberVo mapRow(ResultSet rs, int rowNum) throws SQLException {
					
					AdminMemberVo adminMemberVo = new AdminMemberVo();
					adminMemberVo.setAm_no(rs.getInt("am_no"));
					adminMemberVo.setAm_id(rs.getString("am_id"));
					adminMemberVo.setAm_pw(rs.getString("am_pw"));
					adminMemberVo.setAm_mail(rs.getString("am_mail"));
					adminMemberVo.setAm_reg_date(rs.getString("am_reg_date"));
					adminMemberVo.setAm_mod_date(rs.getString("am_mod_date"));
					
					return adminMemberVo;
					
				}
				
			}, adminMemberVo.getAm_id());
			
			if(!passwordEncoder.matches(adminMemberVo.getAm_pw(), adminMemberVos.get(0).getAm_pw()))
				adminMemberVos.clear();
			
		} catch (Exception e) {
			e.printStackTrace();
			
			adminMemberVos.clear();
			
		}
		
		return adminMemberVos.size() > 0 ? adminMemberVos.get(0) : null;
	}

	public AdminMemberEnum isMember(AdminMemberVo adminMemberVo) {
		System.out.println("[AdminMemberDao] isMember() CALLED!!");
		
		String sql = "SELECT COUNT(*) FROM tbl_admin_member WHERE am_id = ?";
		
		int result = 0;
		
		try {
			
			result = jdbcTemplate.queryForObject(sql, 
												 Integer.class, 
												 adminMemberVo.getAm_id());
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		if (result <= 0)
			return AdminMemberEnum.IS_NOT_ADMIN_MEMBER_ACCOUNT;
		else
			return AdminMemberEnum.IS_ADMIN_MEMBER_ACCOUNT;
		
	}

	public AdminMemberEnum create_account_confirm(AdminMemberVo adminMemberVo) {
		System.out.println("[AdminMemberDao] create_account_confirm() CALLED!!");
		
		String sql = "INSERT INTO tbl_admin_member("
												+ "am_id, "
												+ "am_pw, "
												+ "am_mail, "
												+ "am_reg_date, "
												+ "am_mod_date) "
												+ "VALUES(?, ?, ?, NOW(), NOW())";
		
		int result = jdbcTemplate.update(sql, 
										 adminMemberVo.getAm_id(), 
										 passwordEncoder.encode(adminMemberVo.getAm_pw()), 
										 adminMemberVo.getAm_mail());
		
		return result > 0 ? AdminMemberEnum.ADMIN_MEMBER_ACCOUNT_SUCCESS 
						  : AdminMemberEnum.ADMIN_MEMBER_ACCOUNT_FAIL;
		
	}

}
