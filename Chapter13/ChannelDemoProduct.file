import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.channels.Channels;
import java.nio.ByteBuffer;
import java.io.IOException;

public class ChannelDemo{
	
	public static void main(String[] args){
		ReadableByteChannel readChannel = Channels.newChannel(System.in);
		WritableByteChannel writeChannel = Channels.newChannel(System.out);
		
		try{
			int command = (int)(Math.random() * 2);
			switch(command){
				case 0:
					System.err.println("command = " + command + ", use copy(..) method.");
					System.err.println();
					copy(readChannel, writeChannel);
					break;
				case 1:
					System.err.println("command = " + command + ", use copyAlt(..) method.");
					System.err.println();
					copyAlt(readChannel, writeChannel);
					break;
			}
		}catch(IOException ioe){
			ioe.printStackTrace();
		}finally{
			try{
			readChannel.close();
			writeChannel.close();
			}catch(IOException ioe){
				ioe.printStackTrace();
			}
		}
	}
	
	static void copy(ReadableByteChannel readChannel, WritableByteChannel writeChannel) throws IOException{
		ByteBuffer buffer = ByteBuffer.allocateDirect(2048);
		while(readChannel.read(buffer) != -1){
			buffer.flip();
			writeChannel.write(buffer); // maybe not drain the buffer completely.
			buffer.compact();
		}
		// read ended.
		buffer.flip();
		while(buffer.hasRemaining()){
			writeChannel.write(buffer);
		}
	}
	
	static void copyAlt(ReadableByteChannel readChannel, WritableByteChannel writeChannel) throws IOException{
		ByteBuffer buffer = ByteBuffer.allocateDirect(2048);
		while(readChannel.read(buffer) !=-1){
			buffer.flip();
			while(buffer.hasRemaining()){
				writeChannel.write(buffer);
			}
			// Drained the buffer completely now.
			buffer.clear();
			// Ready for next read process.
		}
	}
}