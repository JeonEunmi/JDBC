package com.test018.Transaction2;

import java.sql.*;

import com.test.connection.OracleConnection;

public class Main {

	public static void main(String[] args) {

		// JDBC - Transactions
		/*
		트랜잭션 : 쿼리 여러개를 실행하는 과정에서 처리 단위를 하나로 묶어주는 역할
		예를 들어,
		1. 번호 자동 증가 쿼리 실행 -> 결과값이 'M02'
		1-1. 액션 추가 -> 결과값이 'M02'
		2. 신규 자료 입력 쿼리 실행 -> 신규번호('M02')를 이용해서 자료 입력 진행
		2-1. 액션 추가 -> 신규번호('M02')를 이용해서 자료 입력 진행 -> 입력 실패!
		
		=> 1, 2 과정을 하나의 처리 단위로 묶어버리면 문제가 발생하지 않는다.
		*/

		Connection conn = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;

		try {
			conn = OracleConnection.connect();
			
			conn.setAutoCommit(false);

			// 주의) 오라클은 모든 자료형(NUMBER, VARCHAR2, NVARCHAR2, DATE)에 NULL 허용한다.
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
			pstmt2.setString(1, newId); // pk 제약이 있는 컬럼에 중복된 자료 입력 불가
			pstmt2.setString(2,  "eun");
			pstmt2.setString(3,  "010-4444-5555");
			pstmt2.setString(4,  "eun22@daum.com");
			int result = pstmt2.executeUpdate();

			System.out.printf("%d 행 이(가) 삽입되었습니다. %n", result);
			
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
