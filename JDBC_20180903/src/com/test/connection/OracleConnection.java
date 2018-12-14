package com.test.connection;

import java.sql.*;

// �����ͺ��̽� ���� ���� ����� Ŭ����
public class OracleConnection {

	// JDBC driver name and database URL
	private static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String DB_URL = "jdbc:oracle:thin:teamone/1111@211.63.89.75:1521:xe";

	private static Connection conn;

	// �����׺��̽� Ŀ�ؼ� ��ü ���� �޼ҵ�
	public static Connection connect() throws ClassNotFoundException, SQLException {

		// STEP 2: Register JDBC driver
		Class.forName(JDBC_DRIVER);
		// STEP 3: Open a connection
		conn = DriverManager.getConnection(DB_URL);

		return conn;
	}

	// �����׺��̽� Ŀ�ؼ� ��ü �Ҹ� �޼ҵ�
	public static void close() throws SQLException {
		if (conn != null) {
			conn.close();
		}
	}
}