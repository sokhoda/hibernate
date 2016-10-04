import main.BeverageMachine;
import org.junit.Before;
import org.junit.Test;
import product.Coffee;
import product.Juice;
import product.Tea;
import sale.Sale;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Oleksandr_Khodakovsk on 10/3/2016.
 */
public class BeverageMaschineTest {
    private BeverageMachine bm;
    private Sale sale;

    @Before
    public void setUp() throws Exception {
        bm = new BeverageMachine();
        bm.init(Arrays.asList(new Coffee(), new Tea(), new Juice()));
        sale = new Sale();
    }

    @Test
    public void getBeveragesCount() {
        Integer result = 0;
        assertThat(bm.getBeveragesCount(), is(3));
    }

    @Test
    public void sellBeverages() throws Exception {
        sale.makeLineItem(new Coffee(), 3);
        sale.makeLineItem(new Juice(), 3);
        Integer orderSum  = bm.sellBeverages(sale);
        assertThat(orderSum, is(240));
    }

    @Test
    public void sellBeveragesNull() throws Exception {
        Integer orderSum  = bm.sellBeverages(sale);
        assertThat(orderSum, is(0));
    }
}
