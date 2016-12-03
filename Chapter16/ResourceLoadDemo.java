import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class ResourceLoadDemo{
  private static final String RESOURCE_FILE = "resource.txt";

  public static void main(String[] args) {
    System.out.println(ResourceLoadDemo.class.getClassLoader());
    InputStream is = ResourceLoadDemo.class.getResourceAsStream(RESOURCE_FILE);
    if(is==null){
      System.err.printf("%s not found%n", RESOURCE_FILE);
      return ;
    }
    try{
      byte[] unit = new byte[1024];
      int rd, length = 0;
      if(is.markSupported()){
        System.out.println("markSupported is true.");
        is.mark(Integer.MAX_VALUE);
        while((rd=is.read())!=-1){
          length+=rd;
        }
        byte[] image = new byte[length];
        int i=0;
        is.reset();
        while((rd=is.read())!=-1){
          image[i++] = (byte)rd;
        }
        for(i=0;i<16;i++){
          System.out.printf("%02X ", image[i]);
        }
      }else{
        System.out.println("markSupported is false, need alternative approach:)");
        while((rd=is.read())!=-1){
          length+=rd;
        }
        byte[] image = new byte[length];
        // Obtain a new InputStream to read.
        InputStream nis = ResourceLoadDemo.class.getResourceAsStream(RESOURCE_FILE);
        int i =0;
        while((rd=nis.read())!=-1){
          image[i++] = (byte)rd;
        }
        for(i=0;i<16;i++){
          System.out.printf("%02X ", image[i]);
        }
      }
    }catch(IOException ioe){
      ioe.printStackTrace();
    }
  }
}
