import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Copy{
  public static void main(String[] args) {
    if(args.length != 2){
      System.err.println("usage: java Copy srcFile srcFile");
      return;
    }
    // ARM, Java added automatic resource management feature implementation in below form.
    try(FileInputStream fis = new FileInputStream(args[0]);
        FileOutputStream fos = new FileOutputStream(args[1])){
          // resource usage block, you can follow it with catch and/or finally block.
          int b;
          while((b=fis.read())!=-1){
            fos.write(b);
          }
    }catch(IOException ioe){
      ioe.printStackTrace();
    };
    // No resource release tedious handle any more:)
  }
}
