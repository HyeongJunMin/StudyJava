import java.util.List;

public class JDBCMain {
	public static void main(String[] args) {
		DBConnection dbc = new DBConnection();
//		dbc.connectionDB();
		//DB에서 읽어온 학생 정보를 lst 변수에 저장
		StudentDAO std = new StudentDAO();
		
		std.printFromDB();
		std.addStudent();
	}
}
