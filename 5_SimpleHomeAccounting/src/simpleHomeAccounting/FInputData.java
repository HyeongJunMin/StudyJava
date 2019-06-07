package simpleHomeAccounting;

public class FInputData implements FAccountingInterface{
	public void run() {
		SingletonData.getInstance();
		System.out.println("가계부 자료 입력");
		String inputd = "";
		char chcOK = 'N';
		
		while(chcOK != 'Y') {
			System.out.print("년/월/일/수입/지출 : ");
			inputd = FStatic.in.next();
						
			System.out.println("입력하신 정보는 "+ inputd + "입니다.");
			System.out.print("맞으면 Y, 틀리면 N을, 중지하려면E를 입력하세요.");
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
