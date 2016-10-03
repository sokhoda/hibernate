package product;

/**
 * Created by Oleksandr_Khodakovsk on 9/26/2016.
 */
public abstract class Product {
    private String name;
    private int price;
    private String description = "<default>";

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return name + (description.length() > 0 ? ", " : "") + description +
                "(" + price + ")";
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
