package com.emp.persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.connection.MySQLConnection;
import com.emp.domain.Jobs;

public class JobDAO {


	// 입력
	public int jobAdd(Jobs job) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rs = 0;

		try {
			conn = MySQLConnection.connect();
			String sql = "INSERT INTO jobs (jobId, job_title, min_basicPay)\r\n" + 
					"    VALUES ((SELECT CONCAT('JOB', LPAD(IFNULL(SUBSTR(MAX(jobId), 4), 0) + 1, 2, 0)) \r\n" + 
					"        AS newId FROM jobs j), ?, ?)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, job.getJobTitle());
			pstmt.setInt(2, job.getMinPay());

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
	
	
	
	// 출력1
	public List<Jobs> list1(){
		

		Connection conn = null;
        PreparedStatement pstmt = null;
 
        List<Jobs> j = new ArrayList<Jobs>();
 
        try {
 
            conn = MySQLConnection.connect();
 
            String sql = "SELECT jobId, job_title, min_basicPay\r\n" + 
            		"		    FROM jobs\r\n" + 
            		"		    ORDER BY jobId";
 
            pstmt = conn.prepareStatement(sql);
 
            ResultSet rs = pstmt.executeQuery();
 
            while (rs.next()) {
 
                String jobId = rs.getString("jobId");
                String jobName = rs.getString("job_title");
                int minPay = rs.getInt("min_basicPay");
 
 
                j.add(new Jobs(jobId, jobName, minPay));
 
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

		
		
		return j;
	}
	
	
	public List<Jobs> list2(){
		
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		List<Jobs> j = new ArrayList<Jobs>();
		
		try {
			
			conn = MySQLConnection.connect();
			
			String sql = "SELECT jobId, job_title, min_basicPay \r\n"
					+ ", (SELECT COUNT(*) FROM employees e WHERE j.jobid = e.jobid) count\r\n" + 
					"		    FROM jobs j\r\n" + 
					"		    ORDER BY jobId";
			
			pstmt = conn.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				String jobId = rs.getString("jobId");
				String jobName = rs.getString("job_title");
				int minPay = rs.getInt("min_basicPay");
				int count = rs.getInt("count");
				
				
				j.add(new Jobs(jobId, jobName, minPay, count));
				
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
		
		
		
		return j;
	}
	
	// 삭제
	public int jobDelete(String jid) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;

		int rs = 0;

		try {
			conn = MySQLConnection.connect();
			String sql = "DELETE FROM jobs \r\n" 
			+ "    WHERE UPPER(jobId) = UPPER(?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, jid);
			
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
