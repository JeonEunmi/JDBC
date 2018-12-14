package com.test019.Exception;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;

import com.test.connection.OracleConnection;

public class Main {

	public static void main(String[] args) {
		
		String mid = "M02";
		
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			conn = OracleConnection.connect();

			// Ʋ�� ������ ����ϴ� ���
			// -> java.sql.SQLSyntaxErrorException: ORA-00942: table or view does not exist
			String sql = "SELECT mid, name_, phone, email \r\n"
					+ ", regDate \r\n"
					+ "FROM members"
					//���� ���ڿ� ���� �� �����̳� \r\n ���� �ʿ�
					// -> java.sql.SQLSyntaxErrorException: ORA-00933: SQL command not properly ended
//					+ "WHERE mid = ?";
					+ "ORDER BY mid";

			pstmt = conn.prepareStatement(sql);
			
			// ������ ���ε� ���ڿ� ? �� ��ġ�ϴ� �ڷ� ������ ���
			//java.sql.SQLException: �ε������� ������ IN �Ǵ� OUT �Ű�����:: 1		
//			pstmt.setString(1, mid);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				
				// SELECT ������ �÷���� getXXX() �޼ҵ��� �÷��ε����� ����ġ, ����
				// -> java.sql.SQLException: �������� �� �̸�
				String mid_ = rs.getString("mid");  //VARCHAR2(3)
				String name_ = rs.getString("name_"); //NVARCHAR2(5)
				String phone = rs.getString("phone"); //VARCHAR2(15)
				String email = rs.getString("email"); //VARCHAR2(20)
				Date regDate = rs.getDate("regDate"); //DATE

				//����) ��½� �ڷῡ ���� ������ ������ �����ؼ� ����Ѵ�.
				//��¥, �ð� ���� ���� ���
				System.out.printf("%s / %s / %s / %s / %tF%n"
						, mid_, name_, phone, email
						, regDate);
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
