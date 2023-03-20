package com.office.diary;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

public class DatabaseConnect {
	
	@Test
	public void connectDiaryDb() {
		
		final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
		final String URL = "jdbc:mysql://192.168.0.2:3306/diary_db?serverTimezone=Asia/Seoul";
		final String USER_ID = "hoazzi";
		final String USER_PW = "1234";
		
		try {
			
			// 드라이버 로드 in 메모리
			Class.forName(JDBC_DRIVER);
			
			// DB 연결
			Connection connection = DriverManager.getConnection(URL, USER_ID, USER_PW);
			System.out.println("connection: " + connection);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
	}
	
}
