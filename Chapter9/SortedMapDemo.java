import java.util.Comparator;
import java.util.SortedMap;
import java.util.TreeMap;

public class SortedMapDemo{
	public static void main(String[] args){
		SortedMap<String, Integer> smsi = new TreeMap<String, Integer>();
		String[] officeSupplies = {
			"Pen", "pencil", "legal pad", "CD", "paper"
		};
		int[] quantities = {
			20, 30, 5, 10, 20
		};
		for(int i=0;i<officeSupplies.length; i++){
			smsi.put(officeSupplies[i], quantities[i]);
		}
		System.out.println(smsi);
		System.out.println(smsi.headMap("pencil"));
		System.out.println(smsi.headMap("paper"));
		
		SortedMap<String, Integer> smsiCopy = new TreeMap<String, Integer>(new Comparator<String>(){
			@Override
			public int compare(String key1, String key2){
				return key2.compareTo(key1);  // reverse as the natural order
			}
		});
		smsiCopy.putAll(smsi);
		System.out.println(smsiCopy);
	}
}