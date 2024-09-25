package com.coherentsolutions.advanced.java.section10;

import java.util.stream.Stream;

/**
 * Ex04InfiniteStreamGenerate
 *
 * This class demonstrates how to create an infinite stream using Stream.generate().
 * It generates an infinite stream of random numbers and limits the stream to a finite number of elements to prevent an infinite loop.
 */
public class Ex04InfiniteStreamGenerate {
    public static void main(String[] args) {
        // Generating an infinite stream of random double numbers between 0.0 and 1.0
        Stream<Double> randomNumbers = Stream.generate(Math::random);

        // Limiting the stream to 5 elements to prevent an infinite loop
        randomNumbers.limit(5)
                .forEach(System.out::println);
    }
}
