package oracleDBTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCModel {
	public void testJDBC() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rset = null;
        // ctrl + shift + O 를 누르면 import 해야하는 항목이 나옴.
         
        // 1. 해당 데이터 베이스에 대한 라이브러리 등록 작업
        // Class.forName("클래스명"); // ClassNotFoundException 처리를 해야 함
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
             
            // 2.데이터베이스와 연결함  // SQLException 처리를 해야한다.
            conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "scott", "tiger");
             
            System.out.println(conn);
             
            // 3. 쿼리문 작성 후 DB에서 쿼리문 실행시키고 결과를 가지고 옴
            String query = "SELECT * FROM EMP";
             
            stmt = conn.createStatement();
             
            rset = stmt.executeQuery(query);
             
            while (rset.next()) {
                System.out.println(rset.getInt("EMPNO") + ", " +
                                    rset.getString("ENAME") + ", " +
                                    rset.getString("JOB") + ", " +
                                    rset.getInt("MGR") + ", " +
                                    rset.getDate("HIREDATE") + ", " +
                                    rset.getInt("SAL") + ", " +
                                    rset.getInt("COMM") + ", " +
                                    rset.getInt("DEPTNO")
                        );
                 
            }
 
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                // 4. DB와 관련된 객체는 반드시 close 해야 함
                rset.close();
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
         
    }
}
