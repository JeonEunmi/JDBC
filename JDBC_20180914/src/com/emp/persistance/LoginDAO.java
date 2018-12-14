package com.emp.persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.connection.MySQLConnection;
import com.emp.domain.Login;


public class LoginDAO {

	// 출력
	public int login(Login l) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0;

		try {

			conn = MySQLConnection.connect();

			String sql = "SELECT COUNT(*) AS count \r\n" + "		    FROM login \r\n"
					+ "		    WHERE UPPER(id)=UPPER(?) AND pw=?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, l.getId());
			pstmt.setString(2, l.getPw());

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				count = rs.getInt("count");

			}

			rs.close();

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
				MySQLConnection.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

		return count;

	}

	// 관리자 추가 메소드
	public int add(Login l) {
		// 주의) id_컬럼은 PK 제약이 지정된 상태
		/*
		 * INSERT INTO login (id_, pw_) VALUES ('TEST', '1234');
		 */
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int rs = 0;
		
		try {
			conn = MySQLConnection.connect();
			
			String sql = "INSERT INTO login (id, pw) \r\n"
					+ "VALUES (?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, l.getId());
			pstmt.setString(2, l.getPw());
			rs = pstmt.executeUpdate();
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
				MySQLConnection.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return rs;

	}

	// 패스워드 변경 메소드
	public int modify(Login l) {

		/*
		 * UPDATE login SET pw_ = '1111' WHERE UPPER(id_) = UPPER('TEST') AND pw_ =
		 * '1234';
		 */
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int rs = 0;
		
		try {
			conn = MySQLConnection.connect();
			
			String sql = "UPDATE login SET pw = ? \r\n"
					+ "WHERE UPPER(id) = UPPER(?) AND pw = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, l.getNewPw());
			pstmt.setString(2, l.getId());
			pstmt.setString(3, l.getPw());
			
			rs = pstmt.executeUpdate();
			
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
				MySQLConnection.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return rs;

	}
		
		
}
