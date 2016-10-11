import java.net.URI;
import java.net.URISyntaxException;

public class Relativize{
	
	public static void main(String[] args){
		if(args.length!=2){
			System.err.println("usage: java Relativize baseURI uriToBeRelativized");
			return;
		}
		try{
			URI baseURI = new URI(args[0]);
			System.out.println("Relativized URI = " + baseURI.relativize(new URI(args[1])));
		}catch(URISyntaxException use){
			use.printStackTrace();
		}
	}
}