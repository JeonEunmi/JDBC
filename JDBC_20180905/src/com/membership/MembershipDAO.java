package com.membership;

import java.sql.*;
import java.sql.Date;
import java.util.*;

import com.test.connection.OracleConnection;

public class MembershipDAO {

	List<Member> member = new ArrayList<Member>();

	public MembershipDAO() {

	}

	// 회원정보입력
	public int membershipAdd(Member m) {

		Connection conn = null;
		PreparedStatement pstmt = null;

		int rs = 0;

		try {

			conn = OracleConnection.connect();

			String sql = "INSERT INTO membership (mid, name_, phone, email, regDate, feeTotal)\r\n"
					+ "	VALUES ((SELECT CONCAT('M', LPAD(NVL(SUBSTR(MAX(mid), 2), 0) + 1, 2, 0)) AS newId FROM membership)\r\n"
					+ "		, ?, ?, ?, SYSDATE, 0)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, m.getName());
			pstmt.setString(2, m.getPhone());
			pstmt.setString(3, m.getEmail());

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
				OracleConnection.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

		return rs;

	}

	// 전체회원 출력
	public List<Member> membershipPrint() {

		Connection conn = null;
		PreparedStatement pstmt = null;

		List<Member> m = new ArrayList<Member>();

		try {

			conn = OracleConnection.connect();

			String sql = "SELECT mid, name_, phone, email, TO_CHAR(regDate, 'YYYY-MM-DD') regDate \r\n"
					+ "    , feeTotal \r\n"
					+ "    , (SELECT COUNT(*) FROM membershipFee WHERE mid = m.mid) feeCount \r\n"
					+ "	FROM membership m \r\n" + "	ORDER BY mid";

			pstmt = conn.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				String mid = rs.getString("mid");
				String name_ = rs.getString("name_");
				String phone = rs.getString("phone");
				String email = rs.getString("email");
				Date regDate = rs.getDate("regDate");
				int feeTotal = rs.getInt("feeTotal");
				int feeCount = rs.getInt("feeCount");

				m.add(new Member(mid, name_, phone, email, regDate, feeTotal, feeCount));

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
				OracleConnection.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

		return m;

	}

	//회원 검색
	public List<Member> membershipSearch(int key, String value) {

		List<Member> m = new ArrayList<Member>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;

		String temp = "SELECT mid, name_, phone, email, TO_CHAR(regDate, 'YYYY-MM-DD') regDate \r\n"
				+ "    , feeTotal \r\n" 
				+ "    , (SELECT COUNT(*) FROM membershipFee WHERE mid = m.mid) feeCount \r\n"
				+ "	FROM membership m \r\n";

		try {

			conn = OracleConnection.connect();

			if (key == 1) {
				sql = temp + "WHERE UPPER(mid) = UPPER(?)";
			}

			if (key == 2) {
				sql = temp + "WHERE INSTR(UPPER(name_), UPPER(?)) > 0";
			}

			if (key == 3) {
				sql = temp + "WHERE INSTR(phone,?) > 0";
			}

			if (key == 4) {
				sql = temp + "WHERE INSTR(LOWER(email),LOWER(?)) > 0";
			}

			if (key == 5) {
				sql = temp + "WHERE INSTR(TO_CHAR(regDate, 'YYYY-MM-DD'), ?) > 0";
			}

			sql = sql + "ORDER BY mid";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, value);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				String mid = rs.getString("mid");
				String name_ = rs.getString("name_");
				String phone = rs.getString("phone");
				String email = rs.getString("email");
				Date regDate = rs.getDate("regDate");
				int feeTotal = rs.getInt("feeTotal");
				int feeCount = rs.getInt("feeCount");

				m.add(new Member(mid, name_, phone, email, regDate, feeTotal, feeCount));

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
				OracleConnection.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

		return m;
	}

	// 회원별 회비 납부 내역 출력 메소드
	public List<Member> memberFeePrint(String value){
		
		List<Member> m = new ArrayList<Member>();

		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "SELECT mid, fee, paymentDate\r\n" + 
				"	FROM membershipFee\r\n" + 
				"	WHERE UPPER(mid) = UPPER(?) \r\n" + 
				"	ORDER BY paymentDate";

		try {

			conn = OracleConnection.connect();	

			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, value);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				String mid = rs.getString("mid");
				int fee = rs.getInt("fee");
				Date paymentDate = rs.getDate("paymentdate");

				m.add(new Member(mid, fee, paymentDate));

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
				OracleConnection.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

		return m;
		
	}

	
	//전체회원 회비 납부 내역 출력
	public List<Member> memberFeePrintAll(){
		
		List<Member> m = new ArrayList<Member>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "SELECT m1.mid, name_, phone, fee, paymentdate\r\n" + 
				"    FROM membership m1, membershipfee m2\r\n" + 
				"    WHERE m1.mid = m2.mid(+)\r\n"
				+ "ORDER BY mid";
		
		try {
			
			conn = OracleConnection.connect();	
			
			pstmt = conn.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				String mid = rs.getString("mid");
				String name = rs.getString("name_");
				String phone = rs.getString("phone");
				int fee = rs.getInt("fee");
				Date paymentDate = rs.getDate("paymentdate");
				
				m.add(new Member(mid, name, phone, fee, paymentDate));
				
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
				OracleConnection.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		
		return m;
		
	}
	
	//회원삭제
	public int membershipDelete(String mid) {

		Connection conn = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;

		int rs = 0;

		try {


			conn = OracleConnection.connect();

			
			conn.setAutoCommit(false);

			
			String sql1 = "DELETE FROM membership\r\n" + 
					"    WHERE UPPER(mid) = UPPER(?)";


			pstmt1 = conn.prepareStatement(sql1);

			pstmt1.setString(1, mid);

			rs = pstmt1.executeUpdate();
			
		
			
			String sql2 = "DELETE FROM membershipfee\r\n" + 
					"    WHERE UPPER(mid) = UPPER(?)";
			
			pstmt2 = conn.prepareStatement(sql2);
			
			pstmt2.setString(1, mid);
			
			rs = pstmt2.executeUpdate();


			conn.commit();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();

			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

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
			} catch (SQLException se2) {
				se2.printStackTrace();
			}

			try {
				OracleConnection.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

		return rs;
	}

	//회비납부
	public int membershipFee(Member m) {

		Connection conn = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;

		int rs = 0;

		try {

			conn = OracleConnection.connect();

			conn.setAutoCommit(false);

			String sql1 = "INSERT INTO membershipFee(mid, fee, paymentDate)\r\n" + 
					"    VALUES (UPPER(?), ?, SYSDATE)";
			
			pstmt1 = conn.prepareStatement(sql1);

			pstmt1.setString(1, m.getMid());
			pstmt1.setInt(2, m.getFee());
			rs = pstmt1.executeUpdate();
	
			
			String sql2 = "UPDATE membership \r\n" + 
					"    SET feeTotal = feeTotal + ? \r\n" + 
					"    WHERE UPPER(mid) = UPPER(?)";

			pstmt2 = conn.prepareStatement(sql2);
			
			pstmt2.setInt(1, m.getFee());
			pstmt2.setString(2, m.getMid());
			rs = pstmt2.executeUpdate();
			
			conn.commit();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

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
		return rs;
		
		
		
	}

}
