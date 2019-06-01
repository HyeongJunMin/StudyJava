package constructerTest;

public class Parents {
	int x = 100;

	Parents() {
		this(200);
		System.out.println("parent" + this.x);
	}

	Parents(int x) {
		System.out.println("parentx" + this.x);
		this.x = x;
	}

	int getX() {
		return x;
	}
}
