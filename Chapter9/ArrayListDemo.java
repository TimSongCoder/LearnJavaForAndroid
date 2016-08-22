import java.util.List;
import java.util.ArrayList;

public class ArrayListDemo{
	public static void main(String[] args){
		List<String> ls = new ArrayList<String>();
		
		String[] weekdays = {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};
		
		for(String weekday : weekdays){
			ls.add(weekday);
		}
		
		dump("ls:", ls);
		
		ls.set(ls.indexOf("WED"), "Wednesday");
		dump("ls:", ls);
		
		ls.remove(ls.lastIndexOf("FRI"));
		dump("ls:", ls);
	}
	
	static void dump(String title, List<String> ls){
		System.out.print(title + " ");
		for(String s: ls){
			System.out.print(s + " ");
		}
		System.out.println();
	}
}