package com.test001;

// 1단계 : Import the packages
import java.sql.*;

public class Main {

	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	static final String DB_URL = "jdbc:oracle:thin:@211.63.89.88:1521:xe";

	// Database credentials
	static final String USER = "test01";
	static final String PASS = "qhdqhd";

	public static void main(String[] args) {

		// JDBC 드라이버 연결 테스트
		Connection conn = null;
		Statement stmt = null;

		// 2단계 : Register the JDBC driver
		try {
			Class.forName(JDBC_DRIVER);

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
				conn = DriverManager.getConnection(DB_URL, USER, PASS);
				
			System.out.println(conn.toString());
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			 try{
				 // 6단계 : Clean up the environment
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		}
		

	}

}
