package payments;

import java.util.List;

public interface IPayment {

    Integer create(List<Integer> coinNominal, List<Integer> quantity);
}
