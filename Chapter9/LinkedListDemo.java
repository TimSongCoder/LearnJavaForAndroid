import java.util.List;
import java.util.LinkedList;
import java.util.ListIterator;

public class LinkedListDemo{
	public static void main(String[] args){
		List<String> ls = new LinkedList<String>();
		String[] weekdays = {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};
		
		for(String weekday : weekdays){
			ls.add(weekday);
		}
		dump("ls:", ls);
		
		ls.add(1, "Sunday");
		ls.add(3, "Monday");
		ls.add(5, "Tuesday");
		ls.add(7, "Wednesday");
		ls.add(9, "Thursday");
		ls.add(11, "Friday");
		ls.add(13, "Saturday");
		
		dump("ls:", ls);
		
		ListIterator<String> li = ls.listIterator(ls.size()); // Return iterator with specified cursor location.
		while(li.hasPrevious()){  // Print out in reverse order.
			System.out.print(li.previous() + " ");
		}
		System.out.println();
	}
	
	static void dump(String title, List<String> ls){
		System.out.print(title + " ");
		for(String s: ls){
			System.out.print(s + " ");
		}
		System.out.println();
	}
}