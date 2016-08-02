public class AlignBinaryString{
	public static void main(String[] args){
		System.out.println(toAlignedBinaryString(7,8));
		System.out.println(toAlignedBinaryString(255,16));
		System.out.println(toAlignedBinaryString(255,7));
		System.out.println(Integer.toBinaryString(-1));
	}
	
	static String toAlignedBinaryString(int i, int numBits){
		String result = Integer.toBinaryString(i);
		if(result.length()>numBits){
			// Can not fit to specified number of bits.
			return null;
		}
		int numPrependedZeros = numBits - result.length();
		StringBuilder sb = new StringBuilder();
		for(int index=0; index<numPrependedZeros;index++){
			sb.append('0');
		}
		sb.append(result);
		return sb.toString();
	}
}