package com.example.currencyconverter;

import java.io.IOException;
import java.util.Currency;

public class Main {
    public static void main(String[] args) {
        try {
            // Specify the source and target currencies
            Currency fromCurrency = Currency.getInstance("USD");
            Currency toCurrency = Currency.getInstance("EUR");

            // Call the rate method
            double conversionRate = CurrencyConverter.rate(fromCurrency, toCurrency);

            // Print the conversion rate
            System.out.println("Conversion rate from " + fromCurrency.getCurrencyCode()
                    + " to " + toCurrency.getCurrencyCode() + " is: " + conversionRate);
        } catch (IOException e) {
            // Handle potential IO exceptions
            System.err.println("Error fetching conversion rate: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            // Handle invalid currency codes
            System.err.println("Invalid currency code: " + e.getMessage());
        }
    }
}
