package com.test;

import java.sql.*;

import com.connection.MySQLConnection;

public class Main {

	public static void main(String[] args) {
		
		Connection conn = null;
		
		try {
			
			conn = MySQLConnection.connect();
			
			System.out.println(conn.toString());
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				MySQLConnection.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}

}
