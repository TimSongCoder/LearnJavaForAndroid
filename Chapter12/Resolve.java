import java.net.URI;
import java.net.URISyntaxException;

public class Resolve{
	
	public static void main(String[] args){
		if(args.length!=2){
			System.err.println("usage: java Resolve baseURI uriToBeResolved");
			return;
		}
		try{
			URI baseURI = new URI(args[0]);
			System.out.println("Resolved URI = " + baseURI.resolve(args[1]));
		}catch(URISyntaxException use){
			use.printStackTrace();
		}
	}
}