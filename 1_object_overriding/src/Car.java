public class Car{
	    String name;
	    int number;
	    
	    //toString을 오버라이드 해 보세요.
	    //return 형식은 아래줄을 참고하세요: 
	    //"name: " + name + ", number: " + number;
	    
	    @Override
		public String toString() {
	    	return "name: " + this.name + ", number: " + this.number;
	    }
}

