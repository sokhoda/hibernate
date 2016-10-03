import org.junit.Test;
import product.Tea;
import sale.SalesLineItem;

import static org.hamcrest.MatcherAssert.*;
import static sun.nio.cs.Surrogate.is;

/**
 * Created by Oleksandr_Khodakovsk on 10/3/2016.
 */
public class SaleLineItemTest {
    @Test
    public void subTotal() throws Exception {
            SalesLineItem sli = new SalesLineItem(new Tea(), 10);
        assertThat(sli.subtotal(), is(10));

    }
}
