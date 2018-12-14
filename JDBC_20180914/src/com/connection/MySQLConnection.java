package com.connection;

import java.sql.*;

//�����ͺ��̽� ���� ���� ����� Ŭ����
public class MySQLConnection {

	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://211.63.89.98:3306/jeon?user=jeon&password=1234&useSSL=false";

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
