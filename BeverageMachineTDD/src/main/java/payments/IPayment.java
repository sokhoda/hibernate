package payments;

import java.util.List;

/**
 * Created by Oleksandr_Khodakovsk on 9/27/2016.
 */
public interface IPayment {

    Integer create(List<Integer> coinNominal, List<Integer> quantity);
}
