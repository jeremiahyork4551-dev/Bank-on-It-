// SavingsAccount.java
// A CheckingAccount plus an interest rate.
// Everything else (balance, menu, start, deposit, withdraw) is inherited.

public class SavingsAccount extends CheckingAccount {
    double interestRate;

    public static void main(String[] args) {
        SavingsAccount sa = new SavingsAccount();
        sa.start();
    } // end main

    public SavingsAccount() {
        super();                  // balance = 0 via CheckingAccount()
        this.interestRate = .05d; // default 5%
    } // end constructor

    public SavingsAccount(double balance, double interestRate) {
        super(balance);
        this.interestRate = interestRate;
    } // end constructor

    // Adds one period of interest to the balance.
    // Not on the menu: the admin will call this later, not the customer.
    public void calcInterest() {
        double interest = this.balance * this.interestRate;
        this.balance += interest;
    } // end calcInterest

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    } // end setInterestRate

    public double getInterestRate() {
        return this.interestRate;
    } // end getInterestRate

} // end class
