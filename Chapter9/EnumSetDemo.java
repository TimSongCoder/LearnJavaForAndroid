import java.util.Set;
import java.util.Iterator;
import java.util.EnumSet;

public class EnumSetDemo{
	public static void main(String[] args){
		Set<Weekday> daysOff = EnumSet.of(Weekday.SUNDAY, Weekday.MONDAY);
		Iterator<Weekday> iter = daysOff.iterator();
		while(iter.hasNext()){
			System.out.println(iter.next());
		}
	}
}

enum Weekday{
	SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY
}