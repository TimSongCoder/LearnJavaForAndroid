import java.nio.ByteBuffer;

public class ByteBufferWR{
	
	public static void main(String[] args){
		ByteBuffer buffer1 = ByteBuffer.allocate(7);
		System.out.println("Capacity: " + buffer1.capacity());
		System.out.println("Limit: " + buffer1.limit());
		System.out.println("Position: " + buffer1.position());
		System.out.println("Remaing: " + buffer1.remaining());
		
		buffer1.put((byte)10).put((byte)20).put((byte)30);
		
		System.out.println("Capacity: " + buffer1.capacity());
		System.out.println("Limit: " + buffer1.limit());
		System.out.println("Position: " + buffer1.position());
		System.out.println("Remaing: " + buffer1.remaining());
		
		for(int i=0;i<buffer1.position();i++){
			System.out.println(buffer1.get(i));
		}
	}
}