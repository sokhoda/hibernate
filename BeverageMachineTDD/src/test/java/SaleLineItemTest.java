import org.junit.Test;
import product.Tea;
import sale.SalesLineItem;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.*;

public class SaleLineItemTest {
    private SalesLineItem sli;
    @Test
    public void subTotal() throws Exception {
        sli = new SalesLineItem(new Tea(), 10);
        assertThat(sli.subtotal(), is(250));

    }
}
