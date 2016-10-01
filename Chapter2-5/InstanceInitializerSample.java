/**
* similar to class initializer system, instance initializer also be included
* in a method <init>(). instance initializer, instance field initializer executed
* from top to bottom, before constructor.
* Instance initializer is barely used in industry.
*/
public class InstanceInitializerSample{
	{
		System.out.println("instance initializer 1");
	}
	
	int counter = 1;
	
	{
		System.out.println("instance initializer 2");
		System.out.println("counter = " + counter);
	}
	
	InstanceInitializerSample(){
		System.out.println("noargument constructer called.");
	}
	
	public static void main(String[] args){
		new InstanceInitializerSample();
	}
}