package com.pluralsight.services;

import com.pluralsight.models.LedgerEntry;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ledger {
    private List<LedgerEntry> entries;
    private static final String FILE_PATH = "src/main/java/com/pluralsight/files/ledger.csv";
    private Scanner scanner;

    public Ledger() {

        // Initialize the list of ledger entries
        entries = new ArrayList<>();
        // Load existing entries from the CSV file (if any)
        loadEntriesFromFile();
    }

    public Ledger(Scanner scanner)
    {
        this.scanner = scanner; // Initialize the scanner field
        entries = new ArrayList<>();
        loadEntriesFromFile();
    }

    // Method to add a deposit entry to the ledger
    public void addDeposit(String date, String time, String description, String vendor, double amount) {
        entries.add(new LedgerEntry(date, time, description, vendor, amount));
        saveEntriesToFile();
        System.out.println("Deposit added successfully.");
    }

    // Method to make a payment (debit) entry to the ledger
    public void makePayment(String date, String time, String description, String vendor, double amount) {
        entries.add(new LedgerEntry(date, time, description, vendor, -amount)); // negative amount for payments
        saveEntriesToFile();
        System.out.println("Payment made successfully.");
    }

    // Method to display all ledger entries
// Method to display all ledger entries in batches of 10
    public void displayLedger() {
        if (entries.isEmpty()) {
            System.out.println("Ledger is empty.");
        } else {
            System.out.println("=== Ledger ===");
            int batchSize = 10;
            int totalEntries = entries.size();
            int startIndex = 0;
            while (startIndex < totalEntries) {
                int endIndex = Math.min(startIndex + batchSize, totalEntries);
                for (int i = endIndex - 1; i >= startIndex; i--) {
                    System.out.println(entries.get(i));
                }
                startIndex += batchSize;
                if (startIndex < totalEntries) {
                    System.out.print("Press Enter to continue...");
                    scanner.nextLine(); // Wait for user to press Enter before printing the next batch
                }
            }
        }
    }


    // Method to save ledger entries to the CSV file
    private void saveEntriesToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (LedgerEntry entry : entries) {
                // Write the entry in the format: date|time|description|vendor|amount
                writer.println(entry.getDate() + "|" + entry.getTime() + "|" + entry.getDescription() + "|" + entry.getVendor() + "|" + entry.getAmount());
            }
        } catch (IOException e) {
            System.err.println("Error saving ledger entries to file: " + e.getMessage());
        }
    }


    // Method to load ledger entries from the CSV file
    private void loadEntriesFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            int lineNumber = 1;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 5) {
                    try {
                        double amount = Double.parseDouble(parts[4]);
                        entries.add(new LedgerEntry(parts[0], parts[1], parts[2], parts[3], amount));
                    } catch (NumberFormatException e) {
                        System.err.println("Invalid amount format in line " + lineNumber + ": " + parts[4]);
                    }
                } else {
                    System.err.println("Invalid entry format in line " + lineNumber + ": " + line);
                }
                lineNumber++;
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
            if (entry.getVendor().equalsIgnoreCase(vendorName)) {
                System.out.println(entry);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No entries found for vendor: " + vendorName);
        }
    }
}


