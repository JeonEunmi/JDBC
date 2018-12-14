package com.emp.persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.connection.MySQLConnection;
import com.emp.domain.Regions;


//(데이터 베이스) 저장소 운영 클래스
public class RegionDAO {

	
	// 출력1
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
 
            conn = MySQLConnection.connect();
 
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
            	MySQLConnection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

		return r;
	}
	
	// 출력2
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
 
            conn = MySQLConnection.connect();
 
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
            	MySQLConnection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

		return r;
		
	}
	
	
	// 입력
	public int regionAdd(Regions r) {
	
		/*
		INSERT INTO regions(regid, reg_name)
		    VALUES ((SELECT CONCAT('REG', LPAD(NVL(SUBSTR(MAX(regid), 4), 0) + 1, 2, 0)) AS newId FROM regions), ?); 
		*/

		Connection conn = null;
		PreparedStatement pstmt = null;
		int rs = 0;

		try {
			conn = MySQLConnection.connect();
			String sql = "INSERT INTO regions(regid, reg_name)\r\n" + 
					"		    VALUES ((SELECT CONCAT('REG', LPAD(IFNULL(SUBSTR(MAX(regid), 4), 0) + 1 \r\n"
					+ ", 2, 0)) AS newId FROM regions r), ?)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, r.getRegName());
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
	
	
	// 삭제
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
			conn = MySQLConnection.connect();
			String sql = "DELETE FROM regions \r\n" + 
					"		    WHERE UPPER(regId) = UPPER(?)";

			
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
				MySQLConnection.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return rs;
		
	}
}
