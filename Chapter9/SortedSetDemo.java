import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Collection;

public class SortedSetDemo{
	public static void main(String[] args){
		SortedSet<String> sss = new TreeSet<String>();
		String[] fruitAndVeg = {
			"apple", "potato", "turnip", "banana", "corn", "carrot", "cherry",
			"pear", "mango", "strawberry", "cucumber", "grape", "banana",
			"kiwi", "radish", "blueberry", "tomato", "onion", "raspberry",
			"lemon", "pepper", "squash", "melon", "zucchini", "peach", "plum",
			"turnip", "onion", "nectarine"
		};
		System.out.println("Array size: " + fruitAndVeg.length);
		
		for(String str: fruitAndVeg){
			sss.add(str);
		}
		dump("sss:", sss);
		System.out.println("SortedSet size: " + sss.size());
		
		System.out.println("First element: " + sss.first());
		System.out.println("last element: " + sss.last());
		System.out.println("Comparator: " + sss.comparator());  // null when using natural ordering.
		
		dump("headSet:", sss.headSet("n")); // less than n
		
		dump("tailSet:", sss.tailSet("n"));  // greater or equal to n
		
		System.out.println("Count of p-named fruits & vegetables: " + sss.subSet("p", "q").size()); 
		// Just use the arguments to compare to determine the range. Half closed range.
		
		System.out.println("Incorrect count of c-named fruits & vegetables: " + sss.subSet("carrot", "cucumber").size());  // Subset excludes high endpoint.
		
		System.out.println("Correct count of c-named fruits & vegetables: " + sss.subSet("carrot", "cucumber\0").size() + "; " + sss.subSet("c", "d").size());
	}
	
	static void dump(String title, Collection<String> cs){
		System.out.print(title + " ");
		for(String s: cs){
			System.out.print(s + " ");
		}
		System.out.println();
	}
}