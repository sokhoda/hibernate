import org.junit.Before;
import org.junit.Test;
import task.FitnessData;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Oleksandr_Khodakovsk on 9/28/2016.
 */
public class FitnessTest {
    private FitnessData fit;
    private static final int WATERDOSE = 3;
    private static final int CALLORIESDOSE = 2500;
    private static final int MOVETIMEDOSE = 2;
    private static final int MOVESTEPSDOSE = 2000;

    @Before
    public void setUp() throws Exception {
        fit = new FitnessData();
    }

    @Test
    public void canDrink() throws Exception {
        fit.drink(LocalDate.now(), 1);
    }

    @Test
    public void canEat() throws Exception {
        fit.eat(LocalDate.now(), 1);
    }

    @Test
    public void canMove() throws Exception {
        fit.move(LocalDate.now(), 0.2, 1);
    }

    @Test
    public void restWater() throws Exception {
        fit.drink(LocalDate.now(), 1);
        assertThat(fit.restWater(WATERDOSE), is(2));
    }

    @Test
    public void restSteps() throws Exception {
        fit.move(LocalDate.now(), 0.5, 400);
        assertThat(fit.restSteps(MOVESTEPSDOSE), is(1600));
    }

    @Test
    public void toMuchMoveSteps() throws Exception {
        fit.move(LocalDate.now(), 1, 11000);
        assertThat(fit.hasToMuchMoveSteps(MOVESTEPSDOSE), is(true));
    }

    @Test
    public void zeroMoveSteps() throws Exception {
        assertThat(fit.hasZeroMoveSteps(), is(true));
    }

    @Test
    public void restMoveHours() throws Exception {
        fit.move(LocalDate.now(), 0.5, 400);
        assertThat(fit.restMoveHours(MOVETIMEDOSE), is(1.5));
    }

    @Test
    public void toMuchWater() throws Exception {
        fit.drink(LocalDate.now(), 10);
        assertThat(fit.hasToMuchWater(WATERDOSE), is(true));
    }

    @Test
    public void zeroWater() throws Exception {
        assertThat(fit.hasZeroWater(), is(true));
    }

    @Test
    public void zeroMoveHours() throws Exception {
        assertThat(fit.hasZeroMoveHours(), is(true));
    }

    @Test
    public void toMuchMoveHours() throws Exception {
        fit.move(LocalDate.now(), 4, 2);
        assertThat(fit.hasToMuchMoveHours(MOVETIMEDOSE), is(true));
    }

    @Test
    public void restCalories() throws Exception {
        fit.eat(LocalDate.now(), 1000);
        assertThat(fit.restCallories(CALLORIESDOSE), is(1500));
    }

    @Test
    public void nothingEatenTest() throws Exception {
        assertThat(fit.hasNothingEaten(), is(true));
    }

    @Test
    public void overeatenTest() throws Exception {
        fit.eat(LocalDate.now(), 5000);
        assertThat(fit.hasOverEaten(CALLORIESDOSE), is(true));
    }


}
