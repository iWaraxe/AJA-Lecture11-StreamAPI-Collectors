package com.coherentsolutions.advanced.java.section10;

import java.util.stream.Stream;

/**
 * Ex08InfiniteStreamPitfall
 *
 * This class demonstrates a common pitfall when working with infinite streams: forgetting to include a termination condition.
 * The code below will run indefinitely and should not be executed as is.
 */
public class Ex08InfiniteStreamPitfall {
    public static void main(String[] args) {
        // Creating an infinite stream of random double numbers
        Stream<Double> endlessStream = Stream.generate(Math::random);

        // Processing the infinite stream without a termination condition
        // This will cause the program to run indefinitely
        // Uncomment the following line to see the pitfall (not recommended)
        // endlessStream.forEach(System.out::println);
    }
}
