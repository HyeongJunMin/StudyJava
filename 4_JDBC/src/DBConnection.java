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
		sql ="INSERT INTO �л� (�й�) VALUES(1002)";
		//�̸� �����Ͻ� ���� �ܰ�����
		String upd = "UPDATE �л� SET �̸�='����', �����Ͻ�='2009/03/02', ����='��ǻ�Ͱ���', �ܰ�����='����' WHERE �й�=1002";
		
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, user, pw);
//			System.out.println("DB���� ����!");
			Statement stmt = con.createStatement();
			stmt.executeQuery(upd);
//			System.out.println("������Ʈ ����!");
			
			doSelectQuery(stmt);
			
			stmt.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("����");
        } finally {
            try {
                if( con != null)
                    con.close();
            }catch(Exception e) {
                
            }
        }
    }
    
    //SELECT ������ ����
    public static void doSelectQuery(Statement s) {
        String slt = "SELECT * FROM �л�";
        ResultSet rs;
        try {
            rs = s.executeQuery(slt);
            
            while( rs.next() ) {
                int num = rs.getInt("�й�");
                String name = rs.getString("�̸�");
                String time = rs.getString("�����Ͻ�");
                String major = rs.getString("����");
                String clg = rs.getString("�ܰ�����");
                System.out.println(num + "\t" + name + "\t" + time + "\t" + major + "\t" + clg);
                //����, �ܰ�����
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

    //DB���� �� SELECT���� ���� DB�����͸� �о Student��ü List ��ȯ
    public List readDBReturnStudentList() {
    	List<Student> lst = new ArrayList<>();
    	Student std;
    	
    	try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, user, pw);
//			System.out.println("DB���� ����!");
			Statement stmt = con.createStatement();
//			System.out.println("������Ʈ ����!");
			
	        String slt = "SELECT * FROM �л�";
	        ResultSet rs = stmt.executeQuery(slt);
	        
            while( rs.next() ) {

                int num = rs.getInt("�й�");
                String name = rs.getString("�̸�");
                String time = rs.getString("�����Ͻ�");
                String major = rs.getString("����");
                String clg = rs.getString("�ܰ�����");
                
                std = new Student(num, name, time, major, clg);
                
                lst.add(std);
                //����, �ܰ�����
            }
			
			stmt.close();
			
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("����");
        } finally {
            try {
                if( con != null)
                    con.close();
            }catch(Exception e) {
                
            }
        }
    	
    	
    	return lst;
    }

    //DB���� �� List�� �Ű������� �޾� INSERT�� UPDATE�� ���� DB�� List������ �ݿ�
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
//			System.out.println("DB���� ����!");
			Statement stmt = con.createStatement();
//			System.out.println("������Ʈ ����!");
			
			int stdNum = 0;
	        String slt = "SELECT * FROM �л�";
	        ResultSet rs = stmt.executeQuery(slt);
			
	        for( Student s : lst) {
	        	dbHasIt = false;
	        	std = s;
	        	
	        	//�й�(Key)�� ���ؼ� ����Ʈ�� ���� ���� DB�� �����ϴ��� Ȯ��
	        	stdNum = std.getNum();
	        	slt = "SELECT �й� FROM �л� WHERE �й�="+stdNum;
	        	rs = stmt.executeQuery(slt);
	        	
	        	while(rs.next()) {
	        		if(stdNum == rs.getInt("�й�")) {
	        			dbHasIt = true;
	        			break;
	        		}
	        	}
	        	//�ش� �й��� ������? UPDATE
	        	if( dbHasIt ) {
	        		query = "UPDATE �л� SET �̸�='" + std.getName() + "', �����Ͻ�='" + std.getTime() + "', ����='" + std.getMajor() + "', �ܰ�����='" + std.getClg() + "' WHERE �й�=" + std.getNum();
	        		stmt.execute(query);
	        	}else {//������?? INSERT
	        		query = "INSERT INTO �л�(�й�, �̸�, �����Ͻ�, ����, �ܰ�����) ";
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