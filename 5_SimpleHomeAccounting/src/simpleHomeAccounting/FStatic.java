package simpleHomeAccounting;

import java.util.Scanner;

public class FStatic {
	public static Scanner in = new Scanner(System.in);

	public FStatic() {

	}

	// 입력값이 Y또는 N인지 검사
	public static char isYOrN() {
		char ch = '1';

		while (ch != 'Y' && ch != 'N' && ch != 'E') {
			ch = in.next().toUpperCase().charAt(0);

			if (ch != 'Y' && ch != 'N' && ch != 'E') {
				System.out.println("Y또는 N을 입력하세요.");
			}
		}
		return ch;
	}

	// 입력값이 정수인지 검사
	public static int inputUntilInteger() {
		int num = 0;
		
		boolean isNotInteger = true;

		while (isNotInteger) {
			try {
				num = Integer.parseInt(in.next());
				isNotInteger = false;
			} catch (Exception e) {
				System.out.println("정수가 아닙니다. 정수를 입력하세요.");
				isNotInteger = true;
			}
		}

		return num;
	}
}
