import java.nio.Buffer;
import java.nio.ByteBuffer;

public class BufferDemo{
	
	public static void main(String[] args){
		int capacity = 7;
		if(args.length>0){
			try{
				capacity = Integer.parseInt(args[0]);
			}catch(NumberFormatException nfe){
				nfe.printStackTrace();
			}
		}
		Buffer buffer = ByteBuffer.allocate(capacity);
		System.out.println("Capacity: " + buffer.capacity());
		System.out.println("Limit: " + buffer.limit());
		System.out.println("Position: " + buffer.position());
		System.out.println("Remaining: " + buffer.remaining());
		
		System.out.println("Changing buffer limit to 5.");
		buffer.limit(5);
		System.out.println("Capacity: " + buffer.capacity());
		System.out.println("Limit: " + buffer.limit());
		System.out.println("Position: " + buffer.position());
		System.out.println("Remaining: " + buffer.remaining());
		
		System.out.println("Changing buffer position to 3.");
		buffer.position(3);
		System.out.println("Capacity: " + buffer.capacity());
		System.out.println("Limit: " + buffer.limit());
		System.out.println("Position: " + buffer.position());
		System.out.println("Remaining: " + buffer.remaining());
		
		System.out.println(buffer);
	}
}