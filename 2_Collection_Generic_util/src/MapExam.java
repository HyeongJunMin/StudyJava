import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MapExam {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//	key와 value 모두 String 타입인 HashMap 인스턴스 생성
		Map<String, String>map = new HashMap<>();
		
		//	key와 value 값을 put으로 저장
		map.put("1", "kim");
		map.put("2", "lee");
		map.put("3", "kim");
		
		//	key는 중복 불가 이므로 이전에 저장되어 있던 1,kim이 1,kang으로 바뀐다
		map.put("1", "kang");
		
		//	map에 저장 된 자료쌍의 개수를 출력
		System.out.println(map.size());
		
		//	key갑에 따라 출력
		System.out.println("key \"1\" : " + map.get("1"));
		
		//	map에 저장 된 모든 key를 Set 자료구조에 저장
		Set<String> keys = map.keySet();
		
		//	Iterator와 Set을 통한 key, value 출력
		Iterator<String> itr = keys.iterator();
		
		while(itr.hasNext()) {
			String key = itr.next();
			String value = map.get(key);
			System.out.println("key : " + key + " value : " + value );
		}
	}
}
