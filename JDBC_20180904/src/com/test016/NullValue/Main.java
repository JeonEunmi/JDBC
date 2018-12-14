package com.test016.NullValue;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.test.connection.OracleConnection;

public class Main {

	public static void main(String[] args) {

		// Handling NULL Values
		String mid = "M02";
		
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			conn = OracleConnection.connect();

			// ����) ����Ŭ�� ��� �ڷ���(NUMBER, VARCHAR2, NVARCHAR2, DATE)�� NULL ����Ѵ�.
			String sql = "SELECT mid, name_, phone, email \r\n"
					+ ", regDate, point, grade \r\n"
					+ "FROM members \r\n"
					+ "WHERE mid = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				//����) Java���� �⺻�ڷ���(int, double)������ NULL ���� ������� �ʴ´�.
				// getInt()�� NULL�� ��� 0 ��ȯ
				String mid_ = rs.getString("mid");  //VARCHAR2(3)
				String name_ = rs.getString("name_"); //NVARCHAR2(5)
				String phone = rs.getString("phone"); //VARCHAR2(15)
				String email = rs.getString("email"); //VARCHAR2(20)
				Date regDate = rs.getDate("regDate"); //DATE
				
				int point1 = rs.getInt("point");
				//getString()�� NULL�� ��� NULL ��ȯ
				String point2 = rs.getString("point");

				double grade1 = rs.getDouble("grade");
				String grade2 = rs.getString("grade");
				//����) ��½� �ڷῡ ���� ������ ������ �����ؼ� ����Ѵ�.
				//��¥, �ð� ���� ���� ���
				System.out.printf("%s / %s / %s / %s / %tF / %d / %s / %.2f / %s%n"
						, mid_, name_, phone, email
						, regDate, point1, point2, grade1, grade2);
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
