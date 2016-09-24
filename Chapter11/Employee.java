import java.io.Serializable;

public class Employee implements Serializable{
	private static final long serialVersionUID = 1517331364702470316L;
	
	private String name;
	private int age;
	// test compatible change with Deserialization
	private int salary;
	private transient int bonus;
	
	public Employee(String name, int age, int salary, int bonus){
		this.name = name;
		this.age = age;
		this.salary = salary;
		this.bonus = bonus;
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
	
	public int getBonus(){
		return bonus;
	}
}