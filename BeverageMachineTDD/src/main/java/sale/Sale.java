package sale;

import interfaces.ISale;
import payments.Payment;
import product.Product;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Oleksandr_Khodakovsk on 9/26/2016.
 */
public class Sale implements ISale {
    private List<SalesLineItem> lineItemList = new ArrayList<>();
    private Payment payment;
    private Date date;

    @Override
    public void makeLineItem(Product product, int quantity) {
        lineItemList.add(new SalesLineItem(product, quantity));
    }

    @Override
    public String makePayment() {
        String message ="";
        payment = new Payment(getTotal());
        date = new Date();
        payment.create();
        if (!payment.isCanceled()) {
            message = getFinalGreeting();
        }
        else {
            payment = null;
        }
        return message;
    }

    @Override
    public String getFinalGreeting() {
        StringBuilder sb = new StringBuilder();

        sb.append("Payment successful!!\n Please take your order");
        int change = payment.getSumPaid() - payment.getSumDue();
        if (change > 0) {
            sb.append(" and a change=" + change);
        }
        return sb.toString();
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
    public int getTotal() {
        int sum = 0;
        for (SalesLineItem item : lineItemList) {
            sum += item.subtotal();
        }
        return sum;
    }
}
