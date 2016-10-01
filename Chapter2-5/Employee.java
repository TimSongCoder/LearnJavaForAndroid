// used to demonstrate the difference of class field and instance field
public class Employee {
	final int ID;
	static int counter = 0;
	
	Employee(){
		ID = ++counter;
	}
}