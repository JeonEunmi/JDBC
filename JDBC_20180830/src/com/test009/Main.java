package com.test009;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class Main {

   public static void main(String[] args) {
      
      Connection conn = null;
      Statement stmt = null;
      try {
         //Properties 컬렉션 테스트
         Properties properties = new Properties();
         String path = Main.class.getResource("database.properties").getPath();
         properties.load(new FileReader(path));
         
         String driver = properties.getProperty("driver");
         String url = properties.getProperty("url");
         String username = properties.getProperty("username");
         String password = properties.getProperty("password");

         // STEP 2: Register JDBC driver
         Class.forName(driver);

         // STEP 3: Open a connection
         System.out.println("Connecting to database...");
         conn = DriverManager.getConnection(url, username, password);
         
         // STEP 4: Execute a query
         // employees, departments 테이블 Left Outer Join 쿼리
         // -> empView로 작성하고, 뷰를 이용한 쿼리 실행
         /*
         CREATE OR REPLACE VIEW empView
         AS
         SELECT ROW_NUMBER() OVER(ORDER BY first_name) rn 
             , employee_id, first_name, last_name, department_name 
         FROM employees e, departments d
         WHERE e.department_id = d.department_id(+);
         */
         String sql = "SELECT rn, employee_id, first_name, last_name, department_name \r\n" + 
               "FROM empView";
         System.out.println("Creating statement...");
         stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql);
         
         // STEP 5: Extract data from result set
         int count = 0;
         while (rs.next()) {
            // Retrieve by column name
            int rn = rs.getInt("rn");
            int employee_id = rs.getInt("employee_id");
            String first_name = rs.getString("first_name");
            String last_name = rs.getString("last_name");
            String department_name = rs.getString("department_name");
            
            ++count;
            // Display values
            System.out.printf("%s / %s / %s / %s / %s %n"
                  , rn
                  , employee_id, first_name, last_name
                  , department_name);
         }
         System.out.println("---------");
         System.out.printf("총 %s건%n", count);
         
         // STEP 6: Clean-up environment
         rs.close();
         
         
         
      } catch (IOException | ClassNotFoundException | SQLException e) {
         e.printStackTrace();
      } finally {
         try {
            //STEP 6: Clean-up environment
            if (stmt != null)
               stmt.close();
         } catch (SQLException se) {
            se.printStackTrace();
         }
         try {
            //STEP 6: Clean-up environment
            if (conn != null)
               conn.close();
         } catch (SQLException se) {
            se.printStackTrace();
         }
      }


   }

}