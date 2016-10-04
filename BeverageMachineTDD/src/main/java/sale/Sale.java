package sale;

import payments.Payment;
import product.Product;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Sale implements ISale {
    private List<SalesLineItem> lineItemList = new ArrayList<>();
    private Payment payment;
    private Date date;

    @Override
    public void makeLineItem(Product product, int quantity) {
        lineItemList.add(new SalesLineItem(product, quantity));
    }

    @Override
    public String getSaleInfo() {
        StringBuilder sb = new StringBuilder();
        for (SalesLineItem item : lineItemList) {
            sb.append(item.getInfo());
        }
        return sb.toString();
    }

    @Override
    public Integer getTotal() {
        int sum = 0;
        for (SalesLineItem item : lineItemList) {
            sum += item.subtotal();
        }
        return sum;
    }
}
