import java.io.Serializable;

public class Employee implements Serializable{
	private static final long serialVersionUID = 1517331364702470316L;
	
	private String name;
	private int age;
	// test compatible change with Deserialization
	private int salary;
	
	public Employee(String name, int age, int salary){
		this.name = name;
		this.age = age;
		this.salary = salary;
	}
	
	public String getName(){
		return name;
	}
	
	public int getAge(){
		return age;
	}
	
	public int getSalary(){
		return salary;
	}
}