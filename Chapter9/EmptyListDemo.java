import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

class Birds{
	private List<String> birds;
	
	Birds(){
		birds = Collections.emptyList();
	}
	
	Birds(String... birdNames){
		birds = new ArrayList<String>();
		for(String str: birdNames){
			birds.add(str);
		}
	}
	
	public String toString(){
		return birds.toString();  // If birds contains null reference, NullPointerException will be thrown.
	}
}

public class EmptyListDemo{
	public static void main(String[] args){
		Birds birds = new Birds();
		System.out.println(birds);
		
		birds = new Birds("Swallow", "Robin", "Bluejay", "Oriole");
		System.out.println(birds);
	}
}