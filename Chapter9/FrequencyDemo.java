import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FrequencyDemo{
	public static void main(String[] args){
		if(args.length < 2){
			System.err.println("usage: java FrequencyDemo Content... target");
			return;
		}else{
			List<String> strList = Arrays.asList(Arrays.copyOf(args, args.length - 1));
			String target = args[args.length -1];
			int frequency = Collections.frequency(strList, target);
			System.out.println("Number of occurrences of " + target + " = " + frequency);
		}
	}
}