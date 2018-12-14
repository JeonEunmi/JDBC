package com.test011.preparedStatement;

import java.sql.*;

import com.test.connection.OracleConnection;

public class Main {

	public static void main(String[] args) {
		
		// The PreparedStatement Objects 
		//->쿼리문이 동적으로 준비된 상태로 액션 진행
		// ->쿼리는 사전에 컴파일된 상태이고, 자료만 매개변수를 통해서 전달된다.
		// ->보안측면에서 더 좋은 방법이다. SQL Injection 공격 방지.
		
		//원래 목적은 특정 사용자의 정보를 출력하는 것이다.
		//외부에서 제공된 자료(정상적인 경우)
		// String mid = "M01";
		
		//SQL Injection 공격 문장을 사용하면 전체 사용자의 정보를 출력할 수 있다.
		//외부에서 제공된 자료(비정상적인 경우)
		//->PreparedStatement 방법 사용시 SQL Injection 공격 방지 가능
		String mid = "M02' OR 'x' = 'x";
		
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			conn = OracleConnection.connect();

			String sql = "SELECT mid, name_, phone, email \r\n"
					+ ", TO_CHAR(regDate, 'YYYY-MM-DD') regDate \r\n"
					+ "FROM members \r\n"
					+ "WHERE mid = ?";

			// PreparedStatement 객체 생성 과정에서 쿼리 문자열 분석이 진행된다.
			pstmt = conn.prepareStatement(sql);
			
			// 데이터 바인딩 문자열에 매핑할 자료를 setXXX() 메소드로 넘겨준다.
			pstmt.setString(1, mid);
			
			// 쿼리 실행 단계에서는 쿼리 문자열 처리 과정이 없다.
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
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
