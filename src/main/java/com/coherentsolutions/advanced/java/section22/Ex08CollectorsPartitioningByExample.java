package com.coherentsolutions.advanced.java.section22;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Ex08CollectorsPartitioningByExample
 *
 * This class demonstrates how to use the built-in collector Collectors.partitioningBy()
 * to partition stream elements into two groups based on a predicate.
 */
public class Ex08CollectorsPartitioningByExample {
    public static void main(String[] args) {
        // Creating a list of integers
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        // Partitioning numbers into even and odd using Collectors.partitioningBy()
        Map<Boolean, List<Integer>> partitionedNumbers = numbers.stream()
                .collect(Collectors.partitioningBy(n -> n % 2 == 0));

        // Displaying the partitioned numbers
        System.out.println("Partitioned Numbers: " + partitionedNumbers);
        // Output: Partitioned Numbers: {false=[1, 3, 5], true=[2, 4, 6]}
    }
}
