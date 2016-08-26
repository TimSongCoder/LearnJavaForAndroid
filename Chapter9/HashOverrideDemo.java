import java.util.Map;
import java.util.HashMap;

public class HashOverrideDemo{
	public static void main(String[] args){
		Point p1 = new Point(10,20);
		Point p2 = new Point(20,30);
		Point p3 = new Point(10,20);
		// Test reflexivity
		System.out.println(p1.equals(p1));
		// Test symmetry
		System.out.println(p1.equals(p2));
		System.out.println(p2.equals(p1));
		// Test transitivity
		System.out.println(p2.equals(p3));
		System.out.println(p1.equals(p3));
		// Test nullability
		System.out.println(p1.equals(null));
		// Extra test to further prove the instanceof operator's usefulness
		System.out.println(p1.equals("abc"));
		
		Map<Point, String> map = new HashMap<Point, String>();
		map.put(p1, "first point");
		System.out.println(map.get(p1));
		// Test the hashCode implementation for Point and HashMap.
		System.out.println(map.get(new Point(10,20)));
		// The last two lines of output is the same.
	}
}

class Point{
	private int x, y;
	
	Point(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	int getX(){
		return x;
	}
	
	int getY(){
		return y;
	}
	
	public boolean equals(Object o){
		if(!(o instanceof Point)){
			return false;
		}
		Point obj = (Point)o;
		return this.x == obj.x && this.y == obj.y;
	}
	
	public int hashCode(){
		int hashCode = 19;  // Choose an arbitrary non-zero number
		int hc = x;
		hashCode = hashCode*31 + hc;
		hc = y;
		hashCode = hashCode*31 + hc;
		return hashCode;
	}
}