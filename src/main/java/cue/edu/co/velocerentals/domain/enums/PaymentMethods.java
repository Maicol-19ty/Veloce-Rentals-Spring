package cue.edu.co.velocerentals.domain.enums;

// Enum representing different payment methods.
public enum PaymentMethods {

    // Enum constants for Credit Card, Debit Card, and Cash.
    creditcard("credit card"),
    debitcard("debit card"),
    cash("cash");

    // Constructor for PaymentMethods enum.
    PaymentMethods(String paymentMethod) {
    }
}
