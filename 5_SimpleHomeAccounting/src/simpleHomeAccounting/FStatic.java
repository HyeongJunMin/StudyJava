package simpleHomeAccounting;

import java.util.Scanner;

public class FStatic {
	public static Scanner in = new Scanner(System.in);

	public FStatic() {

	}

	// �Է°��� Y�Ǵ� N���� �˻�
	public static char isYOrN() {
		char ch = '1';

		while (ch != 'Y' && ch != 'N' && ch != 'E') {
			ch = in.next().toUpperCase().charAt(0);

			if (ch != 'Y' && ch != 'N' && ch != 'E') {
				System.out.println("Y�Ǵ� N�� �Է��ϼ���.");
			}
		}
		return ch;
	}

	// �Է°��� �������� �˻�
	public static int inputUntilInteger() {
		int num = 0;
		
		boolean isNotInteger = true;

		while (isNotInteger) {
			try {
				num = Integer.parseInt(in.next());
				isNotInteger = false;
			} catch (Exception e) {
				System.out.println("������ �ƴմϴ�. ������ �Է��ϼ���.");
				isNotInteger = true;
			}
		}

		return num;
	}
}
