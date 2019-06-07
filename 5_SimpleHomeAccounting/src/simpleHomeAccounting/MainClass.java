package simpleHomeAccounting;

import java.time.LocalDate;

public class MainClass {
	public static void main(String[] args) {
		SingletonData.getInstance();
		
		FDBIO.syncDBToMap();
		
		FAccountingInterface i = new FPrint();
		i.run();
		
//		LocalDate l1 = LocalDate.of(2019, 6, 5);
//		LocalDate l2 = LocalDate.of(2019, 9, 10);
//		Period p = l1.until(l2);
//		System.out.println(p.getDays());
//		
//		LocalDate d = l1;
//		while(d.equals(l2) == false) {
//			System.out.println(d.toString());
//			d = d.plusDays(1);
//		}

		SingletonData.cutDbConnection();
	}
}
