import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiFunction;

public class MapExam {
		Map<String, String> hshMap ;
		
		//	Map 선언 및 초기화 방법 1 : class에서 선언과 동시에 초기화 
		public static final Map<Integer, String> lnkMap;
		static {
			lnkMap = new LinkedHashMap<Integer, String>();
			lnkMap.put(3, "ㄹㄹ");
			lnkMap.put(4, "ㄹㄹ");
			lnkMap.put(5, "ㄹㄹ");
		}		
	
	public MapExam(){
		//	Map 선언 및 초기화 방법 2 : put 메서드 사용
		hshMap = new HashMap<String, String>();
		hshMap.put("001","ㅎㅎ");
		hshMap.put("002","ㅎㅎ");
		hshMap.put("001","gg");
		hshMap.put(null,"ggg");
		hshMap.put(null,"ㅎㅎㅎㅎ");
		hshMap.put("999",null);
	}
	
	void useCompute() {
		System.out.println("hshMap.toString() :\t" + hshMap.toString());
		System.out.println("hshMap.compute(\"001\", (k,v) -> v+v); : " + hshMap.compute("001", (k,v) -> v+v));
		System.out.println("hshMap.toString() :\t" + hshMap.toString());
		System.out.println("hshMap.compute(\"003\", (k,v) -> v+\"123\"); : " + hshMap.compute("003", (k,v) -> v+"123"));
		System.out.println("hshMap.toString() :\t" + hshMap.toString());
	}
	
	void useComputeIfPresent() {
		System.out.println("hshMap.toString() :\t" + hshMap.toString());
		System.out.println("hshMap.computeIfPresent(\"001\", (k,v) -> v+v); : " + hshMap.computeIfPresent("001", (k,v) -> v+v));
		System.out.println("hshMap.toString() :\t" + hshMap.toString());
		System.out.println("hshMap.computeIfPresent(\"003\", (k,v) -> v+v); : " + hshMap.computeIfPresent("003", (k,v) -> v+v));
		System.out.println("hshMap.toString() :\t" + hshMap.toString());
	}
	
	void useComputeIfAbsent() {
		System.out.println("hshMap.toString() :\t" + hshMap.toString());
		System.out.println("hshMap.computeIfAbsent(\"003\", (k) -> \"ㅅㅅㅅㅅㅅ\"); : " + hshMap.computeIfAbsent("003", (k) -> "ㅅㅅㅅㅅㅅ"));
		System.out.println("hshMap.toString() :\t" + hshMap.toString());
		System.out.println("hshMap.computeIfAbsent(\"001\", (k) -> \"ㅅㅅㅅㅅㅅ\"); : " + hshMap.computeIfAbsent("001", (k) -> "ㅅㅅㅅㅅㅅ"));
		System.out.println("hshMap.toString() :\t" + hshMap.toString());
		System.out.println("hshMap.computeIfAbsent(\"999\", (k)->\"ㅅㅅㅅㅅㅅ\"); : " + hshMap.computeIfAbsent("999", (k)->"ㅅㅅㅅㅅㅅ"));
		System.out.println("hshMap.toString() :\t" + hshMap.toString());
	}
	
	void howToUseKeyAndValue() {
		System.out.println("hshMap.toString() :\t" + hshMap.toString());
		
		//	get(K) : K(key)에 해당하는 value를 얻기 위한 메소드
		System.out.println("hshMap.get(\"001\") :\t" + hshMap.get("001"));
		
		//	containsKey(K) : 현재 맵에 해당 key가 있는지 조사
		System.out.println("hshMap.containsKey(\"001\") :\t" + hshMap.containsKey("001"));
		
		//	remove(K) : 현재 맵에서 K에 해당하는 쌍을 제거
		hshMap.remove("001");
		System.out.println("hshMap.remove(\"001\");");
		System.out.println("hshMap.toString() :\t" + hshMap.toString());
	}
	
	void useEntrySetMethod() {
		//toString 메소드를 통한 해쉬맵 요소 출력
		System.out.println("hshMap.toString() :\t" + hshMap.toString());
		//entrySet 메소드 활용
		Set<Entry<String, String>> st1 = hshMap.entrySet();
		System.out.println("Set<Entry<String, String>> st1 = hshMap.entrySet();");
		System.out.println("st1.toString() :\t" + st1.toString());
	}
	
	String str="";
	void useForEach() {
		System.out.println("hshMap.toString() :\t" + hshMap.toString());
		
		//	forEach 메소드 활용
		hshMap.forEach( (k,v) -> str += k.toString() );
		System.out.println(str);
	}
	
	void useGetAndGetOrDefault() {
		System.out.println("hshMap.toString() :\t\t\t" + hshMap.toString());
		
		//	getOrDefault 메소드 활용
		System.out.println("hshMap.getOrDefault(\"001\", \"123\") : \t" + hshMap.getOrDefault("001", "123"));
		System.out.println("hshMap.getOrDefault(\"003\", \"123\") : \t" + hshMap.getOrDefault("003", "123"));
	}
	
	void useKeySet() {
		hshMap.put("003", "ㅎㅎㅎㅎ");
		System.out.println("hshMap.toString() :\t\t" + hshMap.toString());
		
		//	keySet 메소드 활용
		Set<String> st1 = hshMap.keySet(); 
		System.out.println("Set<String> st1 = hshMap.keySet(); " + st1.toString());
	}
	
	void useMerge() {
		hshMap.put("003", null);
		System.out.println("hshMap.toString() :\t\t" + hshMap.toString());
		
		hshMap.merge("002", "1ㅎㅎ", (initV, existV) -> existV + initV);
		System.out.println("hshMap.toString() :\t\t" + hshMap.toString());
		hshMap.merge("003", "ㅎㅎㅎㅎ", (existV, initV) -> existV + initV);
		System.out.println("hshMap.toString() :\t\t" + hshMap.toString());
	}
	
	void usePut() {
		Map<String, String> hshMap2 = new HashMap<>();

		hshMap2.put("001", "vv");
		hshMap2.put("004", "ㅍㅍ");
		hshMap2.put("005", "ㅍㅍㅍㅍ");
		
		System.out.println("hshMap.toString() :\t\t" + hshMap.toString());
		System.out.println("hshMap2.toString() :\t\t" + hshMap2.toString());
		hshMap.putAll(hshMap2);
		System.out.println("hshMap.putAll(hshMap2);");
		System.out.println("hshMap.toString() :\t\t" + hshMap.toString());
	}
	
	void usePutIfAbsent() {
		Map<String, String> hshMap2 = new HashMap<>();

		hshMap2.put("001", "vv");
		hshMap2.put("004", "ㅍㅍ");
		hshMap2.put("005", null);
		
		System.out.println("hshMap2.toString() :\t\t\t" + hshMap2.toString());
		hshMap2.putIfAbsent("004", "ㅎㅎㅎㅎㅎ");
		System.out.println("hshMap2.putIfAbsent(\"004\", \"ㅎㅎㅎㅎㅎ\");");
		System.out.println("return value " + hshMap2.putIfAbsent("004", "ㅎㅎㅎㅎㅎ"));
		System.out.println("hshMap2.toString() :\t\t\t" + hshMap2.toString());
		hshMap2.putIfAbsent("005", "ㅎㅎㅎㅎㅎ");
		System.out.println("hshMap2.putIfAbsent(\"005\", \"ㅎㅎㅎㅎㅎ\");");
		System.out.println("hshMap2.toString() :\t\t\t" + hshMap2.toString());
	}
	
	void useRemove() {
		System.out.println("hshMap.toString() :" + hshMap.toString());
		System.out.println("hshMap.remove(\"002\"); : " + hshMap.remove("002"));
		System.out.println("hshMap.toString() :" + hshMap.toString());
		System.out.println("hshMap.remove(\"001\",\"ggg\"); : " + hshMap.remove("001","ggg"));
		System.out.println("hshMap.toString() :" + hshMap.toString());
		System.out.println("hshMap.remove(\"001\",\"gg\"); : " + hshMap.remove("001","gg"));
		System.out.println("hshMap.toString() :" + hshMap.toString());
	}
	
	void useReplace() {
		System.out.println("hshMap.toString() :" + hshMap.toString());
		System.out.println("hshMap.replace(\"003\", \"gggg\"); : " + hshMap.replace("003", "gggg"));
		System.out.println("hshMap.replace(\"002\", \"gggg\"); : " + hshMap.replace("002", "gggg"));
		System.out.println("hshMap.toString() :" + hshMap.toString());
		System.out.println("hshMap.replace(\"001\", \"gggg\", \"ㅎㅎㅎㅎ\"); : " + hshMap.replace("001", "gggg", "ㅎㅎㅎㅎ"));
		System.out.println("hshMap.toString() :" + hshMap.toString());
		System.out.println("hshMap.replace(\"001\", \"gg\", \"ㅎㅎㅎㅎ\"); : " + hshMap.replace("001", "gg", "ㅎㅎㅎㅎ"));
		System.out.println("hshMap.toString() :" + hshMap.toString());
	}
	
	void useReplaceAll() {
		System.out.println("hshMap.toString() :" + hshMap.toString());
		hshMap.replaceAll((k,v)->v+v);
		System.out.println("hshMap.toString() :" + hshMap.toString());
	}
	
	void useValues() {
		System.out.println("hshMap.toString() :" + hshMap.toString());
		System.out.println(hshMap.values().toString());
		Collection<String> v1 = hshMap.values();
		
	}
	
	/*
	
	public static <T> T requireNonNull(T obj) {
        if (obj == null)
            throw new NullPointerException();
        return obj;
    }
	
    default V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        Objects.requireNonNull(remappingFunction);
        V oldValue = get(key);

        V newValue = remappingFunction.apply(key, oldValue);
        if (newValue == null) {
            // delete mapping
            if (oldValue != null || containsKey(key)) {
                // something to remove
                remove(key);
                return null;
            } else {
                // nothing to do. Leave things as they were.
                return null;
            }
        } else {
            // add or replace old mapping
            put(key, newValue);
            return newValue;
        }
    }
	
	 */	

}
