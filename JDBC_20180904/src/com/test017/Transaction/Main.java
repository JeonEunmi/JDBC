package com.test017.Transaction;

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

		String newId = "M08";
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			conn = OracleConnection.connect();

			// ����) ����Ŭ�� ��� �ڷ���(NUMBER, VARCHAR2, NVARCHAR2, DATE)�� NULL ����Ѵ�.
			String sql = "INSERT INTO members (mid, name_, phone, email, regDate) \r\n"
					+ "VALUES (?, ?, ?, ?, SYSDATE)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newId); // pk ������ �ִ� �÷��� �ߺ��� �ڷ� �Է� �Ұ�
			pstmt.setString(2,  "kim");
			pstmt.setString(3,  "010-1234-1234");
			pstmt.setString(4,  "kim01@naver.com");
			int rs = pstmt.executeUpdate();

			System.out.printf("%d �� ��(��) ���ԵǾ����ϴ�. %n", rs);
			
			
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
