import java.util.regex.Pattern;

public class PatternSplit{
	
	public static void main(String[] args){
		if(args.length!=2){
			System.err.println("usage: java Pattern pattern input");
			return;
		}
		Pattern pattern = Pattern.compile(args[0]);
		String[] items = pattern.split(args[1]); // extract the text around the matches.
		for(String item : items){
			System.out.println(item);
		}
	}
}