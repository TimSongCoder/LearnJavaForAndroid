import java.nio.channels.Channels;
import java.nio.channels.ScatteringByteChannel;
import java.nio.channels.GatheringByteChannel;
import java.nio.ByteBuffer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ScatterGatherChannel{
	
	public static void main(String[] args) throws IOException{
		if(args.length!=2){
			System.err.println("usage: java ScatterGatherChannel inputFile outputFile");
			return;
		}
		ScatteringByteChannel readChannel = (ScatteringByteChannel) Channels.newChannel(new FileInputStream(args[0]));
		ByteBuffer buffer1 = ByteBuffer.allocateDirect(5);
		ByteBuffer buffer2 = ByteBuffer.allocateDirect(10);
		ByteBuffer[] buffers = {buffer1, buffer2};
		readChannel.read(buffers);
		System.out.println("buffers' limit: " + buffer1.limit() + ", " + buffer2.limit());
		// Need do flip operation.
		buffer1.flip();
		buffer2.flip();
		
		// Inspect the contents of the buffer after reading.
		while(buffer1.hasRemaining()){
			System.out.println("buffer1 element at position " + buffer1.position() + " : " + buffer1.get());
		}
		System.out.println();
		while(buffer2.hasRemaining()){
			System.out.println("buffer2 element at position " + buffer2.position() + " : " + buffer2.get());
		}
		// prepare for writing operation.
		buffer1.rewind();
		buffer2.rewind();
		
		GatheringByteChannel writeChannel = (GatheringByteChannel) Channels.newChannel(new FileOutputStream(args[1]));
		buffers[0] = buffer2;
		buffers[1] = buffer1;
		writeChannel.write(buffers);
		
		readChannel.close();
		writeChannel.close();
	}
}