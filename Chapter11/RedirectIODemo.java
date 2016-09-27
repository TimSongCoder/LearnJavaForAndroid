import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.Date;
import java.io.IOException;

public class RedirectIODemo{
	public static void main(String[] args) throws IOException{
		if(args.length!=3){
			System.err.println("usage: java RedirectIODemo inputSource outDest errDest.");
			return;
		}
		System.setIn(new FileInputStream(args[0]));
		System.setOut(new PrintStream(args[1]));
		System.setErr(new PrintStream(args[2]));
		
		int ch;
		while((ch=System.in.read())!=-1){
			//System.out.print((char)ch); // with charset problem
			System.out.write(ch);
		}
		
		System.err.println("This is error log.");
		System.err.println();
		System.err.println("Permission Denied." + new Date(System.currentTimeMillis()));
	}
}