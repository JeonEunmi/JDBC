package com.test010;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.test.connection.OracleConnection;

public class Main {

	public static void main(String[] args) {
		// The statement Objects
		// -> 쿼리문이 정적으로 준비된 상태로 액션 진행
		// -> SQL Injection 공격에 취약한 상태
		
		//원래 목적은 특정 사용자의 정보를 출력하는 것이다.
		//외부에서 제공된 자료(정상적인 경우)
		// String mid = "M01";
		
		//SQL Injection 공격 문장을 사용하면 전체 사용자의 정보를 출력할 수 있다.
		//외부에서 제공된 자료(비정상적인 경우)
		String mid = "M01' OR 'x' = 'x";
		
		Connection conn = null;
		Statement stmt = null;

		try {

			conn = OracleConnection.connect();

			   String sql = String.format("SELECT mid, name_, phone, email \r\n"
					   + ", TO_CHAR(regDate, 'YYYY-MM-DD') regDate \r\n"
					   + "FROM members \r\n"
					   + "WHERE mid = '%s'", mid);


			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				String mid_ = rs.getString("mid");
				String name_ = rs.getString("name_");
				String phone = rs.getString("phone");
				String email = rs.getString("email");
				String regDate = rs.getString("regDate");
				
				System.out.printf("%s / %s / %s / %s / %s%n", mid_, name_, phone, email, regDate);
			}

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


	}

}
