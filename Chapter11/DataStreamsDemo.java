import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class DataStreamsDemo{
	private static final String FILE_PATH = "DataStreams.dat";
	
	public static void main(String[] args){
		FileOutputStream fos = null;
		FileInputStream fis = null;
		DataOutputStream dos = null;
		DataInputStream dis = null;
		try{
			fos = new FileOutputStream(FILE_PATH);
			dos = new DataOutputStream(fos);
			dos.writeInt(2016);
			dos.writeUTF("Saving this string in UTF-8 encoding format");
			dos.writeFloat(1.25F);
			dos.close(); // close underlying output stream
			dos = null;  // avoid another will-failure closing attempt.
			
			fis = new FileInputStream(FILE_PATH);
			dis = new DataInputStream(fis);
			System.out.println(dis.readInt());
			System.out.println(dis.readUTF());
			System.out.println(dis.readFloat());
		}catch(IOException ioe){
			ioe.printStackTrace();
		}finally{
			if(dos!=null){
				try{
					dos.close();
				}catch(IOException ioe){
					ioe.printStackTrace();
				}
			}
			if(dis!=null){
				try{
					dis.close();
				}catch(IOException ioe){
					ioe.printStackTrace();
				}
			}
		}
		
		
	}
}