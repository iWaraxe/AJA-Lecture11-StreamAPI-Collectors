package com.coherentsolutions.advanced.java.section22;

/**
 * Product
 * <p>
 * A simple class representing a product with a name, category, and price.
 */
class Product {
    private String name;
    private String category;
    private double price;

    public Product(String name, String category, double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + " (" + category + ") - $" + price;
    }
}
