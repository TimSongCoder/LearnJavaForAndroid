import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.net.URL;
import java.net.MalformedURLException;
import java.io.IOException;

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
			is = url.openStream(); // may throw UnknownHostException e.g for http://tutortutor.ca
			br = new BufferedReader(new InputStreamReader(is, "utf-8"));
			String reading;
			while((reading = br.readLine()) != null){
				System.out.println(reading);
			}
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