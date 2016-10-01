public class EmployeeClone implements Cloneable{
	String name;
	int age;
	
	EmployeeClone(String name, int age){
		this.name = name;
		this.age = age;
		System.out.println("constructor is called.");
	}
	
	public static void main(String[] args) throws CloneNotSupportedException{
		EmployeeClone e1 = new EmployeeClone("John Doe", 46);  // constructor just called once. clone not invoke it.
		EmployeeClone e2 = (EmployeeClone)e1.clone();
		System.out.println(e1 == e2);    // false
		System.out.println(e1.name == e2.name);    // true
	}
}