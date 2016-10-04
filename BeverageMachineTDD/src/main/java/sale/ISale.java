package sale;


import product.Product;

public interface ISale {
    void makeLineItem(Product product, int quantity);


    String getSaleInfo();

    Integer getTotal();
}
