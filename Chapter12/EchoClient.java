import java.net.Socket;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class EchoClient{
	public static void main(String[] args){
		if(args.length!=1){
			System.err.println("usage: java EchoClient message");
			System.err.println("example: java EchoClient I love New York");
			return;
		}
		String message = args[0];
		System.out.println("Sending message...");
		Socket socket = null;
		try{
			socket = new Socket("localhost", EchoServer.ECHO_SERVER_PORT);
			// explore the ports.
			System.out.println("LocalPort: " + socket.getLocalPort() + ", RemotePort: " + socket.getPort());
			
			OutputStream os = socket.getOutputStream();
			PrintWriter pw = new PrintWriter(os);
			pw.println(message);
			pw.flush();
			
			InputStream is = socket.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String messageBack = br.readLine(); // without line feed.
			System.out.println("EchoBack: " + messageBack);
		}catch(IOException ioe){
			ioe.printStackTrace();
		}finally{
			if(socket!=null){
				try{
					socket.close();
				}catch(IOException ioe){
					ioe.printStackTrace();
				}
			}
		}
	}
}