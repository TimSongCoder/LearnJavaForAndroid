import java.nio.CharBuffer;

public class CharBufferFlip{
	
	public static void main(String[] args){
		String[] poem = {"Roses are red, ", 
						"violets are blue. ", 
						"Sugar is sweet, ", 
						"and so are you. "};
		CharBuffer buffer = CharBuffer.allocate(50);
		for(int i=0; i<poem.length; i++){
			// Fill the buffer.
			for(int j=0; j<poem[i].length(); j++){
				buffer.put(poem[i].charAt(j));
			}
			// Flip the buffer before reading the contents. 
			// flip() method just a convenient way to accomplish the task, you can invoke limit() and position() or discardMark() manually.
			buffer.flip(); // set limit to be the current position, then set position to be 0, discarded any defined mark.
			
			// Drain the buffer.
			while(buffer.hasRemaining()){ // evaluate "position < limit"
				System.out.print(buffer.get());
			}
			// Terminate the current line.
			System.out.println();
			
			// set position to be 0, limit to be capacity, discard any defined mark. 
			// Prepare buffer for asequence channel reading or invoking put() method. Otherwise, BufferOverflowException will be thrown.
			buffer.clear();
		}
	}
}