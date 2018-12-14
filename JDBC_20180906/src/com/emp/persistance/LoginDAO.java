package com.emp.persistance;

import java.sql.*;

import com.emp.connection.OracleConnection;
import com.emp.domain.Login;

public class LoginDAO {

	// 출력
	public int login(Login l) {

		
		Connection conn = null;
        PreparedStatement pstmt = null;
        int count = 0;

        try {
 
            conn = OracleConnection.connect();
 
            String sql = "SELECT COUNT(*) AS count \r\n" + 
            		"		    FROM login \r\n" + 
            		"		    WHERE UPPER(id_)=UPPER(?) AND pw_=?";
 
            pstmt = conn.prepareStatement(sql);
 
            pstmt.setString(1, l.getId());
            pstmt.setString(2, l.getPw());

            ResultSet rs = pstmt.executeQuery(); 
            
            while (rs.next()) {

                count = rs.getInt("count"); 
                
            }

 
            rs.close();
            //conn.commit();       -> 트랜잭션 사용 시 사용
 
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            //conn.rollback();       -> 트랜잭션 사용 시 사용
 
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

		return 1;
		
	}
}
