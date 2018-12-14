package com.test.scores;

import java.sql.*;
import java.util.*;

import com.test.connection.OracleConnection;

//(데이터베이스) 저장소 운영 클래스
public class ScoreDAO {

	List<Score> score = new ArrayList<Score>();

	public ScoreDAO() {

	}

	public int scoreAdd(Score score) {

		Connection conn = null;
		PreparedStatement pstmt = null;

		int rs = 0;

		try {

			conn = OracleConnection.connect();

			String sql = "INSERT INTO scores (sid_, name_, subject1, subject2, subject3)\r\n" + 
					"	VALUES ((SELECT CONCAT('S', LPAD(NVL(SUBSTR(MAX(sid_), 2), 0) + 1, 2, 0)) AS newId FROM scores)\r\n" + 
					"		, ?, ?, ?, ?)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, score.getName());
			pstmt.setInt(2, score.getSubject1());
			pstmt.setInt(3, score.getSubject2());
			pstmt.setInt(4, score.getSubject3());

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

	public List<Score> scorePrint() {

		Connection conn = null;
		PreparedStatement pstmt = null;

		List<Score> score = new ArrayList<Score>();

		try {

			conn = OracleConnection.connect();

			String sql = "SELECT sid_, name_, subject1, subject2, subject3, sumSub, avgSub, rank\r\n" + 
					"            FROM (SELECT sid_, name_, subject1, subject2, subject3, (sumSub / 3) AS avgSub, sumSub, RANK() OVER(ORDER BY sumSub DESC) rank\r\n" + 
					"                FROM (SELECT sid_, name_, subject1, subject2, subject3, (subject1 + subject2 + subject3) AS sumSub\r\n" + 
					"                    FROM scores)) \r\n"
					+ "ORDER BY sid_";

			pstmt = conn.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				
				String sid = rs.getString("sid_");
				String name_ = rs.getString("name_");
				int subject1 = rs.getInt("subject1");
				int subject2 = rs.getInt("subject2");
				int subject3 = rs.getInt("subject3");
				int sumSub = rs.getInt("sumSub");
				double avgSub = rs.getDouble("avgSub");
				int rank = rs.getInt("rank");

				score.add(new Score(sid, name_, subject1, subject2, subject3, sumSub, avgSub, rank));

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

		return score;

	}

	public List<Score> scoreSearch(int key, String value) {

		List<Score> score = new ArrayList<Score>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;

		String temp = "SELECT sid_, name_, subject1, subject2, subject3, sumSub, avgSub, rank\r\n" + 
				"            FROM (SELECT sid_, name_, subject1, subject2, subject3, (sumSub / 3) AS avgSub, sumSub, RANK() OVER(ORDER BY sumSub DESC) rank\r\n" + 
				"                FROM (SELECT sid_, name_, subject1, subject2, subject3, (subject1 + subject2 + subject3) AS sumSub\r\n" + 
				"                    FROM scores)) ";

		

		try {

			conn = OracleConnection.connect();

			if (key == 1) {
				sql = temp + "WHERE UPPER(sid_) = UPPER(?)";
			}

			if (key == 2) {
				sql = temp + "WHERE UPPER(name_) = UPPER(?)";
			}

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, value);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				String sid = rs.getString("sid_");
				String name_ = rs.getString("name_");
				int subject1 = rs.getInt("subject1");
				int subject2 = rs.getInt("subject2");
				int subject3 = rs.getInt("subject3");
				int sumSub = rs.getInt("sumSub");
				double avgSub = rs.getDouble("avgSub");
				int rank = rs.getInt("rank");

				score.add(new Score(sid, name_, subject1, subject2, subject3, sumSub, avgSub, rank));

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

		return score;
	}

}
