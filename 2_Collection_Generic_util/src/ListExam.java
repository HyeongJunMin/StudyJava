import java.util.ArrayList;
import java.util.List;

public class ListExam {
	public static void main(String[] args) {
		//List는 길이가 정해져 있지 않기 때문에 길이를 알 수 없는 배열을 더할 때 유용하다.
		List<String> lst = new ArrayList<>();
		
		//lst에 3개의 문자열 저장
		lst.add("kim");
		lst.add("lee");
		lst.add("kim");
		
		//lst에 저장된 자료의 수 출력
		System.out.println(lst.size());
		
		for (int i = 0; i < lst.size(); i++) {
			String str = lst.get(i);
			System.out.println(str);
		}
	}
}
