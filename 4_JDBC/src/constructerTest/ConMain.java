package constructerTest;

import java.util.ArrayList;
import java.util.List;

class Man{
	private int num = 0;
	public Man (int n ) {this.num = n;}
	public void setNum(int n ) { this.num = n;}
	public int getNum() { return this.num;}
}

public class ConMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Parents p = new Child(50);
		System.out.println(p.getX());
		
		Child c = new Child(50);
		System.out.println(c.getX());
		
		Man m = new Man(2);
		
    	List<Man> lst = new ArrayList<>();
		
		for (int i = 0; i < 10; i++) {
			m = new Man(i);
			System.out.print("인간" + m.getNum()+" ");
			lst.add(m);
		}
		System.out.println();
		for(int i = 0 ; i < lst.size() ; i ++) {
			System.out.print("인간" + lst.get(i).getNum() + "  ");
		}
		
	}
	


}
