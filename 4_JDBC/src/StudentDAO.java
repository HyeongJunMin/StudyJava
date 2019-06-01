import java.util.List;
import java.util.Scanner;

public class StudentDAO {
	Scanner in = new Scanner(System.in);
	public static List<Student> stds;
	
	StudentDAO() { 
		syncDBToList();
	}
	
	//�л� ���
	public void addStudent() {
		this.syncDBToList();
		
		char chcOK = '0';
		int number = 1001+this.stds.size();
		String str = "";

		
		System.out.println("�л������� �Է��� �ּ��� : ");
		
		while(chcOK != 'Y') {
			str="";
			System.out.print("�̸� : ");
			str += in.next() + "\t";
			System.out.print("�����Ͻ�(0000/00/00) : ");
			str += in.next() + "\t";
			System.out.print("���� : ");
			str += in.next() + "\t";
			System.out.print("�ܰ����� : ");
			str += in.next() + "\t";
			
			System.out.println("�Է��Ͻ� ������ "+str + "�Դϴ�.");
			System.out.print("������ Y, Ʋ���� N�� �Է��ϼ���.");
			chcOK = isYOrN();
		}			
		
		String[] strArr = str.split("\t");
		stds.add(new Student(number, strArr[0], strArr[1], strArr[2], strArr[3]));
		
		System.out.println(stds.get(stds.size()-1).toString());
		this.syncListToDB();
	}
	
	
	//DB->����Ʈ ����ȭ
	private static void syncDBToList() {
		//db����Ŭ����
		DBConnection dbc = new DBConnection();
		//�л� ���̺��� �����͸� Student ��ü ����Ʈ�� ����
		stds = dbc.readDBReturnStudentList();		
	}
	
	//����Ʈ->DB ����ȭ
	private static boolean syncListToDB() {
		boolean completed;
		
		//db����Ŭ����
		DBConnection dbc = new DBConnection();
		completed = dbc.setDBByStudentList(stds);
		
		return completed;
	}

	
	//DB->����Ʈ ����ȭ �� ���� ���
	public void printFromDB() {
		this.syncDBToList();
		for(Student s : stds)
			System.out.println(s.toString());		
		this.syncListToDB();
	}

	
	public void connectToDB(String q) {
		//�������� �ް� �ش� ������ ����
		String query = q;
		
		
	}

	//�Է°��� Y�Ǵ� N���� �˻�
	private char isYOrN() {
		char ch = '1';
		
		while( ch != 'Y' && ch != 'N') {
			ch = in.next().toUpperCase().charAt(0);
			
			if( ch != 'Y' && ch != 'N' ) {
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

		while (isNotInteger) {
			try {
				num = Integer.parseInt(in.next());
				isNotInteger = false;
			} catch (Exception e) {
				System.out.println("������ �ƴմϴ�. ������ �Է��ϼ���.");
				isNotInteger = true;
			}
		}

		return num;
	}

	private void sortListByNum() {
		
	}



}
