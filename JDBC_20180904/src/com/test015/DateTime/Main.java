package com.test015.DateTime;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;

import com.test.connection.OracleConnection;

public class Main {

	public static void main(String[] args) {
		
		String mid = "M02";
		
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			conn = OracleConnection.connect();

			// 주의) 쿼리 실행시 자료에 대한 서식을 미리 지정하지 않는다.
			String sql = "SELECT mid, name_, phone, email \r\n"
					+ ", regDate \r\n"
					+ "FROM members \r\n"
					+ "WHERE mid = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				String mid_ = rs.getString("mid");  //VARCHAR2(3)
				String name_ = rs.getString("name_"); //NVARCHAR2(5)
				String phone = rs.getString("phone"); //VARCHAR2(15)
				String email = rs.getString("email"); //VARCHAR2(20)
				Date regDate1 = rs.getDate("regDate"); //DATE
				Time regDate2 = rs.getTime("regDate"); //DATE

				//주의) 출력시 자료에 대한 적절한 서식을 지정해서 출력한다.
				//날짜, 시간 전용 서식 사용
				System.out.printf("%s / %s / %s / %s / %tF %tR / %s %s%n"
						, mid_, name_, phone, email
						, regDate1, regDate2
						, new SimpleDateFormat("yyyy-MM-dd").format(regDate1)
						, new SimpleDateFormat("HH:mm").format(regDate2));
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
