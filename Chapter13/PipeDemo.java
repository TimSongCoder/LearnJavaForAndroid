import java.nio.channels.Pipe;
import java.nio.channels.Pipe.SinkChannel;
import java.nio.channels.Pipe.SourceChannel;
import java.nio.ByteBuffer;

import java.io.IOException;

public class PipeDemo{
	
	private static final int BUFFER_SIZE = 10;
	private static final int SEND_LIMIT = 3;
	
	public static void main(String[] args) throws IOException{
		Pipe pipe = Pipe.open();
		Runnable senderTask = new Runnable(){
			public void run(){
				SinkChannel writeChannle = pipe.sink();
				ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
				for(int i=0;i<SEND_LIMIT;i++){
					buffer.clear(); // prepare for new round
					for(int j=0; j<BUFFER_SIZE; j++){
						buffer.put((byte)(Math.random()*256));
					}
					buffer.flip();
					try{
						while(writeChannle.write(buffer) > 0);
					}catch(IOException ioe){
						ioe.printStackTrace();
					}
				}
				if(writeChannle!=null){
					try{
						writeChannle.close();  // prevent the reading thread block
					}catch(IOException ioe){
						ioe.printStackTrace();
					}
				}
			}
		};
		Runnable receiverTask = new Runnable(){
			public void run(){
				SourceChannel srcChannle = pipe.source();
				ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
				try{
					while(srcChannle.read(buffer) >= 0){ // -1 indicating the end-of-stream
						buffer.flip();
						while(buffer.hasRemaining()){
							System.out.println(Byte.toUnsignedInt(buffer.get()));
						}
						buffer.clear(); // prepare for next read operation.
					}
				}catch(IOException ioe){
					ioe.printStackTrace();
				}
			}
		};
		new Thread(senderTask).start();
		new Thread(receiverTask).start();
	}
}