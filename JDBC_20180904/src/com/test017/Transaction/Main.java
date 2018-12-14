package com.test017.Transaction;

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

		String newId = "M08";
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			conn = OracleConnection.connect();

			// 주의) 오라클은 모든 자료형(NUMBER, VARCHAR2, NVARCHAR2, DATE)에 NULL 허용한다.
			String sql = "INSERT INTO members (mid, name_, phone, email, regDate) \r\n"
					+ "VALUES (?, ?, ?, ?, SYSDATE)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newId); // pk 제약이 있는 컬럼에 중복된 자료 입력 불가
			pstmt.setString(2,  "kim");
			pstmt.setString(3,  "010-1234-1234");
			pstmt.setString(4,  "kim01@naver.com");
			int rs = pstmt.executeUpdate();

			System.out.printf("%d 행 이(가) 삽입되었습니다. %n", rs);
			
			
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
