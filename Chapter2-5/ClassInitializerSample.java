public class ClassInitializerSample {
	// class initializer, class field initializer load and execute from top to bottom.
	// java compiler compiles them into <clinit>() method.
	static{
		System.out.println("class initializer 1");
	}
	
	static int counter = 1;
	
	static{
		System.out.println("class initializer 2");
		System.out.println("counter = " + counter);
	}
	
	public static void main(String[] args){
		System.out.println("app start.");
	}
}