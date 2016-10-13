import java.nio.ByteBuffer;

public class BufferMarkDemo{

	public static void main(String[] args){
		ByteBuffer buffer = ByteBuffer.allocate(7);
		buffer.put((byte)10).put((byte)20).put((byte)30).put((byte)40);  // current position will be 4.
		buffer.limit(4);
		// mark the buffer at specified position
		buffer.position(1).mark().position(3);  // current position will be 3.
		while(buffer.hasRemaining()){
			System.out.println("element at the position " + buffer.position() + " : " + buffer.get());
		}
		System.out.println();
		// reset the buffer, in other words setting the position to be the mark.
		buffer.reset();
		while(buffer.hasRemaining()){
			System.out.println("element at the position " + buffer.position() + " : " + buffer.get());
		}
	}
}