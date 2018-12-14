package com.scores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.connection.MySQLConnection;


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

			conn = MySQLConnection.connect();

			String sql = "INSERT INTO scores (sid, name_, subject1, subject2, subject3)\r\n" + 
					"	VALUES ((SELECT CONCAT('S', LPAD(IFNULL(SUBSTR(MAX(sid), 2), 0) + 1, 2, 0)) AS newId FROM scores s)\r\n" + 
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
				MySQLConnection.close();
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

			conn = MySQLConnection.connect();

			String sql = "SELECT sid, name_, subject1, subject2, subject3, sumSub, avgSub \r\n"
					+ ", (SELECT COUNT(*)+1 FROM score_view sv1 WHERE sv1.sumSub > sv2.sumSub ) AS rank\r\n" + 
					"	FROM score_view sv2";

			pstmt = conn.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				
				String sid = rs.getString("sid");
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
				MySQLConnection.close();
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

		String sql = "SELECT sid, name_, subject1, subject2, subject3, sumSub, avgSub, \r\n"
				+ " (SELECT COUNT(*)+1 FROM score_view sv1 WHERE sv1.sumSub > sv2.sumSub ) AS rank\r\n" + 
				"	FROM score_view sv2 \r\n";
		

		try {

			conn = MySQLConnection.connect();

			if (key == 1) {
				sql += "WHERE UPPER(sid) = UPPER(?)";
			}

			if (key == 2) {
				sql += "WHERE INSTR(name_, ?) > 0";
			}

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, value);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				String sid = rs.getString("sid");
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
				MySQLConnection.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

		return score;
	}

}
