package com.coherentsolutions.advanced.java.section21;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Ex05RealWorldCollectorAnalogy
 *
 * This class provides a real-world analogy of a Collector by simulating a factory assembly line
 * where items are collected into boxes.
 */
public class Ex05RealWorldCollectorAnalogy {
    public static void main(String[] args) {
        // Creating a stream of items to be collected
        Stream<String> itemsStream = Stream.of("Item1", "Item2", "Item3", "Item4", "Item5");

        // Using a custom collector to collect items into a box (List)
        Collector<String, List<String>, List<String>> boxCollector = Collector.of(
                ArrayList::new,           // Supplier: Provides a new ArrayList as the box
                List::add,                // Accumulator: Adds each item to the box
                (box1, box2) -> {         // Combiner: Merges two boxes by adding all items from box2 into box1
                    box1.addAll(box2);
                    return box1;
                },
                Collector.Characteristics.UNORDERED // Characteristics: Indicates unordered collection
        );

        // Collecting items into the box
        List<String> box = itemsStream.collect(boxCollector);

        // Displaying the contents of the box
        System.out.println("Box contains: " + box); // Output: Box contains: [Item1, Item2, Item3, Item4, Item5]
    }
}
