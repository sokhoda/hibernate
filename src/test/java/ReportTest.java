import org.junit.Before;
import org.junit.Ignore;
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
    private final static LocalDate dateSample1 = LocalDate.of(2016, 11, 20);
    private final static LocalDate dateSample2 = LocalDate.of(2016, 11, 26);


    @Before
    public void setUp() throws Exception {
        FitnessDataCollector fit = new FitnessDataCollector();
        TestDataSource.init(fit);
        rep = new Report(fit);
        rep.printAll(dateSample1, fit.getWater());
    }

    @Test
    public void dayRestWaterPercentage() throws Exception {
        assertThat(rep.dayRestWaterPercentage(dateSample1, DoseCollector
                .WATERDOSE), is(BigDecimal.valueOf(-21.66)));
    }

    @Ignore
    @Test
    public void weekRestWater() throws Exception {
        assertThat(rep.weekRestWater(dateSample1, dateSample2, DoseCollector
                        .WATERDOSE)
                , is(-650));
    }
}
