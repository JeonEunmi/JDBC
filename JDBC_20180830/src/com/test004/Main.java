package com.test004;

// 1�ܰ� : Import the packages
import java.sql.*;

public class Main {

	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	static final String DB_URL = "jdbc:oracle:thin:@211.63.89.88:1521:xe";

	// Database credentials
	static final String USER = "test01";
	static final String PASS = "qhdqhd";

	public static void main(String[] args) {

		// JDBC ����̹� ���� �׽�Ʈ
		Connection conn = null;
		Statement stmt = null;

		// 2�ܰ� : Register the JDBC driver
		try {
			Class.forName(JDBC_DRIVER);

			// 3�ܰ� : Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println(conn.toString());

			// 4�ܰ� : Execute a query
			// employees, departments ���̺� ���� ����
			String sql = "SELECT employee_id, first_name, last_name, d.department_id, department_name "
				    + "FROM employees e, departments d "
				    + "WHERE e.department_id = d.department_id(+)";
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			// 5�ܰ� : Extract data from result set
			int count = 0;
			while (rs.next()) {
				// Retrieve by column name
				int employee_id = rs.getInt("employee_id");
				String first_name = rs.getString("first_name");
				String last_name = rs.getString("last_name");
				String department_id = rs.getString("department_id");
				String department_name = rs.getString("department_name");
				++count;

				// Display values
				System.out.printf("%s / %s / %s / %s / %s %n", employee_id, first_name, last_name, department_id, department_name);

			}
			
			System.out.println("------------------");
			System.out.printf("�� %s��%n", count);
			// 6�ܰ� : Clean-up environment
			rs.close();
			stmt.close();
			conn.close();

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
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

	}

}
