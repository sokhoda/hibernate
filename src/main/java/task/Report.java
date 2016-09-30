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


    public BigDecimal waterConsumptionPercentage(Integer waterdose, LocalDate...
            date) {
        BigDecimal bd1 = BigDecimal.valueOf(rl.getSum(fit.getWater(), date));
        LocalDate endDate = date.length > 1 ? date[1] : date[0];
        BigDecimal bd2 = BigDecimal.valueOf(waterdose * (date[0].until
                (endDate, DAYS) + 1));
        BigDecimal result = (bd1.multiply(HUNDRED)).divide(bd2, digits,
                RoundingMode.CEILING);
        return waterdose != 0 ? result : BigDecimal.ZERO;
    }

    public ReportLogic getRl() {
        return rl;
    }

    public BigDecimal waterConsumptionMedian(Integer waterdose, LocalDate...date) {
        return rl.getMedian(fit.getWater(), date);
    }
}
