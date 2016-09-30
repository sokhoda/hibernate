package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.time.temporal.ChronoUnit.DAYS;

/**
 * Created by Oleksandr_Khodakovsk on 9/28/2016.
 */
public class Misc {
    public static void main(String[] args) {
        LocalDate date = LocalDate.of(2016, 11, 16);
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                "dd.MM.yyyy");
        System.out.println(date.format(formatter));
        System.out.println("daysCount=" + date.until(date, DAYS));
        Double d1 = new Double(0.5);
        Double d2 = new Double(2.0);
        d1.compareTo(d2);
        System.out.println(d2.compareTo(d1));
        System.out.println((Integer.valueOf(0)).equals(Double.valueOf(0)));
//        System.out.println((Integer.valueOf(2) - null));
    }
}
