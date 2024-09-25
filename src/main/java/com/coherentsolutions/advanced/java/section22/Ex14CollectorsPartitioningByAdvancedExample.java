package com.coherentsolutions.advanced.java.section22;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Ex14CollectorsPartitioningByAdvancedExample
 *
 * This class demonstrates advanced usage of Collectors.partitioningBy(),
 * including partitioning with downstream collectors.
 */
public class Ex14CollectorsPartitioningByAdvancedExample {
    public static void main(String[] args) {
        // Creating a list of integers
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // Partitioning numbers into even and odd, and then summing each group
        Map<Boolean, Integer> sumByParity = numbers.stream()
                .collect(Collectors.partitioningBy(n -> n % 2 == 0,
                        Collectors.summingInt(Integer::intValue)));

        // Displaying the sum of even and odd numbers
        System.out.println("Sum by Parity: " + sumByParity);
        // Output: Sum by Parity: {false=25, true=30}
    }
}
