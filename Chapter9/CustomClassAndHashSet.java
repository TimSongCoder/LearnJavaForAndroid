import java.util.Set;
import java.util.HashSet;

public class CustomClassAndHashSet{
	public static void main(String[] args){
		Set<Planet> sp = new HashSet<Planet>();
		sp.add(new Planet("Mercury"));
		sp.add(new Planet("Venus"));
		sp.add(new Planet("Earth"));
		sp.add(new Planet("Mars"));
		sp.add(new Planet("Jupiter"));
		sp.add(new Planet("Saturn"));
		sp.add(new Planet("Uranus"));
		sp.add(new Planet("Neptune"));
		sp.add(new Planet("Fomalhaut b"));
		
		Planet p1 = new Planet("51 pegasi b");
		sp.add(p1);
		Planet p2 = new Planet("51 pegasi b");
		sp.add(p2);
		
		System.out.println("EQUALS: " + p1.equals(p2));
		System.out.println("HASHCODE: " + (p1.hashCode() == p2.hashCode()));
		// Duplicate check both equals and hashCode.
		System.out.println(sp);
	}
}

class Planet{
	private String name;
	
	Planet(String name){
		this.name = name;
	}
	
	@Override
	public boolean equals(Object o){
		if( !(o instanceof Planet)){
			return false;
		}
		Planet p = (Planet)o;
		return this.name.equals(p.name);
	}
	
	String getName(){
		return name;
	}
	
	public String toString(){
		return name;
	}
	
	public int hashCode(){
		return name.hashCode(); // Because String implements the hashCode method validly.
	}
}