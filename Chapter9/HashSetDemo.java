import java.util.Set;
import java.util.HashSet;

public class HashSetDemo{
	public static void main(String[] args){
		Set<String> ss = new HashSet<String>();
		String[] fruits = {"apples", "pears", "grapes", "bananas", "kiwis", "pears", null};
		for(String fruit: fruits){
			ss.add(fruit);
		}
		dump("ss:", ss); // Permit null but not duplicate elements.
	}
	
	static void dump(String title, Set<String> ss){
		System.out.print(title + " ");
		for(String s: ss){
			System.out.print(s + " ");
		}
	}
}