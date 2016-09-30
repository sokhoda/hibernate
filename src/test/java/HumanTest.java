import main.report.Report;
import org.junit.Before;
import org.junit.Test;
import main.DoseCalculator;
import main.Human;
import testdatasource.TestDataSource;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Oleksandr_Khodakovsk on 9/28/2016.
 */
public class HumanTest {
    private final LocalDate now = LocalDate.now();
    private DoseCalculator dc;
    private Report rep;
    private Human fit;


    @Before
    public void setUp() throws Exception {
        fit = new Human();
        TestDataSource.init(fit);
        dc = new DoseCalculator();
        rep = new Report(fit, dc);
    }

    @Test
    public void canDrink() throws Exception {
        fit.drink(now, 1);
    }

    @Test
    public void canEat() throws Exception {
        fit.eat(now, 1);
    }

    @Test
    public void canMove() throws Exception {
        fit.move(now, 0.2, 1);
    }

    @Test
    public void dayZeroWater() throws Exception {
        assertThat(fit.dayZeroWater(now), is(true));
    }

    @Test
    public void dayZeroSteps() throws Exception {
        assertThat(fit.dayZeroSteps(now), is(true));
    }

    @Test
    public void dayZeroHours() throws Exception {
        assertThat(fit.dayZeroHours(now), is(true));
    }

    @Test
    public void dayZeroCalories() throws Exception {
        assertThat(fit.dayZeroCalories(now), is(true));
    }

    @Test
    public void dayTooMuchHours() throws Exception {
        fit.move(now, dc.getHours() + 1, 2);
        assertThat(fit.dayTooMuchHours(now), is(true));
    }

    @Test
    public void dayTooMuchWater() throws Exception {
        fit.drink(now, dc.getWater() + 1);
        assertThat(fit.dayTooMuchWater(now), is(true));
    }

    @Test
    public void dayTooMuchSteps() throws Exception {
        fit.move(now, 1, dc.getSteps() + 1);
        assertThat(fit.dayTooMuchSteps(now), is(true));
    }

    @Test
    public void dayTooMuchCalories() throws Exception {
        fit.eat(now, dc.getCalories() + 1);
        assertThat(fit.dayTooMuchCalories(now), is(true));
    }

}
