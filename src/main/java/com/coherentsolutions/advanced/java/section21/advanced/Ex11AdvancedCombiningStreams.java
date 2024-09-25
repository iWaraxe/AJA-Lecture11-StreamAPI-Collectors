package com.coherentsolutions.advanced.java.section21.advanced;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Ex11AdvancedCombiningStreams
 *
 * This class explores advanced techniques for combining streams, including using custom collectors
 * and handling parallel stream processing. It demonstrates collecting stream elements into a custom data structure.
 */
public class Ex11AdvancedCombiningStreams {
    public static void main(String[] args) {
        // Creating multiple streams of transactions
        Stream<Transaction> stream1 = Stream.of(
                new Transaction("T1", 100.0),
                new Transaction("T2", 200.0)
        );
        Stream<Transaction> stream2 = Stream.of(
                new Transaction("T3", 300.0),
                new Transaction("T4", 400.0)
        );
        Stream<Transaction> stream3 = Stream.of(
                new Transaction("T5", 500.0),
                new Transaction("T6", 600.0)
        );

        // Combining multiple streams using Stream.of() and flatMap()
        Stream<Transaction> combinedStream = Stream.of(stream1, stream2, stream3)
                .flatMap(Function.identity());

        // Collecting transactions into a custom TransactionSummary using a custom collector
        TransactionSummary summary = combinedStream.collect(new TransactionSummaryCollector());

        // Displaying the summary
        System.out.println(summary);
    }
}

/**
 * TransactionSummary
 *
 * A custom data structure to hold aggregated information about transactions.
 * It includes the total amount, average amount, and a list of transaction IDs.
 */
class TransactionSummary {
    private double totalAmount;
    private double averageAmount;
    protected List<String> transactionIds;

    public TransactionSummary() {
        this.totalAmount = 0.0;
        this.averageAmount = 0.0;
        this.transactionIds = new ArrayList<>();
    }

    /**
     * Adds a transaction to the summary by updating the total amount and adding the transaction ID.
     *
     * @param transaction the Transaction to add
     */
    public void addTransaction(Transaction transaction) {
        totalAmount += transaction.getAmount();
        transactionIds.add(transaction.getId());
    }

    /**
     * Combines another TransactionSummary into this one by adding their totals and merging transaction IDs.
     *
     * @param other the other TransactionSummary to combine
     */
    public void combine(TransactionSummary other) {
        this.totalAmount += other.totalAmount;
        this.transactionIds.addAll(other.transactionIds);
    }

    /**
     * Calculates the average transaction amount based on the number of transactions.
     *
     * @param count the number of transactions
     */
    public void calculateAverage(int count) {
        if (count > 0) {
            this.averageAmount = totalAmount / count;
        }
    }

    /**
     * Overrides toString to provide a readable representation of the transaction summary.
     *
     * @return the string representation of the transaction summary
     */
    @Override
    public String toString() {
        return "TransactionSummary{" +
                "totalAmount=" + totalAmount +
                ", averageAmount=" + averageAmount +
                ", transactionIds=" + transactionIds +
                '}';
    }
}

/**
 * TransactionSummaryCollector
 *
 * A custom collector that aggregates Transaction objects into a TransactionSummary.
 */
class TransactionSummaryCollector implements Collector<Transaction, TransactionSummary, TransactionSummary> {

    @Override
    public Supplier<TransactionSummary> supplier() {
        // Supplier: Provides a new TransactionSummary instance
        return TransactionSummary::new;
    }

    @Override
    public BiConsumer<TransactionSummary, Transaction> accumulator() {
        // Accumulator: Adds a Transaction to the TransactionSummary
        return TransactionSummary::addTransaction;
    }

    @Override
    public BinaryOperator<TransactionSummary> combiner() {
        // Combiner: Merges two TransactionSummary instances
        return (summary1, summary2) -> {
            summary1.combine(summary2);
            return summary1;
        };
    }

    @Override
    public Function<TransactionSummary, TransactionSummary> finisher() {
        // Finisher: Calculates the average amount based on the number of transactions
        return summary -> {
            summary.calculateAverage(summary.transactionIds.size());
            return summary;
        };
    }

    @Override
    public Set<Characteristics> characteristics() {
        // Characteristics: No specific characteristics
        return Collections.emptySet();
    }
}
