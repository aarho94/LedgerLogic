package com.pluralsight.models;

public class LedgerEntry {
    private String date;
    private String time;
    private String description;
    private String vendor;
    private double amount;

    public LedgerEntry(String date, String time, String description, String vendor, double amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    public String getVendor() {
        return vendor;
    }

    public double getAmount() {
        return amount;
    }

    public String toString() {
        return "Date: " + date + ", Time: " + time + ", Description: " + description + ", Vendor: " + vendor + ", Amount: " + amount;
    }
}




