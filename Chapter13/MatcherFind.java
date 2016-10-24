import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class MatcherFind{
	
	public static void main(String[] args){
		if(args.length != 2){
			System.err.println("usage: java MatcherFind regex input");
			return;
		}
		Pattern pattern = Pattern.compile(args[0]);
		Matcher matcher = pattern.matcher(args[1]);
		int count = 0;
		while(matcher.find()){
			count++;
			System.out.println("Matcher number " + count);
			System.out.println("start(): " + matcher.start());
			System.out.println("end(): " + matcher.end());
		}
	}
}