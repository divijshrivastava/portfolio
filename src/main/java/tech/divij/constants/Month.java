package tech.divij.constants;

import java.util.HashMap;
import java.util.Map;

public class Month {

  private static final Map<Integer, String> MONTH_NAMES = new HashMap<>();

  static {
    MONTH_NAMES.put(1, "Jan");
    MONTH_NAMES.put(2, "Feb");
    MONTH_NAMES.put(3, "March");
    MONTH_NAMES.put(4, "April");
    MONTH_NAMES.put(5, "May");
    MONTH_NAMES.put(6, "June");
    MONTH_NAMES.put(7, "July");
    MONTH_NAMES.put(8, "August");
    MONTH_NAMES.put(9, "September");
    MONTH_NAMES.put(10, "October");
    MONTH_NAMES.put(11, "November");
    MONTH_NAMES.put(12, "December");
  }

  public static String month(int monthNum) {
    if (monthNum < 1 || monthNum > 12) {
      throw new IllegalArgumentException("Invalid month number: " + monthNum);
    }

    return MONTH_NAMES.get(monthNum);
  }
}
