import java.util.Calendar;

public class CalenderExam {
	public static void main(String[] args) {
		
		Calendar cl = Calendar.getInstance();
		cl.add(Calendar.DATE, 100);
		System.out.println(cl.DAY_OF_MONTH);
		System.out.println(cl.getFirstDayOfWeek());
		int y = cl.get(Calendar.YEAR);
		int m = cl.get(Calendar.MONTH)+1;
		int d = cl.get(Calendar.DATE);
		System.out.println(y+"년"+m+"월"+d+"일");
	}
}
