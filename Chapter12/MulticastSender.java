import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.io.IOException;

public class MulticastSender{
	
	public static void main(String[] args){
		if(args.length!=1){
			System.err.println("usage: java MulticastSender message");
			return;
		}
		try{
			DatagramSocket dgs = new DatagramSocket();
			byte[] msgBytes = args[0].getBytes();
			DatagramPacket dgp = new DatagramPacket(msgBytes, msgBytes.length,
				InetAddress.getByName(MulticastSocketClient.MULTICAST_GROUP_IP), MulticastSocketClient.MULTICAST_GROUP_PORT);
			dgs.send(dgp);
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
	}
}