import java.net.CookieManager;
import java.net.CookieHandler;
import java.net.URL;
import java.net.HttpCookie;

import java.io.IOException;

public class ListAllCookies{
	
	public static void main(String[] args){
		if(args.length < 1){
			System.err.println("usage: java ListAllCookies url");
			return;
		}
		CookieManager cm = new CookieManager();
		CookieHandler.setDefault(cm);
		try{
			for(String url : args){
				new URL(url).openConnection().getContent();
			}
			for(HttpCookie cookie : cm.getCookieStore().getCookies()){
				System.out.println("Domain = " + cookie.getDomain());
				System.out.println("Name = " + cookie.getName());
				System.out.println("Value = " + cookie.getValue());
				System.out.println("Lifetime = " + cookie.getMaxAge());
				// unit in second, default -1, indicating cookie persist until browser shutdown.
				System.out.println("Path = " + cookie.getPath());
				System.out.println();
			}
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
	}
}