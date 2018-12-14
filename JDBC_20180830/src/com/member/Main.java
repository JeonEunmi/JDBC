package com.member;

import java.sql.*;
import com.test.connection.*;

public class Main {

	public static void main(String[] args) {
		// 문제) 회원관리 v2.0
		// members 테이블에 저장된 자료를 읽어와서 화면에 출력

		Connection conn = null;
		Statement stmt = null;

		try {

			conn = OracleConnection.connect();

			String sql = "SELECT mid, name_, phone, email, TO_CHAR(regDate, 'YYYY-MM-DD') regDate \r\n" + "FROM members";

			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			System.out.println();

			int count = 0;
			while (rs.next()) {
				String mid = rs.getString("mid");
				String name_ = rs.getString("name_");
				String phone = rs.getString("phone");
				String email = rs.getString("email");
				String regDate = rs.getString("regDate");

				++count;

				System.out.printf("%s / %s / %s / %s / %s%n", mid, name_, phone, email, regDate);

			}

			System.out.println("------------------");
			System.out.printf("총 %s건%n", count);

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

	}
}
