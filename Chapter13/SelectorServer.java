import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.Selector;
import java.nio.channels.SelectionKey;
import java.net.InetSocketAddress;

import java.util.Set;
import java.util.Date;
import java.util.Iterator;

import java.lang.NumberFormatException;
import java.io.IOException;

public class SelectorServer{
	public static final int SERVER_PORT = 9999;
	public static final String SERVER_HOST = "localhost";
	
	static ByteBuffer buffer = ByteBuffer.allocateDirect(8);
	
	public static void main(String[] args) throws IOException{
		int port = SERVER_PORT;
		if(args.length>0){
			try{
				port = Integer.parseInt(args[0]);
			}catch(NumberFormatException nfe){
				nfe.printStackTrace();
			}
		}
		ServerSocketChannel ssc = ServerSocketChannel.open();
		ssc.bind(new InetSocketAddress(SERVER_HOST, port));
		ssc.configureBlocking(false); // must be set unblocking mode to register
		Selector selector = Selector.open();
		ssc.register(selector, SelectionKey.OP_ACCEPT);
		System.out.println("Server starting... listening on port " + port);
		
		while(true){
			int n = selector.select(); // perform a blocking selection operation
			if(n==0){
				System.out.print("*");
				continue;
			}
			System.out.println("select() return value: " + n);
			Set<SelectionKey> selectedKeys = selector.selectedKeys();
			Iterator<SelectionKey> iter = selectedKeys.iterator();
			while(iter.hasNext()){
				SelectionKey key = iter.next();
				if(key.isAcceptable()){
					SocketChannel sc = ((ServerSocketChannel)key.channel()).accept(); // non-blocking mode.
					if(sc==null){
						System.out.print(".");
						continue;
					}
					System.out.println("Receiving connection");
					buffer.clear(); // prepare to new round operation
					long currentTime = System.currentTimeMillis();
					buffer.putLong(currentTime);
					buffer.flip();  // prepare for draining.
					System.out.println("Writing current time = " + new Date(currentTime));
					while(buffer.hasRemaining()){
						sc.write(buffer);
					}
					System.out.println("Time was written.");
					sc.close();
				}
				iter.remove();  // must remove the key after you finished the channel operation.
			}
		}
	}
}