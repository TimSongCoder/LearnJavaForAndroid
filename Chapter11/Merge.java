import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.io.FilenameFilter;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;

public class Merge{
	public static void main(String[] args){
		File dir = new File(".");
		File[] files = dir.listFiles(new FilenameFilter(){
			@Override
			public boolean accept(File dir, String name){
				return name.contains("_part_");
			}
		});
		if(files.length < 1){
			System.err.println("No valid part files.");
			return;
		}
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try{
			String temp = files[0].getName();
			File mergeFile = new File("Merge_" + temp.substring(0, temp.indexOf("_")));
			bos = new BufferedOutputStream(new FileOutputStream(mergeFile));
			for(File file: files){
				bis = new BufferedInputStream(new FileInputStream(file));
				int read;
				while((read = bis.read())!=-1){
					bos.write(read);
				}
				bis.close();
			}
		}catch(IOException ioe){
			ioe.printStackTrace();
		}finally{
			if(bis!=null){
				try{
					bis.close();
				}catch(IOException ioe){
					ioe.printStackTrace();
				}
			}
			if(bos!=null){
				try{
					bos.close();
				}catch(IOException ioe){
					ioe.printStackTrace();
				}
			}
		}
	}
}