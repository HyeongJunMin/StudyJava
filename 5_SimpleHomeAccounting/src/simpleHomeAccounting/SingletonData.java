package simpleHomeAccounting;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SingletonData {
	private static SingletonData single = null;
	
	public static List<AccountingVO> data;
	public static Map<LocalDate, AccountingVO> acc;
	public static File f;
	
	public static int count = 0;
	public static Connection con = null;
	public static Statement stmt = null;
	public static String user = "minhj";
	public static String pw = "1234";
	public static String url = "jdbc:oracle:thin:@localhost:1521:studyDB";
	
	private SingletonData() {
		acc = new HashMap<>();
		data = new ArrayList<AccountingVO>();
		f = new File("d:\\tmp\\acc.txt");
		dbConnection();
	}
	
	private static void dbConnection() {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, user, pw);

			stmt = con.createStatement();
			
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("½ÇÆÐ");
        } 
	}
	
	public static void cutDbConnection() {
		try {
			if( stmt != null)	stmt.close();
			if( con != null)	con.close();
		} catch (Exception e) {		e.printStackTrace();	}
	}
	
	public static SingletonData getInstance() {
		if( single == null ) {
			single = new SingletonData();
		}
		return single;
	}
}
