package com.member06;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.test.connection.OracleConnection;

public class MemberDAO {

	public int memberAdd(Member m) {
		
		//Insert 쿼리 액션
		//-> PreparedStatement 방법으로 수정
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rs = 0;

		try {

			conn = OracleConnection.connect();

			   String sql = "INSERT INTO members (mid, name_, phone, email, regdate)\r\n"
					     + " VALUES ((SELECT CONCAT('M', LPAD(NVL(SUBSTR(MAX(mid), 2), 0)+1, 2, 0)) AS newld\r\n"
					  
					     + " FROM members), ?, ?, ?, SYSDATE )";


			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getName_());
			pstmt.setString(2, m.getPhone());
			pstmt.setString(3, m.getEmail());
			
			rs = pstmt.executeUpdate();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException se2) {
				se2.printStackTrace();
			}

			try {
				OracleConnection.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

		return rs;
	}
	
	public List<Member> memberPrintAll() {

		//SELECT 쿼리 액션
		//-> PreparedStatement 방법으로 수정
		Connection conn = null;
		PreparedStatement pstmt = null;

		List<Member> m = new ArrayList<Member>();
		
		try {

			conn = OracleConnection.connect();

			String sql = "SELECT mid, name_, phone, email, TO_CHAR(regDate, 'YYYY-MM-DD') regDate \r\n" + "FROM members \r\n"
					+ "ORDER BY mid";

			pstmt = conn.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();


			while (rs.next()) {
				String mid = rs.getString("mid");
				String name_ = rs.getString("name_");
				String phone = rs.getString("phone");
				String email = rs.getString("email");
				String regDate = rs.getString("regDate");
				
				m.add(new Member(mid,name_, phone, email, regDate));
				

			}
			

			rs.close();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException se2) {
				se2.printStackTrace();
			}

			try {
				OracleConnection.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		
		return m;
		
	}
	
	
}
