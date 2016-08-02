public class StringDemo{
	public static void main(String[] args){
		char[] chars = {'T', 'e', 'n', 's', 'o', 'r', 'F', 'l', 'o', 'w'};
		String str = new String(chars);
		System.out.println(str);
		
		System.out.println("abc".length());
		
		System.out.println("abc" instanceof String);
		
		System.out.println("abc" == "a" + "bc");
		
		System.out.println("abc" == new String("abc"));
		
		System.out.println("abc" == new String("abc").intern()); // explictly call the intern() method.
	}
}