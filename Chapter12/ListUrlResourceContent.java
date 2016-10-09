import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.net.URL;
import java.net.MalformedURLException;
import java.net.URLConnection;
import java.io.IOException;
/*
* @see <a href="https://docs.oracle.com/javase/7/docs/api/java/net/URLConnection.html">URLConnection doc</a>
* @see <a href="https://docs.oracle.com/javase/7/docs/api/java/net/HttpURLConnection.html">HttpURLConnection doc</a>
* @see <a href="https://docs.oracle.com/javase/7/docs/api/java/net/JarURLConnection.html">JarURLConnection doc, very detailed.</a>
*/
public class ListUrlResourceContent{
	public static void main(String[] args){
		if(args.length!=1){
			System.err.println("usage: java ListResource url");
			return;
		}
		BufferedReader br = null;
		InputStream is = null;
		try{
			URL url = new URL(args[0]);
			URLConnection urlConnection = url.openConnection();
			// Explore the connection setup parameters and the general request properties
			System.out.println("AllowUserInteraction: " + urlConnection.getAllowUserInteraction());
			System.out.println("IfModifiedSince: " + urlConnection.getIfModifiedSince());  
			// default value is 0. fetch only occur if the current time is more recently than the field value.
			System.out.println("AllowUseCaches: " + urlConnection.getUseCaches());
			System.out.println("AllowDoOutput: " + urlConnection.getDoOutput());
			System.out.println("RequestProperties: " + urlConnection.getRequestProperties());
			
			// Manipulation about connection setup parameters and general request properties need to be done before invoking below method.
			urlConnection.connect();
			System.out.println("create connection time: " + System.currentTimeMillis());
			
			// Explore the header fields and contents; Interact with the remote resource referenced by the url. content about the fetched object.
			System.out.println("getContent(): " + urlConnection.getContent());
			System.out.println("getContentEncoding(): " + urlConnection.getContentEncoding());
			System.out.println("getContentLength(): " + urlConnection.getContentLength());
			System.out.println("getContentType(): " + urlConnection.getContentType());
			System.out.println("getDate(): " + urlConnection.getDate());
			System.out.println("getExpiration(): " + urlConnection.getExpiration());
			System.out.println("getLastModified(): " + urlConnection.getLastModified());
			
			/*is = urlConnection.getInputStream(); // may throw UnknownHostException e.g for http://tutortutor.ca
			br = new BufferedReader(new InputStreamReader(is, "utf-8"));
			String reading;
			while((reading = br.readLine()) != null){
				System.out.println(reading);
			}*/
		}catch(MalformedURLException mue){
			System.err.println("The url is invalid.");
		}catch(IOException ioe){
			ioe.printStackTrace();
		}finally{
			if(is!=null){
				try{
					is.close();
				}catch(IOException ioe){
					ioe.printStackTrace();
				}
			}
		}
	}
}