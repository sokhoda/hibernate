package main;

import product.Product;

import java.util.List;

/**
 * Created by Oleksandr_Khodakovsk on 9/26/2016.
 */
public interface IBeverageMashine {
    Integer getBeveragesCount();

    void init(List<Product> products);
}
