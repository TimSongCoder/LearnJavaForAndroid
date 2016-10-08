import java.io.IOException;
import java.net.MulticastSocket;
import java.net.InetAddress;
import java.net.DatagramPacket;

/* MulticastSocket is used on the client-side to listen for packets that
* the server broadcasts to multiple clients.
* @see <a href="https://docs.oracle.com/javase/tutorial/networking/datagrams/broadcasting.html">The Oracle doc has a related tutorial to reference.</a>
/
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
		
		// explore the time-to-live feature, limit the multicast scope.
		System.out.println("I am ready to receive message with default time-to-live: " + mcs.getTimeToLive()); 
		// default value is 1.
		mcs.setTimeToLive(255);
		System.out.println("Modify the time-to-live value: " + mcs.getTimeToLive()); 
		
		while(true){
			mcs.receive(dgp);
			validReceiveBytes = new byte[dgp.getLength()];
			System.arraycopy(dgp.getData(), 0, validReceiveBytes, 0, validReceiveBytes.length);
			System.out.println(dgp.getSocketAddress() + ": " + new String(validReceiveBytes));
		}
	}
}