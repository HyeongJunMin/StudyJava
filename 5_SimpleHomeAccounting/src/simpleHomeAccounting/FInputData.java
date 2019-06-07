package simpleHomeAccounting;

public class FInputData implements FAccountingInterface{
	public void run() {
		SingletonData.getInstance();
		System.out.println("����� �ڷ� �Է�");
		String inputd = "";
		char chcOK = 'N';
		
		while(chcOK != 'Y') {
			System.out.print("��/��/��/����/���� : ");
			inputd = FStatic.in.next();
						
			System.out.println("�Է��Ͻ� ������ "+ inputd + "�Դϴ�.");
			System.out.print("������ Y, Ʋ���� N��, �����Ϸ���E�� �Է��ϼ���.");
			chcOK = FStatic.isYOrN();			
			if(chcOK == 'E')
				return;
		}
		
		String[] strArr = inputd.split("/");
		if(strArr.length == 5) {
			int year = Integer.parseInt(strArr[0]);
			int month = Integer.parseInt(strArr[1]);
			int date = Integer.parseInt(strArr[2]);
			int income = Integer.parseInt(strArr[3]);
			int spending = Integer.parseInt(strArr[4]);
			AccountingVO a = new AccountingVO(year, month, date, income, spending); 
			SingletonData.acc.put(a.t, a);	
		}
	}
}
