package com.member;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.connection.MySQLConnection;

public class MemberDAO {
	//회원 정보 입력 메소드
		public int memberAdd(Member m) {
			int result = 0;
			
			//INSERT 쿼리 액션
			
			Connection conn = null;	
			PreparedStatement pstmt = null;
			try {	
				conn = MySQLConnection.connect();
				
				String sql = "INSERT INTO members(mid_, name_, email, phone, regDate)\r\n" + 
						"	VALUES ((SELECT CONCAT('M', LPAD(IFNULL(SUBSTR(MAX(mid_), 2), 0) + 1, 2, 0)) newId FROM members m)\r\n" + 
						"		, ?, ?, ?, now())";
						
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, m.getName_());
				pstmt.setString(2, m.getPhone());
				pstmt.setString(3, m.getEmail());
				
				result = pstmt.executeUpdate();
				
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (pstmt != null)
						pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
				try {
					MySQLConnection.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}			
			
			return result;
		}
		
		//회원 정보 출력 메소드
		public List<Member> memberList() {
			List<Member> list = new ArrayList<Member>();
			
			//SELECT 쿼리 액션
			
			Connection conn = null;	
			PreparedStatement pstmt = null;

			try {	
				conn = MySQLConnection.connect();
				
				String sql = "SELECT mid_, name_, email, phone, regDate\r\n" + 
						"	FROM members\r\n" + 
						"    ORDER BY mid_;";
				pstmt = conn.prepareStatement(sql);
				
				
				ResultSet rs = pstmt.executeQuery(sql);
				
				while (rs.next()) {
					String mid = rs.getString("mid_");
					String name_ = rs.getString("name_");
					String phone = rs.getString("phone");
					String email = rs.getString("email");
					Date regDate = rs.getDate("regDate");
					
					//주의) ResultSet 객체는 현재 메소드 범위를 벗어날 수 없다
					//ResultSet 객체 -> 컬렉션 객체로 복사가 필요하다
					//컬렉션 저장소에 Member 객체를 저장하는 액션 추가
					list.add(new Member(mid, name_, phone, email, regDate));
					
				}
				
				//ResultSet 객체의 소멸
				rs.close();
				
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (pstmt != null)
						pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
				try {
					MySQLConnection.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}			
			
			return list;
		}
}
