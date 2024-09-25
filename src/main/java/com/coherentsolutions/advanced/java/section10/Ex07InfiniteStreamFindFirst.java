package com.coherentsolutions.advanced.java.section10;

import java.util.stream.Stream;

/**
 * Ex07InfiniteStreamFindFirst
 *
 * This class demonstrates how to handle infinite streams safely by using short-circuiting operations like findFirst().
 * It finds the first number divisible by 17 in an infinite stream of natural numbers.
 */
public class Ex07InfiniteStreamFindFirst {
    public static void main(String[] args) {
        // Generating an infinite stream of natural numbers starting from 1
        Stream<Integer> naturalNumbers = Stream.iterate(18, n -> n + 1);

        // Finding the first number divisible by 17
        int result = naturalNumbers
                .filter(n -> n % 17 == 0) // Filtering numbers divisible by 17
                .findFirst()              // Finding the first occurrence
                .orElse(-1);              // Providing a default value if not found

        System.out.println("First number divisible by 17: " + result);
    }
}
