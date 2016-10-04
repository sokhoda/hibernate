package main;

import product.Product;
import sale.Sale;

import java.util.ArrayList;
import java.util.List;

public class BeverageMachine implements IBeverageMashine {
    private List<Product> productList = new ArrayList<>();
    private Sale sale;

    @Override
    public Integer getBeveragesCount(){
        Integer result = 0;
        if (productList != null){
            result = productList.size();
        }
        return result;
    }

    @Override
    public void init(List<Product> products) {
        if (products != null) {
            for (Product item : products) {
                productList.add(item);
            }
        }
    }


    public Integer sellBeverages(Sale sale) {
        return sale.getTotal();
    }
}
