import org.junit.Before;
import org.junit.Test;
import task.FitnessData;
import task.Report;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Oleksandr_Khodakovsk on 9/28/2016.
 */
public class ReportTest {
    private Report rep;

    @Before
    public void setUp() throws Exception {
        rep = new Report();
    }



    @Test
    public void canDrink() throws Exception {
        rep.drink(LocalDate.now(), 1);
    }

    @Test
    public void canEat() throws Exception {
        rep.eat(LocalDate.now(), 1);
    }

    @Test
    public void canMove() throws Exception {
        rep.move(LocalDate.now(), 0.2, 1);
    }

    @Test
    public void restWater() throws Exception {
        rep.drink(LocalDate.now(), 1);
        assertThat(rep.restWater(WATERDOSE), is(2));
    }

    @Test
    public void restSteps() throws Exception {
        rep.move(LocalDate.now(), 0.5, 400);
        assertThat(rep.restSteps(MOVESTEPSDOSE), is(1600));
    }

    @Test
    public void toMuchMoveSteps() throws Exception {
        rep.move(LocalDate.now(), 1, 11000);
        assertThat(rep.hasToMuchMoveSteps(MOVESTEPSDOSE), is(true));
    }

    @Test
    public void zeroMoveSteps() throws Exception {
        assertThat(rep.hasZeroMoveSteps(), is(true));
    }

    @Test
    public void restMoveHours() throws Exception {
        rep.move(LocalDate.now(), 0.5, 400);
        assertThat(rep.restMoveHours(MOVETIMEDOSE), is(1.5));
    }

    @Test
    public void toMuchWater() throws Exception {
        rep.drink(LocalDate.now(), 10);
        assertThat(rep.hasToMuchWater(WATERDOSE), is(true));
    }

    @Test
    public void zeroWater() throws Exception {
        assertThat(rep.hasZeroWater(), is(true));
    }

    @Test
    public void zeroMoveHours() throws Exception {
        assertThat(rep.hasZeroMoveHours(), is(true));
    }

    @Test
    public void toMuchMoveHours() throws Exception {
        rep.move(LocalDate.now(), 4, 2);
        assertThat(rep.hasToMuchMoveHours(MOVETIMEDOSE), is(true));
    }

    @Test
    public void restCalories() throws Exception {
        rep.eat(LocalDate.now(), 1000);
        assertThat(rep.restCallories(CALLORIESDOSE), is(1500));
    }

    @Test
    public void nothingEatenTest() throws Exception {
        assertThat(rep.hasNothingEaten(), is(true));
    }

    @Test
    public void overeatenTest() throws Exception {
        rep.eat(LocalDate.now(), 5000);
        assertThat(rep.hasOverEaten(CALLORIESDOSE), is(true));
    }


}
