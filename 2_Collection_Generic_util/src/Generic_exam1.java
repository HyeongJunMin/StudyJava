public class Generic_exam1 {

	public static void main(String[] args) {
		Generic_exam1 oobj = new Generic_exam1();
		Generic_exam1.Box box = oobj.new Box();
		
		box.setObj(new Object());
		Object obj = box.getObj();
		
		box.setObj("hello");
		String str = (String)box.getObj();
		System.out.println(str);
		
		box.setObj(1);
		int value = (int)box.getObj();
		System.out.println(value);
	}

	class Box {
		private Object obj;

		public void setObj(Object obj) {
			this.obj = obj;
		}

		public Object getObj() {
			return obj;
		}
	}
}
