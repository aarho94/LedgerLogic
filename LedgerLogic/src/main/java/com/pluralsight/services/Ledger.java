package com.pluralsight.services;

import com.pluralsight.models.LedgerEntry;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ledger {
    private List<LedgerEntry> entries;
    private static final String FILE_PATH = "src/main/java/com/pluralsight/files/ledger.csv";
    private Scanner scanner;

    public Ledger(Scanner scanner) {
        this.scanner = scanner;
        entries = new ArrayList<>();
        loadEntriesFromFile();
    }

    public void addDeposit(String date, String time, String description, String vendor, double amount) {
        entries.add(new LedgerEntry(date, time, description, vendor, amount));
        saveEntriesToFile();
        System.out.println("Deposit added successfully.");
    }

    public void makePayment(String date, String time, String description, String vendor, double amount) {
        entries.add(new LedgerEntry(date, time, description, vendor, -amount));
        saveEntriesToFile();
        System.out.println("Payment made successfully.");
    }

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
                    scanner.nextLine();
                }
            }
        }
    }

    public void generateMonthToDateReport() {
        LocalDate currentDate = LocalDate.now();
        LocalDate startDateOfMonth = currentDate.withDayOfMonth(1);
        LocalDate endDateOfMonth = currentDate;

        List<LedgerEntry> monthToDateEntries = filterEntries(startDateOfMonth, endDateOfMonth);

        printReport("Month To Date", monthToDateEntries);
    }

    public void generateYearToDateReport() {
        LocalDate currentDate = LocalDate.now();
        LocalDate startDateOfYear = LocalDate.of(currentDate.getYear(), 1, 1);
        LocalDate endDateOfYear = currentDate;

        List<LedgerEntry> yearToDateEntries = filterEntries(startDateOfYear, endDateOfYear);

        printReport("Year To Date", yearToDateEntries);
    }

    public void generatePreviousMonthReport() {
        LocalDate currentDate = LocalDate.now();
        LocalDate startDateOfPreviousMonth = currentDate.minusMonths(1).withDayOfMonth(1);
        LocalDate endDateOfPreviousMonth = currentDate.minusMonths(1);

        List<LedgerEntry> previousMonthEntries = filterEntries(startDateOfPreviousMonth, endDateOfPreviousMonth);

        printReport("Previous Month", previousMonthEntries);
    }

    public void generatePreviousYearReport() {
        LocalDate currentDate = LocalDate.now();
        LocalDate startDateOfPreviousYear = LocalDate.of(currentDate.getYear() - 1, 1, 1);
        LocalDate endDateOfPreviousYear = LocalDate.of(currentDate.getYear() - 1, 12, 31);

        List<LedgerEntry> previousYearEntries = filterEntries(startDateOfPreviousYear, endDateOfPreviousYear);

        printReport("Previous Year", previousYearEntries);
    }

    public void searchByVendor(String vendorName) {
        List<LedgerEntry> vendorEntries = new ArrayList<>();
        for (LedgerEntry entry : entries) {
            if (entry.getVendor().equalsIgnoreCase(vendorName)) {
                vendorEntries.add(entry);
            }
        }

        printReport("Entries for Vendor: " + vendorName, vendorEntries);
    }

    private List<LedgerEntry> filterEntries(LocalDate startDate, LocalDate endDate) {
        List<LedgerEntry> filteredEntries = new ArrayList<>();
        for (LedgerEntry entry : entries) {
            LocalDate entryDate = LocalDate.parse(entry.getDate());
            if (!entryDate.isBefore(startDate) && !entryDate.isAfter(endDate)) {
                filteredEntries.add(entry);
            }
        }
        return filteredEntries;
    }

    private void printReport(String reportName, List<LedgerEntry> reportEntries) {
        if (reportEntries.isEmpty()) {
            System.out.println("No entries for " + reportName);
        } else {
            System.out.println("=== " + reportName + " Report ===");
            for (LedgerEntry entry : reportEntries) {
                System.out.println(entry);
            }
        }
    }

    private void saveEntriesToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (LedgerEntry entry : entries) {
                writer.println(entry.getDate() + "|" + entry.getTime() + "|" + entry.getDescription() + "|" + entry.getVendor() + "|" + entry.getAmount());
            }
        } catch (IOException e) {
            System.err.println("Error saving ledger entries to file: " + e.getMessage());
        }
    }

    private void loadEntriesFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 5) {
                    try {
                        double amount = Double.parseDouble(parts[4]);
                        entries.add(new LedgerEntry(parts[0], parts[1], parts[2], parts[3], amount));
                    } catch (NumberFormatException e) {
                        System.err.println("Invalid amount format: " + parts[4]);
                    }
                } else {
                    System.err.println("Invalid entry format: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading ledger entries from file: " + e.getMessage());
        }
    }
}
