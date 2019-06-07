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
		//이름 입학일시 전공 단과대학
//		String upd = "UPDATE 학생 SET 이름='만득', 입학일시='2009/03/02', 전공='컴퓨터공학', 단과대학='공대' WHERE 학번=1002";
		
		
		try {
			
			ResultSet rs =  SingletonData.stmt.executeQuery("SELECT * FROM HOMEACCOUNTING");
			Iterator<LocalDate> it = SingletonData.acc.keySet().iterator();			

			AccountingVO v;
			while( it.hasNext() ) {		//날짜값이 있으면 update, 없으면 insert
				v = SingletonData.acc.get(it.next());
				sql = "SELECT * FROM HOMEACCOUNTING WHERE YEAR=" + v.getT().getYear()
						 + " AND MONTH=" + v.getT().getMonthValue()
						 + " AND DAY=" + v.getT().getDayOfMonth();
				rs = SingletonData.stmt.executeQuery(sql);
				if(  rs.next()  ) {	//SELECT문 결과가 있으면 UPDATE
					sql = "UPDATE HOMEACCOUNTING SET INCOME=" + v.getIncome()
					 + ", SPENDING=" + v.getSpending()
					 + " WHERE YEAR=" + v.getT().getYear()
					 + " AND MONTH=" + v.getT().getMonthValue()
					 + " AND DAY=" + v.getT().getDayOfMonth();
					SingletonData.stmt.executeUpdate(sql);
				}else {		//SELECT문 결과가 없으면 INSERT
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
            System.out.println("실패");
        } 
		
		return done;
	}
}
