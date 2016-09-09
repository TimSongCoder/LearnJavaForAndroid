import java.io.File;

public class Permissions{
	
	public static void main(String[] args){
		if(args.length!=1){
			System.err.println("usage: java Permissions pathname");
			return;
		}
		File file = new File(args[0]);
		System.out.println("Checking permissions for " + file);
		System.out.println("Execute = " + file.canExecute());
		// true if and only if the abstract pathname exists and the application is allowed to execute the file.
		System.out.println("Read = " + file.canRead());
		// true if and only if the file specified by this abstract pathname exists and can be read by the application.
		System.out.println("Write = " + file.canWrite());
		// true if and only if the file system actually contains a file denoted by this abstract pathname and the application is allowed to write to the file.
	}
}