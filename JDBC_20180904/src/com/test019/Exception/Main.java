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

			// 틀린 쿼리문 사용하는 경우
			// -> java.sql.SQLSyntaxErrorException: ORA-00942: table or view does not exist
			String sql = "SELECT mid, name_, phone, email \r\n"
					+ ", regDate \r\n"
					+ "FROM members"
					//쿼리 문자열 편집 시 공백이나 \r\n 삽입 필요
					// -> java.sql.SQLSyntaxErrorException: ORA-00933: SQL command not properly ended
//					+ "WHERE mid = ?";
					+ "ORDER BY mid";

			pstmt = conn.prepareStatement(sql);
			
			// 데이터 바인딩 문자열 ? 와 일치하는 자료 누락된 경우
			//java.sql.SQLException: 인덱스에서 누락된 IN 또는 OUT 매개변수:: 1		
//			pstmt.setString(1, mid);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				
				// SELECT 쿼리의 컬럼명과 getXXX() 메소드의 컬럼인덱스가 불일치, 누락
				// -> java.sql.SQLException: 부적합한 열 이름
				String mid_ = rs.getString("mid");  //VARCHAR2(3)
				String name_ = rs.getString("name_"); //NVARCHAR2(5)
				String phone = rs.getString("phone"); //VARCHAR2(15)
				String email = rs.getString("email"); //VARCHAR2(20)
				Date regDate = rs.getDate("regDate"); //DATE

				//주의) 출력시 자료에 대한 적절한 서식을 지정해서 출력한다.
				//날짜, 시간 전용 서식 사용
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
