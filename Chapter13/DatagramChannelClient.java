import java.nio.channels.DatagramChannel;
import java.nio.ByteBuffer;

import java.net.InetSocketAddress;

import java.io.IOException;

public class DatagramChannelClient{
	
	public static void main(String[] args) throws IOException{
		if(args.length!=1){
			System.err.println("usage: java DatagramChannelClient StockSymbol");
			return;
		}
		DatagramChannel dc = DatagramChannel.open();
		ByteBuffer symbolBuffer = ByteBuffer.wrap(args[0].getBytes());
		ByteBuffer response = ByteBuffer.allocate(16); // DatagramChannel.receive() method can only handle ByteBuffer type.
		
		InetSocketAddress serverAddress = new InetSocketAddress(DatagramChannelServer.SERVER_HOST, DatagramChannelServer.SERVER_PORT);
		dc.send(symbolBuffer, serverAddress);
		
		// receive the server response
		System.out.println("Receiving datagram from " + dc.receive(response));  // blocking mode
		System.out.println("Open price: " + response.getFloat(0));  
		// can use FloatBuffer as view buffer to manipulate the element in float like the server implementation.
		System.out.println("Low price: " + response.getFloat(4));
		System.out.println("High price: " + response.getFloat(8));
		System.out.println("Close price: " + response.getFloat(12));
	}
}