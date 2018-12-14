package com.test018.Transaction2;

import java.sql.*;

import com.test.connection.OracleConnection;

public class Main {

	public static void main(String[] args) {

		// JDBC - Transactions
		/*
		Ʈ����� : ���� �������� �����ϴ� �������� ó�� ������ �ϳ��� �����ִ� ����
		���� ���,
		1. ��ȣ �ڵ� ���� ���� ���� -> ������� 'M02'
		1-1. �׼� �߰� -> ������� 'M02'
		2. �ű� �ڷ� �Է� ���� ���� -> �űԹ�ȣ('M02')�� �̿��ؼ� �ڷ� �Է� ����
		2-1. �׼� �߰� -> �űԹ�ȣ('M02')�� �̿��ؼ� �ڷ� �Է� ���� -> �Է� ����!
		
		=> 1, 2 ������ �ϳ��� ó�� ������ ��������� ������ �߻����� �ʴ´�.
		*/

		Connection conn = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;

		try {
			conn = OracleConnection.connect();
			
			conn.setAutoCommit(false);

			// ����) ����Ŭ�� ��� �ڷ���(NUMBER, VARCHAR2, NVARCHAR2, DATE)�� NULL ����Ѵ�.
			String sql1 = "SELECT CONCAT('M', LPAD(NVL(SUBSTR(MAX(mid), 2), 0) + 1, 2, 0)) \r\n"
					+ "AS newId FROM members";

			pstmt1 = conn.prepareStatement(sql1);
			ResultSet rs = pstmt1.executeQuery();
			
			String newId = null;
			
			while(rs.next()) {
				newId = rs.getString("newId");
			}
			
			
			String sql2 = "INSERT INTO members (mid, name_, phone, email, regDate) \r\n"
					+ "VALUES (?, ?, ?, ?, SYSDATE)";
			
			pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setString(1, newId); // pk ������ �ִ� �÷��� �ߺ��� �ڷ� �Է� �Ұ�
			pstmt2.setString(2,  "eun");
			pstmt2.setString(3,  "010-4444-5555");
			pstmt2.setString(4,  "eun22@daum.com");
			int result = pstmt2.executeUpdate();

			System.out.printf("%d �� ��(��) ���ԵǾ����ϴ�. %n", result);
			
			conn.commit();
			
		} catch (ClassNotFoundException | SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			
		} finally {
			try {
				if (pstmt1 != null)
					pstmt1.close();
			} catch (SQLException se2) {
				se2.printStackTrace();
			}
			try {
				if (pstmt2 != null)
					pstmt2.close();
			} catch (SQLException se3) {
				se3.printStackTrace();
			}

			try {
				OracleConnection.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

	}

}
