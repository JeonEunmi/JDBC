package com.test012;

import java.sql.*;

import com.test.connection.OracleConnection;

public class Main {

	public static void main(String[] args) {
		
		// The PreparedStatement Objects 
		//->�������� �������� �غ�� ���·� �׼� ����
		// ->������ ������ �����ϵ� �����̰�, �ڷḸ �Ű������� ���ؼ� ���޵ȴ�.
		// ->�������鿡�� �� ���� ����̴�. SQL Injection ���� ����.
		
		//���� ������ Ư�� ������� ������ ����ϴ� ���̴�.
		String mid = "M02";
		
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			conn = OracleConnection.connect();

			// TO_CHAR() �Լ� ��� �� ��Ī ��� ������ ���
			String sql = "SELECT mid, name_, phone, email \r\n"
					+ ", TO_CHAR(regDate, 'YYYY-MM-DD')  \r\n"
					+ "FROM members \r\n"
					+ "WHERE mid = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				
				//getXXX() �޼ҵ忡�� �Ű����� ������
				//�÷��� �Ǵ� �ε��� ���� ����
				//getString("�÷���") or getString(�ε���)
				String mid_ = rs.getString("mid");
				String name_ = rs.getString("name_");
				String phone = rs.getString("phone");
				String email = rs.getString("email");
				
				//�������� �ʴ� ��Ī�� ������ getString() �޼ҵ� ȣ��
				//String regDate = rs.getString("regDate");
				//-> java.sql.SQLEXception : �������� �� �̸�
				//-> ��Ī�� ���� ��� �ε����� ��ü ��� ����
				String regDate = rs.getString(5);

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
