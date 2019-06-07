package simpleHomeAccounting;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;

public class FDBIO {
	public static boolean syncDBToMap() {
		boolean done = false;
		
		AccountingVO a;
		
		try {
			ResultSet rs =  SingletonData.stmt.executeQuery("SELECT * FROM HOMEACCOUNTING");
			
			while( rs.next() ) {
				int year = rs.getInt(1);
				int month = rs.getInt(2);
				int day = rs.getInt(3);
				int income = rs.getInt(4);
				int spending = rs.getInt(5);
				a = new AccountingVO(year, month, day, income, spending); 
				SingletonData.acc.put(a.t, a);
			}
			
			done = true;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return done;
	}
	
	public static boolean syncMapToDB() {
		boolean done = false;
		
		String sql;
		sql ="SELECT * FROM HOMEACCOUNTING";
		//�̸� �����Ͻ� ���� �ܰ�����
//		String upd = "UPDATE �л� SET �̸�='����', �����Ͻ�='2009/03/02', ����='��ǻ�Ͱ���', �ܰ�����='����' WHERE �й�=1002";
		
		
		try {
			
			ResultSet rs =  SingletonData.stmt.executeQuery("SELECT * FROM HOMEACCOUNTING");
			Iterator<LocalDate> it = SingletonData.acc.keySet().iterator();			

			AccountingVO v;
			while( it.hasNext() ) {		//��¥���� ������ update, ������ insert
				v = SingletonData.acc.get(it.next());
				sql = "SELECT * FROM HOMEACCOUNTING WHERE YEAR=" + v.getT().getYear()
						 + " AND MONTH=" + v.getT().getMonthValue()
						 + " AND DAY=" + v.getT().getDayOfMonth();
				rs = SingletonData.stmt.executeQuery(sql);
				if(  rs.next()  ) {	//SELECT�� ����� ������ UPDATE
					sql = "UPDATE HOMEACCOUNTING SET INCOME=" + v.getIncome()
					 + ", SPENDING=" + v.getSpending()
					 + " WHERE YEAR=" + v.getT().getYear()
					 + " AND MONTH=" + v.getT().getMonthValue()
					 + " AND DAY=" + v.getT().getDayOfMonth();
					SingletonData.stmt.executeUpdate(sql);
				}else {		//SELECT�� ����� ������ INSERT
					sql = "INSERT INTO HOMEACCOUNTING VALUES ('"
							+ v.getT().getYear() + "', '" 
							+ v.getT().getMonthValue() + "', '"
							+ v.getT().getDayOfMonth() + "', '"
							+ v.getIncome() +"', '" 
							+ v.getSpending() + "')";
					SingletonData.stmt.executeUpdate(sql);
				}
			}
			done = true;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("����");
        } 
		
		return done;
	}
}
