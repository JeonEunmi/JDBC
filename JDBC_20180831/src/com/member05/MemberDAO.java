package com.member05;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.test.connection.OracleConnection;

public class MemberDAO {

	public int memberAdd(Member m) {
		
		
		Connection conn = null;
		Statement stmt = null;
		int rs = 0;

		try {

			conn = OracleConnection.connect();

			   String sql = String.format("INSERT INTO members (mid, name_, phone, email, regdate)\r\n"
					     + "    VALUES ((SELECT CONCAT('M', LPAD(NVL(SUBSTR(MAX(mid), 2), 0)+1, 2, 0)) AS newld\r\n"
					  
					     + "    FROM members), '%s', '%s', '%s', SYSDATE )", m.getName_(), m.getPhone(), m.getEmail());


			stmt = conn.createStatement();
			rs = stmt.executeUpdate(sql);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
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

		Connection conn = null;
		Statement stmt = null;

		List<Member> m = new ArrayList<Member>();
		
		try {

			conn = OracleConnection.connect();

			String sql = "SELECT mid, name_, phone, email, TO_CHAR(regDate, 'YYYY-MM-DD') regDate \r\n" + "FROM members \r\n"
					+ "ORDER BY mid";

			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);


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
				if (stmt != null)
					stmt.close();
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
