package main;

import product.Coffee;
import product.Product;
import product.Tea;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        BeverageMachine machine = new BeverageMachine();
        List<Product> products = new ArrayList<>(Arrays.asList(new Tea(), new
                Coffee()));
        machine.init(products);
    }
}
