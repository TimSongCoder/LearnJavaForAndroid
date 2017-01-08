import java.io.IOException;
import java.io.FileOutputStream;

import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.StreamHandler;
import java.util.logging.SimpleFormatter;
import java.util.logging.FileHandler;

public class LoggingErrorManageDemo{
  public static void main(String[] args) throws IOException{
    Logger logger = Logger.getLogger("LoggingErrorManageDemo");
    FileOutputStream fos = new FileOutputStream("log");
    StreamHandler sh = new StreamHandler(fos, new SimpleFormatter());
    FileHandler fh = new FileHandler("logFile");
    logger.addHandler(sh);
    logger.addHandler(fh);
    for (int i=0; i<5; i++) {
      logger.log(Level.WARNING, String.valueOf(i));
      if(i==2){
        fos.close();
        // The logging framework, the logging Handler specifically will meet an error
        // after the FileOutputStream has been closed prematurely.
      }
    }
    System.out.println("---------Logging error has been handled by ErrorManager associated with Handler, so we can move on.---------");

    logger.log(Level.INFO, "CAN NOT UNISTALL PRE-INSTALLED APP ON SAMSUNG.\n");
  }
}
