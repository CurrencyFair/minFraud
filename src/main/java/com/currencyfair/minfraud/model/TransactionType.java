package com.currencyfair.minfraud.model;

import com.currencyfair.minfraud.util.Stringifiable;

/**
 * Enumeration of allowed
 * {@link TransactionInfo#getTxnType() transaction types}.
 * @since 1.0.0
 */
public enum TransactionType implements Stringifiable {
    /**
     * Credit card transaction.
     */
    CREDITCARD,
    /**
     * Debit card transaction.
     */
    DEBITCARD,
    /**
     * Paypal transaction.
     */
    PAYPAL,
    /**
     * Google checkout transaction.
     */
    GOOGLE,
    /**
     * Other transaction.
     */
    OTHER,
    /**
     * Lead generation (non-purchase transaction).
     */
    LEAD,
    /**
     * Online survey (non-purchase transaction).
     */
    SURVEY,
    /**
     * Site registration (non-purchase transaction).
     */
    SITEREG;

    @Override
    public String stringify() {
        return name().toLowerCase();
    }
}
