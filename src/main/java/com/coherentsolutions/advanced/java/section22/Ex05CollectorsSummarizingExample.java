package com.coherentsolutions.advanced.java.section22;

import java.util.IntSummaryStatistics;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Ex05CollectorsSummarizingExample
 *
 * This class demonstrates how to use the built-in summarizing collectors
 * to compute statistical information about numerical data.
 */
public class Ex05CollectorsSummarizingExample {
    public static void main(String[] args) {
        // Creating an IntStream of numbers from 1 to 100
        IntStream numbersStream = IntStream.rangeClosed(1, 100); // Numbers from 1 to 100

        // Using Collectors.summarizingInt() to collect statistics
        IntSummaryStatistics stats = numbersStream
                .mapToObj(Integer::valueOf)
                .collect(Collectors.summarizingInt(Integer::intValue));

        // Displaying the statistical information
        System.out.println("Count: " + stats.getCount());
        System.out.println("Sum: " + stats.getSum());
        System.out.println("Min: " + stats.getMin());
        System.out.println("Max: " + stats.getMax());
        System.out.println("Average: " + stats.getAverage());

        // Output:
        // Count: 100
        // Sum: 5050
        // Min: 1
        // Max: 100
        // Average: 50.5
    }
}
