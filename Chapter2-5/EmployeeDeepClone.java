public class EmployeeDeepClone implements Cloneable{
	String name;
	int age;
	Date hireDate;
	
	EmployeeDeepClone(String name, int age, Date hireDate){
		this.name = name;
		this.age = age;
		this.hireDate = hireDate;
		System.out.println("EmployeeDeepClone constructor called.");
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException{
		EmployeeDeepClone emp = (EmployeeDeepClone) super.clone();
		if(hireDate != null){    // no point to clone a null object
			emp.hireDate = new Date(hireDate.year, hireDate.month, hireDate.day);
		}
		return emp;
	}
	
	public static void main(String[] args) throws CloneNotSupportedException {
		EmployeeDeepClone e1 = new EmployeeDeepClone("John Doe", 46, new Date(2000, 1, 20));
		EmployeeDeepClone e2 = (EmployeeDeepClone) e1.clone();
		System.out.println(e1 == e2); // output--false
		System.out.println(e1.name == e2.name); // shallow clone  output--true
		System.out.println(e1.hireDate == e2.hireDate); // deep clone		output -- false
		System.out.println(e1.hireDate.year == e2.hireDate.year && e1.hireDate.month == e2.hireDate.month && e1.hireDate.day == e2.hireDate.day);
		// verify the deep clone result.  output -- true;
	}
}