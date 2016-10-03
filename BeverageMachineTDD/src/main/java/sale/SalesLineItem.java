package sale;


import product.Product;

/**
 * Created by Oleksandr_Khodakovsk on 9/26/2016.
 */
public class SalesLineItem{
    private int quantity;
    private Product product;

    public SalesLineItem(Product product, int quantity) {
        this.quantity = quantity;
        this.product = product;
    }

    public String getInfo() {
        return product.getName() + "x" + quantity + "\n";
    }

    public int subtotal(){
        return product.getPrice()*quantity;
    }
}
