package payments;

import java.util.*;

/**
 * Created by Oleksandr_Khodakovsk on 9/27/2016.
 */
public class Payment implements IPayment {
    private final Set<Integer> allowedCoins = new HashSet<>();
    private int sumDue;
    private int sumPaid;
    private boolean canceled;

    public Payment(int sumDue) {
        this.sumDue = sumDue;
    }

    public Payment() {
    }

    @Override
    public Integer create(List<Integer> coinNominal, List<Integer> quantity){
        Integer result = null;
        Integer sum = 0;
        if (coinNominal != null && quantity != null && coinNominal.size() ==
                quantity.size()) {
            for (int i = 0; i < coinNominal.size(); i++) {
                sum += quantity.get(i) * coinNominal.get(i);
            }
            result = sum - sumDue;
        }
        return  result;
    }


    public int getSumDue() {
        return sumDue;
    }

    public void setSumDue(int sumDue) {
        this.sumDue = sumDue;
    }
}
