import java.net.Socket;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.ServerSocket;

public class EchoServer{
	public static final int ECHO_SERVER_PORT = 9999;
	
	public static void main(String[] args){
		System.out.println("EchoServer started.");
		ServerSocket ss = null;
		try{
			ss = new ServerSocket(ECHO_SERVER_PORT);
		}catch(IOException ioe){
			ioe.printStackTrace();
			return;
		}
		while(true){
			try{
				Socket socket = ss.accept();
				// explore the ports.
				System.out.println("LocalPort: " + socket.getLocalPort() + ", RemotePort: " + socket.getPort());
				
				InputStream is = socket.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(is));
				String incomingMsg = br.readLine();
				System.out.println("ReceiveMsg: " + incomingMsg);
			
				OutputStream os = socket.getOutputStream();
				PrintWriter pw = new PrintWriter(os);
				pw.println(incomingMsg);
				pw.flush();
				socket.close();
			}catch(IOException ioe){
				ioe.printStackTrace();
			}
		}
	}
}