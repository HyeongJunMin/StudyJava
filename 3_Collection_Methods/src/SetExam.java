import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SetExam {
	//Set 선언과 초기화
	Set<Integer> intSet;
	Set<Integer> intSet2;
	Set<String> strSet;
	public SetExam(){
		this.intSet = new HashSet<Integer>();
		this.intSet2 = new HashSet<Integer>();		
		
		//add 메소드를 사용해서 Set에 값 저장
		intSet.add(1);
		intSet.add(2);
		intSet.add(3);
		
		//for문을 사용해서 배열의 값을 Set에 저장
		int[] intArr1 = {4,5,6,7};
		
		for(int i : intArr1) {
			intSet2.add(i);
		}
		
		//배열을 Set으로 변환
		String[] strArr1 = {"A","B","C","D"};
		this.strSet = new HashSet<String>(Arrays.asList(strArr1)); 
	}
	
	void setToArray() {
		//toString 메소드 사용
		System.out.println("intSet.toString\t" + intSet.toString());
		
		//toArray 메소드 사용
		Integer[] intArr2 = intSet.toArray(new Integer[intSet.size()]);
		System.out.println("intSet.toArray\t" + Arrays.toString(intArr2));
		String[] strArr2 = strSet.toArray(new String[strSet.size()]);
		System.out.println("strSet.toArray\t" + Arrays.toString(strArr2));
		
		//stream 사용
		Integer[] intArr3 = intSet.stream().toArray(Integer[] :: new);
		System.out.println(".stream().toArray\t"+Arrays.toString(intArr3));
		
		//Array to Set
		intSet = new HashSet<Integer>(Arrays.asList(intArr3));
	}
	
	void handleElementsInSet() {
		System.out.println("intSet.toString() : \t" + intSet.toString());
		//Iterator 활용
		Iterator<Integer> iter = intSet.iterator();
		while(iter.hasNext()) {
			System.out.print(iter.next() + " ");
		}
		
		//contains, remove 활용
		if( intSet.contains(1) ) {
			System.out.println("\nintSet.remove(1) : \t"+intSet.remove(1));
		}
		
		System.out.println("intSet.toString() : \t" + intSet.toString());
	}
	
	void useAdd() {
		System.out.println("intSet.toString() : \t" + intSet.toString());
		System.out.println("intSet.add(1); : " + intSet.add(1));
		System.out.println("intSet.toString() : \t" + intSet.toString());
	}
	
	void useAddAll() {
		System.out.println("intSet.toString() : \t" + intSet.toString());
		System.out.println("intSet.addAll(intSet2); : " + intSet.addAll(intSet2));
		System.out.println("intSet.toString() : \t" + intSet.toString());
		intSet.clear();
		intSet.add(1);
		intSet.add(2);
		intSet.add(3);
		System.out.println("intSet.add(1); : " + intSet.add(4));
		System.out.println("intSet.addAll(intSet2); : " + intSet.addAll(intSet2));
		System.out.println("intSet.toString() : \t" + intSet.toString());		
	}
	
	void useContainsAll() {
		intSet.add(4);
		System.out.println("intSet.toString() : \t" + intSet.toString());
		System.out.println("intSet2.toString() : \t" + intSet2.toString());
		System.out.println("intSet2.containsAll(intSet); : " + intSet2.containsAll(intSet));
		intSet.clear();
		intSet.add(4);
		intSet.add(5);
		System.out.println("intSet.toString() : \t" + intSet.toString());
		System.out.println("intSet2.toString() : \t" + intSet2.toString());
		System.out.println("intSet2.containsAll(intSet); : " + intSet2.containsAll(intSet));
	}
	
	void useRemoveAll() {
		intSet.add(4);
		System.out.println("intSet.toString() : \t" + intSet.toString());
		System.out.println("intSet2.toString() : \t" + intSet2.toString());
		System.out.println("intSet2.removeAll(intSet); : " + intSet2.removeAll(intSet));
		System.out.println("intSet2.toString() : \t" + intSet2.toString());
		intSet.clear();
		intSet.add(2);
		intSet.add(3);
		System.out.println("intSet.toString() : \t" + intSet.toString());
		System.out.println("intSet2.toString() : \t" + intSet2.toString());
		System.out.println("intSet2.containsAll(intSet); : " + intSet2.removeAll(intSet));
	}
	
	void useRetainAll() {
//		intSet.add(4);
		System.out.println("intSet.toString() : \t" + intSet.toString());
		System.out.println("intSet2.toString() : \t" + intSet2.toString());
		System.out.println("intSet.retainAll(intSet2); : " + intSet.retainAll(intSet2));
		System.out.println("intSet.toString() : \t" + intSet.toString());
		
	}
}



