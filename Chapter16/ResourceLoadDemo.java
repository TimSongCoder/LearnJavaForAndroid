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
      byte[] image = new byte[(int)new File(RESOURCE_FILE).length()];
      int rd, i = 0;
      while((rd=is.read())!=-1){
        image[i++] = (byte)rd;
      }
      for(i=0;i<16;i++){
        System.out.printf("%02X ", image[i]);
      }
    }catch(IOException ioe){
      ioe.printStackTrace();
    }
  }
}
