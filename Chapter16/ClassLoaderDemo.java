import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class ClassLoaderDemo{
	final static String URL_X = "file:///" + System.getProperty("user.dir") + "/x/";
	final static String URL_Y = "file:///" + System.getProperty("user.dir") + "/y/";
	
	public static void main(String[] args){
		boolean init = true;
		if(args.length == 1 && args[0].equalsIgnoreCase("noinit")){
			init = false;
		}
		try{
			URL[] urls =new URL[]{new URL(URL_X)};
			URLClassLoader urlc = new URLClassLoader(urls);
			Class<?> clazz = Class.forName("Version", init, urlc);
			System.out.println(clazz.getClassLoader());
			run(clazz);
			
			URL[] urls2 = new URL[]{new URL(URL_Y)};
			URLClassLoader urlc2 = new URLClassLoader(urls2);
			Class<?> clazz2 = Class.forName("Version", init, urlc2);
			System.out.println(clazz2.getClassLoader());
			run(clazz2);
			
			//run(Thread.currentThread().getContextClassLoader().loadClass("Version"));
			
			Thread.currentThread().setContextClassLoader(urlc);
			
			run(Thread.currentThread().getContextClassLoader().loadClass("Version"));
			
			run(ClassLoader.getSystemClassLoader().loadClass("Version"));
			
			run(Class.forName("Version"));
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