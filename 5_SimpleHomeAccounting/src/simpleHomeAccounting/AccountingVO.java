package simpleHomeAccounting;

import java.time.*;

public class AccountingVO {
	LocalDate t;
	int income;
	int spending;
	
	public AccountingVO() {  }
	
	public AccountingVO (int year, int month, int date, int income, int spending) {
		try {
			t = LocalDate.of(year, month, date);	
		}catch (Exception e) {
			System.out.println("날짜 입력 오류 - AccountingVO");
		}
		this.income = income;
		this.spending = spending;
	}

	public LocalDate getT() {
		return t;
	}

	public void setT(LocalDate t) {
		this.t = t;
	}

	public int getIncome() {
		return income;
	}

	public void setIncome(int income) {
		this.income = income;
	}

	public int getSpending() {
		return spending;
	}

	public void setSpending(int spending) {
		this.spending = spending;
	}
	
	@Override
	public String toString() {
		String blank = (getIncome() > 99999)? "\t" : "\t\t";
		String s;
		s = getT().getYear() + "년 " 
				+ getT().getMonthValue() + "월 " 
				+ getT().getDayOfMonth() 
				+ "일\t수입:" + getIncome()
				+ blank + "지출:" + getSpending();
		return s;
	}
}
