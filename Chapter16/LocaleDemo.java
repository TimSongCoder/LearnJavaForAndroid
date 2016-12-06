import java.util.Locale;

public class LocaleDemo{
  public static void main(String[] args) {
    System.out.println("Default Locale: " + Locale.getDefault());
    Locale.setDefault(Locale.US);
    System.out.println("Default Locale: " + Locale.getDefault());
  }
}
