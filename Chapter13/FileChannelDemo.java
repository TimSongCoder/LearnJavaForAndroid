import java.io.RandomAccessFile;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.ByteBuffer;

public class FileChannelDemo{
	
	public static void main(String[] args) throws IOException{
		RandomAccessFile file = new RandomAccessFile("FileChannelDemo.temp", "rw");
		FileChannel fileChannel = file.getChannel();
		long pos;
		System.out.println("Underlying file position: " + (pos = fileChannel.position()) 
							+ ", file size: " + fileChannel.size());
		String msg = null;
		if(args.length > 0){
			msg = args[0];
		}else{
			msg = "This is a demo message for test.";
		}
		ByteBuffer buffer = ByteBuffer.allocateDirect(msg.length()*2); // char is two byte long.
		buffer.asCharBuffer().put(msg); // CharBuffer used as a view buffer, its change is visible to the backing ByteBuffer.
		fileChannel.write(buffer);
		// recommend the operating system to commit the written data to storage device, including metadata.
		fileChannel.force(true);
		System.out.println("Underlying file position: " + fileChannel.position() 
							+ ", file size: " + fileChannel.size());
		buffer.clear(); // prepare for refresh use as newly created buffer.
		
		fileChannel.position(pos); // prepare for read operation
		fileChannel.read(buffer);
		// Inspect the buffer data after read.
		buffer.flip();
		while(buffer.hasRemaining()){
			System.out.print(buffer.getChar());  // retrieve 2 bytes per time.
		}
	}
}