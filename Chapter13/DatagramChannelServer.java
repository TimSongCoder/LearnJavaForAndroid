import java.nio.channels.DatagramChannel;
import java.nio.ByteBuffer;

import java.io.IOException;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

public class DatagramChannelServer{
	
	public static final String SERVER_HOST = "localhost";
	public static final int SERVER_PORT = 9999;
	
	public static void main(String[] args) throws IOException{
		System.out.println("server starting and listening on port " + SERVER_PORT + " for incoming requests...");
		DatagramChannel dc = DatagramChannel.open();
		dc.bind(new InetSocketAddress(SERVER_HOST, SERVER_PORT));
		ByteBuffer symbolBuffer = ByteBuffer.allocate(4);
		ByteBuffer payload = ByteBuffer.allocate(16);
		while(true){
			// prepare for buffer read/write
			payload.clear();
			symbolBuffer.clear();
			SocketAddress sa = dc.receive(symbolBuffer); // blocing mode
			if(sa == null){
				System.err.println("Receive return null SocketAddress.");
				return;
			}
			// indicating the client
			System.out.println("Received request from " + sa);
			String stockSymbol = new String(symbolBuffer.array(), 0, 4);
			// Didn't use hasArray() method to verify the existing of backing array because of the buffer's instantiation manner with control
			System.out.println("Stock Symbol: " + stockSymbol);
			if("MSFT".equals(stockSymbol.toUpperCase())){
				payload.putFloat(0, 37.40f).putFloat(4, 37.22f).putFloat(8, 37.48f).putFloat(12, 37.41f);
			}else{
				payload.putFloat(0, 0.0f).putFloat(4, 0.0f).putFloat(8, 0.0f).putFloat(12, 0.0f);
			}
			// response to client, send() method can only handle ByteBuffer.
			dc.send(payload, sa);
		}
	}
}