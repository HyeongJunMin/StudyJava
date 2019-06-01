package constructerTest;

public class Child extends Parents {
	int x = 3000;

	Child() {
		this(1000);
		System.out.println("child");
	}

	Child(int x) {
		System.out.println("childx : " + this.x);
		this.x = x;
	}
}
