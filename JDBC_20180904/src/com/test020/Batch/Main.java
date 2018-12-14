package com.test020.Batch;

import java.sql.*;
import java.util.Arrays;

import com.test.connection.OracleConnection;

public class Main {

	public static void main(String[] args) {

		// JDBC - Batch Processing(일괄 처리)
		

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = OracleConnection.connect();
			
			conn.setAutoCommit(false);

			String sql = "INSERT INTO members (mid, name_, phone, email, regDate) \r\n"
					+ "VALUES ((SELECT CONCAT('M', LPAD(NVL(SUBSTR(MAX(mid), 2), 0) + 1, 2, 0)) \r\n" 
					+ "AS newId FROM members), ?, ?, ?, SYSDATE)";
			
			pstmt = conn.prepareStatement(sql);

			//주의) pk 제약조건 비활성 상태로 변경한 후 테스트 
			pstmt.setString(1,  "야");
			pstmt.setString(2,  "010-4444-5555");
			pstmt.setString(3,  "eun22@daum.net");
			pstmt.addBatch();
			
			pstmt.setString(1,  "호");
			pstmt.setString(2,  "010-3333-2222");
			pstmt.setString(3,  "mindd2@daum.net");
			pstmt.addBatch();
			
			int[] count = pstmt.executeBatch();

			System.out.printf("%s 행 이(가) 삽입되었습니다. %n", Arrays.toString(count));
			
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
