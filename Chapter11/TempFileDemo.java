import java.io.File;
import java.io.IOException;

import java.util.Random;

public class TempFileDemo{
	public static void main(String[] args) throws IOException{
		System.out.println("default relative/user directory: " + System.getProperty("user.dir"));  // current directory where JVM lanched from.
		System.out.println("default temp directory: " + System.getProperty("java.io.tmpdir"));
		
		File tempFile = File.createTempFile("text", ".txt");
		System.out.println("tempFile pathname: " + tempFile);
		System.out.println("tempFile existing: " + tempFile.exists());
		
		tempFile.deleteOnExit(); // delete the file after JVM terminated.
		
		try{
			System.out.println("Simulating operations for 5 seconds...");
			Thread.sleep(5000);
			// during this period, you can observe tempFile existing and deleting.
		}catch(InterruptedException ie){
			ie.printStackTrace();
		}
		
		Random random = new Random();
		if(random.nextBoolean()){
			System.out.println("Simulating error occured before application terminated normally.");
			throw new NullPointerException("Fake Exception Just for Demo Intention.");
			// Still can not simulate the deleting failure scenario through uncaught exception, which may be handled by JVM itself.
			// Finally, I succeed to simulate what I expect through terminating the Terminal application with force. Then the tempFile was not deleted.
		}
		
		System.out.println("Application is terminating.");
	}
}