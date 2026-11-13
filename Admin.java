// Admin.java
// A User with the admin menu. The admin's actions run in Bank.startAdmin(),
// because they all work on Bank's customer list.

public class Admin extends User {

    public static void main(String[] args) {
        Admin admin = new Admin();
        if (admin.login()) {
            System.out.println("Admin menu choice: " + admin.menu());
        } // end if
    } // end main

    public Admin() {
        this.userName = "admin";
        this.PIN = "0000";
    } // end constructor

    public String menu() {
        System.out.println();
        System.out.println("Admin Menu");
        System.out.println();
        System.out.println("0) Exit this menu");
        System.out.println("1) Full customer report");
        System.out.println("2) Add user");
        System.out.println("3) Apply interest to savings accounts");
        System.out.println();
        System.out.print("Action: ");
        return input.nextLine();
    } // end menu

    // Left blank on purpose: Bank.startAdmin() handles admin actions.
    public void start() {
    } // end start

    public String getReport() {
        return "Admin: " + userName + ", PIN: " + PIN;
    } // end getReport

} // end class
