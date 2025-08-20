package com.khyuna0.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.khyuna0.dto.BoardDto;

public class BoardDao {

	private String drivername = "com.mysql.jdbc.Driver"; 
	private String url = "jdbc:mysql://localhost:3306/jspdb"; 
	private String username = "root";
	private String password = "12345";
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	
	public List<BoardDto> boardList() { // 게시판의 모든 글 리스트를 가져와서 반환하는 메서드
		
		String sql = "SELECT * FROM board ORDER BY bnum desc";
		
		List<BoardDto> boardlist = new ArrayList<BoardDto>();

		try {
			Class.forName(drivername); //MySQL 드라이버 클래스 불러오기			
			conn = DriverManager.getConnection(url, username, password);
			//커넥션이 메모리 생성(DB와 연결 커넥션 conn 생성)
			
			pstmt = conn.prepareStatement(sql); //pstmt 객체 생성(sql 삽입)
			rs = pstmt.executeQuery(); 
			
			while(rs.next()) { // 성공
				
				int bnum = rs.getInt("bnum");	
				String btitle = rs.getString("btitle");
				String bcontents = rs.getString("bcontents");
				String memberid = rs.getString("memberid");
				int bhit =rs.getInt("bhit");
				String bdate = rs.getString("bdate");
			
				BoardDto bDto = new BoardDto(bnum, btitle, bcontents, memberid, bhit, bdate);
				boardlist.add(bDto);
				
			}
			
			
		} catch (Exception e) {
			System.out.println("DB 에러 발생!");
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
		
		return boardlist; // 글들(bDto)이 담긴 boardlist 리스트 반환
	}
	
	public void boardWrite (String btitle, String bcontents, String memberid, int bhit) { // 게시판 글쓰기
		
		String sql = "INSERT INTO board(btitle, bcontents, memberid, bhit) VALUES (?,?,?,0)";
		// 새 글 등록하기
		
		try {
			Class.forName(drivername);			
			conn = DriverManager.getConnection(url, username, password);
			
			pstmt = conn.prepareStatement(sql); 
			pstmt.setString(1, btitle);
			pstmt.setString(2, bcontents);
			pstmt.setString(3, memberid);
				
			pstmt.executeUpdate(); 
			
		} catch (Exception e) {
			System.out.println("DB 에러 발생! 글 등록 실패!");
			e.printStackTrace(); 
		} finally {  
			try {
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
		
	} // boardWrite 메서드 끝
	
	public BoardDto contentView (String bnum) { // 게시판 글 목록에서 유저가 클릭한 글 번호의 글 dto 반환 메서드
		
		BoardDto bDto = new BoardDto();
		bDto = null;
		String sql = "SELECT * FROM board WHERE bnum = ?";
		try {
			Class.forName(drivername);		
			conn = DriverManager.getConnection(url, username, password);
			
			pstmt = conn.prepareStatement(sql); 
			pstmt.setString(1, bnum);
			rs = pstmt.executeQuery(); // 해당 번호글의 레코드 1개 또는 0개 반환

			
			if(rs.next()) { 
				
				int contentNum = rs.getInt("bnum");
				String btitle = rs.getString("btitle");
				String bcontents = rs.getString("bcontents");
				String memberid = rs.getString("memberid");
				int bhit =rs.getInt("bhit");
				String bdate = rs.getString("bdate");
				
				bDto = new BoardDto(contentNum, btitle, bcontents, memberid, bhit, bdate);
				
			} 

		} catch (Exception e) {
			System.out.println("DB 에러 발생!");
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
		
		return bDto; // bDto 값에 따른 처리는 다른 페이지에서
	}

}
	 