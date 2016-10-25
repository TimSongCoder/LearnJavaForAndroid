import java.nio.channels.FileChannel;
import java.nio.ByteBuffer;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Copy{
	
	public static void main(String[] args){
		if(args.length != 2){
			System.err.println("usage: java Copy srcFile destFile");
			return;
		}
		FileChannel inChannel = null;
		FileChannel outChannel = null;
		try{
			FileInputStream fis = new FileInputStream(args[0]);
			FileOutputStream fos = new FileOutputStream(args[1]);
			inChannel = fis.getChannel();
			outChannel = fos.getChannel();
			ByteBuffer buffer = ByteBuffer.allocate(128);
			while(inChannel.read(buffer) != -1){
				buffer.flip();
				outChannel.write(buffer);
				buffer.compact();
			}
		}catch(IOException ioe){
			ioe.printStackTrace();
		}finally{
			if(inChannel!=null){
				try{
					inChannel.close();
				}catch(IOException ioe){
					ioe.printStackTrace();
				}
			}
			if(outChannel != null){
				try{
					outChannel.close();
				}catch(IOException ioe){
					ioe.printStackTrace();
				}
			}
		}
		
	}
}