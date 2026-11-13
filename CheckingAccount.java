// CheckingAccount.java
// A wrapper around a balance, with a menu for checking/deposit/withdraw.

import java.io.Serializable;

public class CheckingAccount implements HasMenu, Serializable {
    double balance;
    // "input" (the shared Scanner) comes from HasMenu.

    public static void main(String[] args) {
        CheckingAccount ca = new CheckingAccount();
        ca.start();
    } // end main

    public CheckingAccount() {
        this.balance = 0d;
    } // end constructor

    public CheckingAccount(double balance) {
        this.balance = balance;
    } // end constructor

    public String menu() {
        System.out.println();
        System.out.println("Account menu");
        System.out.println();
        System.out.println("0) quit");
        System.out.println("1) check balance");
        System.out.println("2) make a deposit");
        System.out.println("3) make a withdrawal");
        System.out.println();
        System.out.print("Please enter 0-3: ");
        return input.nextLine();
    } // end menu

    public void start() {
        boolean keepGoing = true;
        while (keepGoing) {
            String response = menu();
            if (response.equals("0")) {
                keepGoing = false;
            } else if (response.equals("1")) {
                checkBalance();
            } else if (response.equals("2")) {
                makeDeposit();
            } else if (response.equals("3")) {
                makeWithdrawal();
            } else {
                System.out.println("Please enter 0, 1, 2, or 3");
            } // end if
        } // end while
    } // end start

    public double getBalance() {
        return this.balance;
    } // end getBalance

    public String getBalanceString() {
        return String.format("$%.2f", this.balance);
    } // end getBalanceString

    public void setBalance(double balance) {
        this.balance = balance;
    } // end setBalance

    public void checkBalance() {
        System.out.println("Checking balance...");
        System.out.println("Current balance: " + getBalanceString());
    } // end checkBalance

    // Reads a double from the user. Bad input returns 0.
    private double getDouble() {
        String raw = input.nextLine();
        double result = 0d;
        try {
            result = Double.parseDouble(raw);
        } catch (NumberFormatException e) {
            System.out.println("Not a valid number. Using 0.");
            result = 0d;
        } // end try
        return result;
    } // end getDouble

    public void makeDeposit() {
        System.out.println("Making a deposit...");
        System.out.print("How much to deposit? ");
        double amount = getDouble();
        if (amount <= 0) {
            System.out.println("Deposit must be more than zero.");
        } else {
            this.balance += amount;
        } // end if
        System.out.println("New balance: " + getBalanceString());
    } // end makeDeposit

    public void makeWithdrawal() {
        System.out.println("Making a withdrawal...");
        System.out.print("How much to withdraw? ");
        double amount = getDouble();
        if (amount <= 0) {
            System.out.println("Withdrawal must be more than zero.");
        } else if (amount > this.balance) {
            System.out.println("Insufficient funds.");
        } else {
            this.balance -= amount;
        } // end if
        System.out.println("New balance: " + getBalanceString());
    } // end makeWithdrawal

} // end class
