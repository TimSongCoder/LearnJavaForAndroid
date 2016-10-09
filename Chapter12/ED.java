import java.net.URLEncoder;
import java.net.URLDecoder;
import java.io.UnsupportedEncodingException;

public class ED{
	public static void main(String[] args) throws UnsupportedEncodingException{
		String sampleStr;
		if(args.length == 1){
			sampleStr = args[0];
		}else{
			sampleStr = "string $@foo-bar";
		}
		System.out.println("Original data: " + sampleStr);
		String encodedData = URLEncoder.encode(sampleStr, "utf-8");
		System.out.println("Data After Encoded: " + encodedData);
		System.out.println("Data After Decoded: " + URLDecoder.decode(encodedData, "utf-8"));
	}
}