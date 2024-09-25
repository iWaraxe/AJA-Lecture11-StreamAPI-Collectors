package com.coherentsolutions.advanced.java.section10.advanced;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Stream;

/**
 * Ex10AdvancedCombiningStreams
 *
 * This class explores advanced techniques for combining streams, including using custom collectors
 * and handling parallel stream processing. It demonstrates collecting stream elements into a custom data structure.
 */
public class Ex10AdvancedCombiningStreams {
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
 * Transaction
 *
 * A simple class representing a financial transaction with an ID and an amount.
 */
class Transaction {
    private String id;
    private double amount;

    public Transaction(String id, double amount) {
        this.id = id;
        this.amount = amount;
    }

    public String getId() { return id; }
    public double getAmount() { return amount; }

    @Override
    public String toString() {
        return "Transaction{id='" + id + "', amount=" + amount + "}";
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
    private List<String> transactionIds;

    public TransactionSummary() {
        this.totalAmount = 0.0;
        this.averageAmount = 0.0;
        this.transactionIds = new ArrayList<>();
    }

    public void addTransaction(Transaction transaction) {
        totalAmount += transaction.getAmount();
        transactionIds.add(transaction.getId());
    }

    public void combine(TransactionSummary other) {
        this.totalAmount += other.totalAmount;
        this.transactionIds.addAll(other.transactionIds);
    }

    public void calculateAverage(int count) {
        if (count > 0) {
            this.averageAmount = totalAmount / count;
        }
    }

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
        //    summary.calculateAverage(summary.transactionIds.size());
            return summary;
        };
    }

    @Override
    public Set<Characteristics> characteristics() {
        // Characteristics: No specific characteristics
        return Collections.emptySet();
    }
}
