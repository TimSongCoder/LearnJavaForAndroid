import java.nio.ByteBuffer;

public class ByteBufferDemo{
	
	public static void main(String[] args){
		ByteBuffer buffer1= ByteBuffer.allocate(10);
		if(buffer1.hasArray()){
			System.out.println("buffer1 array: " + buffer1.array());
			System.out.println("buffer1 array offset: " + buffer1.arrayOffset());
			System.out.println("Capacity: " + buffer1.capacity());
			System.out.println("Limit: " + buffer1.limit());
			System.out.println("Position: " + buffer1.position());
			System.out.println("Remaing: " + buffer1.remaining());
			System.out.println();
		}
		
		byte[] bytes = new byte[128];
		ByteBuffer buffer2 = ByteBuffer.wrap(bytes, 10, 50);
		if(buffer2.hasArray()){
			System.out.println("buffer2 array: " + buffer2.array());
			System.out.println("buffer2 array offset: " + buffer2.arrayOffset());
			System.out.println("Capacity: " + buffer2.capacity());
			System.out.println("Limit: " + buffer2.limit());
			System.out.println("Position: " + buffer2.position());
			System.out.println("Remaing: " + buffer2.remaining());
			System.out.println();
		}
		
		// explore the view buffer, buffer backed by another buffer.
		ByteBuffer bufferView = buffer1.duplicate();
		System.out.println("buffer view changed the element at current position:" + bufferView.position() + " to be 10");
		bufferView.put((byte)10);
		System.out.println("buffer1 position: " + buffer1.position() + ", remaining: " + buffer1.remaining());
		System.out.println("view buffer position: " + bufferView.position() + ", remaining: " + bufferView.remaining());
		
		System.out.println("buffer1 get the corresponding element changed by view buffer: " + buffer1.get());
	}
}