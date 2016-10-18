import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.ByteBuffer;

import java.net.InetSocketAddress;

import java.io.IOException;

public class ServerSocketChannelDemo{
	
	public static void main(String[] args) throws IOException{
		System.out.println("Starting server...");
		ServerSocketChannel ssc = ServerSocketChannel.open(); // unbound
		ssc.bind(new InetSocketAddress(9999));
		// Configure the blocking mode to be false
		ssc.configureBlocking(false);
		String msg = "Local address: " + ssc.getLocalAddress();
		ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
		while(true){
			System.out.print(".");
			SocketChannel sc = ssc.accept(); // non-blocking
			if(sc!=null){
				System.out.println();
				System.out.println("Received connection from " + sc.getRemoteAddress());
				buffer.rewind(); // set the position to be 0
				sc.write(buffer);
				sc.close();
			}else{
				try{
					Thread.sleep(100);
				}catch(InterruptedException ie){
					ie.printStackTrace();
				}
			}
		}
	}
}