// Customer.java
// A User who HAS a checking account and a savings account.

public class Customer extends User {
    CheckingAccount checking;
    SavingsAccount savings;

    public static void main(String[] args) {
        Customer alice = new Customer("Alice", "0000");
        if (alice.login()) {
            alice.start();
        } // end if
    } // end main

    public Customer() {
        this("Alice", "0000");
    } // end constructor

    public Customer(String userName, String PIN) {
        this.userName = userName;
        this.PIN = PIN;
        this.checking = new CheckingAccount();
        this.savings = new SavingsAccount();
    } // end constructor

    public String menu() {
        System.out.println();
        System.out.println("Customer Menu");
        System.out.println();
        System.out.println("0) Exit");
        System.out.println("1) Manage Checking Account");
        System.out.println("2) Manage Savings Account");
        System.out.println("3) change PIN");
        System.out.println();
        System.out.print("Action (0-3): ");
        return input.nextLine();
    } // end menu

    public void start() {
        boolean keepGoing = true;
        while (keepGoing) {
            String response = menu();
            if (response.equals("0")) {
                keepGoing = false;
            } else if (response.equals("1")) {
                System.out.println("Checking Account");
                checking.start();
            } else if (response.equals("2")) {
                System.out.println("Savings Account");
                savings.start();
            } else if (response.equals("3")) {
                changePin();
            } else {
                System.out.println("Please enter 0, 1, 2, or 3");
            } // end if
        } // end while
    } // end start

    public void changePin() {
        System.out.print("Enter new PIN: ");
        String newPin = input.nextLine();
        System.out.print("Confirm new PIN: ");
        String confirm = input.nextLine();
        if (newPin.equals(confirm)) {
            setPIN(newPin);
            System.out.println("PIN changed.");
        } else {
            System.out.println("PINs do not match. PIN not changed.");
        } // end if
    } // end changePin

    public String getReport() {
        return "User: " + userName
             + ", Checking: " + checking.getBalanceString()
             + ", Savings: " + savings.getBalanceString();
    } // end getReport

} // end class
