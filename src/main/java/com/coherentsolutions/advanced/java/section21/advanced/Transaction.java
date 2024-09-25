package com.coherentsolutions.advanced.java.section21.advanced;

/**
 * Transaction
 *
 * A simple class representing a financial transaction with an ID and an amount.
 */
public class Transaction {
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
        return "Transaction{id='" + id + "', amount=" + amount + '}';
    }
}
