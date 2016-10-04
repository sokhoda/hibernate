package sale;


import product.Product;

/**
 * Created by Oleksandr_Khodakovsk on 9/27/2016.
 */
public interface ISale {
    void makeLineItem(Product product, int quantity);


    String getSaleInfo();

    Integer getTotal();
}
