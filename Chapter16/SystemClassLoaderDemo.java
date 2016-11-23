public class SystemClassLoaderDemo{
	public static void main(String[] args){
		// Get the main thread associated context class loader.
		System.out.println(Thread.currentThread().getContextClassLoader());
		// Get the system class loader associated with this application.
		System.out.println(ClassLoader.getSystemClassLoader());
	}
}