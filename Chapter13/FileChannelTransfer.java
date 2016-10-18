import java.io.FileInputStream;
import java.io.IOException;

import java.nio.channels.FileChannel;
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;

public class FileChannelTransfer{
	
	public static void main(String[] args){
		if(args.length!=1){
			System.err.println("usage: java FileChannelTransfer file");
			return;
		}
		FileInputStream fis = null;
		try{
			fis = new FileInputStream(args[0]);
			FileChannel fc = fis.getChannel(); // position initially sunsequent input stream reading position
			WritableByteChannel outChannel = Channels.newChannel(System.out);
			fc.transferTo(0, fc.size(), outChannel);  // transfer bytes to another channel without intermediate buffers.
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
		}
	}
}