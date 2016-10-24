import java.io.UnsupportedEncodingException;

public class StringCharset{
	
	public static void main(String[] args) throws UnsupportedEncodingException{
		byte[] encodedMsg = {0x66, 0x61, (byte)0xc3, (byte)0xa7, 0x61, 0x64, 0x65, 0x20, 0x74,
							0x6f, 0x75, 0x63, 0x68, (byte)0xc3, (byte)0xa9};
		System.out.println("Initial bytes length = " + encodedMsg.length);
		for(byte bt : encodedMsg){
			System.out.print(Integer.toHexString(Byte.toUnsignedInt(bt)) + " ");
		}
		System.out.println();
		String s = new String(encodedMsg, "UTF-8");  // with specified charset
		System.out.println(s);
		System.out.println();
		
		byte[] bytes = s.getBytes();  // with default charset.
		System.out.println("SecondEncoded bytes length = " + bytes.length);
		for(byte _byte : bytes){
			System.out.print(Integer.toHexString(Byte.toUnsignedInt(_byte)) + " ");
		};
		System.out.println();
	}
}