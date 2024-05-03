package com.pluralsight.services;

import com.pluralsight.models.LedgerEntry;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Ledger {
    private List<LedgerEntry> entries;
    private static final String FILE_PATH = "src/main/java/com/pluralsight/files/ledger.csv";

    public Ledger() {
        // Initialize the list of ledger entries
        entries = new ArrayList<>();
        // Load existing entries from the CSV file (if any)
        loadEntriesFromFile();
    }

    // Method to add a deposit entry to the ledger
    public void addDeposit(String date, String description, double amount) {
        entries.add(new LedgerEntry(date, description, amount));
        saveEntriesToFile();
        System.out.println("Deposit added successfully.");
    }

    // Method to make a payment (debit) entry to the ledger
    public void makePayment(String date, String description, double amount) {
        entries.add(new LedgerEntry(date, description, -amount)); // negative amount for payments
        saveEntriesToFile();
        System.out.println("Payment made successfully.");
    }

    // Method to display all ledger entries
    public void displayLedger() {
        if (entries.isEmpty()) {
            System.out.println("Ledger is empty.");
        } else {
            System.out.println("=== Ledger ===");
            for (int i = entries.size() - 1; i >= 0; i--) {
                System.out.println(entries.get(i));
            }
        }
    }

    // Method to save ledger entries to the CSV file
    private void saveEntriesToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (LedgerEntry entry : entries) {
                writer.println(entry.toCSV());
            }
        } catch (IOException e) {
            System.err.println("Error saving ledger entries to file: " + e.getMessage());
        }
    }

    // Method to load ledger entries from the CSV file
    private void loadEntriesFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    entries.add(new LedgerEntry(parts[0], parts[1], Double.parseDouble(parts[2])));
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading ledger entries from file: " + e.getMessage());
        }
    }

    // Optional: method to search for entries by vendor name
    public void searchByVendor(String vendorName) {
        System.out.println("=== Entries for Vendor: " + vendorName + " ===");
        boolean found = false;
        for (LedgerEntry entry : entries) {
            if (entry.getDescription().equalsIgnoreCase(vendorName)) {
                System.out.println(entry);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No entries found for vendor: " + vendorName);
        }
    }
}

