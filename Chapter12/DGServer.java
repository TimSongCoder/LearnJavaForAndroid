import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.io.IOException;

public class DGServer{
	public static final int SERVER_PORT = 10000;
	public static final String SERVER_IP = "localhost";
	
	public static void main(String[] args){
		System.out.println("Server started...");
		try{
			DatagramSocket dgs = new DatagramSocket(SERVER_PORT);
			// Explore the buffer size that DatagramSocket support by underlying platform.
			System.out.println("ReceiveBufferSize: " + dgs.getReceiveBufferSize() + ", SendBufferSize: " + dgs.getSendBufferSize());
			
			byte[] buffer = new byte[128];
			DatagramPacket dgp = new DatagramPacket(buffer, buffer.length);
			
			while(true){	
				dgs.receive(dgp);
				System.out.println("Receiving: " + new String(dgp.getData()));
			
				// echoing back the data
				dgs.send(dgp);
			}
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
	}
}