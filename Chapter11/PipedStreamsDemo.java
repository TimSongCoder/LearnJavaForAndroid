import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.IOException;

public class PipedStreamsDemo{
	public static void main(String[] args) throws IOException{
		final PipedOutputStream pos = new PipedOutputStream();
		final PipedInputStream pis = new PipedInputStream(pos);
		Runnable senderTask = new Runnable(){
			final static int LIMIT = 10;
			
			public void run(){
				try{
					for(int i=0;i<LIMIT;i++){
						int data = (int)(Math.random()*256);
						pos.write(data);
						System.out.println("Writing data: " + data);
					}
					System.out.println();
				}catch(IOException ioe){
					ioe.printStackTrace();
				}finally{
					try{
						pos.close();
					}catch(IOException ioe){
						ioe.printStackTrace();
					}
				}
				
			}
		};
		
		Runnable receiverTask = new Runnable(){
			public void run(){
				try{
					int b;
					while((b=pis.read())!=-1){
						System.out.println("Reading data: " + b);
					}
				}catch(IOException ioe){
					ioe.printStackTrace();
				}finally{
					try{
						pis.close();
					}catch(IOException ioe){
						ioe.printStackTrace();
					}
				}
			}
		};
		
		System.out.println("byte experiment: (byte)128= " + (byte)128);
		
		new Thread(senderTask).start();
		new Thread(receiverTask).start();
	}
}