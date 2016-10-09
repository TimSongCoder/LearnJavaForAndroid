import java.net.URL;
import java.net.JarURLConnection;
import java.net.MalformedURLException;
import java.io.IOException;
import java.util.jar.Manifest;

/**
 *@see <a href="https://docs.oracle.com/javase/7/docs/api/java/net/JarURLConnection.html">JarURLConnection doc, very detailed.</a>
 */
public class JarURLConnectionDemo{
	public static void main(String[] args){
		if(args.length!=1){
			System.err.println("usage: java JarURLConnectionDemo jarUrl");
			return;
		}
		try{
			URL url = new URL(args[0]);
			JarURLConnection jarUrlConnection = (JarURLConnection) url.openConnection();
			Manifest manifest = jarUrlConnection.getManifest();
			System.out.println("Jar's manifest entries: " + manifest.getEntries());
		}catch(MalformedURLException mue){
			System.err.println("Invalid url.");
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
	}
}