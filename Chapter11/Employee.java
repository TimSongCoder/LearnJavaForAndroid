import java.io.Serializable;

public class Employee implements Serializable{
	private static final long serialVersionUID = 1517331364702470316L;
	
	private String name;
	private int age;
	
	public Employee(String name, int age){
		this.name = name;
		this.age = age;
	}
	
	public String getName(){
		return name;
	}
	
	public int getAge(){
		return age;
	}
}