import java.io.Console;
import java.io.IOError;

public class Login{
  public static void main(String[] args) {
    Console console = System.console();
    if(console == null){
      System.err.println("no console device is present.");
      return ;
    }
    try{
      String username = console.readLine("Username: ");
      char[] pwd = console.readPassword("Password: ");
      // Do something useful with the username and password, this application just prints them out for demonstration.
      System.out.println("demo_username: " + username);
      System.out.println("demo_password: " + new String(pwd));
      // Prepare username for garbage collection.
      // More importantly, destroy the password by zeroing out the char array.
      username = "";
      for(int i=0;i<pwd.length;i++){
        pwd[i] = 0;
      }
    }catch(IOError ioe){
        ioe.printStackTrace();
    }
  }
}
