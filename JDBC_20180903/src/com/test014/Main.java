package com.test014;

import java.sql.*;

import com.test.connection.OracleConnection;

public class Main {

	public static void main(String[] args) {

		// The PreparedStatement Objects
		// ->쿼리문이 동적으로 준비된 상태로 액션 진행
		// ->쿼리는 사전에 컴파일된 상태이고, 자료만 매개변수를 통해서 전달된다.
		// ->보안측면에서 더 좋은 방법이다. SQL Injection 공격 방지.
		int count = 0;

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			conn = OracleConnection.connect();

			String sql = "SELECT mid, name_, phone, email \r\n" 
					+ ", TO_CHAR(regDate, 'YYYY-MM-DD') regDate \r\n"
					+ "FROM members \r\n" 
					+ "ORDER BY mid";

			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, 
											ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = pstmt.executeQuery();

			rs.next();
			System.out.printf("%s / %s / %s / %s / %s%n", rs.getString("mid")
					, rs.getString("name_"), rs.getString("phone")
					, rs.getString("email"), rs.getString("regDate"));
			++count;

			
			rs.last();
			System.out.printf("%s / %s / %s / %s / %s%n", rs.getString("mid")
					, rs.getString("name_"), rs.getString("phone")
					, rs.getString("email"), rs.getString("regDate"));
			++count;
			
			rs.close();

			System.out.println("--------------------------------------------------");
			System.out.printf("총 %s건%n", count);

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

	}

}
