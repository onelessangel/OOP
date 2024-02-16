package entities;

import enums.Category;

public final class Gift {
    private final String productName;
    private final double price;
    private final Category category;
    private int quantity;

    public Gift(final String productName, final double price, final Category category,
                    final int quantity) {
        this.productName = productName;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
    }

    /**
     * Decreases gift quantity by one unit.
     */
    public void decreaseQuantity() {
        quantity--;
    }

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

    public Category getCategory() {
        return category;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "\nGift{"
                + "productName='" + productName
                + ", price=" + price
                + ", category='" + category
                + ", quantity='" + quantity
                + '}' + '\n';
    }
}
