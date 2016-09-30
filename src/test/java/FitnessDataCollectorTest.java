import org.junit.Before;
import org.junit.Test;
import main.DoseCalculator;
import main.FitnessDataCollector;
import testdatasource.TestDataSource;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Oleksandr_Khodakovsk on 9/28/2016.
 */
public class FitnessDataCollectorTest {
    private final LocalDate now = LocalDate.now();
    private DoseCalculator dc;
    private FitnessDataCollector fit;


    @Before
    public void setUp() throws Exception {
        fit = new FitnessDataCollector();
        TestDataSource.init(fit);
        dc = new DoseCalculator();
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
    public void restWaterMAX() throws Exception {
        assertThat(fit.dayRestWater(now, dc.getWater()), is(dc.getWater()));
    }

    @Test
    public void restWaterZERO() throws Exception {
        fit.drink(now, dc.getWater());
        assertThat(fit.dayRestWater(now, dc.getWater()), is(0));
    }

    @Test
    public void toMuchWater() throws Exception {
        fit.drink(now, 10000);
        assertThat(fit.hasToMuchWater(now, dc.getWater()), is(true));
    }

    @Test
    public void zeroWater() throws Exception {
        assertThat(fit.hasZeroWater(now), is(true));
    }

    @Test
    public void restStepsZERO() throws Exception {
        fit.move(now, 0.5, dc.getSteps());
        assertThat(fit.restSteps(now, dc.getSteps()), is(0));
    }

    @Test
    public void restStepsMAX() throws Exception {
        assertThat(fit.restSteps(now, dc.getSteps()), is(dc.getSteps()));
    }

    @Test
    public void toMuchMoveSteps() throws Exception {
        fit.move(now, 1, 11000);
        assertThat(fit.hasToMuchMoveSteps(now, dc.getSteps()), is(true));
    }

    @Test
    public void zeroMoveSteps() throws Exception {
        assertThat(fit.hasZeroMoveSteps(now), is(true));
    }

    @Test
    public void restMoveHoursMAX() throws Exception {
        assertThat(fit.restMoveHours(now, dc.getHours()), is(dc.getHours()));
    }

    @Test
    public void restMoveHoursZERO() throws Exception {
        fit.move(now, dc.getHours(), 12);
        assertThat(fit.restMoveHours(now, dc.getHours()), is(0.));
    }

    @Test
    public void zeroMoveHours() throws Exception {
        assertThat(fit.hasZeroMoveHours(now), is(true));
    }

    @Test
    public void toMuchMoveHours() throws Exception {
        fit.move(now, 4, 2);
        assertThat(fit.hasToMuchMoveHours(now, dc.getHours()), is(true));
    }

    @Test
    public void restCaloriesMAX() throws Exception {
        assertThat(fit.restCallories(now, dc.getCalories()), is(dc.getCalories()));
    }

    @Test
    public void restCaloriesZERO() throws Exception {
        fit.eat(now, dc.getCalories());
        assertThat(fit.restCallories(now, dc.getCalories()), is(0));
    }

    @Test
    public void nothingEatenTest() throws Exception {
        assertThat(fit.hasNothingEaten(now), is(true));
    }

    @Test
    public void overeatenTest() throws Exception {
        fit.eat(now, 5000);
        assertThat(fit.hasOverEaten(now, dc.getCalories()), is(true));
    }


}
