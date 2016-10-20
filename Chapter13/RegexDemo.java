import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.regex.PatternSyntaxException;

public class RegexDemo{
	
	public static void main(String[] args){
		if(args.length != 2){
			System.err.println("usage: java RegexDemo regex input");
			return;
		}
		System.out.println("regex = " + args[0]);
		System.out.println("input = " + args[1]);
		try{
			Pattern pattern = Pattern.compile(args[0]);
			Matcher matcher = pattern.matcher(args[1]);
			while(matcher.find()){
				System.out.println("Located [" + matcher.group() + "] starting at " + matcher.start() + " and ending at " + (matcher.end()-1));
				// need to understand the methods return values.
			}
		}catch(PatternSyntaxException pse){
			System.err.println("Bad regex: " + pse.getMessage());
			System.err.println("Description: " + pse.getDescription());
			System.err.println("Index: " + pse.getIndex());
			System.err.println("Incorrect pattern: " + pse.getPattern());
		}
	}
}