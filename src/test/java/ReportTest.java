import org.junit.Before;
import org.junit.Test;
import task.DoseCollector;
import task.FitnessDataCollector;
import task.Report;
import task.TestDataSource;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Oleksandr_Khodakovsk on 9/28/2016.
 */
public class ReportTest {
    private final static LocalDate now = LocalDate.now();
    private Report rep;
    private FitnessDataCollector fit;
    private final static LocalDate dateSample1 = LocalDate.of(2016, 11, 20);
    private final static LocalDate dateSample2 = LocalDate.of(2016, 11, 26);


    @Before
    public void setUp() throws Exception {
        fit = new FitnessDataCollector();
        TestDataSource.init(fit);
        rep = new Report(fit);

    }

    @Test
    public void dayWaterConsumptionPercentage() throws Exception {
        rep.getRl().printList(fit.getWater(), dateSample1);
        assertThat(rep.waterConsumptionPercentage(DoseCollector.WATERDOSE,
                dateSample1), is(BigDecimal.valueOf(121.67)));
    }

    @Test
    public void weekWaterConsumptionPercentage() throws Exception {
        rep.getRl().printList(fit.getWater(), dateSample1);
        assertThat(rep.waterConsumptionPercentage(DoseCollector.WATERDOSE,
                dateSample1, dateSample2), is(BigDecimal.valueOf(63.34)));
    }

    @Test
    public void waterConsumptionMedian() throws Exception {
        rep.getRl().printList(fit.getWater(), dateSample1, dateSample2);
        assertThat(rep.waterConsumptionMedian(DoseCollector.WATERDOSE,
                dateSample1, dateSample2), is(BigDecimal.valueOf(300)));


    }
}
