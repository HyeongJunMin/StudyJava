package simpleHomeAccounting;

import java.time.LocalDate;
import java.util.Iterator;

public class FPrint implements FAccountingInterface{
	int s=0;
	
	public FPrint() {
		SingletonData.getInstance();
	}
	
	public void run() {
		Iterator it = SingletonData.acc.keySet().iterator();
		LocalDate a;
		while(it.hasNext()) {
			a = (LocalDate)it.next();
			System.out.println(SingletonData.acc.get(a));
		}
	}
	
	public static void runBetweenTwoDays() {
		String[] str;
		LocalDate d1;
		LocalDate d2;
		System.out.print("언제부터?(YYYY/MM/DD) : ");
		str = FStatic.in.next().split("/");
		d1 = LocalDate.of(Integer.parseInt(str[0]), Integer.parseInt(str[1]), Integer.parseInt(str[2]));
		
		System.out.print("언제까지?(YYYY/MM/DD) : ");
		str = FStatic.in.next().split("/");
		d2 = LocalDate.of(Integer.parseInt(str[0]), Integer.parseInt(str[1]), Integer.parseInt(str[2]));
		d2 = d2.plusDays(1);
		
		int income = 0;
		int spending = 0;
		while( d1.equals(d2) == false ) {
			AccountingVO v = SingletonData.acc.get(d1);
			if( v != null) {
				System.out.println( v.toString() );
				income += v.getIncome();
				spending += v.getSpending();
			}
			d1 = d1.plusDays(1);
		}
		System.out.println("기간 내 총 수입 : " + income + "\t기간 내 총 지출 : " + spending);
	}
}
