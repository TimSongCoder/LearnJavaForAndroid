import java.io.File;
import java.io.IOException;

public class CompareFile{
	public static void main(String[] args) throws IOException{
		if(args.length!=2){
			System.err.println("usage: java CompareFile file1 file2");
			return;
		}
		File file1 = new File(args[0]);
		File file2 = new File(args[1]);
		System.out.println("file1.compareTo(file2) with return value= " + file1.compareTo(file2));
		System.out.println("file1CanonicalFile.compareTo(file2CanonicalFile) with return value= "
							+ file1.getCanonicalFile().compareTo(file2.getCanonicalFile()));
	}
}