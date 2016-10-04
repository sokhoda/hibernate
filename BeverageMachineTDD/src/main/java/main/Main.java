package main;

import product.Coffee;
import product.Product;
import product.Tea;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Oleksandr_Khodakovsk on 9/26/2016.
 */
public class Main {
    public static void main(String[] args) {
        BeverageMachine machine = new BeverageMachine();
        List<Product> products = new ArrayList<>(Arrays.asList(new Tea(), new
                Coffee()));
        machine.init(products);
    }
}
