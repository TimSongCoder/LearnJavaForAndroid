import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.InterfaceAddress;

import java.util.Collections;
import java.util.ArrayList;

public class NetworkInterfaceInfo{
	
	public static void main(String[] args){
		try{
			ArrayList<NetworkInterface> networkInterfaces =
				Collections.list(NetworkInterface.getNetworkInterfaces());
			// getNetworkInterfaces()method return Enumeration<NI> as legacy codes.
			for(NetworkInterface ni : networkInterfaces){
				System.out.println("Name = " + ni.getName());
				System.out.println("Display Name = " + ni.getDisplayName());
				System.out.println("Loopback = " + ni.isLoopback());
				System.out.println("Up and running = " + ni.isUp());
				System.out.println("MTU = " + ni.getMTU());  
				// MTU stands for maximum transmission unit, the max length of a single datagram message
				System.out.println("Supports multicast = " + ni.supportsMulticast());
				for(NetworkInterface subNi : Collections.list(ni.getSubInterfaces())){
					System.out.println("    SubInterface --> " + subNi);
				}
				for(InterfaceAddress ia : ni.getInterfaceAddresses()){
					System.out.println("    InterfaceAddress --> " + ia);
				}
				System.out.println();
			}
		}catch(SocketException se){
			se.printStackTrace();
		}
	}
}