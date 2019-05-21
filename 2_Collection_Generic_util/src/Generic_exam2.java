public class Generic_exam2 {
	public static void main(String[] args) {
		Generic_exam2 oobj = new Generic_exam2();
		Generic_exam2.Box<Object> box = oobj.new Box<>();

		box.setObj(new Object());
		//Object 타입 변수에 저장할 수 있는지 확인
		Object obj = box.getObj();
		System.out.println(obj.toString());
				
		//String 타입 변수로 설정하고 저장 및 출력 확인
		Generic_exam2.Box<String> box2 = oobj.new Box<>();
		box2.setObj("Hello");
		String str = box2.getObj();
		System.out.println(str);
		System.out.println(box2.getObj().charAt(1));

		//int 타입 변수로 설정하고 저장 및 출력 확인
		Generic_exam2.Box<Integer> box3 = oobj.new Box<>();
		box3.setObj(1);
		int value = (int)box3.getObj();
		System.out.println(value);
				
	}
	//Generic을 이용하여 Box 클래스 수정
	class Box<E> {
		private E obj;

		public void setObj(E obj) {
			this.obj = obj;
		}

		public E getObj() {
			return obj;
		}
	}
}
