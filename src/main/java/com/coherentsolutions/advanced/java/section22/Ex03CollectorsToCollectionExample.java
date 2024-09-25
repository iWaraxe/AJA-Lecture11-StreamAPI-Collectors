package com.coherentsolutions.advanced.java.section22;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Ex03CollectorsToCollectionExample
 *
 * This class demonstrates how to use the built-in collector Collectors.toCollection()
 * to accumulate stream elements into specific collection types, such as LinkedList and TreeSet.
 */
public class Ex03CollectorsToCollectionExample {
    public static void main(String[] args) {
        // Creating a stream of names with duplicates
        Stream<String> namesStream = Stream.of("Alice", "David", "Bob", "Charlie", "Alice");

        // Collecting to a specific collection using toCollection() - LinkedList
        List<String> namesLinkedList = namesStream.collect(Collectors.toCollection(LinkedList::new));
        System.out.println("LinkedList: " + namesLinkedList);
        // Output: LinkedList: [Alice, Bob, Charlie, David, Alice]

        // Since the stream is consumed, we need to recreate it
        namesStream = Stream.of("Alice", "Bob", "David", "Charlie", "Alice");

        // Collecting to a specific collection using toCollection() - TreeSet (sorted and unique)
        TreeSet<String> namesTreeSet = namesStream.collect(Collectors.toCollection(TreeSet::new));
        System.out.println("TreeSet: " + namesTreeSet);
        // Output: TreeSet: [Alice, Bob, Charlie, David]
    }
}
