import java.io.IOException;
import java.io.RandomAccessFile;

import java.nio.channels.FileChannel;
import java.nio.MappedByteBuffer;

public class MappedByteBufferDemo{
	
	public static void main(String[] args) throws IOException{
		if(args.length != 1){
			System.err.println("usage: java MappedByteBufferDemo file");
			return;
		}
		RandomAccessFile raf = new RandomAccessFile(args[0], "rw");
		FileChannel fc = raf.getChannel();
		long size = fc.size();
		System.out.println("Size: " + size);
		MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_WRITE, 0, size);
		while(mbb.hasRemaining()){
			System.out.print((char)mbb.get());
		}
		System.out.println();
		System.out.println();
		
		// reverse the file content.
		for(int i=0; i<mbb.limit()/2; i++){
			int mirrorIndex = mbb.limit()-1-i;
			byte b1 = mbb.get(i);
			byte b2 = mbb.get(mirrorIndex);
			mbb.put(i, b2);
			mbb.put(mirrorIndex, b1);
		}
		// read the buffer content again.
		mbb.flip();
		while(mbb.hasRemaining()){
			System.out.print((char)mbb.get());
		}
		fc.close();
	}
}