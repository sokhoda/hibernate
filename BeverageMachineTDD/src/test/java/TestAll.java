import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({BeverageMaschineTest.class, PaymentTest.class,
        SaleLineItemTest.class, SaleTest.class})
public class TestAll {
}
