import java.nio.charset.Charset;
import java.nio.ByteBuffer;

public class CharsetDemo{
	
	public static void main(String[] args){
		String msg = "ξξεγβνεμ θθδμχρ";
		if(args.length > 0){
			msg = args[0];
		}
		String[] csNames = {"US-ASCII", "ISO-8859-1", "UTF-8", "UTF-16BE", "UTF-16LE", "UTF-16"};
		encode(msg, Charset.defaultCharset());
		for(String csName : csNames){
			encode(msg, Charset.forName(csName));
		}
	}
	
	static void encode(String msg, Charset charset){
		System.out.println("Charset: " + charset.toString());
		System.out.println("Message: " + msg);
		
		ByteBuffer buffer = charset.encode(msg);
		System.out.println("Encoded: ");
		
		for(int i=0; i< buffer.limit(); i++){
			int _byte = Byte.toUnsignedInt(buffer.get(i));
			char ch = (char)_byte;
			if(Character.isWhitespace(ch) || Character.isISOControl(ch)){
				ch = '\u0000'; // empty string
			}
			System.out.printf("%2d: %02x (%c)%n", i, _byte, ch);
		}
		System.out.println();
	}
}