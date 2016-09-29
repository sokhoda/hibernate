import org.junit.Before;
import org.junit.Test;
import org.mockito.cglib.core.Local;
import task.DoseCollector;
import task.FitnessDataCollector;
import task.Report;
import task.TestDataSource;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Oleksandr_Khodakovsk on 9/28/2016.
 */
public class ReportTest {
    private final LocalDate now = LocalDate.now();
    private Report rep;
    private static LocalDate dateSample = LocalDate.of(2016,11,20);


    @Before
    public void setUp() throws Exception {
        FitnessDataCollector fit = new FitnessDataCollector();
        TestDataSource.init(fit);
        System.out.println();
        rep = new Report(fit);
        rep.printAll(dateSample, fit.getWater());
    }

    @Test
    public void dayRestWater() throws Exception {
        assertThat(rep.dayRestWater(dateSample, DoseCollector.WATERDOSE),is(-650));
    }
}
