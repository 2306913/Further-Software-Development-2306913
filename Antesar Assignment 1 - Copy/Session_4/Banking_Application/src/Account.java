public class Account {

    // Class level variables for account information
    private final String accountName;
    private final String accountNumber;
    private double accountBalance;

    // Constructor to initialize account details
    public Account(String name, String number, double balance) {
        this.accountName = name;
        this.accountNumber = number;
        this.accountBalance = balance;
    }

    public String getAccountName(){
        return this.accountName;
    }

    public String getAccountNumber(){
        return this.accountNumber;
    }

    public double getAccountBalance() {
        return this.accountBalance;
    }

    public void setAccountBalance(double balanceChange) {this.accountBalance += balanceChange; }

}
