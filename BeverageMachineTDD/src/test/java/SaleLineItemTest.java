import org.junit.Test;
import product.Tea;
import sale.SalesLineItem;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.*;

public class SaleLineItemTest {
    @Test
    public void subTotal() throws Exception {
            SalesLineItem sli = new SalesLineItem(new Tea(), 10);
        assertThat(sli.subtotal(), is(250));

    }
}
