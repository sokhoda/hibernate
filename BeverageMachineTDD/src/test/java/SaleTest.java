import org.junit.Before;
import org.junit.Test;
import product.Coffee;
import product.Juice;
import sale.Sale;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SaleTest {
    private Sale sale;

    @Before
    public void setUp() throws Exception {
        sale = new Sale();
        sale.makeLineItem(new Coffee(), 3);
        sale.makeLineItem(new Juice(), 3);
    }

    @Test
    public void getTotal() throws Exception {
        Integer result = sale.getTotal();
        assertThat(result, is(240));
    }
}
