
public class IntegerExam {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// about Wrapper Class

	        Integer i1 = 5;
	        int max_int = i1.MAX_VALUE;
	        long i1_long = i1.longValue();
	        
	        int i2 = 5;
//	        int min_int = i2.MIN_VALUE;
//	        long i2_long = i2.longValue();
	    
	}

}

/*
 * Wrapper class - 실습 문제 설명 Integer는 int의 wrapper class입니다. 
 * class이기 때문에 속성과 메소드를 가지는데요. 
 * 다음 코드를 실행해 보면 Integer 타입인 경우 필드와 method를 사용할 수 있지만, 
 * 기본형 타입인 int의 경우 필드와 method를 사용할 수 없는걸 확인할 수 있습니다. 
 * (코드의 7~9번째 줄을 지워야 정상동작 합니다.)
 * 
 * https://docs.oracle.com/javase/7/docs/api/java/lang/Integer.html 를 참고해서
 * Integer의 다양한 필드와 메소드를 테스트해 보세요. 
 * java.lang패키지에는 기본형 타입을 객체로 변환시킬 때 사용하는 Wrapper클래스에 대해 알아 보세요.
 * 
 * public class WrapperExam { public static void main(String[] args) { int i =
 * 5; Integer i2 = new Integer(5); Integer i3 = 5; //오토박싱 int i4 = i2; //오토언박싱
 * long i2_long = i2.longValue(); long i4_long = i4.longValue(); // 오류 발생! } } 
 * 이때, integer타입과 int타입의 오토박싱, 
 * 오토언박싱의 경우에도 Wrapper클래스가 사용됩니다. 
 * i2는 Integer형이므로 longValue()메소드를 사용할 수 있으나 
 * i4는 int형이므로 메소드를 사용하면 오류가 발생합니다.
 * 
 */