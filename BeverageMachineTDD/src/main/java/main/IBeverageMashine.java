package main;

import product.Product;

import java.util.List;

public interface IBeverageMashine {
    Integer getBeveragesCount();

    void init(List<Product> products);
}
