import interfaces.IMenu;
import products.Coffee;
import products.Juice;
import products.Product;
import products.Tea;
import sale.Sale;

import java.util.List;
import java.util.Scanner;

/**
 * Created by Oleksandr_Khodakovsk on 9/26/2016.
 */
public class Menu implements IMenu {
    private static final int MIN_PRODUCT_QUANTITY = 1;
    private static final int MAX_PRODUCT_QUANTITY = 99;
    private Scanner scan = new Scanner(System.in);
    private List<Product> productList = null;
    private Sale sale = null;

    private final String[] MENU_ITEMS = {"0: Select product:", "1: " +
            "List of available products:", "2: exit"};
    private final String[] SUBMENU_ITEMS = {"Invalid selection",
            "Select quantity[" + MIN_PRODUCT_QUANTITY + ".." +
                    MAX_PRODUCT_QUANTITY + "]: \n",
            "What do you want to do next: \n" +
                    "0: select more products\n" +
                    "1: proceed to payment\n" +
                    "2: cancel order?", "Proceed to payment(0-yes, 1- no)?",
            "Currently paid:"};


    @Override
    public void printMenuItems() {
        for (String item : MENU_ITEMS)
            System.out.println(item);
    }

    @Override
    public void init() {
        productList.add(new Tea());
        productList.add(new Coffee());
        productList.add(new Juice());
    }

    @Override
    public String getProducts() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < productList.size(); i++) {
            sb.append(i + ": " + productList.get(i) + "\n");
        }
        return sb.toString();
    }

    @Override
    public void run() {
        String message;
        boolean runWhile = true;
        while (runWhile) {
            message = "";
            printMenuItems();
            switch (getIndex(0, 2, "Choose operation:")) {
                case 0:
                    message = makeSale();
                    break;
                case 1:
                    message = getProducts();
                    break;
                case 2:
                    runWhile = false;
                    message = "Machine is stopping its operation. " +
                            "Thank you for using it!";
                    break;
            }
            System.out.println(message);
        }
    }

    private String makeSale() {
        String message = "";
        int k = 0;
        sale = new Sale();

        while (k == 0) {
            addSaleLineItem();
            k = getIndex(0, 2, sale.getSaleInfo() + "\n" +
                    SUBMENU_ITEMS[2]);
        }
        if (k == 2) {
            sale = null;
            message = "Order canceled";
        }
        else {
            message = sale.makePayment();
        }
        return message;
    }


    public Sale addSaleLineItem() {
        System.out.println(getProducts());
        int productInx = getIndex(0, productList.size() - 1, "Make your" +
                " choice:");
        int quantity = getIndex(MIN_PRODUCT_QUANTITY,
                MAX_PRODUCT_QUANTITY, SUBMENU_ITEMS[1]);
        sale.makeLineItem(productList.get(productInx), quantity);

        return sale;
    }



    private int getIndex(final int lBound, final int rBound,
                         final String message) {
        int selectedIndex = lBound - 1;

        while ((selectedIndex <= lBound - 1) || (selectedIndex > rBound)) {
            try {
                System.out.println(message);
                selectedIndex = Integer.parseInt(scan.next());
                if ((selectedIndex <= lBound - 1) || (selectedIndex > rBound)) {
                    System.out.println(SUBMENU_ITEMS[0]);
                }
            }
            catch (NumberFormatException e) {
                System.out.println(SUBMENU_ITEMS[0]);
            }
        }
        return selectedIndex;
    }
}
