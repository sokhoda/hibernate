import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by Oleksandr_Khodakovsk on 9/30/2016.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({BeverageMaschineTest.class, PaymentTest.class,
        SaleLineItemTest.class, SaleTest.class})
public class TestAll {
}
