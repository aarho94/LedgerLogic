package com.pluralsight;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        com.pluralsight.Ledger ledger = new com.pluralsight.Ledger(); // Assuming Ledger class manages ledger entries

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
                    // Prompt user for deposit information and save it to the ledger
                    // Example: ledger.addDeposit(...);
                    break;
                case "P":
                    // Make Payment (Debit)
                    System.out.println("Making Payment...");
                    // Prompt user for payment information and save it to the ledger
                    // Example: ledger.makePayment(...);
                    break;
                case "L":
                    // Display Ledger
                    System.out.println("=== Ledger ===");
                    // Display ledger entries (newest entries first)
                    // Example: ledger.displayLedger();
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

