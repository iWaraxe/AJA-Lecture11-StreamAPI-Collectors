package com.coherentsolutions.advanced.java.section22;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Ex12CollectorsCustomTransformationExample
 *
 * This class demonstrates how to use Collectors.mapping() to perform custom transformations
 * on grouped stream elements.
 */
public class Ex12CollectorsCustomTransformationExample {
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

        // Grouping products by category and mapping to their prices
        Map<String, List<Double>> pricesByCategory = products.stream()
                .collect(Collectors.groupingBy(Product::getCategory,
                        Collectors.mapping(Product::getPrice, Collectors.toList())));

        // Displaying the prices grouped by category
        System.out.println("Prices by Category: " + pricesByCategory);
        // Output:
        // Prices by Category: {Electronics=[1200.0, 800.0], Furniture=[150.0, 300.0], Stationery=[2.5, 5.0]}
    }
}

