import java.io.File;

public class Touch{
	public static void main(String[] args){
		if(args.length!=1){
			System.err.println("usage: java Touch pathname");
			return;
		}
		File file = new File(args[0]);
		if(file.exists()){
			file.setLastModified(System.currentTimeMillis());
		}else{
			System.err.println("The specified file does not exist.");
		}
	}
}