import java.io.FilterInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.util.Random;

public class Unscramble{
	public static void main(String[] args){
		if(args.length!=2){
			System.err.println("usage: java Unscramble srcpath destpath");
			return;
		}
		UnscrambleInputStream uis = null;
		FileOutputStream fos = null;
		try{
			FileInputStream fis = new FileInputStream(args[0]);
			uis = new UnscrambleInputStream(fis, makeMap());
			fos = new FileOutputStream(args[1]);
			int b;
			while((b=uis.read())!=-1){
				fos.write(b);
			}
		}catch(IOException ioe){
			ioe.printStackTrace();
		}finally{
			if(uis!=null){
				try{
					uis.close();
				}catch(IOException ioe){
					ioe.printStackTrace();
				}
			}
			if(fos!=null){
				try{
					fos.close();
				}catch(IOException ioe){
					ioe.printStackTrace();
				}
			}
		}
	}
	
	static int[] makeMap(){
		int[] map = new int[256];
		for(int i=0;i<map.length;i++){
			map[i]=i;
		}
		// Shuffle with the same seed.
		Random random = new Random(Scramble.RANDOM_SEED);
		for(int i=0; i<map.length;i++){
			int randomNum = random.nextInt(map.length);
			if(randomNum!=i){
				int temp = map[i];
				map[i] = map[randomNum];
				map[randomNum] = temp;
			}
			
		}
		
		int[] indexMap = new int[map.length];
		for(int i=0;i<indexMap.length;i++){
			indexMap[map[i]] = i;
			// store the index for the corresponding element map[i] in the indexMap array. 
		}
			
		return indexMap;
	}
}

class UnscrambleInputStream extends FilterInputStream{
	private int[] map;
	
	public UnscrambleInputStream(InputStream in, int[] map){
		super(in);
		if(map==null || map.length!=256){
			throw new IllegalArgumentException("map.length need to be 256 exactly");
		}
		this.map = map;
	}
	
	@Override
	public int read() throws IOException{
		int readValue = in.read();
		if(readValue != -1){
			readValue = map[readValue];
		}
		return readValue;
	}
	
	@Override
	public int read(byte[] b, int offset, int length) throws IOException{
		int readResult = in.read(b, offset, length);
		// in is inherited from Ancestor class
		if(readResult!=-1){
			for(int i=offset;i<readResult+offset;i++){
				b[i] = (byte)map[b[i]];
			}
		}
		return readResult;
	}
}