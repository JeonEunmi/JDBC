package com.test007;

// 1단계 : Import the packages
import java.sql.*;
import com.test.connection.*;

public class Main {

	public static void main(String[] args) {

		// JDBC 드라이버 연결 테스트
		Connection conn = null;
		Statement stmt = null;

		try {
			// 2단계 : Register the JDBC driver
			// 3단계 : Open a connection
			conn = OracleConnection.connect();

			// 4단계 : Execute a query
			// employees, departments 테이블 Left Outer Join 쿼리
			// -> empView로 작성하고, 뷰를 이용한 쿼리 실행
			String sql = "SELECT rw, employee_id, first_name, last_name, department_id, department_name \r\n"
					+ "FROM empView";
			/*
			 * CREATE OR REPLACE VIEW empView AS SELECT ROW_NUMBER() OVER(ORDER BY
			 * employee_id) rw, employee_id, first_name, last_name, d.department_id,
			 * department_name FROM employees e, departments d WHERE e.department_id
			 * =d.department_id(+);
			 */
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			System.out.println();

			// 5단계 : Extract data from result set
			int count = 0;
			while (rs.next()) {
				// Retrieve by column name
				int rw = rs.getInt("rw");
				int employee_id = rs.getInt("employee_id");
				String first_name = rs.getString("first_name");
				String last_name = rs.getString("last_name");
				String department_id = rs.getString("department_id");
				String department_name = rs.getString("department_name");
				++count;

				// Display values
				System.out.printf("%s / %s / %s / %s / %s / %s %n", rw, employee_id, first_name, last_name,
						department_id, department_name);

			}

			System.out.println("------------------");
			System.out.printf("총 %s건%n", count);
			// 6단계 : Clean-up environment
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
