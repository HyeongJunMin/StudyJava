import java.util.List;
import java.util.Scanner;

public class StudentDAO {
	Scanner in = new Scanner(System.in);
	public static List<Student> stds;
	
	StudentDAO() { 
		syncDBToList();
	}
	
	//학생 등록
	public void addStudent() {
		this.syncDBToList();
		
		char chcOK = '0';
		int number = 1001+this.stds.size();
		String str = "";

		
		System.out.println("학생정보를 입력해 주세요 : ");
		
		while(chcOK != 'Y') {
			str="";
			System.out.print("이름 : ");
			str += in.next() + "\t";
			System.out.print("입학일시(0000/00/00) : ");
			str += in.next() + "\t";
			System.out.print("전공 : ");
			str += in.next() + "\t";
			System.out.print("단과대학 : ");
			str += in.next() + "\t";
			
			System.out.println("입력하신 정보는 "+str + "입니다.");
			System.out.print("맞으면 Y, 틀리면 N을 입력하세요.");
			chcOK = isYOrN();
		}			
		
		String[] strArr = str.split("\t");
		stds.add(new Student(number, strArr[0], strArr[1], strArr[2], strArr[3]));
		
		System.out.println(stds.get(stds.size()-1).toString());
		this.syncListToDB();
	}
	
	
	//DB->리스트 동기화
	private static void syncDBToList() {
		//db연결클래스
		DBConnection dbc = new DBConnection();
		//학생 테이블의 데이터를 Student 객체 리스트에 저장
		stds = dbc.readDBReturnStudentList();		
	}
	
	//리스트->DB 동기화
	private static boolean syncListToDB() {
		boolean completed;
		
		//db연결클래스
		DBConnection dbc = new DBConnection();
		completed = dbc.setDBByStudentList(stds);
		
		return completed;
	}

	
	//DB->리스트 동기화 후 내용 출력
	public void printFromDB() {
		this.syncDBToList();
		for(Student s : stds)
			System.out.println(s.toString());		
		this.syncListToDB();
	}

	
	public void connectToDB(String q) {
		//쿼리문을 받고 해당 쿼리를 수행
		String query = q;
		
		
	}

	//입력값이 Y또는 N인지 검사
	private char isYOrN() {
		char ch = '1';
		
		while( ch != 'Y' && ch != 'N') {
			ch = in.next().toUpperCase().charAt(0);
			
			if( ch != 'Y' && ch != 'N' ) {
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

		while (isNotInteger) {
			try {
				num = Integer.parseInt(in.next());
				isNotInteger = false;
			} catch (Exception e) {
				System.out.println("정수가 아닙니다. 정수를 입력하세요.");
				isNotInteger = true;
			}
		}

		return num;
	}

	private void sortListByNum() {
		
	}



}
