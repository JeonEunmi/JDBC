package com.test012;

import java.sql.*;

import com.test.connection.OracleConnection;

public class Main {

	public static void main(String[] args) {
		
		// The PreparedStatement Objects 
		//->쿼리문이 동적으로 준비된 상태로 액션 진행
		// ->쿼리는 사전에 컴파일된 상태이고, 자료만 매개변수를 통해서 전달된다.
		// ->보안측면에서 더 좋은 방법이다. SQL Injection 공격 방지.
		
		//원래 목적은 특정 사용자의 정보를 출력하는 것이다.
		String mid = "M02";
		
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			conn = OracleConnection.connect();

			// TO_CHAR() 함수 사용 시 별칭 사용 생략한 경우
			String sql = "SELECT mid, name_, phone, email \r\n"
					+ ", TO_CHAR(regDate, 'YYYY-MM-DD')  \r\n"
					+ "FROM members \r\n"
					+ "WHERE mid = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				
				//getXXX() 메소드에서 매개변수 값으로
				//컬럼명 또는 인덱스 지정 가능
				//getString("컬럼명") or getString(인덱스)
				String mid_ = rs.getString("mid");
				String name_ = rs.getString("name_");
				String phone = rs.getString("phone");
				String email = rs.getString("email");
				
				//존재하지 않는 별칭을 가지고 getString() 메소드 호출
				//String regDate = rs.getString("regDate");
				//-> java.sql.SQLEXception : 부적합한 열 이름
				//-> 별칭이 없는 경우 인덱스로 대체 사용 가능
				String regDate = rs.getString(5);

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
