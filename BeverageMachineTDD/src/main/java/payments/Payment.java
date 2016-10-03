package payments;

import exceptions.NotValidCoinNominalValue;
import interfaces.IPayment;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by Oleksandr_Khodakovsk on 9/27/2016.
 */
public class Payment implements IPayment {
    private final Set<Integer> allowedCoins = new HashSet<>();
    private final Integer[] allowedCoinsArr = {1, 5, 10, 25, 50};
    private int sumDue;
    private int sumPaid;
    private boolean canceled;

    public Payment(int sumDue) {
        this.sumDue = sumDue;
    }

    public Payment() {
        allowedCoins.addAll(Arrays.asList(allowedCoinsArr));
    }

    public String processCoin(int coin) throws NotValidCoinNominalValue {
        if (!allowedCoins.contains(coin)) {
            throw new NotValidCoinNominalValue("\nCoins of nominal value " +
                    coin + " are not accepted.");
        }
        else {
            sumPaid += coin;
        }
        return "Paid: " + getSumPaid() + " of " + getSumDue();
    }


    @Override
    public void create() {
        String message = "";
        while (sumPaid < sumDue && !canceled) {
            try {
                int coin = getCoinFromCustomer("Please insert coins(0 - " +
                        "cancel):");
                if (coin == 0) {
                    setCanceled(true);
                    message = "Payment canceled by the customer.";
                }
                else {
                    message = processCoin(coin);
                }
            }
            catch (NotValidCoinNominalValue e) {
                e.printStackTrace();
            }
            System.out.println(message);
        }
    }

    private int getCoinFromCustomer(final String message) {
        Scanner scan = new Scanner(System.in);
        int result = -1;
        while (result == -1) {
            try {
                System.out.println(message);
                result = Integer.parseInt(scan.next());
            }
            catch (NumberFormatException e) {
                System.out.println("Not int value.");
            }
        }
        return result;
    }

    public Set<Integer> getAllowedCoins() {
        return allowedCoins;
    }

    public Integer[] getAllowedCoinsArr() {
        return allowedCoinsArr;
    }

    public int getSumDue() {
        return sumDue;
    }

    public void setSumDue(int sumDue) {
        this.sumDue = sumDue;
    }

    public int getSumPaid() {
        return sumPaid;
    }

    public void setSumPaid(int sumPaid) {
        this.sumPaid = sumPaid;
    }

    public boolean isCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }
}
