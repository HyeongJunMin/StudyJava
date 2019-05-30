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
        // ctrl + shift + O �� ������ import �ؾ��ϴ� �׸��� ����.
         
        // 1. �ش� ������ ���̽��� ���� ���̺귯�� ��� �۾�
        // Class.forName("Ŭ������"); // ClassNotFoundException ó���� �ؾ� ��
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
             
            // 2.�����ͺ��̽��� ������  // SQLException ó���� �ؾ��Ѵ�.
            conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "scott", "tiger");
             
            System.out.println(conn);
             
            // 3. ������ �ۼ� �� DB���� ������ �����Ű�� ����� ������ ��
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
                // 4. DB�� ���õ� ��ü�� �ݵ�� close �ؾ� ��
                rset.close();
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
         
    }
}
