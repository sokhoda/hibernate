import domain.Human;
import main.DoseCalculator;
import org.junit.Before;
import org.junit.Test;
import report.Report;
import testdatasource.TestDataSource;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ReportTest {
    private final static LocalDate now = LocalDate.now();
    private Report rep;
    private Human fit;
    private DoseCalculator dc;
    private final static LocalDate dateSample1 = LocalDate.of(2016, 11, 20);
    private final static LocalDate dateSample2 = LocalDate.of(2016, 11, 26);


    @Before
    public void setUp() throws Exception {
        fit = new Human();
        TestDataSource.init(fit);
        dc = new DoseCalculator();
        rep = new Report(fit, dc);
    }

    @Test
    public void dayRestHoursZERO() throws Exception {
        fit.move(now, dc.getHours(), 12);
        assertThat(rep.dayRestHours(now), is(new BigDecimal("0.0")));
    }

    @Test
    public void dayRestWaterZERO() throws Exception {
        fit.drink(now, dc.getWater());
        assertThat(rep.dayRestWater(now), is(BigDecimal.ZERO));
    }

    @Test
    public void dayRestCaloriesZERO() throws Exception {
        fit.eat(now, dc.getCalories());
        assertThat(rep.dayRestCalories(now), is(BigDecimal.ZERO));
    }

    @Test
    public void dayRestStepsZERO() throws Exception {
        fit.move(now, 0.5, dc.getSteps());
        assertThat(rep.dayRestSteps(now), is(BigDecimal.ZERO));
    }

    @Test
    public void dayRestStepsMAX() throws Exception {
        assertThat(rep.dayRestSteps(now), is(BigDecimal.valueOf(dc.getSteps()
        )));
    }

    @Test
    public void dayRestHoursMAX() throws Exception {
        assertThat(rep.dayRestHours(now), is(BigDecimal.valueOf(dc.getHours()
        )));
    }

    @Test
    public void dayRestWaterMAX() throws Exception {
        assertThat(rep.dayRestWater(now), is(BigDecimal.valueOf(dc.getWater())));
    }

    @Test
    public void dayRestCaloriesMAX() throws Exception {
        assertThat(rep.dayRestCalories(now), is(
                BigDecimal.valueOf(dc.getCalories())));
    }

    @Test
    public void dayWaterConsumpPercent() throws Exception {
        assertThat(rep.calcWaterConsumpPercent(dateSample1),
                is(BigDecimal.valueOf(121.67)));
    }

    @Test
    public void weekWaterConsumpPercent() throws Exception {
        assertThat(rep.calcWaterConsumpPercent(dateSample1, dateSample2),
                is(BigDecimal.valueOf(63.34)));
    }

    @Test
    public void weekWaterConsumpMedian() throws Exception {
        assertThat(rep.calcWaterConsumpMedian(dateSample1, dateSample2),
                is(BigDecimal.valueOf(300)));
    }

    @Test
    public void dayWaterConsumpMedian() throws Exception {
        assertThat(rep.calcWaterConsumpMedian(dateSample1), is(BigDecimal.valueOf(250)));
    }

    @Test
    public void dayFoodConsumpPercent() throws Exception {
        assertThat(rep.calcFoodConsumpPercent(dateSample1), is(new
                BigDecimal("110.00")));
    }

    @Test
    public void weekFoodConsumpPercent() throws Exception {
        assertThat(rep.calcFoodConsumpPercent(dateSample1, dateSample2),
                is(BigDecimal.valueOf(66.86)));
    }

    @Test
    public void weekFoodConsumpMedian() throws Exception {
        assertThat(rep.calcFoodConsumpMedian(dateSample1, dateSample2),
                is(BigDecimal.valueOf(250)));
    }
    @Test
    public void dayFoodConsumpMedian() throws Exception {
        assertThat(rep.calcFoodConsumpMedian(dateSample1), is(BigDecimal
                .valueOf(225)));
    }

    @Test
    public void dayStepsPercent() throws Exception {
        assertThat(rep.calcStepsPercent(dateSample1), is(new
                BigDecimal("182.50")));
    }

    @Test
    public void weekStepsPercent() throws Exception {
        assertThat(rep.calcStepsPercent(dateSample1, dateSample2),
                is(new BigDecimal("95.00")));
    }

    @Test
    public void weekStepsMedian() throws Exception {
        assertThat(rep.calcStepsMedian(dateSample1, dateSample2),
                is(BigDecimal.valueOf(300)));
    }

    @Test
    public void dayStepsMedian() throws Exception {
        assertThat(rep.calcStepsMedian(dateSample1), is(BigDecimal
                .valueOf(250)));
    }

    @Test
    public void dayHoursPercent() throws Exception {
        assertThat(rep.calcHoursPercent(dateSample1), is(new
                BigDecimal("160.00")));
    }

    @Test
    public void weekHoursPercent() throws Exception {
        assertThat(rep.calcHoursPercent(dateSample1, dateSample2),
                is(new BigDecimal("62.15")));
    }

    @Test
    public void weekHoursMedian() throws Exception {
        assertThat(rep.calcHoursMedian(dateSample1, dateSample2),
                is(BigDecimal.valueOf(0.20)));
    }

    @Test
    public void dayHoursMedian() throws Exception {
        assertThat(rep.calcHoursMedian(dateSample1), is(BigDecimal
                .valueOf(0.50)));
    }

}
