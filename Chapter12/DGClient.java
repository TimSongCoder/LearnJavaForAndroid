import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.io.IOException;
import java.net.InetAddress;

public class DGClient{
	public static void main(String[] args){
		if(args.length!=1){
			System.out.println("usage: java DGClient message");
			return;
		}
		byte[] messageBytes = args[0].getBytes();
		try{
			byte[] buffer = new byte[128];
			System.arraycopy(messageBytes, 0, buffer, 0, messageBytes.length);
			DatagramSocket dgs = new DatagramSocket();
			DatagramPacket dgp = new DatagramPacket(buffer, buffer.length, InetAddress.getByName(DGServer.SERVER_IP), DGServer.SERVER_PORT);
			dgs.send(dgp);
			
			// waiting and get echoing message
			dgs.receive(dgp);
			System.out.println("Client Receive Echo: " + new String(dgp.getData()));
			
			// Explore the buffer size that DatagramSocket support by underlying platform.
			System.out.println("ReceiveBufferSize: " + dgs.getReceiveBufferSize() + ", SendBufferSize: " + dgs.getSendBufferSize());
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
	}
}