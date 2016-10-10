import java.util.List;
import java.util.ArrayList;

public class OutPutImp{
	public static void main(String[] args){
		List<String> ls = new ArrayList<String>();
		List<Object> lo = ls; // can not  cast
		lo.add(new Employee());
		String str = ls.get(0);
	}
}

class Employee{
	String name;
}