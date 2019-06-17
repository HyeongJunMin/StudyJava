package simpleHomeAccounting;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
//ddddd
public class AccountingDAO {
	HashMap<Integer, AccountingVO> m;
	List<AccountingVO> lst;
	File f;
	Scanner in = new Scanner(System.in);
	private static int count = 0;
	private static Connection con = null;
	private static String user = "minhj";
	private static String pw = "1234";
	private static String url = "jdbc:oracle:thin:@localhost:1521:studyDB";
	
	public AccountingDAO() {
		lst = new ArrayList<AccountingVO>();
		m = new HashMap<Integer, AccountingVO>();
		f = new File("d:\\tmp\\acc.txt");
	}
	
	//�޴�����
	public void runAcc() {
		
		int chc = 0;
		
		System.out.print("�޴�����\n(1)������Է�\t(2)��������\t(3)���ϴٿ�ε�");
		in.nextInt();
		
		switch( chc ) {
			case 1: inputData(); break;
			case 2: break;
			case 3: break;
			default : break;
		}
		

	}
	
	public void inputData() {
		System.out.println("����� �ڷ� �Է�");
		String inputd = "";
		char chcOK = 'N';
		
		while(chcOK != 'Y') {
			System.out.print("��/��/��/����/���� : ");
			inputd = in.next();
						
			System.out.println("�Է��Ͻ� ������ "+ inputd + "�Դϴ�.");
			System.out.print("������ Y, Ʋ���� N��, �����Ϸ���E�� �Է��ϼ���.");
			chcOK = isYOrN();			
			if(chcOK == 'E')
				return;
		}
		
		String[] strArr = inputd.split("/");
		if(strArr.length == 5) {
			int year = Integer.parseInt(strArr[0]);
			int month = Integer.parseInt(strArr[1]);
			int date = Integer.parseInt(strArr[2]);
			int income = Integer.parseInt(strArr[3]);
			int spending = Integer.parseInt(strArr[4]);
			lst.add(new AccountingVO(year, month, date, income, spending));	
		}
		
	}
	
	//��¥, �������� ���� �Է�
	public boolean insertData(int year, int month, int date, int income, int spending) {
		boolean complete = false;
		
		complete = lst.add(new AccountingVO(year, month, date, income, spending));
		
		count++;
		
		return complete;
	}

	//DB�ڷḦ List�� ����
	public boolean syncDBToList() {		
		int year = 0;
		int month = 0;
		int day = 0;
		int income = 0;
		int spending = 0;
		
		String sql;
		sql ="SELECT * FROM HOMEACCOUNTING";
		//�̸� �����Ͻ� ���� �ܰ�����
//		String upd = "UPDATE �л� SET �̸�='����', �����Ͻ�='2009/03/02', ����='��ǻ�Ͱ���', �ܰ�����='����' WHERE �й�=1002";
		
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, user, pw);
//			System.out.println("DB���� ����!");
			Statement stmt = con.createStatement();
//			stmt.executeQuery(upd);
//			System.out.println("������Ʈ ����!");
			
			ResultSet rs =  stmt.executeQuery("SELECT * FROM HOMEACCOUNTING");
//			rs.setFetchSize(10);
			
			while( rs.next() ) {
				year = rs.getInt(1);
				month = rs.getInt(2);
				day = rs.getInt(3);
				income = rs.getInt(4);
				spending = rs.getInt(5);
			
				lst.add(new AccountingVO(year, month, day, income, spending));
			}
			count = lst.size();
			
			rs.close();
			stmt.close();
			con.close();
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
		return true;
	}
	
	//List ������ DB�� ����
	public boolean syncListToDB() {
		boolean complete = false;
		
		int year = 0;
		int month = 0;
		int day = 0;
		int income = 0;
		int spending = 0;
		
		String sql;
		sql ="SELECT * FROM HOMEACCOUNTING";
		//�̸� �����Ͻ� ���� �ܰ�����
//		String upd = "UPDATE �л� SET �̸�='����', �����Ͻ�='2009/03/02', ����='��ǻ�Ͱ���', �ܰ�����='����' WHERE �й�=1002";
		
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, user, pw);
			Statement stmt = con.createStatement();
			
			ResultSet rs =  stmt.executeQuery("SELECT * FROM HOMEACCOUNTING");
						
			//��¥���� ������ update, ������ insert
			for(AccountingVO v : lst) {
				sql = "SELECT * FROM HOMEACCOUNTING WHERE YEAR=" + v.getT().getYear()
						 + " AND MONTH=" + v.getT().getMonthValue()
						 + " AND DAY=" + v.getT().getDayOfMonth();
				rs = stmt.executeQuery(sql);
				
				if( rs.next() ) {	//SELECT�� ����� ������ UPDATE
					sql = "UPDATE HOMEACCOUNTING SET INCOME=" + v.getIncome()
							 + ", SPENDING=" + v.getSpending()
							 + " WHERE YEAR=" + v.getT().getYear()
							 + " AND MONTH=" + v.getT().getMonthValue()
							 + " AND DAY=" + v.getT().getDayOfMonth();
					stmt.executeUpdate(sql);
				} else {		//SELECT�� ����� ������ INSERT
					sql = "INSERT INTO HOMEACCOUNTING VALUES ('"
							+ v.getT().getYear() + "', '" 
							+ v.getT().getMonthValue() + "', '"
							+ v.getT().getDayOfMonth() + "', '"
							+ v.getIncome() +"', '" 
							+ v.getSpending() + "')";
					stmt.executeUpdate(sql);
				}
			}
//			System.out.println("dbdbdbdbdbdb");
//			rs = stmt.executeQuery("SELECT * FROM HOMEACCOUNTING WHERE YEAR=2019 AND MONTH=6 AND DAY=3");
//			System.out.println(rs.next());
//			System.out.println(rs.next());
//			rs = stmt.executeQuery("SELECT * FROM HOMEACCOUNTING WHERE YEAR=2019 AND MONTH=6 AND DAY=10");
//			System.out.println(rs.next());
//			System.out.println(rs.next());
			
			
			complete = true;
			
			rs.close();
			stmt.close();
			con.close();
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
		
		
		return complete;
	}
	
	//�ֿܼ� List ���� ���
	public void printAll() {
		System.out.println("����� �α׸� ����մϴ�.");
		System.out.println("��¥\t\t����\t\t����");
		for(AccountingVO a : lst) {
			System.out.println(a.toString());
		}
	}
	
	//�Է°��� Y�Ǵ� N���� �˻�
	private char isYOrN() {
		char ch = '1';
		
		while( ch != 'Y' && ch != 'N' && ch != 'E') {
			ch = in.next().toUpperCase().charAt(0);
			
			if( ch != 'Y' && ch != 'N' && ch != 'E' ) {
				System.out.println("Y�Ǵ� N�� �Է��ϼ���.");
			}			
		}		
		return ch;
	}
		
	//�Է°��� �������� �˻�
	private int inputUntilInteger() {
		int num = 0;		

		char ch = 'f';
		boolean isNotInteger = true;
		
		while( isNotInteger ) {
		try {
			num = Integer.parseInt(in.next());
			isNotInteger = false;
		}catch (Exception e){
			System.out.println("������ �ƴմϴ�. ������ �Է��ϼ���.");
			isNotInteger = true;
		}
		}
		
		
		return num;
	}
}
