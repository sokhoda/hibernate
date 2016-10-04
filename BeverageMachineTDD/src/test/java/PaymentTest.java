import org.junit.Test;
import payments.Payment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PaymentTest {
    private Payment payment = new Payment();
    private final List<Integer> coinNominal = new ArrayList<>(Arrays.asList
            (1, 5, 10, 25, 50));

    @Test
    public void create() throws Exception {
        List<Integer> quantity = new ArrayList<>(Arrays.asList(0, 2, 4, 2, 0));
        payment.setSumDue(100);
        Integer rest = payment.create(coinNominal, quantity);
        assertThat(rest, is(0));
    }
    @Test
    public void createNull() throws Exception {
        List<Integer> quantity = new ArrayList<>(Arrays.asList(0, 2, 4, 2));
        payment.setSumDue(100);
        Integer rest = payment.create(coinNominal, quantity);
        assertThat(rest == null, is(true));
    }
}
