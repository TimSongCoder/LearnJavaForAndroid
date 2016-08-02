/*这么设计Float与Double，是为了在hash-based集合框架中正确地使用他们。*/
public class FloatDoubleDemo{
	public static void main(String[] args){
		Float f1 = new Float(Float.NaN);
		System.out.println("NaN f1 float value : " + f1.floatValue());
		
		Float f2 = new Float(Float.NaN);
		System.out.println("NaN f2 float value : " + f2.floatValue());
		
		System.out.println("f1.equals(f2) is : " + f1.equals(f2));
		
		System.out.println("Float.NaN == Float.NaN is :" + (Float.NaN == Float.NaN));
		System.out.println();
		
		Double d1 = new Double(+0.0);
		System.out.println("d1.doubleValue : " + d1.doubleValue());
		
		Double d2 = new Double(-0.0);
		System.out.println("d2.doubleValue : " + d2.doubleValue());
		
		System.out.println("d1.equals(d2) is : " + d1.equals(d2));
		
		System.out.println("+0.0 == -0.0 is : " + (+0.0==-0.0));
	}
}