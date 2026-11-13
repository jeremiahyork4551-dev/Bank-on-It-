// User.java
// Abstract base for Customer (this week) and Admin (next week).
// Its main job is managing logins.

import java.io.Serializable;

public abstract class User implements HasMenu, Serializable {
    String userName;
    String PIN;

    // Prompts for name and PIN, then checks them and reports the result.
    public boolean login() {
        System.out.print("User name: ");
        String userNameIn = input.nextLine();
        System.out.print("PIN: ");
        String pinIn = input.nextLine();
        boolean result = login(userNameIn, pinIn);
        if (result) {
            System.out.println("Login Successful");
        } else {
            System.out.println("Login Failed");
        } // end if
        return result;
    } // end login

    // Quietly checks the given name and PIN against this user.
    // No printing, so Bank can check every customer without spamming "Login Failed".
    public boolean login(String userNameIn, String pinIn) {
        return userNameIn.equals(this.userName) && pinIn.equals(this.PIN);
    } // end login

    public void setUserName(String userName) {
        this.userName = userName;
    } // end setUserName

    public String getUserName() {
        return this.userName;
    } // end getUserName

    public void setPIN(String PIN) {
        this.PIN = PIN;
    } // end setPIN

    public String getPIN() {
        return this.PIN;
    } // end getPIN

    // Each subclass writes its own report.
    public abstract String getReport();

} // end class
