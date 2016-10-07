public class EchoText {
	public static void main(String[] args){
		boolean isRedirected = args.length != 0;
		int ch;
		try{
			while((ch = System.in.read()) != (isRedirected ? -1 : '\n')){
			System.out.print((char)ch);
			}
		} catch (java.io.IOException ioe) {
			System.err.println("IO ERROR");
		}
		System.out.println();
	}
}