package com.coherentsolutions.advanced.java.section22;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Ex16CollectorsAdvancedGroupAndMapExample
 *
 * This class demonstrates advanced usage of Collectors.groupingBy() and Collectors.mapping()
 * to perform complex data transformations within grouped stream elements.
 */
public class Ex16CollectorsAdvancedGroupAndMapExample {
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

        // Grouping products by category and mapping to their names
        Map<String, List<String>> productNamesByCategory = products.stream()
                .collect(Collectors.groupingBy(Product::getCategory,
                        Collectors.mapping(Product::getName, Collectors.toList())));

        // Displaying the product names grouped by category
        System.out.println("Product Names by Category: " + productNamesByCategory);
        // Output:
        // Product Names by Category: {Electronics=[Laptop, Smartphone], Furniture=[Desk Chair, Table], Stationery=[Pen, Notebook]}
    }
}