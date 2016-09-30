import org.junit.Before;
import org.junit.Test;
import main.DoseCalculator;
import main.FitnessDataCollector;
import main.report.Report;
import testdatasource.TestDataSource;

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
        rep = new Report(fit, new DoseCalculator());

    }

    @Test
    public void dayWaterConsumpPercent() throws Exception {
        rep.getRl().printList(fit.getWater(), dateSample1);
        assertThat(rep.calcWaterConsumpPercent(dateSample1),
                is(BigDecimal.valueOf(121.67)));
    }

    @Test
    public void weekWaterConsumpPercent() throws Exception {
        rep.getRl().printList(fit.getWater(), dateSample1, dateSample2);
        assertThat(rep.calcWaterConsumpPercent(dateSample1, dateSample2),
                is(BigDecimal.valueOf(63.34)));
    }

    @Test
    public void weekWaterConsumpMedian() throws Exception {
        rep.getRl().printList(fit.getWater(), dateSample1, dateSample2);
        assertThat(rep.calcWaterConsumpMedian(dateSample1, dateSample2),
                is(BigDecimal.valueOf(300)));
    }

    @Test
    public void dayWaterConsumpMedian() throws Exception {
        rep.getRl().printList(fit.getWater(), dateSample1);
        assertThat(rep.calcWaterConsumpMedian(dateSample1), is(BigDecimal.valueOf(250)));
    }

    @Test
    public void dayFoodConsumpPercent() throws Exception {
        rep.getRl().printList(fit.getCalories(), dateSample1);
        assertThat(rep.calcFoodConsumpPercent(dateSample1), is(new
                BigDecimal("110.00")));
    }

    @Test
    public void weekFoodConsumpPercent() throws Exception {
        rep.getRl().printList(fit.getCalories(), dateSample1, dateSample2);
        assertThat(rep.calcFoodConsumpPercent(dateSample1, dateSample2),
                is(BigDecimal.valueOf(66.86)));
    }

    @Test
    public void weekFoodConsumpMedian() throws Exception {
        rep.getRl().printList(fit.getCalories(), dateSample1, dateSample2);
        assertThat(rep.calcFoodConsumpMedian(dateSample1, dateSample2),
                is(BigDecimal.valueOf(250)));
    }

    @Test
    public void dayFoodConsumpMedian() throws Exception {
        rep.getRl().printList(fit.getCalories(), dateSample1);
        assertThat(rep.calcFoodConsumpMedian(dateSample1), is(BigDecimal
                .valueOf(225)));
    }

    @Test
    public void dayStepsPercent() throws Exception {
        rep.getRl().printList(fit.getSteps(), dateSample1);
        assertThat(rep.calcStepsPercent(dateSample1), is(new
                BigDecimal("182.50")));
    }

    @Test
    public void weekStepsPercent() throws Exception {
        rep.getRl().printList(fit.getSteps(), dateSample1, dateSample2);
        assertThat(rep.calcStepsPercent(dateSample1, dateSample2),
                is(new BigDecimal("95.00")));
    }

    @Test
    public void weekStepsMedian() throws Exception {
        rep.getRl().printList(fit.getSteps(), dateSample1, dateSample2);
        assertThat(rep.calcStepsMedian(dateSample1, dateSample2),
                is(BigDecimal.valueOf(300)));
    }

    @Test
    public void dayStepsMedian() throws Exception {
        rep.getRl().printList(fit.getSteps(), dateSample1);
        assertThat(rep.calcStepsMedian(dateSample1), is(BigDecimal
                .valueOf(250)));
    }

    @Test
    public void dayHoursPercent() throws Exception {
        rep.getRl().printList(fit.getHours(), dateSample1);
        assertThat(rep.calcHoursPercent(dateSample1), is(new
                BigDecimal("160.00")));
    }

    @Test
    public void weekHoursPercent() throws Exception {
        rep.getRl().printList(fit.getHours(), dateSample1, dateSample2);
        assertThat(rep.calcHoursPercent(dateSample1, dateSample2),
                is(new BigDecimal("62.15")));
    }

    @Test
    public void weekHoursMedian() throws Exception {
        rep.getRl().printList(fit.getHours(), dateSample1, dateSample2);
        assertThat(rep.calcHoursMedian(dateSample1, dateSample2),
                is(BigDecimal.valueOf(0.20)));
    }

    @Test
    public void dayHoursMedian() throws Exception {
        rep.getRl().printList(fit.getHours(), dateSample1);
        assertThat(rep.calcHoursMedian(dateSample1), is(BigDecimal
                .valueOf(0.50)));
    }

}
