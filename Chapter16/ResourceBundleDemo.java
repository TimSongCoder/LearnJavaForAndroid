import java.util.ResourceBundle;

public class ResourceBundleDemo{
  public static void main(String[] args) {
    ResourceBundle resources = ResourceBundle.getBundle("game");
    System.out.println("elevator = " + resources.getString("elevator"));
    System.out.println("nameSample = " + resources.getString("nameSample"));
  }
}
