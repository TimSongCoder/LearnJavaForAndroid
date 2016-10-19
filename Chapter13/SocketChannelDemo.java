import java.nio.channels.SocketChannel;
import java.nio.ByteBuffer;

import java.net.InetSocketAddress;

import java.io.IOException;

public class SocketChannelDemo{
	
	public static void main(String[] args){
		SocketChannel sc = null;
		try{
			sc = SocketChannel.open();
			sc.configureBlocking(false);
			sc.connect(new InetSocketAddress("localhost", 9999));
			while(!sc.finishConnect()){
				System.out.println("waiting to finish connection");
			}
			// connection completed
			ByteBuffer buffer = ByteBuffer.allocate(200);
			while(sc.read(buffer) >= 0){
				buffer.flip();
				while(buffer.hasRemaining()){
					System.out.print((char)buffer.get());
				}
				buffer.clear(); // prepare for next reading operation
			}
		}catch(IOException ioe){
			ioe.printStackTrace();
		}finally{
			if(sc!=null){
				try{
					sc.close();
				}catch(IOException ioe){
					ioe.printStackTrace();
				}
			}
		}
	}
}