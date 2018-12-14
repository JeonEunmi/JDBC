package com.test010;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.test.connection.OracleConnection;

public class Main {

	public static void main(String[] args) {
		// The statement Objects
		// -> �������� �������� �غ�� ���·� �׼� ����
		// -> SQL Injection ���ݿ� ����� ����
		
		//���� ������ Ư�� ������� ������ ����ϴ� ���̴�.
		//�ܺο��� ������ �ڷ�(�������� ���)
		// String mid = "M01";
		
		//SQL Injection ���� ������ ����ϸ� ��ü ������� ������ ����� �� �ִ�.
		//�ܺο��� ������ �ڷ�(���������� ���)
		String mid = "M01' OR 'x' = 'x";
		
		Connection conn = null;
		Statement stmt = null;

		try {

			conn = OracleConnection.connect();

			   String sql = String.format("SELECT mid, name_, phone, email \r\n"
					   + ", TO_CHAR(regDate, 'YYYY-MM-DD') regDate \r\n"
					   + "FROM members \r\n"
					   + "WHERE mid = '%s'", mid);


			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
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
