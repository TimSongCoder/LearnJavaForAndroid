import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class AppendReplacementDemo{
	
	public static void main(String[] args){
		if(args.length != 3){
			System.err.println("usage: java AppendReplacementDemo regex input replace");
			return;
		}
		Pattern pattern = Pattern.compile(args[0]);
		Matcher matcher = pattern.matcher(args[1]);
		StringBuffer sb = new StringBuffer();
		while(matcher.find()){
			matcher.appendReplacement(sb, args[2]);
		}
		System.out.println("Output after appendReplacement: " + sb);
		// use appendTail to copy the remainder.
		matcher.appendTail(sb);
		System.out.println("Output after appendTail: " + sb);
	}
}