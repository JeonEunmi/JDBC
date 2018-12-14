package com.emp.persistance;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.connection.MySQLConnection;
import com.emp.domain.Employees;


public class EmpDAO {
	
	
	/*
	CREATE OR REPLACE VIEW empView
	AS
	SELECT empId, name_, ssn, hiredate, phone
	    , reg_name, dept_name, job_title, basicpay, extrapay
	    , (basicpay + extrapay) pay
	    FROM employees E, departments D, jobs j, regions R
	    WHERE E.deptid = D.deptid
	        AND E.jobid = j.jobid
	        AND E.regid = R.regid
	WITH READ ONLY;

	    
	SELECT empId, name_, ssn, hiredate, phone
	    , reg_name, dept_name, job_title, basicpay, extrapay,  pay
	    FROM empView
	    ORDER BY empId;
	*/
	

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
			conn = MySQLConnection.connect();
			String sql = "INSERT INTO employees(empId, name_, ssn, hiredate, phone, regId, deptId, jobId, basicpay, extrapay)\r\n" + 
					"	 VALUES ((SELECT IFNULL(SUBSTR(MAX(empId), 1), 0) + 1 AS newId FROM employees e)\r\n" + 
					"	, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, emp.getName());
			pstmt.setString(2, emp.getSsn());
			pstmt.setDate(3, emp.getHireDate());
			pstmt.setString(4, emp.getPhone());
			pstmt.setString(5, emp.getRegId());
			pstmt.setString(6, emp.getDeptId());
			pstmt.setString(7, emp.getJobId());
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
				MySQLConnection.close();
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
			conn = MySQLConnection.connect();

			String sql = "SELECT empid, name_, ssn, hiredate, phone, reg_name, dept_name, job_title\r\n"
					+ "    , basicpay, extrapay, pay \r\n" 
					+ "    FROM empView \r\n";

			if (key == 1) {
				sql += "ORDER BY empid";
			} else if (key == 2) {
				sql += "ORDER BY name_";
			} else if (key == 3) {
				sql += "ORDER BY reg_name";
			} else if (key == 4) {
				sql += "ORDER BY dept_name";
			} else if (key == 5) {
				sql += "ORDER BY job_title";
			}

			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery(sql);

			while (rs.next()) {

				String empid = rs.getString("empid");
				String name_ = rs.getString("name_");
				String ssn = rs.getString("ssn");
				Date hiredate = rs.getDate("hiredate");
				String phone = rs.getString("phone");
				String reg_name = rs.getString("reg_name");
				String dept_name = rs.getString("dept_name");
				String job_title = rs.getString("job_title");
				int basicpay = rs.getInt("basicpay");
				int extrapay = rs.getInt("extrapay");
				int pay = rs.getInt("pay");

				emp.add(new Employees(empid, name_, ssn, hiredate, phone, reg_name, dept_name, job_title, null, null, null, basicpay,
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
				MySQLConnection.close();
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
			
			conn = MySQLConnection.connect();

			String sql = "SELECT empid, name_, ssn, hiredate, phone, reg_name, dept_name, job_title\r\n"
					+ "    , basicpay, extrapay, pay \r\n"
					+ "    FROM empView \r\n";


			if (key == 1) {
				sql += "WHERE empid = ?";
			} else if (key == 2) {
				sql += "WHERE INSTR(UPPER(name_), UPPER(?)) > 0";
			} else if (key == 3) {
				sql += "WHERE INSTR(UPPER(reg_name), UPPER(?)) > 0";
			} else if (key == 4) {
				sql += "WHERE INSTR(UPPER(dept_name), UPPER(?)) > 0";
			} else if (key == 5) {
				sql += "WHERE INSTR(UPPER(job_title), UPPER(?)) > 0";
			}

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, value);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				String empid = rs.getString("empid");
				String name_ = rs.getString("name_");
				String ssn = rs.getString("ssn");
				Date hiredate = rs.getDate("hiredate");
				String phone = rs.getString("phone");
				String reg_name = rs.getString("reg_name");
				String dept_name = rs.getString("dept_name");
				String job_title = rs.getString("job_title");
				int basicpay = rs.getInt("basicpay");
				int extrapay = rs.getInt("extrapay");
				int pay = rs.getInt("pay");

				emp.add(new Employees(empid, name_, ssn, hiredate, phone, reg_name, dept_name, job_title, null, null, null, basicpay,
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
				MySQLConnection.close();
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
			conn = MySQLConnection.connect();
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
				MySQLConnection.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return rs;
		
	}

}
