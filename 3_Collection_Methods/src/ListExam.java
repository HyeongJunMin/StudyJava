import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.function.UnaryOperator;

public class ListExam {
	List<Integer> lst ;
	List<Integer> lst2 ;
	ListExam() {
		//List 초기화 방법 1. Arrays.asList == 배열을 List로 바꿔주는 메소드
		lst = new ArrayList<>(
				Arrays.asList(1,2,3,4));
		
		//List 초기화 방법 2. add 메소드 활용
		lst2 = new ArrayList<>();
		lst2.add(3);
		lst2.add(2);
		lst2.add(1);
		lst2.add(2, 55);
		
	}
	
	void useContainsAll() {
		lst2 = new ArrayList<Integer>(Arrays.asList(1,2,4,3,2,5));
		System.out.println("lst2.toString() : \t" + lst2.toString());
		System.out.println("lst.toString() : \t" + lst.toString());
		
		//인수로 받은 컬렉션에 있는 모든값을 포함하고 있으면 true
		boolean lstRtn = lst2.containsAll(lst);
		System.out.println("lst2.containsAll(lst) : "+lstRtn);
		
		//인수로 받은 컬렉션에 있는 모든 값을 포함하는게 아니라면 false
		lstRtn = lst.containsAll(lst2);
		System.out.println("lst.containsAll(lst2) : "+lstRtn);
		
	}
	
	void useRetainAll() {
		lst2 = new ArrayList<Integer>(Arrays.asList(1,2,4,3,2,5));
		System.out.println("lst2.toString() : \t" + lst2.toString());
		System.out.println("lst.toString() : \t" + lst.toString());
		
		//인수로 받은 컬렉션에 있는 값을 유지하고 나머지는 삭제
		boolean lstRtn = lst2.retainAll(lst);
		System.out.println("lst2.retainAll(lst) : "+lstRtn);
		System.out.println("lst2.toString() : \t" + lst2.toString() + " lst2.size() : " + lst2.size());
	}
	
	void useHashCode() {
		lst2 = new ArrayList<Integer>(Arrays.asList(1,2,4,3,2,5));
		System.out.println("lst2.toString() : \t" + lst2.toString());
		System.out.println(lst2.hashCode());
	}
	
	void useSubList() {
		lst2 = new ArrayList<Integer>(Arrays.asList(1,2,4,3,2,5));
		System.out.println("lst2.toString() : \t" + lst2.toString());
		System.out.println("lst2.subList(2, 4) : \t" + lst2.subList(2, 4));
		//fromIndex 부터 toIndex 전 까지의 값을 list 형태로 반환
		List<Integer> lstSub = lst2.subList(2, 4);
		System.out.println("lstSub = lst2.subList(2, 4) : \t " + lstSub.toString());
	}
	
	void useListIterator() {
		lst2 = new ArrayList<Integer>(Arrays.asList(1,2,4,3,2,5));
		System.out.println("lst2.toString() : \t" + lst2.toString());
		ListIterator<Integer> lstItr = lst2.listIterator();
		while(lstItr.hasPrevious()) {
			System.out.print(lstItr.previous()+" ");
		}//next를 사용하기 전에는 동작하지 않는다.		
		while(lstItr.hasNext()) {
			System.out.print(lstItr.next()+" ");
		}
		System.out.println();
		while(lstItr.hasPrevious()) {
			System.out.print(lstItr.previous()+" ");
		}
		System.out.println();
		
		//previous를 쓰려면 ListIterator를 생성할 때 index값을 인수로 전달해야 한다.
		ListIterator<Integer> lstItr2 = lst2.listIterator(lst2.size());
		while(lstItr2.hasPrevious()) {
			System.out.print(lstItr2.previous()+" ");
		}
	}
	
	void someWaysToRemove() {
		lst2 = new ArrayList<Integer>(Arrays.asList(1,2,4,3,2,5));
		System.out.println("lst2.toString() : \t" + lst2.toString());
		lst2.remove(2);
		System.out.println("lst2.remove(2) : \t" + lst2.toString());
		lst2 = new ArrayList<Integer>(Arrays.asList(1,2,4,3,2,5));
		lst2.remove((Integer)2);
		System.out.println("lst2.remove( (Integer)2 ) : " + lst2.toString());
	}
	
	void removeAllWithOperator() {
		lst2 = new ArrayList<Integer>(Arrays.asList(1,2,4,3,2,5));
		MyOperator<Integer> operator = new MyOperator<>();
		operator.varc1 = 2;
		System.out.println("lst2.toString() : " + lst2.toString());
		lst2.replaceAll(operator);
		System.out.println("lst2.toString() : " + lst2.toString());
		//
	}
	
	void checkEqualsContains() {
		lst2 = new ArrayList<Integer>(Arrays.asList(1,2,2,3,4,5));
		System.out.println("List<Integer> lst :\t" + lst.toString());
		System.out.println("List<Integer> lst2 :\t" + lst2.toString());
		System.out.println("lst.contains(1) : " + lst.contains(1));
		System.out.println("lst.equals(lst2) : " + lst.equals(lst2));
		//lst2.removeAll(lst) 실행하면? lst2에 있는 값 중 lst와 동일한 모든 값들은 삭제됨  
		System.out.println("lst2.removeAll(lst) : " + lst2.removeAll(lst));
		System.out.println("List<Integer> lst2 :\t" + lst2.toString());
		System.out.println("lst2.size() : " + lst2.size());
	}
	
	void doAddAll() {
		System.out.println("List<Integer> lst :\t" + lst.toString());
		System.out.println("List<Integer> lst2 :\t" + lst2.toString());		
		lst2.addAll(2, lst);
		System.out.println("lst2.addAll(2, lst) :\t" + lst2.toString());
		lst2.addAll(lst);
		System.out.println("lst2.addAll(lst) :\t" + lst2.toString());
	}
	
	void doSortWithComparator() {
		System.out.println("이름, 점수 필드를 갖는 객체 Student가 저장된 List lst3이 있다.");
		List<Student> lst3 = new ArrayList<>();
		lst3.add(new Student("학생1",100));
		lst3.add(new Student("학생2",80));
		lst3.add(new Student("학생3",70));
		for(Student s : lst3)
			System.out.print(s + "\t");
		System.out.println();
		System.out.println();
		System.out.println("일반 자료형들 처럼 Collections.sort(lst3)을 하게되면?");
		System.out.println("에러 발생 > java.lang.Error : Unresolved compilation problem");
		System.out.println();
		System.out.println("방법 : score를 기준으로 오름차순 정렬하기 위해 Comparator 활용");
		System.out.println("1. Student 클래스에 Comparable<T>를 implements");
		System.out.println("2. Comparator메소드를 Override해서 작성");
		System.out.println("3. Collections.sort(lst3) 실행 결과값 확인");
		Collections.sort(lst3);
		for(Student s : lst3)
			System.out.print(s + "\t");
	}
		
	void setAndGet() {
		System.out.println("lst2 == 1, 2, 3, 1, 2 ");
		lst2 = new ArrayList<>(Arrays.asList(1,2,3,1,2));
		System.out.println("lst2.set(1, 55)");
		lst2.set(1, 55);
		for(int i : lst2)
			System.out.print(i + " ");
		System.out.println();
		System.out.println("lst2.get(1) : " + lst2.get(1));
		
	}
	
	void indexAndSize() {
		System.out.println("lst2 == 1, 2, 3, 1, 2 ");
		lst2 = new ArrayList<>(Arrays.asList(1,2,3,1,2));
		System.out.println("lst2의 길이 : " + lst2.size());
		System.out.println("lst2.indexOf(2)  : " + lst2.indexOf(2));
		System.out.println("lst2.lastIndexOf(2)  : " + lst2.lastIndexOf(2));
		System.out.println("lst2.indexOf(4)  : " + lst2.indexOf(4));
	}
	
	void sortList() {
		//오름차순 정렬 Collections의 static 메소드 sort 활용
		Collections.sort(lst2);
		for(int i : lst2)
			System.out.print(i + " ");
		
		System.out.println();

		//내림차순 정렬 Collections의 static 메소드 reverse 활용
		Collections.reverse(lst2);
		for(int i : lst2)
			System.out.print(i + " ");
	}
	
	void print() {
		//List 데이터 꺼내기 방법 1. forEach 활용
		for(int i : lst)
			System.out.print(i + " ");
		System.out.println();
		
		//List 데이터 꺼내기 방법 2. Iterator 활용
		Iterator itr = lst2.iterator();
		while(itr.hasNext()) {
			System.out.print(itr.next()+ " ");
		}		
		System.out.println();
	}
	
	Integer[] setArray(List<Integer> lstInput) {
		//
		Integer[] inArray1;
		inArray1 = lstInput.toArray(new Integer[lstInput.size()]);
				
		return inArray1; 
	}
	
	class Student implements Comparable<Student> {
		String name;
		int score;
		public Student(String name, int score) {
			this.name = name;
			this.score = score;
		}
		
		@Override
		public int compareTo(Student s) {
			// 현재 객체의 점수와 대상 객체의 점수를 비교
			// 현재 객체의 점수가 더 크면 1, 작으면 -1, 같으면 0 반환
			if(this.score < s.score) {
				return -1;
			} else if(this.score > s.score) {
				return 1;
			}			
			return 0;
		}
		
		public String toString() {
			return "name: " + this.name + " score: " + this.score;
		}
	}
	
	class MyOperator<T> implements UnaryOperator<T>{
		T varc1;
		public T apply(T varc) {
			return varc1;
		}
	}
}




