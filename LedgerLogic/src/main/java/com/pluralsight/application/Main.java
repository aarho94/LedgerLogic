package com.pluralsight.application;

import com.pluralsight.services.Ledger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Ledger ledger = new Ledger(scanner);

        // Main menu loop
        boolean running = true;
        while (running) {
            System.out.println("=== Home Screen ===");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment (Debit)");
            System.out.println("L) Ledger");
            System.out.println("R) Reports");
            System.out.println("X) Exit");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine().toUpperCase();

            switch (choice) {
                case "D":
                    // Add Deposit
                    System.out.println("Adding Deposit...");
                    LocalDateTime depositDateTime = LocalDateTime.now();
                    String formattedDate = depositDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    String formattedTime = depositDateTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
                    System.out.println("Date: " + formattedDate);
                    System.out.println("Time: " + formattedTime);
                    System.out.print("CONFIRM: ENTER: 'Deposit': ");
                    String description = scanner.nextLine();
                    System.out.print("Where is this from?: ");
                    String vendor = scanner.nextLine();
                    System.out.print("Enter amount: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline character
                    ledger.addDeposit(formattedDate, formattedTime, description, vendor, amount);
                    System.out.println("Deposit added successfully.");
                    break;
                case "P":
                    // Make Payment (Debit)
                    System.out.println("Making Payment...");
                    LocalDateTime paymentDateTime = LocalDateTime.now();
                    formattedDate = paymentDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    formattedTime = paymentDateTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
                    System.out.println("Date: " + formattedDate);
                    System.out.println("Time: " + formattedTime);
                    System.out.print("CONFIRM: ENTER 'Payment': ");
                    description = scanner.nextLine();
                    System.out.print("What is this for?: ");
                    vendor = scanner.nextLine();
                    System.out.print("Enter amount: ");
                    amount = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline character
                    ledger.makePayment(formattedDate, formattedTime, description, vendor, amount);
                    System.out.println("Payment made successfully.");
                    break;
                case "L":
                    // Display Ledger
                    System.out.println("=== Ledger ===");
                    ledger.displayLedger();
                    break;
                case "R":
                    // Reports
                    boolean inReports = true;
                    while (inReports) {
                        System.out.println("=== Reports ===");
                        System.out.println("1) Month To Date");
                        System.out.println("2) Previous Month");
                        System.out.println("3) Year To Date");
                        System.out.println("4) Previous Year");
                        System.out.println("5) Search by Vendor");
                        System.out.println("0) Back");
                        System.out.print("Enter your choice: ");
                        String reportChoice = scanner.nextLine();

                        switch (reportChoice) {
                            case "1":
                                // Generate Month To Date report
                                break;
                            case "2":
                                // Generate Previous Month report
                                break;
                            case "3":
                                // Generate Year To Date report
                                break;
                            case "4":
                                // Generate Previous Year report
                                break;
                            case "5":
                                // Search by Vendor
                                System.out.print("Enter vendor name: ");
                                String vendorName = scanner.nextLine();
                                // Display entries for the specified vendor
                                // Example: ledger.searchByVendor(vendorName);
                                break;
                            case "0":
                                // Back to main menu
                                inReports = false;
                                break;
                            default:
                                System.out.println("Invalid choice. Please try again.");
                        }
                    }
                    break;
                case "X":
                    // Exit
                    System.out.println("Exiting...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}


