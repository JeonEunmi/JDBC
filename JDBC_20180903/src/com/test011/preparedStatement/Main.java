package com.test011.preparedStatement;

import java.sql.*;

import com.test.connection.OracleConnection;

public class Main {

	public static void main(String[] args) {
		
		// The PreparedStatement Objects 
		//->�������� �������� �غ�� ���·� �׼� ����
		// ->������ ������ �����ϵ� �����̰�, �ڷḸ �Ű������� ���ؼ� ���޵ȴ�.
		// ->�������鿡�� �� ���� ����̴�. SQL Injection ���� ����.
		
		//���� ������ Ư�� ������� ������ ����ϴ� ���̴�.
		//�ܺο��� ������ �ڷ�(�������� ���)
		// String mid = "M01";
		
		//SQL Injection ���� ������ ����ϸ� ��ü ������� ������ ����� �� �ִ�.
		//�ܺο��� ������ �ڷ�(���������� ���)
		//->PreparedStatement ��� ���� SQL Injection ���� ���� ����
		String mid = "M02' OR 'x' = 'x";
		
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			conn = OracleConnection.connect();

			String sql = "SELECT mid, name_, phone, email \r\n"
					+ ", TO_CHAR(regDate, 'YYYY-MM-DD') regDate \r\n"
					+ "FROM members \r\n"
					+ "WHERE mid = ?";

			// PreparedStatement ��ü ���� �������� ���� ���ڿ� �м��� ����ȴ�.
			pstmt = conn.prepareStatement(sql);
			
			// ������ ���ε� ���ڿ��� ������ �ڷḦ setXXX() �޼ҵ�� �Ѱ��ش�.
			pstmt.setString(1, mid);
			
			// ���� ���� �ܰ迡���� ���� ���ڿ� ó�� ������ ����.
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
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
