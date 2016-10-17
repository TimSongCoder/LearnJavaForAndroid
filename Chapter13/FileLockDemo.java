import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

import java.io.IOException;
import java.io.RandomAccessFile;

public class FileLockDemo{
	
	private static final int MAX_QUERIES = 150000;
	private static final int MAX_UPDATES = 150000;
	private static final int REC_LENGTH = 16;
	
	static ByteBuffer buffer = ByteBuffer.allocate(REC_LENGTH);
	static IntBuffer intBuffer = buffer.asIntBuffer();
	
	static int counter = 1;
	
	public static void main(String[] args) throws IOException{
		boolean isWriter = false;
		if(args.length != 0){
			isWriter = true;
		}
		RandomAccessFile raf = new RandomAccessFile("FileLockDemo.temp", isWriter ? "rw" : "r");
		FileChannel fc = raf.getChannel();
		if(isWriter){
			update(fc);
		}else{
			query(fc);
		}
	}
	
	static void query(FileChannel fc) throws IOException{
		for(int i=0; i<MAX_QUERIES; i++){
			System.out.println("acquiring shared lock");
			FileLock lock = fc.lock(0, REC_LENGTH, true);  // Lock a record region of the file.
			try{
				buffer.clear();
				fc.read(buffer, 0);  // From file position 0.
				int a = intBuffer.get(0);
				int b = intBuffer.get(1);
				int c = intBuffer.get(2);
				int d = intBuffer.get(3);
				System.out.println("Reading: " + a + ", " + b + ", " + c + ", " +d);
				if(a*2!=b || a*3!=c || a*4!=d){
					System.err.println("Error: record corruption.");
					return;
				}
			}finally{
				lock.release();
			}
		}
	}
	
	static void update(FileChannel fc) throws IOException{
		for(int i=0;i<MAX_UPDATES;i++){
			System.out.println("acquiring exclusive lock.");
			FileLock lock = fc.lock(0, REC_LENGTH, false);
			try{
				intBuffer.clear();
				int a = counter;
				int b = counter*2;
				int c = counter*3;
				int d = counter*4;
				System.out.println("Writing: " + a + ", " + b + ", " + c + ", " + d);
				intBuffer.put(a).put(b).put(c).put(d);
				counter++;
				buffer.rewind();
				fc.write(buffer, 0);  // write into file beginning from position 0.
			}finally{
				lock.release();
			}
		}
	}
}