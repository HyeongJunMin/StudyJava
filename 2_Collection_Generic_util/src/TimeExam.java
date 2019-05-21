import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

public class TimeExam {
	public static void main(String[] args) {
		//현재 시간을 알려주는 now
		LocalDateTime tm = LocalDateTime.now();
		
		//원하는 날짜로 time 객체 생성하는방법
		LocalDate ld1 = LocalDate.of(2012, Month.DECEMBER, 12);
		System.out.println(ld1.toString());
		
		//원하는 시간으로 LocalTime 객체 생성
		LocalTime lt1 = LocalTime.of(17, 18);
		System.out.println(lt1.toString());
		LocalTime lt2 = LocalTime.parse("16:15:40");
		System.out.println(lt2);
		
		//현재 날짜와 시간 정보를 getter 메소드를 이용하여 구하는 방법
		LocalDate theDate = tm.toLocalDate();
		Month month = tm.getMonth();
		int day = tm.getDayOfMonth();
		int hour = tm.getHour();
		int minute = tm.getMinute();
		int second = tm.getSecond();
		String str = month.getValue()+"/"+day+" "+hour+":"+minute+":"+second;
		System.out.println(str);
		
		LocalDateTime ldnow = LocalDateTime.now();
		Month nowMon = ldnow.getMonth();
		
		
		
	}
}
