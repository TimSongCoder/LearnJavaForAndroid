import java.io.File;
import java.io.FilenameFilter;

public class Dir{
	public static void main(String[] args){
		if(args.length!=2){
			System.err.println("usage: java Dir dirpath extension");
			return;
		}
		
		File dir = new File(args[0]);
		FilenameFilter filenameFilter = new FilenameFilter(){
			@Override
			public boolean accept(File dir, String name){
				System.out.println("accept test: dir=" + dir + ", name=" + name);
				return name.endsWith(args[1]);
			}
		};
		
		String[] filenames = dir.list(filenameFilter);
		
		System.out.println();
		
		if(filenames!=null){  // may be null because of non-directory dir argument or IO error.
			for(String filename: filenames){
				System.out.println(filename);
			}
		}
	}
}