import java.util.List;

public class JDBCMain {
	public static void main(String[] args) {
		DBConnection dbc = new DBConnection();
//		dbc.connectionDB();
		//DB���� �о�� �л� ������ lst ������ ����
		StudentDAO std = new StudentDAO();
		
		std.printFromDB();
		std.addStudent();
	}
}
