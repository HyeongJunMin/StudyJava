import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
 
public class DBConnection {
	private static Connection con = null;
	
	private static String user = "minhj";
	private static String pw = "1234";
	private static String url = "jdbc:oracle:thin:@localhost:1521:studyDB";
    
    public static void connectionDB() {

		String sql;
		sql ="INSERT INTO 학생 (학번) VALUES(1002)";
		//이름 입학일시 전공 단과대학
		String upd = "UPDATE 학생 SET 이름='만득', 입학일시='2009/03/02', 전공='컴퓨터공학', 단과대학='공대' WHERE 학번=1002";
		
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, user, pw);
//			System.out.println("DB연결 성공!");
			Statement stmt = con.createStatement();
			stmt.executeQuery(upd);
//			System.out.println("업데이트 성공!");
			
			doSelectQuery(stmt);
			
			stmt.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("실패");
        } finally {
            try {
                if( con != null)
                    con.close();
            }catch(Exception e) {
                
            }
        }
    }
    
    //SELECT 쿼리문 예제
    public static void doSelectQuery(Statement s) {
        String slt = "SELECT * FROM 학생";
        ResultSet rs;
        try {
            rs = s.executeQuery(slt);
            
            while( rs.next() ) {
                int num = rs.getInt("학번");
                String name = rs.getString("이름");
                String time = rs.getString("입학일시");
                String major = rs.getString("전공");
                String clg = rs.getString("단과대학");
                System.out.println(num + "\t" + name + "\t" + time + "\t" + major + "\t" + clg);
                //전공, 단과대학
            }
            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public static String[] dbConnectionProperty() {
    	String[] userInfo = new String[3];
    	
        String user = "minhj";
        String pw = "1234";
        String url = "jdbc:oracle:thin:@localhost:1521:studyDB";
        
        userInfo[0] = user;
        userInfo[1] = pw;
        userInfo[2] = url;
    	
    	return userInfo;
    }

    //DB연결 후 SELECT문을 통해 DB데이터를 읽어서 Student객체 List 반환
    public List readDBReturnStudentList() {
    	List<Student> lst = new ArrayList<>();
    	Student std;
    	
    	try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, user, pw);
//			System.out.println("DB연결 성공!");
			Statement stmt = con.createStatement();
//			System.out.println("업데이트 성공!");
			
	        String slt = "SELECT * FROM 학생";
	        ResultSet rs = stmt.executeQuery(slt);
	        
            while( rs.next() ) {

                int num = rs.getInt("학번");
                String name = rs.getString("이름");
                String time = rs.getString("입학일시");
                String major = rs.getString("전공");
                String clg = rs.getString("단과대학");
                
                std = new Student(num, name, time, major, clg);
                
                lst.add(std);
                //전공, 단과대학
            }
			
			stmt.close();
			
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("실패");
        } finally {
            try {
                if( con != null)
                    con.close();
            }catch(Exception e) {
                
            }
        }
    	
    	
    	return lst;
    }

    //DB연결 후 List를 매개변수로 받아 INSERT와 UPDATE를 통해 DB에 List데이터 반영
    public boolean setDBByStudentList(List stdLst) {
    	List<Student> lst = stdLst;
    	Student std;
    	boolean dbHasIt;
    	boolean completed = false;
    	String query;
    	String qValue;
    	
    	try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, user, pw);
//			System.out.println("DB연결 성공!");
			Statement stmt = con.createStatement();
//			System.out.println("업데이트 성공!");
			
			int stdNum = 0;
	        String slt = "SELECT * FROM 학생";
	        ResultSet rs = stmt.executeQuery(slt);
			
	        for( Student s : lst) {
	        	dbHasIt = false;
	        	std = s;
	        	
	        	//학번(Key)을 비교해서 리스트의 값이 현재 DB에 존재하는지 확인
	        	stdNum = std.getNum();
	        	slt = "SELECT 학번 FROM 학생 WHERE 학번="+stdNum;
	        	rs = stmt.executeQuery(slt);
	        	
	        	while(rs.next()) {
	        		if(stdNum == rs.getInt("학번")) {
	        			dbHasIt = true;
	        			break;
	        		}
	        	}
	        	//해당 학번이 있으면? UPDATE
	        	if( dbHasIt ) {
	        		query = "UPDATE 학생 SET 이름='" + std.getName() + "', 입학일시='" + std.getTime() + "', 전공='" + std.getMajor() + "', 단과대학='" + std.getClg() + "' WHERE 학번=" + std.getNum();
	        		stmt.execute(query);
	        	}else {//없으면?? INSERT
	        		query = "INSERT INTO 학생(학번, 이름, 입학일시, 전공, 단과대학) ";
	        		qValue = "VALUES ("+std.getNum() +", '" + std.getName() + "', '" + std.getTime() + "', '" + std.getMajor() + "', '" + std.getClg() +"')";
	        		stmt.execute(query+qValue);
	        	}

	        }	        
	        
			System.out.println("List -> DB Update Complete!");
			stmt.close();
			completed = true;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
			completed = false;
        } finally {
            try {
                if( con != null)
                    con.close();
            }catch(Exception e) {
                
            }
        }
    	
    	return completed;
    }

}