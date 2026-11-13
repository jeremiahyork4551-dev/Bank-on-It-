# ATM (Bank on It)

## Algorithms

### HasMenu.java

Interface. Every class that implements it shares the same Scanner (input) for keyboard input.

#### fields

* input: one shared Scanner on System.in

#### menu

* abstract: each class writes its own menu that prints options and returns the choice as a string

#### start

* abstract: each class writes its own loop that runs the menu and acts on the choice

### CheckingAccount.java

Implements HasMenu and Serializable. A wrapper around the balance.

#### main

* make a new CheckingAccount
* call start

#### CheckingAccount constructor (no parameters)

* set balance to 0

#### CheckingAccount constructor (balance)

* set balance to the given balance

#### menu

* print the Account menu with options 0 through 3
* read the choice as a string
* return the choice

#### start

* set keepGoing to true
* loop while keepGoing
   * get a choice from menu
   * 0: set keepGoing to false
   * 1: checkBalance
   * 2: makeDeposit
   * 3: makeWithdrawal
   * anything else: print an error

#### getBalance

* return balance

#### getBalanceString

* return balance formatted as dollars with 2 decimals using String.format

#### setBalance

* set balance to the given value

#### checkBalance

* print Checking balance
* print the current balance with getBalanceString

#### getDouble

* read a line as a string
* convert it to a double with Double.parseDouble
   * if it isn't a number, print an error and set result to 0
* return the result

#### makeDeposit

* print Making a deposit
* ask how much to deposit and read it with getDouble
* if the amount is 0 or less, print an error
* otherwise add the amount to balance
* print the new balance

#### makeWithdrawal

* print Making a withdrawal
* ask how much to withdraw and read it with getDouble
* if the amount is 0 or less, print an error
* if the amount is more than balance, print insufficient funds
* otherwise subtract the amount from balance
* print the new balance

### SavingsAccount.java

Extends CheckingAccount. Inherits balance, menu, start, deposit, and withdraw. Adds an interest rate.

#### main

* make a new SavingsAccount
* call start

#### SavingsAccount constructor (no parameters)

* call the parent constructor so balance is 0
* set interestRate to .05

#### SavingsAccount constructor (balance, interestRate)

* call the parent constructor with balance
* set interestRate to the given rate

#### calcInterest

* set interest to balance times interestRate
* add interest to balance
* not on the menu: the admin will call this later

#### setInterestRate

* set interestRate to the given value

#### getInterestRate

* return interestRate

### User.java

Abstract class. Implements HasMenu and Serializable. Manages logins for Customer (and Admin later).

#### login (no parameters)

* ask for the user name and read it
* ask for the PIN and read it
* check them with login(userName, PIN)
* if they match, print Login Successful
* otherwise print Login Failed
* return the result

#### login (userName, PIN)

* return true if userName and PIN both match this user, otherwise false
* prints nothing, so Bank can check every customer quietly

#### setUserName

* set userName to the given value

#### getUserName

* return userName

#### setPIN

* set PIN to the given value

#### getPIN

* return PIN

#### getReport

* abstract: each subclass writes its own report

### Customer.java

Extends User. Has a CheckingAccount and a SavingsAccount.

#### main

* make a new Customer named Alice with PIN 0000
* if login succeeds, call start

#### Customer constructor (no parameters)

* call the other constructor with Alice and 0000

#### Customer constructor (userName, PIN)

* set userName and PIN
* make a new CheckingAccount
* make a new SavingsAccount

#### menu

* print the Customer Menu with options 0 through 3
* read the choice as a string
* return the choice

#### start

* set keepGoing to true
* loop while keepGoing
   * get a choice from menu
   * 0: set keepGoing to false
   * 1: print Checking Account, then call checking.start
   * 2: print Savings Account, then call savings.start
   * 3: changePin
   * anything else: print an error

#### changePin

* ask for the new PIN and read it
* ask to confirm the new PIN and read it
* if they match, set PIN and print PIN changed
* otherwise print that they don't match and leave PIN alone

#### getReport

* return the user name, checking balance, and savings balance as one string

### Admin.java

Extends User. Holds the admin menu. The admin actions run in Bank, because they all work on the customer list.

#### main

* make a new Admin
* if login succeeds, show the menu once and print the choice

#### Admin constructor

* set userName to admin
* set PIN to 0000

#### menu

* print the Admin Menu with options 0 through 3
* read the choice as a string
* return the choice

#### start

* left blank on purpose: Bank.startAdmin does this work

#### getReport

* return the admin name and PIN as one string

### CustomerList.java

* an empty class that extends ArrayList of Customer
* lets the list be loaded from the file without a warning

### Bank.java

Implements HasMenu. The actual application. Has one Admin and a CustomerList, and saves the customers to customers.dat.

#### main

* make a new Bank

#### Bank constructor

* (to refresh data, uncomment loadSampleCustomers and saveCustomers)
* loadCustomers
* start
* saveCustomers

#### loadSampleCustomers

* make a new empty CustomerList
* add Alice (PIN 0000) with $1000 in checking and $1000 in savings
* add Bob (PIN 1111) and Cindy (PIN 2222) with $0

#### saveCustomers

* open customers.dat for writing with an ObjectOutputStream
* write the customer list
* close the streams
* if anything goes wrong, print an error

#### loadCustomers

* open customers.dat for reading with an ObjectInputStream
* read the customer list and cast it to CustomerList
* close the streams
* if the file is missing or unreadable, print a message and loadSampleCustomers

#### menu

* print the Bank Menu with options 0 through 2
* read the choice as a string
* return the choice

#### start

* set keepGoing to true
* loop while keepGoing
   * get a choice from menu
   * 0: set keepGoing to false
   * 1: print Admin login; if admin.login works, startAdmin
   * 2: loginAsCustomer
   * anything else: print an error

#### startAdmin

* set keepGoing to true
* loop while keepGoing
   * get a choice from admin.menu
   * 0: set keepGoing to false
   * 1: fullCustomerReport
   * 2: addUser
   * 3: applyInterest
   * anything else: print an error

#### fullCustomerReport

* print Full customer report
* for each customer in the list, print getReport

#### addUser

* print Add User
* ask for a name and a PIN
* make a new Customer with them
* add the customer to the end of the list

#### applyInterest

* print Apply interest
* for each customer in the list
   * call calcInterest on their savings account
   * print the new savings balance

#### loginAsCustomer

* ask for a user name and a PIN
* set currentCustomer to null
* for each customer in the list
   * if login(userName, PIN) works, set currentCustomer to that customer
* if currentCustomer is still null, print Customer not found
* otherwise print Login Successful and call currentCustomer.start
