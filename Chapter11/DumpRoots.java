import java.io.File;

public class DumpRoots{
	public static void main(String[] args){
		File[] roots = File.listRoots();  // may return null according to the doc explanation.
		if(roots != null){
			for(File root: roots){
				System.out.println(root);
			}
		}
	}
}