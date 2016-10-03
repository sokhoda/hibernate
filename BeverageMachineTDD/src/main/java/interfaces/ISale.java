package interfaces;


import product.Product;

/**
 * Created by Oleksandr_Khodakovsk on 9/27/2016.
 */
public interface ISale {
    void makeLineItem(Product product, int quantity);

    String makePayment();

    String getFinalGreeting();

    String getSaleInfo();

    int getTotal();
}
