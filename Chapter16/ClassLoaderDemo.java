import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class ClassLoaderDemo{
	final static String _URL_ = "file:///" + System.getProperty("user.dir") + "/x/";
	
	public static void main(String[] args){
		boolean init = true;
		if(args.length == 1 && args[0].equalsIgnoreCase("noinit")){
			init = false;
		}
		try{
			URL[] urls =new URL[]{new URL(_URL_)};
			URLClassLoader urlc = new URLClassLoader(urls);
			Class<?> clazz = Class.forName("Hello", init, urlc);
			System.out.println(clazz.getClassLoader());
			run(clazz);
		}catch(ClassNotFoundException | MalformedURLException classLoadException){
			classLoadException.printStackTrace();
		}
	}
	
	static void run(Class<?> clazz){
		try{
			Method main = clazz.getMethod("main", new Class[]{String[].class});
			Object[] args = new Object[]{new String[0]};
			main.invoke(null, args);
		}catch(NoSuchMethodException | IllegalAccessException | InvocationTargetException invokeMainException){
			invokeMainException.printStackTrace();
		}
	}
}