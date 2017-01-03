import java.text.DateFormat;

import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateFormatDemo{
  public static void main(String[] args) {
    Date epoch = new Date(0);
    System.out.println(epoch);

    // default format with locale setting to US, using US timezone
    DateFormat df = DateFormat.getDateTimeInstance(DateFormat.LONG,
      DateFormat.LONG, Locale.US);
    System.out.println("Default format: " + df.format(epoch));

    // Using UTC timezone
    df.setTimeZone(TimeZone.getTimeZone("UTC"));
    System.out.println("Taking UTC into account:" + df.format(epoch));
  }
}
