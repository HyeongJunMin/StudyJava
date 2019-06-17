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
	
	//메뉴선택
	public void runAcc() {
		
		int chc = 0;
		
		System.out.print("메뉴선택\n(1)가계부입력\t(2)가계부출력\t(3)파일다운로드");
		in.nextInt();
		
		switch( chc ) {
			case 1: inputData(); break;
			case 2: break;
			case 3: break;
			default : break;
		}
		

	}
	
	public void inputData() {
		System.out.println("가계부 자료 입력");
		String inputd = "";
		char chcOK = 'N';
		
		while(chcOK != 'Y') {
			System.out.print("년/월/일/수입/지출 : ");
			inputd = in.next();
						
			System.out.println("입력하신 정보는 "+ inputd + "입니다.");
			System.out.print("맞으면 Y, 틀리면 N을, 중지하려면E를 입력하세요.");
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
	
	//날짜, 수입지출 정보 입력
	public boolean insertData(int year, int month, int date, int income, int spending) {
		boolean complete = false;
		
		complete = lst.add(new AccountingVO(year, month, date, income, spending));
		
		count++;
		
		return complete;
	}

	//DB자료를 List에 저장
	public boolean syncDBToList() {		
		int year = 0;
		int month = 0;
		int day = 0;
		int income = 0;
		int spending = 0;
		
		String sql;
		sql ="SELECT * FROM HOMEACCOUNTING";
		//이름 입학일시 전공 단과대학
//		String upd = "UPDATE 학생 SET 이름='만득', 입학일시='2009/03/02', 전공='컴퓨터공학', 단과대학='공대' WHERE 학번=1002";
		
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, user, pw);
//			System.out.println("DB연결 성공!");
			Statement stmt = con.createStatement();
//			stmt.executeQuery(upd);
//			System.out.println("업데이트 성공!");
			
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
            System.out.println("실패");
        } finally {
            try {
                if( con != null)
                    con.close();
            }catch(Exception e) {
                
            }
        }
		return true;
	}
	
	//List 내용을 DB에 저장
	public boolean syncListToDB() {
		boolean complete = false;
		
		int year = 0;
		int month = 0;
		int day = 0;
		int income = 0;
		int spending = 0;
		
		String sql;
		sql ="SELECT * FROM HOMEACCOUNTING";
		//이름 입학일시 전공 단과대학
//		String upd = "UPDATE 학생 SET 이름='만득', 입학일시='2009/03/02', 전공='컴퓨터공학', 단과대학='공대' WHERE 학번=1002";
		
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, user, pw);
			Statement stmt = con.createStatement();
			
			ResultSet rs =  stmt.executeQuery("SELECT * FROM HOMEACCOUNTING");
						
			//날짜값이 있으면 update, 없으면 insert
			for(AccountingVO v : lst) {
				sql = "SELECT * FROM HOMEACCOUNTING WHERE YEAR=" + v.getT().getYear()
						 + " AND MONTH=" + v.getT().getMonthValue()
						 + " AND DAY=" + v.getT().getDayOfMonth();
				rs = stmt.executeQuery(sql);
				
				if( rs.next() ) {	//SELECT문 결과가 있으면 UPDATE
					sql = "UPDATE HOMEACCOUNTING SET INCOME=" + v.getIncome()
							 + ", SPENDING=" + v.getSpending()
							 + " WHERE YEAR=" + v.getT().getYear()
							 + " AND MONTH=" + v.getT().getMonthValue()
							 + " AND DAY=" + v.getT().getDayOfMonth();
					stmt.executeUpdate(sql);
				} else {		//SELECT문 결과가 없으면 INSERT
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
            System.out.println("실패");
        } finally {
            try {
                if( con != null)
                    con.close();
            }catch(Exception e) {
                
            }
        }
		
		
		return complete;
	}
	
	//콘솔에 List 내용 출력
	public void printAll() {
		System.out.println("가계부 로그를 출력합니다.");
		System.out.println("날짜\t\t수입\t\t지출");
		for(AccountingVO a : lst) {
			System.out.println(a.toString());
		}
	}
	
	//입력값이 Y또는 N인지 검사
	private char isYOrN() {
		char ch = '1';
		
		while( ch != 'Y' && ch != 'N' && ch != 'E') {
			ch = in.next().toUpperCase().charAt(0);
			
			if( ch != 'Y' && ch != 'N' && ch != 'E' ) {
				System.out.println("Y또는 N을 입력하세요.");
			}			
		}		
		return ch;
	}
		
	//입력값이 정수인지 검사
	private int inputUntilInteger() {
		int num = 0;		

		char ch = 'f';
		boolean isNotInteger = true;
		
		while( isNotInteger ) {
		try {
			num = Integer.parseInt(in.next());
			isNotInteger = false;
		}catch (Exception e){
			System.out.println("정수가 아닙니다. 정수를 입력하세요.");
			isNotInteger = true;
		}
		}
		
		
		return num;
	}
}
