import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;

public class Split{
	public static void main(String[] args){
		if(args.length!=2){
			System.err.println("usage: java Split pathname partsize");
			return;
		}
		File srcFile = new File(args[0]);
		if(!srcFile.exists()){
			System.err.println("File does not exist.");
			return;
		}
		if(srcFile.isDirectory()){
			System.err.println("The specified file is a directory.");
			return;
		}
		long srcSize = srcFile.length(); // unit byte.
		long partSize = 0;
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try{
			partSize = (long)(Float.parseFloat(args[1]) * 1024 * 1024); // unit megabyte.
			int quotient = (int)(srcSize/partSize);
			int partCount = srcSize % partSize==0 ? quotient : quotient+1;
			
			int partOffset = 0;
			File partFile = new File(srcFile.getName() + "_part_" + partOffset);
			
			int read;
			int partFileSize = 0;
			bis = new BufferedInputStream(new FileInputStream(srcFile));
			bos = new BufferedOutputStream(new FileOutputStream(partFile));
			while((read=bis.read())!=-1){
				if(partFileSize == partSize){
					partOffset++;
					partFile = new File(srcFile.getName() + "_part_" + partOffset);
					partFileSize = 0;
					bos.flush();
					bos.close();
					bos = new BufferedOutputStream(new FileOutputStream(partFile));
				}
				bos.write(read);
				partFileSize ++;
			}
		}catch(NumberFormatException nfe){
			nfe.printStackTrace();
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