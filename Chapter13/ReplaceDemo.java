import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class ReplaceDemo{
	
	public static void main(String[] args){
		if(args.length != 3){
			System.err.println("usage: java ReplaceDemo regex input replace");
			return;
		}
		Pattern pattern = Pattern.compile(args[0]);
		Matcher matcher = pattern.matcher(args[1]);
		String output = matcher.replaceAll(args[2]);
		System.out.println("Output after replacement: " + output);
	}
}