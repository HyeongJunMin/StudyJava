package simpleHomeAccounting;

public class MainClass {
	public static void main(String[] args) {
		AccountingVO a = new AccountingVO(2019,6,5,4000,0);
		System.out.println(a.toString());
		AccountingDAO DD = new AccountingDAO();
		DD.syncDBToList();

		DD.printAll();
		
		DD.syncListToDB();
		DD.printAll();
	}
}
