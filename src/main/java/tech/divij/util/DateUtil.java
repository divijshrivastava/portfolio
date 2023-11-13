package tech.divij.util;

import java.time.LocalDateTime;
import org.springframework.stereotype.Component;
import tech.divij.constants.Constants;
import tech.divij.constants.Month;

@Component("singleton")
public class DateUtil {

  public String mapDate(LocalDateTime date) {
    String dateString;
    int day = date.getDayOfMonth();
    int month = date.getMonth().getValue();
    int year = date.getYear();

    if (LocalDateTime.now().getYear() == year) {
      dateString = Month.month(month) + Constants.EMPTY_SPACE + day;
    } else {
      dateString =
          Month.month(month)
              + Constants.EMPTY_SPACE
              + day
              + Constants.COMMA
              + Constants.EMPTY_SPACE
              + year;
    }

    return dateString;
  }
}
