package com.emp.persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.emp.connection.OracleConnection;
import com.emp.domain.Departments;
import com.emp.domain.Jobs;

public class JobDAO {

	List<Jobs> j = new ArrayList<Jobs>();
	
	// �Է�
	public int jobAdd() {
		
		return 0;
	}
	
	
	// ���
	public List<Jobs> list(){
		

		Connection conn = null;
        PreparedStatement pstmt = null;
 
        List<Jobs> j = new ArrayList<Jobs>();
 
        try {
 
            conn = OracleConnection.connect();
 
            String sql = "SELECT jobId, job_name, min_basicPay\r\n" + 
            		"		    FROM jobs\r\n" + 
            		"		    ORDER BY jobId";
 
            pstmt = conn.prepareStatement(sql);
 
            ResultSet rs = pstmt.executeQuery();
 
            while (rs.next()) {
 
                String jobId = rs.getString("jobId");
                String jobName = rs.getString("job_name");
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
                OracleConnection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

		
		
		return j;
	}
	
	// ����
	public int jobDelete() {
		
		return 0;
	}
	
}
