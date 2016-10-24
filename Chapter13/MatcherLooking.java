import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class MatcherLooking{
	
	public static void main(String[] args){
		if(args.length!=2){
			System.err.println("usage: java MatcherLooking regex input");
			return;
		}
		Pattern pattern = Pattern.compile(args[0]);
		Matcher matcher = pattern.matcher(args[1]);
		System.out.println("lookingAt(): " + matcher.lookingAt());
		System.out.println("matches(): " + matcher.matches());
	}
}