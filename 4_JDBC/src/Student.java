
public class Student {
	private int num;
	private String name;
	private String time;
	private String major;
	private String clg;
	private static int amtOfStudents = 0;
	
	public Student() { amtOfStudents++; }
	
	public Student(int num, String name, String time, String major, String clg) {
		this.num = num;
		this.name = name;
		this.time = time;
		this.major = major;
		this.clg = clg;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getClg() {
		return clg;
	}

	public void setClg(String clg) {
		this.clg = clg;
	}

	@Override
	public String toString() {
		return "Student [num=" + num + ", name=" + name + ", time=" + time + ", major=" + major + ", clg=" + clg + "]";
	}
	
}
