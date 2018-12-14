package com.emp.persistance;

import java.sql.*;
import java.util.*;

import com.emp.connection.OracleConnection;
import com.emp.domain.Departments;
import com.emp.domain.Regions;

public class DepartmentDAO {

	List<Departments> d = new ArrayList<Departments>();

	// 입력
	public int depAdd() {

		return 0;
	}

	// 출력
	public List<Departments> list() {

		Connection conn = null;
		PreparedStatement pstmt = null;

		List<Departments> d = new ArrayList<Departments>();

		try {

			conn = OracleConnection.connect();

			String sql = "SELECT deptId, dept_name\r\n"
					+ "		    FROM departments\r\n"
					+ "		    ORDER BY deptId";

			pstmt = conn.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				String deptId = rs.getString("deptId");
				String deptName = rs.getString("dept_name");

				d.add(new Departments(deptId, deptName));

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

		return d;
	}

	// 삭제
	public int depDelete(String did) {

		Connection conn = null;
		PreparedStatement pstmt = null;

		int rs = 0;

		try {
			conn = OracleConnection.connect();
			String sql = "DELETE FROM departments \r\n" 
			+ "    WHERE UPPER(deptid) = UPPER(?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, did);
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

}
