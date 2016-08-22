import java.util.SortedSet;
import java.util.TreeSet;

public class CustomClassAndSortedSet{
	public static void main(String[] args){
		SortedSet<Employee> sse = new TreeSet<Employee>();
		sse.add(new Employee("Sally Doe"));
		sse.add(new Employee("Bob Doe"));
		Employee j1 = new Employee("John Doe");
		Employee j2 = new Employee("John Doe");
		sse.add(j1);
		sse.add(j2);
		System.out.println(sse);
		System.out.println(j1.equals(j2));
	}
}

class Employee implements Comparable<Employee>{
	private String name;
	
	Employee(String name){
		this.name = name;
	}
	
	public String toString(){
		return name;
	}
	
	public int compareTo(Employee e){
		return this.name.compareTo(e.name);
	}
	
	public boolean equals(Object o){
		if(!(o instanceof Employee)){
			return false;
		}
		Employee obj = (Employee)o;
		return this.name.equals(obj.name);
	}
}