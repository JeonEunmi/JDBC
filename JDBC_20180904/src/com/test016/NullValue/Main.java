package com.test016.NullValue;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.test.connection.OracleConnection;

public class Main {

	public static void main(String[] args) {

		// Handling NULL Values
		String mid = "M02";
		
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			conn = OracleConnection.connect();

			// 주의) 오라클은 모든 자료형(NUMBER, VARCHAR2, NVARCHAR2, DATE)에 NULL 허용한다.
			String sql = "SELECT mid, name_, phone, email \r\n"
					+ ", regDate, point, grade \r\n"
					+ "FROM members \r\n"
					+ "WHERE mid = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				//주의) Java에서 기본자료형(int, double)에서는 NULL 값을 허용하지 않는다.
				// getInt()가 NULL인 경우 0 반환
				String mid_ = rs.getString("mid");  //VARCHAR2(3)
				String name_ = rs.getString("name_"); //NVARCHAR2(5)
				String phone = rs.getString("phone"); //VARCHAR2(15)
				String email = rs.getString("email"); //VARCHAR2(20)
				Date regDate = rs.getDate("regDate"); //DATE
				
				int point1 = rs.getInt("point");
				//getString()은 NULL인 경우 NULL 반환
				String point2 = rs.getString("point");

				double grade1 = rs.getDouble("grade");
				String grade2 = rs.getString("grade");
				//주의) 출력시 자료에 대한 적절한 서식을 지정해서 출력한다.
				//날짜, 시간 전용 서식 사용
				System.out.printf("%s / %s / %s / %s / %tF / %d / %s / %.2f / %s%n"
						, mid_, name_, phone, email
						, regDate, point1, point2, grade1, grade2);
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
