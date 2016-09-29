package task;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

/**
 * Created by Oleksandr_Khodakovsk on 9/28/2016.
 */
public class Report {
    private final static int digits = 2;
    private final static BigDecimal HUNDRED = BigDecimal.valueOf(100);
    private FitnessDataCollector fit;
    private final ReportLogic rl = new ReportLogic();

    public Report(FitnessDataCollector fit) {
        this.fit = fit;
    }

    public <T extends Number> void printAll(LocalDate date,
                                            List<Record<T>> list) {
        if (list != null) {
            rl.sort(list);
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

    public BigDecimal restWaterPercentage(Integer waterdose, LocalDate...
            date ) {
        BigDecimal bd1 = BigDecimal.valueOf(rl.getSum(fit.getWater(), date));
        BigDecimal bd2 = BigDecimal.valueOf(waterdose * date.length) ;
        BigDecimal result = (bd2.subtract(bd1).multiply(HUNDRED)).divide(bd2, digits,
                RoundingMode.CEILING);
        return waterdose != 0 ? result : BigDecimal.ZERO;
    }

}
