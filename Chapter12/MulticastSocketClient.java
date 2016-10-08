import java.io.IOException;
import java.net.MulticastSocket;
import java.net.InetAddress;
import java.net.DatagramPacket;

public class MulticastSocketClient{
	public static final String MULTICAST_GROUP_IP = "233.0.0.1";
	public static final int MULTICAST_GROUP_PORT = 10000;
	
	public static void main(String[] args) throws IOException{
		MulticastSocket mcs = new MulticastSocket(MULTICAST_GROUP_PORT);
		InetAddress groupAddr = InetAddress.getByName(MULTICAST_GROUP_IP);
		mcs.joinGroup(groupAddr);
		byte[] buffer = new byte[128]; // limited message length, just for demenstration.
		DatagramPacket dgp = new DatagramPacket(buffer, buffer.length);
		byte[] validReceiveBytes;
		while(true){
			mcs.receive(dgp);
			validReceiveBytes = new byte[dgp.getLength()];
			System.arraycopy(dgp.getData(), 0, validReceiveBytes, 0, validReceiveBytes.length);
			System.out.println(dgp.getSocketAddress() + ": " + new String(validReceiveBytes));
		}
	}
}