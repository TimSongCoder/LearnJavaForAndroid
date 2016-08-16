import java.util.StringTokenizer;

public class Tokenize{
	public static void main(String[] args){
		String source = "03-12-2014 03:05:20";
		StringTokenizer tokenizer = new StringTokenizer(source, "- :"); // second parameter specifies the delimiters, character by character.
		String[] tokens = new String[tokenizer.countTokens()];
		int i = 0;
		while(tokenizer.hasMoreTokens()){
			tokens[i] = tokenizer.nextToken();
			i++;
		}
		System.out.println("month: " + tokens[0]);
		System.out.println("day: " + tokens[1]);
		System.out.println("year: " + tokens[2]);
		System.out.println("hour: " + tokens[3]);
		System.out.println("minute: " + tokens[4]);
		System.out.println("second: " + tokens[5]);
	}
}