import java.util.NavigableSet;
import java.util.TreeSet;
import java.util.Iterator;

public class NavigableSetDemo{
	public static void main(String[] args){
		NavigableSet<Integer> ns = new TreeSet<Integer>();
		int[] ints = {82, -13, 4, 0, 11, -6, 9};
		
		for(int i: ints){
			ns.add(i);
		}
		
		System.out.print("Ascending order: ");
		for(Integer i: ns){
			System.out.print(i + " ");
		}
		System.out.println();
		
		System.out.print("Descending order: ");
		Iterator iter = ns.descendingIterator();
		while(iter.hasNext()){
			System.out.print(iter.next() + " ");
		}
		System.out.println("\n");
		
		outputClosestMatches(ns, 4);
		outputClosestMatches(ns.descendingSet(), 12);
	}
	
	static void outputClosestMatches(NavigableSet<Integer> ns, int i){
		System.out.println("Element < " + i + " is " + ns.lower(i));
		System.out.println("Element <= " + i + " is " + ns.floor(i));
		System.out.println("Element > " + i + " is " + ns.higher(i));
		System.out.println("Element >= " + i + " is " + ns.ceiling(i));
		System.out.println();
	}
}