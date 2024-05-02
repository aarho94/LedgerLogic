package com.pluralsight.models;

public class LedgerEntry
{
        private String date;
        private String description;
        private double amount;

        public LedgerEntry(String date, String description, double amount)
        {
            this.date = date;
            this.description = description;
            this.amount = amount;
        }

        public String getDate()
        {
            return date;
        }

        public String getDescription()
        {
            return description;
        }

        public double getAmount()
        {
            return amount;
        }

        public String toString()
        {
            return "Date: " + date + ", Description: " + description + ", Amount: " + amount;
        }

        // Method to format the entry as a CSV string
        public String toCSV()
        {
            return date + "," + description + "," + amount;
        }
    }



