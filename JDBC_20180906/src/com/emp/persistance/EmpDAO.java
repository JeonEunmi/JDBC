package com.emp.persistance;

import java.util.*;
import java.sql.*;

import com.emp.connection.OracleConnection;
import com.emp.domain.Employees;

public class EmpDAO {

	// 입력
	public int employeeAdd(Employees emp) {

		/*
		 * INSERT INTO employees(empId, name_, ssn, hiredate, phone, regId, deptId,
		 * jobId, basicpay, extrapay) VALUES ((SELECT CONCAT('EMP',
		 * LPAD(NVL(SUBSTR(MAX(empId), 4), 0) + 1, 3, 0)) AS newId FROM employees), ?,
		 * ?, ?, ?, ?, ?, ?, ?, ?);
		 * 
		 */

		Connection conn = null;
		PreparedStatement pstmt = null;
		int rs = 0;

		try {
			conn = OracleConnection.connect();
			String sql = "INSERT INTO employees(empId, name_, ssn, hiredate, phone, regId, deptId, jobId, basicpay, extrapay) \r\n"
					+ "    VALUES ((SELECT CONCAT('EMP', LPAD(NVL(SUBSTR(MAX(empId), 4), 0) + 1, 3, 0)) AS newId FROM employees)\r\n"
					+ "    , ?, ?, ?, ?\r\n" + "    , (SELECT regId FROM regions WHERE reg_name = ?)\r\n"
					+ "    , (SELECT deptId FROM departments WHERE dept_name = ?)\r\n"
					+ "    , (SELECT jobId FROM jobs WHERE job_title = ?) \r\n" + "    , ?, ?)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, emp.getName());
			pstmt.setString(2, emp.getSsn());
			pstmt.setString(3, emp.getHireDate());
			pstmt.setString(4, emp.getPhone());
			pstmt.setString(5, emp.getRegionName());
			pstmt.setString(6, emp.getDepName());
			pstmt.setString(7, emp.getJobName());
			pstmt.setInt(8, emp.getBasicPay());
			pstmt.setInt(9, emp.getExtraPay());
			rs = pstmt.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				if (pstmt != null)
					pstmt.close(); // 쿼리문이 여러가지일 경우 pstmt를 여러개 생성
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

	// 전체 출력
	public List<Employees> list1(int key) {

		/*
		 * SELECT * FROM employees OREDER BY ?;
		 * 
		 * if문 - 1.사번정렬 2.이름정렬 3.지역명정렬 4.부서명정렬 5.직위명정렬
		 */

		Connection conn = null;
		PreparedStatement pstmt = null;

		List<Employees> emp = new ArrayList<Employees>();

		try {

			// conn.setAutoCommit(false); -> 트랜잭션 사용 시 사용
			conn = OracleConnection.connect();

			String tmp = "SELECT empid, name_, ssn, TO_CHAR(hiredate, 'YYYY-MM-DD') hiredate, phone, reg_name, dept_name, job_title\r\n"
					+ "    , basicpay, extrapay, pay \r\n" 
					+ "    FROM empView1 \r\n";
			String sql = null;

			if (key == 1) {
				sql = tmp + "ORDER BY empid";
				System.out.println(sql);
			} else if (key == 2) {
				sql = tmp + "ORDER BY name_";
			} else if (key == 3) {
				sql = tmp + "ORDER BY reg_name";
			} else if (key == 4) {
				sql = tmp + "ORDER BY dept_name";
			} else if (key == 5) {
				sql = tmp + "ORDER BY job_title";
			}

			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				String empid = rs.getString("empid");
				String name_ = rs.getString("name_");
				String ssn = rs.getString("ssn");
				String hiredate = rs.getString("hiredate");
				String phone = rs.getString("phone");
				String reg_name = rs.getString("reg_name");
				String dept_name = rs.getString("dept_name");
				String job_title = rs.getString("job_title");
				int basicpay = rs.getInt("basicpay");
				int extrapay = rs.getInt("extrapay");
				int pay = rs.getInt("pay");

				emp.add(new Employees(empid, name_, ssn, hiredate, phone, reg_name, dept_name, job_title, basicpay,
						extrapay, pay));
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

		return emp;

	}

	// 검색 출력
	public List<Employees> list2(int key, String value) {

		/*
		 * SELECT * FROM employees WHERE UPPER() = UPPER ? OREDER BY ?;
		 * 
		 * if문 - 1.사번검색 2.이름검색 3.지역명검색 4.부서명검색 5.직위명검색
		 */
		Connection conn = null;
		PreparedStatement pstmt = null;

		List<Employees> emp = new ArrayList<Employees>();

		try {

			// conn.setAutoCommit(false); -> 트랜잭션 사용 시 사용
			conn = OracleConnection.connect();

			String tmp = "SELECT empid, name_, ssn, TO_CHAR(hiredate, 'YYYY-MM-DD') hiredate, phone, reg_name, dept_name, job_title\r\n"
					+ "    , basicpay, extrapay, pay \r\n" + "    FROM empView1 \r\n";

			String sql = null;

			if (key == 1) {
				sql = tmp + "WHERE UPPER(empid) = UPPER(?)";
			} else if (key == 2) {
				sql = tmp + "WHERE INSTR(UPPER(name_), UPPER(?)) > 0";
			} else if (key == 3) {
				sql = tmp + "WHERE INSTR(UPPER(reg_name), UPPER(?)) > 0";
			} else if (key == 4) {
				sql = tmp + "WHERE INSTR(UPPER(dept_name), UPPER(?)) > 0";
			} else if (key == 5) {
				sql = tmp + "WHERE INSTR(UPPER(job_title), UPPER(?)) > 0";
			}

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, value);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				String empid = rs.getString("empid");
				String name_ = rs.getString("name_");
				String ssn = rs.getString("ssn");
				String hiredate = rs.getString("hiredate");
				String phone = rs.getString("phone");
				String reg_name = rs.getString("reg_name");
				String dept_name = rs.getString("dept_name");
				String job_title = rs.getString("job_title");
				int basicpay = rs.getInt("basicpay");
				int extrapay = rs.getInt("extrapay");
				int pay = rs.getInt("pay");

				emp.add(new Employees(empid, name_, ssn, hiredate, phone, reg_name, dept_name, job_title, basicpay,
						extrapay, pay));
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

		return emp;
	}

	// 직원 삭제
	public int empDelete(String eid) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int rs = 0;
		
		try {
			conn = OracleConnection.connect();
			String sql = "DELETE FROM employees \r\n" + 
					"    WHERE UPPER(empid) = UPPER(?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, eid);
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
