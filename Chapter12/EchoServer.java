import java.net.Socket;
import java.net.ServerSocket;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.File;
import java.io.FilenameFilter;

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
		File indicatingFile = new File("kill"); // Just represents file path.
		do{
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
		}while(!commandKill(indicatingFile));
		if(ss!=null){
			try{
				ss.close();
			}catch(IOException ioe){
				ioe.printStackTrace();
			}
		}
	}
	
	static boolean commandKill(File file){
		return file.exists() && file.isFile();
		// Just need to verify the existence of the single interesting file, not need to use filter.
	}
}