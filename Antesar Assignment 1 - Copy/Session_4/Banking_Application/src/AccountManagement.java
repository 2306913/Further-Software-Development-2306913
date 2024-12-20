import java.util.Scanner;

public class AccountManagement {

    public Scanner sc = new Scanner(System.in);

    public void viewInformation(Account account) {
        // display information
        System.out.printf("%-20s %-10s %s %n", "Name", "Number", "Balance");
        System.out.println("-".repeat(40));
        System.out.printf("%-20s %-10s $%.2f %n", account.getAccountName(), account.getAccountNumber(), account.getAccountBalance());

    }

    public void deposit(Account account) {

        System.out.print("Enter deposit amount: ");
        int amount = sc.nextInt();
        if (amount > 0) {
            account.setAccountBalance(amount);
            System.out.println("Successfully deposited $" + amount);
            System.out.println("Updated Balance: $" + account.getAccountBalance());
        } else {
            System.out.println("Invalid deposit amount.");
        }

    }

    public void withdraw(Account account) {

        System.out.print("Enter withdrawal amount: ");
        int amount = sc.nextInt();
        if (account.getAccountBalance() >= amount) {
            account.setAccountBalance(-amount);
            System.out.println("Successfully withdrew $" + amount);
            System.out.println("Updated Balance: $" + account.getAccountBalance());
        } else {
            System.out.println("Invalid withdrawal amount or insufficient funds.");
        }
    }
}
