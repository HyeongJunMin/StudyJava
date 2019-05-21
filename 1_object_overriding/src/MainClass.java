
public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 프로그래머스 - 자바중급 강의
		// 1. Object와 오버라이드
		// https://programmers.co.kr/learn/courses/9/lessons/400
		
		// StringBuffer
		StringBuffer sb = new StringBuffer();
		sb.append("hello");
		sb.append(" ");
		sb.append("world");
		String str = sb.toString();
		System.out.println(str);
		
		StringBuffer sb2 = new StringBuffer();
		String str2 = sb2.append("hello").append(" ").append("world").toString();
		System.out.println(str2);
	}

}
