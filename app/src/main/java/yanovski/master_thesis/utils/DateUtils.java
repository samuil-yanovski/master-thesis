package yanovski.master_thesis.utils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Created by Samuil on 1/5/2016.
 */
public class DateUtils {

    public static String formatDate(DateTime date) {
        DateTimeFormatter formatter = DateTimeFormat.fullDate();
        return formatter.print(date);
    }
}
