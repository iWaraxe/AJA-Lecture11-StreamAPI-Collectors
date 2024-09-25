package com.coherentsolutions.advanced.java.section22;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Ex01CollectorsToListExample
 *
 * This class demonstrates how to use the built-in collector Collectors.toList()
 * to accumulate stream elements into a List.
 */
public class Ex01CollectorsToListExample {
    public static void main(String[] args) {
        // Creating a list of names
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");

        // Using stream to filter names starting with 'A' or 'B' and collect them into a List
        List<String> filteredNames = names.stream()
                .filter(name -> name.startsWith("A") || name.startsWith("B"))
                .collect(Collectors.toList()); // Using Collectors.toList() to collect the results into a List

        // Displaying the filtered names
        System.out.println("Filtered Names: " + filteredNames); // Output: [Alice, Bob]
    }
}
