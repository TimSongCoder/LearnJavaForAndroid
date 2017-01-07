import java.util.logging.Logger;
import java.util.logging.Level;

public class LoggingDemo{
  public static void main(String[] args) {
    Logger logger = Logger.getLogger("LoggingDemo");
    for (int i=0; i<5; i++) {
      logger.log(Level.INFO, String.valueOf(i));
    }
    System.out.println();
    logWarning("Something weird has happened...");
  }

  static void logWarning(String msg){
    Logger.getLogger("LoggingDemo").log(Level.WARNING, msg);
  }
}
