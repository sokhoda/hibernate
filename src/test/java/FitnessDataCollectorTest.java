import org.junit.Before;
import org.junit.Test;
import task.DoseCollector;
import task.FitnessDataCollector;
import task.TestDataSource;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Oleksandr_Khodakovsk on 9/28/2016.
 */
public class FitnessDataCollectorTest {
    private final LocalDate now = LocalDate.now();
    private FitnessDataCollector fit;


    @Before
    public void setUp() throws Exception {
        fit = new FitnessDataCollector();
        TestDataSource.init(fit);
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
        assertThat(fit.dayRestWater(now, DoseCollector.WATERDOSE), is(DoseCollector.WATERDOSE));
    }

    @Test
    public void restWaterZERO() throws Exception {
        fit.drink(now, DoseCollector.WATERDOSE);
        assertThat(fit.dayRestWater(now, DoseCollector.WATERDOSE), is(0));
    }

    @Test
    public void toMuchWater() throws Exception {
        fit.drink(now, 10000);
        assertThat(fit.hasToMuchWater(now, DoseCollector.WATERDOSE), is(true));
    }

    @Test
    public void zeroWater() throws Exception {
        assertThat(fit.hasZeroWater(now), is(true));
    }

    @Test
    public void restStepsZERO() throws Exception {
        fit.move(now, 0.5, DoseCollector.MOVESTEPSDOSE);
        assertThat(fit.restSteps(now, DoseCollector.MOVESTEPSDOSE), is(0));
    }

    @Test
    public void restStepsMAX() throws Exception {
        assertThat(fit.restSteps(now, DoseCollector.MOVESTEPSDOSE), is(DoseCollector.MOVESTEPSDOSE));
    }

    @Test
    public void toMuchMoveSteps() throws Exception {
        fit.move(now, 1, 11000);
        assertThat(fit.hasToMuchMoveSteps(now, DoseCollector.MOVESTEPSDOSE), is(true));
    }

    @Test
    public void zeroMoveSteps() throws Exception {
        assertThat(fit.hasZeroMoveSteps(now), is(true));
    }

    @Test
    public void restMoveHoursMAX() throws Exception {
        assertThat(fit.restMoveHours(now, DoseCollector.MOVETIMEDOSE), is(DoseCollector.MOVETIMEDOSE));
    }

    @Test
    public void restMoveHoursZERO() throws Exception {
        fit.move(now, DoseCollector.MOVETIMEDOSE, 12);
        assertThat(fit.restMoveHours(now, DoseCollector.MOVETIMEDOSE), is(0.));
    }

    @Test
    public void zeroMoveHours() throws Exception {
        assertThat(fit.hasZeroMoveHours(now), is(true));
    }

    @Test
    public void toMuchMoveHours() throws Exception {
        fit.move(now, 4, 2);
        assertThat(fit.hasToMuchMoveHours(now, DoseCollector.MOVETIMEDOSE), is(true));
    }

    @Test
    public void restCaloriesMAX() throws Exception {
        assertThat(fit.restCallories(now, DoseCollector.CALLORIESDOSE), is(DoseCollector.CALLORIESDOSE));
    }

    @Test
    public void restCaloriesZERO() throws Exception {
        fit.eat(now, DoseCollector.CALLORIESDOSE);
        assertThat(fit.restCallories(now, DoseCollector.CALLORIESDOSE), is(0));
    }

    @Test
    public void nothingEatenTest() throws Exception {
        assertThat(fit.hasNothingEaten(now), is(true));
    }

    @Test
    public void overeatenTest() throws Exception {
        fit.eat(now, 5000);
        assertThat(fit.hasOverEaten(now, DoseCollector.CALLORIESDOSE), is(true));
    }


}
