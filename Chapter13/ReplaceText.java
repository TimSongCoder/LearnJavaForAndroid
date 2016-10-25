import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class ReplaceText{
	
	public static void main(String[] args){
		if(args.length!=3){
			System.err.println("usage: java ReplaceText regex input replacement");
			return;
		}
		Pattern pattern = Pattern.compile(args[0]);
		Matcher matcher = pattern.matcher(args[1]);
		String output = matcher.replaceAll(args[2]);
		System.out.println("Output after replacement: " + output);
	}
}