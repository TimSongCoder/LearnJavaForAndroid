import java.io.InputStream;
import java.io.IOException;

public class Exec{
  public static void main(String[] args) {
    if(args.length != 1){
      System.err.println("usage: java Exec program");
      return;
    }
    try{
      Process p = Runtime.getRuntime().exec(args[0]);
      // Obtaining standard output of process that p represented.
      InputStream is = p.getInputStream();
      int _byte;
      while((_byte = is.read())!=-1){
        System.out.print((char)_byte);
      }
      // Wait for the executing process's exit status.
      System.out.println("\nExit status: " + p.waitFor());
    }catch(InterruptedException ie){
      assert false;
    }catch(IOException ioe){
      ioe.printStackTrace();
    }
  }
}
