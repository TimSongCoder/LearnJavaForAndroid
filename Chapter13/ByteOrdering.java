import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;

public class ByteOrdering{
	
	public static void main(String[] args){
		ByteBuffer buffer = ByteBuffer.allocate(5);
		buffer.put((byte)1).put((byte)2).put((byte)3).put((byte)4);
		buffer.flip();
		System.out.println("ByteBuffer reading with byte order: " + buffer.order());
		while(buffer.hasRemaining()){
			System.out.println("Element at position " + buffer.position() + " : " + buffer.get());
		}
		System.out.println();
		buffer.flip();
		
		CharBuffer charBuf = buffer.asCharBuffer();
		System.out.println("charBuf byte order: " + charBuf.order());
		while(charBuf.hasRemaining()){
			System.out.println("charBuf element's int value at position " + charBuf.position() + " : " + (int)(charBuf.get()));
		}
		System.out.println();
		
		// set the byte buffer order to be little endian.
		buffer.order(ByteOrder.LITTLE_ENDIAN);
		CharBuffer charBuf2 = buffer.asCharBuffer();
		System.out.println("ByteBuffer reading with byte order: " + buffer.order());
		while(buffer.hasRemaining()){
			System.out.println("Element at position " + buffer.position() + " : " + buffer.get());
			// single byte read, byte order does not matter here.
		}
		System.out.println();
		
		System.out.println("charBuf2 byte order: " + charBuf2.order());
		while(charBuf2.hasRemaining()){
			System.out.println("charBuf2 element's int value at position " + charBuf2.position() + " : " + (int)(charBuf2.get()));
			// multi-byte read, where the byte order does matter.
		}
	}
}