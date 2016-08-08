import java.lang.reflect.Field;

public class FieldAccessDemo{
	public static void main(String[] args){
		Field f = null;
		try{
			Class<?> clazz = Class.forName("X");
			X x = (X)clazz.newInstance();
			f = clazz.getField("i");
			System.out.println(f.getInt(x));
			f.setInt(x, 20);
			System.out.println(f.getInt(x));
			
			f = clazz.getField("PI");
			System.out.println(f.getDouble(null)); // The argument is ignored for static field, otherwise NullPointerException will be thrown out.
			f.setDouble(x, 20); // It is not accessible with final modifier.
		}catch(ClassNotFoundException cnfe){
			cnfe.printStackTrace();
		}catch(InstantiationException ie){
			ie.printStackTrace();
		}catch(NoSuchFieldException nsfe){
			nsfe.printStackTrace();
		}catch(IllegalAccessException iae){
			iae.printStackTrace();
		}finally{
			try{
				if(f!=null){
					System.out.println(f.getDouble(null));
				}
			}catch(IllegalAccessException iae){
				iae.printStackTrace();
			}
		}
	}
}

class X{
	public int i = 10;
	public static final double PI = 3.14;
}