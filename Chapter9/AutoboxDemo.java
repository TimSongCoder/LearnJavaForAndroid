public class AutoboxDemo{
	public static void main(String[] args){
		Integer i1 = 127;  // Use jd-gui tool to check the class file. You can find the truth: Integer i1 = Integer.valueOf(127);
		Integer i2 = 127;
		
		System.out.println(i1 == i2); // output: true.  127 lies in the internal cache of the Integer class.
		System.out.println(i1 < i2);  // auto unboxing before comparison operation: localInteger1.intValue() < localInteger2.intValue()
		System.out.println(i1 > i2);
		System.out.println(i1 + i2);  // auto unboxing before addition operation.
		
		i1 = 30000;
		i2 = 30000;
		
		System.out.println(i1 == i2); // output: false.  30000 does not lie in the internal cache of the Integer class.
		System.out.println(i1 < i2);
		System.out.println(i1 > i2);
		i2 = 30001;
		System.out.println(i1 < i2);
		System.out.println(i1 + i2);
	}
}