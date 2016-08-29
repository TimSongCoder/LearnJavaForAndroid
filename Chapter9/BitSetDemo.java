import java.util.BitSet;

public class BitSetDemo{
	public static void main(String[] args){
		BitSet bs1 = new BitSet();
		bs1.set(4, true);
		bs1.set(5, true);
		bs1.set(9, true);
		bs1.set(10, true);
		BitSet bsTemp = (BitSet)bs1.clone();
		dumpBitSet("        ", bs1);
		
		BitSet bs2 = new BitSet();
		bs2.set(4, true);
		bs2.set(6, true);
		bs2.set(7, true);
		bs2.set(9, true);
		dumpBitSet("        ", bs2);
		
		bs1.and(bs2);
		dumpSeparator(Math.min(bs1.size(), 16));
		dumpBitSet("AND (&) ", bs1);
		System.out.println();
		
		bs1 = bsTemp;  // From this beginning, bs1 and bsTemp reference the identical object.
		dumpBitSet("        ", bs1);
		dumpBitSet("        ", bs2);
		bsTemp = (BitSet)bsTemp.clone(); // Assign a new reference object to bsTemp before the or() method on bs1 to record the original value.
		
		bs1.or(bs2);
		dumpSeparator(Math.min(bs1.size(), 16));
		dumpBitSet("OR (|)  ", bs1);
		System.out.println();
		
		bs1 = bsTemp;
		dumpBitSet("        ", bs1);
		dumpBitSet("        ", bs2);
		//bsTemp = (BitSet)bs1.clone();  No more use for bsTemp, otherwise you need to uncomment this line.
		
		bs1.xor(bs2);
		dumpSeparator(Math.min(bs1.size(), 16));
		dumpBitSet("XOR (^) ", bs1);
	}
	
	static void dumpBitSet(String preamble, BitSet bs){
		System.out.print(preamble);
		int size = Math.min(bs.size(), 16);
		for(int i=0;i<size;i++){
			System.out.print(bs.get(i)?"1":"0");
		}
		System.out.print("  size(" + bs.size() + "), length(" + bs.length() + ")");
		System.out.println();
	}
	
	static void dumpSeparator(int length){
		System.out.print("        ");
		for(int i =0 ; i<length;i++){
			System.out.print("-");
		}
		System.out.println();
	}
}