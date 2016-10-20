import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import java.util.Date;

import java.lang.NumberFormatException;
import java.io.IOException;

import java.net.InetSocketAddress;

public class SelectorClient{
	static ByteBuffer buffer = ByteBuffer.allocateDirect(9);
	
	public static void main(String[] args) throws IOException{
		int port = SelectorServer.SERVER_PORT;
		if(args.length>0){
			try{
				port = Integer.parseInt(args[0]);
			}catch(NumberFormatException nfe){
				nfe.printStackTrace();
			}
		}
		SocketChannel sc = SocketChannel.open(new InetSocketAddress(SelectorServer.SERVER_HOST, port));
		
		long time = 0;
		int readLen = 0;
		while((readLen = sc.read(buffer)) != -1){
			System.out.print("+" + readLen);
		};
		System.out.println();
		buffer.flip();
		time = buffer.getLong();
		System.out.println("Received Server Time = " + new Date(time));
		// close the channel? or close the socket?
		sc.close();
	}
}