// Bank.java
// The actual application. Holds one Admin and the list of Customers,
// runs the main menu, handles admin duties, and saves/loads customers.

import java.io.*;

public class Bank implements HasMenu {
    Admin admin = new Admin();
    CustomerList customers = new CustomerList();

    static final String DATA_FILE = "customers.dat";

    public static void main(String[] args) {
        new Bank();
    } // end main

    public Bank() {
        // uncomment the next two lines to refresh data
        //this.loadSampleCustomers();
        //this.saveCustomers();
        this.loadCustomers();
        this.start();
        this.saveCustomers();
    } // end constructor

    public void loadSampleCustomers() {
        customers = new CustomerList();
        Customer alice = new Customer("Alice", "0000");
        alice.checking.setBalance(1000d);
        alice.savings.setBalance(1000d);
        customers.add(alice);
        customers.add(new Customer("Bob", "1111"));
        customers.add(new Customer("Cindy", "2222"));
    } // end loadSampleCustomers

    public void saveCustomers() {
        try {
            FileOutputStream fo = new FileOutputStream(DATA_FILE);
            ObjectOutputStream obOut = new ObjectOutputStream(fo);
            obOut.writeObject(customers);
            obOut.close();
            fo.close();
        } catch (Exception e) {
            System.out.println("Error saving customers: " + e);
        } // end try
    } // end saveCustomers

    public void loadCustomers() {
        try {
            FileInputStream fIn = new FileInputStream(DATA_FILE);
            ObjectInputStream obIn = new ObjectInputStream(fIn);
            customers = (CustomerList) obIn.readObject();
            obIn.close();
            fIn.close();
        } catch (Exception e) {
            // First run (no file yet) or the class changed: start over with sample data.
            System.out.println("No usable saved data (" + e.getClass().getSimpleName()
                + "). Loading sample customers.");
            loadSampleCustomers();
        } // end try
    } // end loadCustomers

    public String menu() {
        System.out.println();
        System.out.println("Bank Menu");
        System.out.println();
        System.out.println("0) Exit system");
        System.out.println("1) Login as admin");
        System.out.println("2) Login as customer");
        System.out.println();
        System.out.print("Action: ");
        return input.nextLine();
    } // end menu

    public void start() {
        boolean keepGoing = true;
        while (keepGoing) {
            String response = menu();
            if (response.equals("0")) {
                keepGoing = false;
            } else if (response.equals("1")) {
                System.out.println("Admin login");
                if (admin.login()) {
                    startAdmin();
                } // end if
            } else if (response.equals("2")) {
                loginAsCustomer();
            } else {
                System.out.println("Please enter 0, 1, or 2");
            } // end if
        } // end while
    } // end start

    public void startAdmin() {
        boolean keepGoing = true;
        while (keepGoing) {
            String response = admin.menu();
            if (response.equals("0")) {
                keepGoing = false;
            } else if (response.equals("1")) {
                fullCustomerReport();
            } else if (response.equals("2")) {
                addUser();
            } else if (response.equals("3")) {
                applyInterest();
            } else {
                System.out.println("Please enter 0, 1, 2, or 3");
            } // end if
        } // end while
    } // end startAdmin

    public void fullCustomerReport() {
        System.out.println("Full customer report");
        for (Customer customer : customers) {
            System.out.println(customer.getReport());
        } // end for
    } // end fullCustomerReport

    public void addUser() {
        System.out.println("Add User");
        System.out.print("Name: ");
        String userName = input.nextLine();
        System.out.print("PIN: ");
        String PIN = input.nextLine();
        customers.add(new Customer(userName, PIN));
    } // end addUser

    public void applyInterest() {
        System.out.println("Apply interest");
        for (Customer customer : customers) {
            customer.savings.calcInterest();
            System.out.println("New balance: " + customer.savings.getBalanceString());
        } // end for
    } // end applyInterest

    public void loginAsCustomer() {
        System.out.println("Customer login");
        System.out.print("User name: ");
        String userNameIn = input.nextLine();
        System.out.print("PIN: ");
        String pinIn = input.nextLine();

        Customer currentCustomer = null;
        for (Customer customer : customers) {
            if (customer.login(userNameIn, pinIn)) {
                currentCustomer = customer;
            } // end if
        } // end for

        if (currentCustomer == null) {
            System.out.println("Customer not found");
        } else {
            System.out.println("Login Successful");
            currentCustomer.start();
        } // end if
    } // end loginAsCustomer

} // end class
