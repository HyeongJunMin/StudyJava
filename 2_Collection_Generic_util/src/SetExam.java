import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;

public class SetExam {
	public static void main(String[] args) {
		Set<String> set1 = new HashSet<>();
		boolean b1 = set1.add("kim");
		boolean b2 = set1.add("lee");
		boolean b3 = set1.add("kim");
		
		//중복을 허용하지 않기 때문에 kim은 1번만 들어갔고 b3는 false
		System.out.println(set1.toString());
		System.out.println(b1 + " " + b2 + " " + b3);

		
		//set1의 iterator를 Iterator 타입 변수 iter에 저장
		Iterator<String> iter = set1.iterator();
		
		//출력방법 : Iterator, forEach
		do {
			System.out.println(iter.next());
		}while( iter.hasNext() );
		
		for(String a : set1)
			System.out.println(a);
		
	}
}
