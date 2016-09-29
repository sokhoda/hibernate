package task;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by Oleksandr_Khodakovsk on 9/28/2016.
 */
public class Report {
    private final static int digits =2;
    private FitnessDataCollector fit;

    public Report(FitnessDataCollector fit) {
        this.fit = fit;
    }

    public <T extends Number> void printAll(LocalDate date,
                                            List<Record<T>> list) {
        if (list != null) {
            fit.sort(list);
            int lsize = list.size();
            int i = 0;
            while (i < lsize && !list.get(i).getDate().equals(date)) {
                i++;
            }
            while (i < lsize && list.get(i).getDate().equals(date)) {
                System.out.println(list.get(i++));
            }
        }
        else{
            System.out.println("<empty>");
        }
    }

    public BigDecimal dayRestWaterPercentage(LocalDate date, Integer waterdose) {
        BigDecimal bd1 = BigDecimal.valueOf(fit.restWater(date, waterdose)
                * 100);
        BigDecimal bd2 = BigDecimal.valueOf(waterdose);
        return waterdose != 0 ? bd1.divide(bd2, digits, RoundingMode.CEILING)
                : BigDecimal.ZERO;
    }

    public int weekRestWater(LocalDate dateSample1, LocalDate dateSample2, Integer waterdose) {

        return 0;
    }
}
