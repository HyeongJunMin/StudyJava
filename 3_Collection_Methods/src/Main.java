import java.util.*;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListExam l = new ListExam();
//		l.setAndGet();
//		l.doSortWithComparator();
//		l.doAddAll();
//		l.checkEqualsContains();
//		l.removeAllWithOperator();
//		l.someWaysToRemove();
//		l.useListIterator();
//		l.useSubList();
//		l.useHashCode();
//		l.useRetainAll();
//		l.useContainsAll();
		
		String num1 = "123.123";
		String num2 = "123123";
		System.out.println(chcStr(num1));
		System.out.println(chcStr(num2));
		
	}	
	
	static boolean chcStr(String str) {
		boolean result = false;
		
		for(int i = 0 ; i < str.length() ; i++) {
			if(str.charAt(i) >= '0' && str.charAt(i) <= '9') {
				result = true;
			} else {
				result = false;
				break;
			}
		}
		
		return result;
	}
}
