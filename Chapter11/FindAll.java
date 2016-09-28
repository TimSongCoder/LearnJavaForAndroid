import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class FindAll{
	public static void main(String[] args){
		if(args.length!=2){
			System.err.println("usage: java FindAll scope keyword");
			return;
		}
		if(!findAll(new File(args[0]), args[1])){
			System.err.println(args[0] + "is not a directory");
		}
	}
	
	static boolean findAll(File dir, String keyword){
		File[] files = dir.listFiles();
		if(files == null){
			// Not a directory or IO error
			return false;
		}
		File tempFile;
		for(int i=0; i<files.length; i++){
			tempFile = files[i];
			if(tempFile.isDirectory()){
				findAll(tempFile, keyword); // invoke this method recursively
			}else{
				if(find(tempFile, keyword)){
					System.out.println(tempFile.getPath());
				}
			}
		}
		return true;
	}
	
	static boolean find(File file, String keyword){
		BufferedReader br = null;
		try{
			br = new BufferedReader(new FileReader(file));
			int ch;
			outer_loop:
			do{
				if((ch=br.read())==-1){
					return false;
				}
				if(ch==keyword.charAt(0)){ //check the head character
					for(int i=1;i<keyword.length();i++){
						if((ch=br.read())==-1){
							return false;
						}
						if(ch!=keyword.charAt(i)){
							continue outer_loop;
						}
					}
					return true;  // match for one found
				}
			}while(true);
		}catch(IOException ioe){
			ioe.printStackTrace();
		}finally{
			if(br!=null){
				try{
				br.close();
				}catch(IOException ioe){
					ioe.printStackTrace();
				}
			}
		}
		return false;
	}
}