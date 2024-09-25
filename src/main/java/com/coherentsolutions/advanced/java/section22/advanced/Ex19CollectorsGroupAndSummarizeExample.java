package com.coherentsolutions.advanced.java.section22.advanced;

import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Ex19CollectorsGroupAndSummarizeExample
 *
 * This class demonstrates how to combine Collectors.groupingBy() with Collectors.summarizingDouble()
 * to compute statistical summaries for each group.
 */
public class Ex19CollectorsGroupAndSummarizeExample {
    public static void main(String[] args) {
        // Creating a list of products with categories and prices
        List<Product> products = Arrays.asList(
                new Product("Laptop", "Electronics", 1200.0),
                new Product("Smartphone", "Electronics", 800.0),
                new Product("Desk Chair", "Furniture", 150.0),
                new Product("Pen", "Stationery", 2.5),
                new Product("Notebook", "Stationery", 5.0),
                new Product("Table", "Furniture", 300.0)
        );

        // Grouping products by category and summarizing their prices
        Map<String, DoubleSummaryStatistics> priceStatsByCategory = products.stream()
                .collect(Collectors.groupingBy(Product::getCategory,
                        Collectors.summarizingDouble(Product::getPrice)));

        // Displaying the summary statistics by category
        System.out.println("Price Statistics by Category:");
        priceStatsByCategory.forEach((category, stats) -> {
            System.out.println(category + ": " + stats);
        });
        // Output:
        // Price Statistics by Category:
        // Electronics: DoubleSummaryStatistics{count=2, sum=2000.000000, min=800.000000, average=1000.000000, max=1200.000000}
        // Furniture: DoubleSummaryStatistics{count=2, sum=450.000000, min=150.000000, average=225.000000, max=300.000000}
        // Stationery: DoubleSummaryStatistics{count=2, sum=7.500000, min=2.500000, average=3.750000, max=5.000000}
    }
}

/**
 * Product
 *
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

    public String getCategory() { return category; }
    public double getPrice() { return price; }
    public String getName() { return name; }

    @Override
    public String toString() {
        return name + " (" + category + ") - $" + price;
    }
}
