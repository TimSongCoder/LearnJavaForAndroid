import java.nio.ByteBuffer;

// The point I got is that, flip using scenario: read after write
// compact using scenario: write after read.
// The key is to understand what these method actually do.
public class BufferCompactAndCompare{
	
	public static void main(String[] args){
		ByteBuffer buffer1 = ByteBuffer.allocate(10);
		buffer1.position(5);  // return type is Buffer, can not invoke put() method directly.
		buffer1.put((byte)10).put((byte)20).put((byte)30); // remaining elemnts: 0 0, start position 8
		
		ByteBuffer buffer2 = ByteBuffer.allocate(20);
		buffer2.position(10);
		buffer2.put((byte)40).put((byte)50).put((byte)60);
		buffer2.limit(15);  // remaining elements 0 0, start position 13.
		
		System.out.println("Equality of buffer1 and buffer2: " + buffer1.equals(buffer2));  // true: both two 0 valued elements
		System.out.println();
		
		// compact the buffer1, different with the flip operation, which change limit to be the current position then set position to be 0.
		buffer1.compact(); // copy the remaining two 0 to the beginning place, set position to be 2.
		// copy the remaining data to beginning place, set the limit to be the same with the capacity, 
		// set the position to be at the index of next data to be read or written after aforementioned remaining data.
		while(buffer1.hasRemaining()){
			System.out.println("buffer1's element at position " + buffer1.position() + " : " + buffer1.get());
		}
	}
}