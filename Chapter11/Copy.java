import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Copy{
	public static void main(String[] args){
		if(args.length != 2){
			System.err.println("usage: java Copy srcfile dstfile");
			return;
		}
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try{
			fis = new FileInputStream(args[0]);
			fos = new FileOutputStream(args[1]);
			int b;
			while((b=fis.read())!=-1){
				fos.write(b);
			}
		}catch(FileNotFoundException fnfe){
			fnfe.printStackTrace();
		}catch(IOException ioe){
			ioe.printStackTrace();
		}finally{
			if(fis!=null){
				try{
					fis.close();
				}catch(IOException ioe){
					ioe.printStackTrace();
				}
			}
			if(fos!=null){
				try{
					fos.close();
				}catch(IOException ioe){
					ioe.printStackTrace();
				}
			}
		}
	}
}