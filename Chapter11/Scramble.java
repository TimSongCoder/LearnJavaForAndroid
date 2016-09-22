import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.util.Random;

class ScrambledOutputStream extends FilterOutputStream{
	private int[] map;
	
	public ScrambledOutputStream(OutputStream out, int[] map){
		super(out);
		if(map==null || map.length!=256){
			throw new IllegalArgumentException("map need contain 256 elements exactly.");
		}
		this.map = map;
	}
	
	@Override
	public void write(int b) throws IOException{
		// implementing trivial encryption, true value used as index. The map's role is key, so we need store it or retrive the identical one.
		// The result looks having no relation with the source, haha.
		out.write(map[b]);
		// filed out is protected, so you can access it here.
	}
}

public class Scramble{
	public static final int RANDOM_SEED = 0;
	
	public static void main(String[] args){
		if(args.length != 2){
			System.err.println("usage: java Scramble srcpath destpath");
			return;
		}
		FileInputStream fis = null;
		ScrambledOutputStream sos = null;
		try{
			fis = new FileInputStream(args[0]);
			FileOutputStream fos = new FileOutputStream(args[1]);
			sos = new ScrambledOutputStream(fos, makeMap());
			int b;
			while((b=fis.read())!=-1){
				sos.write(b);
			}
		}catch(IOException ioe){
			ioe.printStackTrace();
		}finally{
			if(fis!=null){
				try{
					fis.close();
				}catch(IOException ioe){
					ioe.printStackTrace();
				}
			}
			if(sos!=null){
				try{
					sos.close();
				}catch(IOException ioe){
					ioe.printStackTrace();
				}
			}
		}
	}
	
	static int[] makeMap(){
		int[] map = new int[256];
		for(int i=0;i<map.length;i++){
			map[i] = i;
		}
		// shuffle the map
		Random random = new Random(RANDOM_SEED); 
		// Specified seed intentionally to retrive the identical map when operating decryption.
		for(int i=0;i<map.length;i++){
			int index = random.nextInt(map.length);
			if(index!=i){
				int temp = map[index];
				map[index] = map[i];
				map[i] = temp;
			}
		}
		return map;
	}
}