package com.emp.persistance;

import java.sql.*;
import java.util.*;

import com.emp.connection.OracleConnection;
import com.emp.domain.Regions;

//(������ ���̽�) ����� � Ŭ����
public class RegionDAO {

	
	// ���1
	public List<Regions> list1(){
		
		/*
		SELECT regId, reg_name
		    FROM regions
		    ORDER BY regId;
		*/
		
		Connection conn = null;
        PreparedStatement pstmt = null;
 
        List<Regions> r = new ArrayList<Regions>();
 
        try {
 
            conn = OracleConnection.connect();
 
            String sql = "SELECT regId, reg_name\r\n" + 
            		"		    FROM regions\r\n" + 
            		"		    ORDER BY regId";
 
            pstmt = conn.prepareStatement(sql);
 
            ResultSet rs = pstmt.executeQuery(sql);
 
            while (rs.next()) {
 
                String regId = rs.getString("regId");
                String regName = rs.getString("reg_name");
 
 
                r.add(new Regions(regId, regName));
 
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

		return r;
	}
	
	// ���2
	public List<Regions> list2(){
		
		/*
		SELECT regId, reg_name, (SELECT COUNT(*) FROM employees e WHERE r.regid = e.regid) count
		    FROM regions r
		    ORDER BY regId;
		*/
		
		Connection conn = null;
        PreparedStatement pstmt = null;
 
        List<Regions> r = new ArrayList<Regions>();
 
        try {
 
            conn = OracleConnection.connect();
 
            String sql = "SELECT regId, reg_name, (SELECT COUNT(*) FROM employees e WHERE r.regid = e.regid) count\r\n" + 
            		"		    FROM regions r\r\n" + 
            		"		    ORDER BY regId";
 
            pstmt = conn.prepareStatement(sql);
 
            ResultSet rs = pstmt.executeQuery(sql);
 
            while (rs.next()) {
 
                String regId = rs.getString("regId");
                String regName = rs.getString("reg_name");
                int count = rs.getInt("count");
 
 
                r.add(new Regions(regId, regName, count));
 
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

		return r;
		
	}
	
	
	// �Է�
	public int regionAdd(Regions r) {
	
		/*
		INSERT INTO regions(regid, reg_name)
		    VALUES ((SELECT CONCAT('REG', LPAD(NVL(SUBSTR(MAX(regid), 4), 0) + 1, 2, 0)) AS newId FROM regions), ?); 
		*/

		Connection conn = null;
		PreparedStatement pstmt = null;
		int rs = 0;

		try {
			conn = OracleConnection.connect();
			String sql = "INSERT INTO regions(regid, reg_name)\r\n" + 
					"		    VALUES ((SELECT CONCAT('REG', LPAD(NVL(SUBSTR(MAX(regid), 4), 0) + 1 \r\n"
					+ ", 2, 0)) AS newId FROM regions), ?)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, r.getRegName());
			rs = pstmt.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				if (pstmt != null)
					pstmt.close(); // �������� ���������� ��� pstmt�� ������ ����
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
	
	
	// ����
	public int regionDelete(String r) {
		
		/*
		DELETE FROM regions r
		    WHERE UPPER(regId) = UPPER('REG04')
			AND (SELECT COUNT(*) FROM employees e WHERE r.regid = e.regid) = 0;
		*/
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int rs = 0;
		
		try {
			conn = OracleConnection.connect();
			String sql = "DELETE FROM regions r\r\n" + 
					"		    WHERE UPPER(regId) = UPPER(?)\r\n" + 
					"			AND (SELECT COUNT(*) FROM employees e WHERE r.regid = e.regid) = 0";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, r);
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
