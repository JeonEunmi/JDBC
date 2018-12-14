package com.emp.persistance;

import java.sql.*;
import java.util.*;

import com.emp.connection.OracleConnection;
import com.emp.domain.Departments;

public class DepartmentDAO {


	// 입력
	public int depAdd(Departments dep) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		int rs = 0;

		try {
			conn = OracleConnection.connect();
			String sql = "INSERT INTO departments (deptId, dept_name)\r\n" + 
					"    VALUES ((SELECT CONCAT('DEPT', LPAD(NVL(SUBSTR(MAX(deptId), 5), 0) + 1, 2, 0)) \r\n" + 
					"	AS newId FROM departments), ?)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, dep.getDepName());

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

	
	// 출력
	public List<Departments> list1() {

		Connection conn = null;
		PreparedStatement pstmt = null;

		List<Departments> d = new ArrayList<Departments>();

		try {

			conn = OracleConnection.connect();

			String sql = "SELECT deptId, dept_name\r\n"
					+ "		    FROM departments\r\n"
					+ "		    ORDER BY deptId";

			pstmt = conn.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery(sql);

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
	
	
	
	// 출력2
	public List<Departments> list2() {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		List<Departments> d = new ArrayList<Departments>();
		
		try {
			
			conn = OracleConnection.connect();
			
			String sql = "SELECT deptId, dept_name, (SELECT COUNT(*) FROM employees e WHERE d.deptid = e.deptid) count\r\n"
					+ "		    FROM departments d\r\n"
					+ "		    ORDER BY deptId";
			
			pstmt = conn.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery(sql);
			
			while (rs.next()) {
				
				String deptId = rs.getString("deptId");
				String deptName = rs.getString("dept_name");
				int count = rs.getInt("count");
				
				d.add(new Departments(deptId, deptName, count));
				
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
