import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

class X{
	public void objectMethod(String arg){
		System.out.println("Instance method: " + arg);
	}
	
	public static void classMethod(){
		System.out.println("Class method.");
	}
}

public class MethodInvocationDemo{
	public static void main(String[] args){
		try{
			Class<?> clazz = Class.forName("X");
			X x = (X)clazz.newInstance();
			
			Method method = clazz.getMethod("objectMethod", new Class[]{String.class});
			method.invoke(x, "This is method argument.");
			
			method = clazz.getMethod("classMethod", (Class[])null);  // (Class[])null is equivalent to the empty array.
			method.invoke(null, (Object[])null); // second parameter is equivalent to an empty array. It is prefered instead of an empty array's allocation.
		}catch(ClassNotFoundException cnfe){
			cnfe.printStackTrace();
		}catch(InstantiationException ie){
			ie.printStackTrace();
		}catch(NoSuchMethodException nsme){
			nsme.printStackTrace();
		}catch(IllegalAccessException iae){
			iae.printStackTrace();
		}catch(InvocationTargetException ite){
			ite.printStackTrace();
		}
	}
}