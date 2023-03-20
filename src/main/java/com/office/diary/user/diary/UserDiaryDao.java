package com.office.diary.user.diary;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.office.diary.user.diary.page.Criteria;

@Component
public class UserDiaryDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public int diary_write_confirm(UserDiaryVo userDiaryVo) {
		System.out.println("[UserDiaryDao] diary_write_confirm() CALLED!!");
		
		String sql = "INSERT INTO tbl_diary("
						+ "m_id, "
						+ "d_txt, "
						+ "d_img_name,"
						+ "d_write_full_year, "
						+ "d_write_month, "
						+ "d_write_date, "
						+ "d_write_day, "
						+ "d_write_hours, "
						+ "d_write_minutes, "
						+ "d_write_seconds, "
						+ "d_reg_date, "
						+ "d_mod_date) "
					+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), NOW())";
		
		int result = 0;
		try {
			
			result = jdbcTemplate.update(sql, 
										 userDiaryVo.getM_id(),
										 userDiaryVo.getD_txt(),
										 userDiaryVo.getD_img_name(),
										 userDiaryVo.getD_write_full_year(),
										 userDiaryVo.getD_write_month(),
										 userDiaryVo.getD_write_date(),
										 userDiaryVo.getD_write_day(),
										 userDiaryVo.getD_write_hours(),
										 userDiaryVo.getD_write_minutes(),
										 userDiaryVo.getD_write_seconds());
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return result;
	}

	public List<UserDiaryVo> getDiaryItems(Map<String, String> msgMap, Criteria criteria) {
		System.out.println("[UserDiaryDao] getDiaryItems() CALLED!!");
		
		List<String> args = new ArrayList<String>();
		
		String sql = "SELECT * FROM tbl_diary ";
			   sql += "WHERE 1 = 1 ";
			
			   if (msgMap.get("m_id") != null || msgMap.get("m_id").equals("") || msgMap.get("m_id").equals(" ")) {
				   sql += "AND m_id = ? ";
				   args.add(msgMap.get("m_id"));
			   }
				
			   if (Integer.parseInt(msgMap.get("full_year")) > 0) {
				   sql += "AND d_write_full_year = ? ";
				   args.add(msgMap.get("full_year"));
			   }
				   
			   if (Integer.parseInt(msgMap.get("month")) > 0) {
				   sql += "AND d_write_month = ? ";
				   args.add(msgMap.get("month"));
			   }
				   
			   if (Integer.parseInt(msgMap.get("date")) > 0) {
				   sql += "AND d_write_date = ? ";
				   args.add(msgMap.get("date"));
			   }
				   
			   sql += "ORDER BY d_no DESC ";
			   sql += "LIMIT " + criteria.getSkip() + ", " + criteria.getAmount();
		
		List<UserDiaryVo> userDiaryVos = null;
		
		try {
			
			userDiaryVos = jdbcTemplate.query(sql, new RowMapper<UserDiaryVo>() {

				@Override
				public UserDiaryVo mapRow(ResultSet rs, int rowNum) throws SQLException {
					
					UserDiaryVo userDiaryVo = new UserDiaryVo();
					userDiaryVo.setD_no(rs.getInt("d_no"));
					userDiaryVo.setM_id(rs.getString("m_id"));
					userDiaryVo.setD_txt(rs.getString("d_txt"));
					userDiaryVo.setD_img_name(rs.getString("d_img_name"));
					userDiaryVo.setD_write_full_year(rs.getInt("d_write_full_year"));
					userDiaryVo.setD_write_month(rs.getInt("d_write_month"));
					userDiaryVo.setD_write_date(rs.getInt("d_write_date"));
					userDiaryVo.setD_write_day(rs.getInt("d_write_day"));
					userDiaryVo.setD_write_hours(rs.getInt("d_write_hours"));
					userDiaryVo.setD_write_minutes(rs.getInt("d_write_minutes"));
					userDiaryVo.setD_write_seconds(rs.getInt("d_write_seconds"));
					userDiaryVo.setD_reg_date(rs.getString("d_reg_date"));
					userDiaryVo.setD_mod_date(rs.getString("d_mod_date"));
					
					return userDiaryVo;
				}
				
			}, args.toArray());
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return userDiaryVos;
	}

	public int deleteDiaryItem(Map<String, String> msgMap) {
		System.out.println("[UserDiaryDao] deleteDiaryItem() CALLED!!");
		
		String sql = "DELETE FROM tbl_diary WHERE d_no = ?";
		
		int result = 0;
		
		try {
			
			result = jdbcTemplate.update(sql, msgMap.get("d_no"));
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return result;
		
	}

	public UserDiaryVo diary_modify(UserDiaryVo userDiaryVo) {
		System.out.println("[UserDiaryDao] diary_modify() CALLED!!");
		
		String sql = "SELECT * FROM tbl_diary WHERE d_no = ?";
		
		List<UserDiaryVo> userDiaryVos = null;
		
		try {
			
			userDiaryVos = jdbcTemplate.query(sql, new RowMapper<UserDiaryVo>() {

				@Override
				public UserDiaryVo mapRow(ResultSet rs, int rowNum) throws SQLException {
					
					UserDiaryVo userDiaryVo = new UserDiaryVo();
					userDiaryVo.setD_no(rs.getInt("d_no"));
					userDiaryVo.setM_id(rs.getString("m_id"));
					userDiaryVo.setD_txt(rs.getString("d_txt"));
					userDiaryVo.setD_img_name(rs.getString("d_img_name"));
					userDiaryVo.setD_write_full_year(rs.getInt("d_write_full_year"));
					userDiaryVo.setD_write_month(rs.getInt("d_write_month"));
					userDiaryVo.setD_write_date(rs.getInt("d_write_date"));
					userDiaryVo.setD_write_day(rs.getInt("d_write_day"));
					userDiaryVo.setD_write_hours(rs.getInt("d_write_hours"));
					userDiaryVo.setD_write_minutes(rs.getInt("d_write_minutes"));
					userDiaryVo.setD_write_seconds(rs.getInt("d_write_seconds"));
					userDiaryVo.setD_reg_date(rs.getString("d_reg_date"));
					userDiaryVo.setD_mod_date(rs.getString("d_mod_date"));
					
					return userDiaryVo;
					
				}
				
			}, userDiaryVo.getD_no());
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return userDiaryVos != null ? userDiaryVos.get(0) : null;
		
	}

	public int diary_modify_confirm(UserDiaryVo userDiaryVo) {
		System.out.println("[UserDiaryDao] diary_modify_confirm() CALLED!!");
		
		String  sql = "UPDATE tbl_diary SET d_txt = ?, ";
		
		if (userDiaryVo.getD_img_name() != null)
				sql += "d_img_name = ?, ";
		
				sql += "d_mod_date = NOW() WHERE d_no = ?";
				
		int result = 0;
		
		try {
			
			if (userDiaryVo.getD_img_name() != null)
				result = jdbcTemplate.update(sql, 
											 userDiaryVo.getD_txt(), 
											 userDiaryVo.getD_img_name(), 
											 userDiaryVo.getD_no());
			else
				result = jdbcTemplate.update(sql, 
											 userDiaryVo.getD_txt(), 
											 userDiaryVo.getD_no());
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
				
		return result;
	}

	public UserDiaryVo getDiaryItem(Map<String, String> msgMap) {
		System.out.println("[UserDiaryDao] getDiaryItem() CALLED!!");
		
		String sql = "SELECT * FROM tbl_diary WHERE d_no = ?";
		
		List<UserDiaryVo> userDiaryVos = null;
		
		try {
			
			userDiaryVos = jdbcTemplate.query(sql, new RowMapper<UserDiaryVo>() {

				@Override
				public UserDiaryVo mapRow(ResultSet rs, int rowNum) throws SQLException {
					
					UserDiaryVo userDiaryVo = new UserDiaryVo();
					userDiaryVo.setD_no(rs.getInt("d_no"));
					userDiaryVo.setM_id(rs.getString("m_id"));
					userDiaryVo.setD_txt(rs.getString("d_txt"));
					userDiaryVo.setD_img_name(rs.getString("d_img_name"));
					userDiaryVo.setD_write_full_year(rs.getInt("d_write_full_year"));
					userDiaryVo.setD_write_month(rs.getInt("d_write_month"));
					userDiaryVo.setD_write_date(rs.getInt("d_write_date"));
					userDiaryVo.setD_write_day(rs.getInt("d_write_day"));
					userDiaryVo.setD_write_hours(rs.getInt("d_write_hours"));
					userDiaryVo.setD_write_minutes(rs.getInt("d_write_minutes"));
					userDiaryVo.setD_write_seconds(rs.getInt("d_write_seconds"));
					userDiaryVo.setD_reg_date(rs.getString("d_reg_date"));
					userDiaryVo.setD_mod_date(rs.getString("d_mod_date"));
					
					return userDiaryVo;
				}
				
			}, msgMap.get("d_no"));
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return userDiaryVos != null ? userDiaryVos.get(0) : null;
		
	}

	public int getTotalCnt(Map<String, String> msgMap) {
		System.out.println("[UserDiaryDao] getTotalCnt() CALLED!!");
		
		List<String> args = new ArrayList<String>();
		
		String sql = "SELECT COUNT(*) FROM tbl_diary ";
				sql += "WHERE 1 = 1 ";
				
		   	   if (msgMap.get("m_id") != null || msgMap.get("m_id").equals("") || msgMap.get("m_id").equals(" ")) {
					sql += "AND m_id = ? ";
					args.add(msgMap.get("m_id"));
			   }
				
			   if (Integer.parseInt(msgMap.get("full_year")) > 0) {
				   sql += "AND d_write_full_year = ? ";
				   args.add(msgMap.get("full_year"));
			   }
				   
			   if (Integer.parseInt(msgMap.get("month")) > 0) {
				   sql += "AND d_write_month = ? ";
				   args.add(msgMap.get("month"));
			   }
				   
			   if (Integer.parseInt(msgMap.get("date")) > 0) {
				   sql += "AND d_write_date = ? ";
				   args.add(msgMap.get("date"));
			   }
		
		int totalCnt = -1;
		
		try {
			
			totalCnt = jdbcTemplate.queryForObject(sql, 
												  Integer.class, 
												  args.toArray());
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return totalCnt;
	}

}
