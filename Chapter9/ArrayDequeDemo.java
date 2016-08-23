import java.util.ArrayDeque;
import java.util.Deque;

public class ArrayDequeDemo{
	public static void main(String[] args){
		Deque<String> stackDeque = new ArrayDeque<String>();
		String[] weekdays = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
		for(String weekday: weekdays){
			stackDeque.push(weekday);  // insert
		}
		
		while(stackDeque.peek()!=null){ // examine
			System.out.println(stackDeque.pop());  // remove
		}
	}
}