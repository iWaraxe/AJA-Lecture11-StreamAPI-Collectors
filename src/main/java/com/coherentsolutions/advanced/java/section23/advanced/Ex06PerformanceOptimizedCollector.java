package com.coherentsolutions.advanced.java.section23.advanced;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collector;
import java.util.stream.Stream;

/**
 * Ex06PerformanceOptimizedCollector
 *
 * This class demonstrates how to create a performance-optimized custom collector
 * for large datasets, minimizing synchronization overhead in parallel streams.
 * It accumulates integers into a thread-safe ConcurrentHashMap and calculates their frequencies.
 */
public class Ex06PerformanceOptimizedCollector {
    public static void main(String[] args) {
        // Creating a large stream of random numbers
        Stream<Integer> numbersStream = new Random().ints(1, 100).limit(1000000).boxed();

        // Using the performance-optimized custom collector to count frequencies
        Map<Integer, Long> frequencyMap = numbersStream.collect(toConcurrentFrequencyMap());

        // Displaying some sample frequencies
        System.out.println("Sample Frequencies:");
        frequencyMap.entrySet().stream().limit(10).forEach(System.out::println);
    }

    /**
     * Creates a performance-optimized custom collector that accumulates Integer elements into a concurrent frequency map.
     * It counts the occurrences of each integer in the stream.
     *
     * @return a Collector that collects Integer elements into a concurrent frequency Map<Integer, Long>
     */
    public static Collector<Integer, ConcurrentMap<Integer, Long>, Map<Integer, Long>> toConcurrentFrequencyMap() {
        return Collector.of(
                ConcurrentHashMap::new,                 // Supplier: Provides a new ConcurrentHashMap
                (map, number) -> map.merge(number, 1L, Long::sum), // Accumulator: Increments the count for each number
                (map1, map2) -> {                       // Combiner: Merges two ConcurrentHashMaps
                    map2.forEach((key, value) ->
                            map1.merge(key, value, Long::sum)
                    );
                    return map1;
                },
                map -> Collections.unmodifiableMap(map), // Finisher: Returns an immutable view of the map
                Collector.Characteristics.CONCURRENT,    // Characteristics: Indicates that the collector is concurrent
                Collector.Characteristics.UNORDERED    // Characteristics: Indicates unordered collection
        );
    }
}
