package com.khyuna0.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MemberDao {

	private String drivername = "com.mysql.jdbc.Driver"; 
	private String url = "jdbc:mysql://localhost:3306/jspdb"; 
	private String username = "root";
	private String password = "12345";
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public int loginCheck(String mid, String mpw) { // 로그인 성공여부 반환하는 메서드
		
		String sql = "SELECT * FROM members WHERE memberid = ? AND memberpw =?";
		int sqlResult = 0;
		
		try {
			Class.forName(drivername);		
			conn = DriverManager.getConnection(url, username, password);
			
			pstmt = conn.prepareStatement(sql); 
			pstmt.setString(1, mid);
			pstmt.setString(2, mpw);
			rs = pstmt.executeQuery(); // 해당 번호글의 레코드 1개 또는 0개 반환
			
			if (rs.next()) { // 참이면 로그인 성공
				sqlResult = 1; // 성공
			} 
			
		} catch (Exception e) {
			System.out.println("DB 에러 발생! 로그인 실패");
			e.printStackTrace();
		} finally { 
			try {
				if(rs != null) { 
					rs.close();
				}				
				if(pstmt != null) { 
					pstmt.close();
				}				
				if(conn != null) { 
					conn.close();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return sqlResult;
		
	}
}
