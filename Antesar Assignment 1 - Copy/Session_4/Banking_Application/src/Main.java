
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import java.util.Scanner;

public class Main {

    public Scanner scanner = new Scanner(System.in);

    public static void createAccounts(){

    }

    public static void exit(){
        System.out.println("Thank you for using our service.");
    }

    public static void main(String[] args) {

        // Scanner object
        Scanner sc = new Scanner(System.in);

        // Create an account management object

        AccountManagement am = new AccountManagement();

        // Create an array of Account objects

        Account[] accounts = new Account[5];

        accounts[0] = new Account("John Doe", "100000001", 1000.00);
        accounts[1] = new Account("Jane Doe", "100000002", 2000.00);
        accounts[2] = new Account("Jim Beam", "100000003", 3000.00);
        accounts[3] = new Account("Jack Ruby", "100000004", 4000.00);
        accounts[4] = new Account("June July", "100000005", 5000.00);

        // Create an Account object with initial account details
        //Account account = new Account("John Doe", "123456789", 1000.00);



        int choice;
        int loggedInAccount = -1;

        do {
            if (loggedInAccount == -1){
                // Display login menu
                System.out.println("\nWelcome, please select an account. ");
                for (int i = 0; i <= accounts.length - 1; i++){
                    System.out.println(i + ". " + accounts[i].getAccountName());
                }

                System.out.println("Enter your choice: ");
                choice = sc.nextInt();
            }else {
                // Display account
                System.out.println("\nWelcome, " +
                        accounts[loggedInAccount].getAccountName() + "\n"
                        + "\t1. View account information \n"
                        + "\t2. Deposit \n"
                        + "\t3. Withdraw \n"
                        + "\t4. Exit \n"
                        + "Enter your choice: ");
                choice = sc.nextInt();
                switch (choice) {
                    case 1 -> am.viewInformation(accounts[loggedInAccount]);
                    case 2 -> am.deposit(accounts[loggedInAccount]);
                    case 3 -> am.withdraw(accounts[loggedInAccount]);
                    case 4 -> exit();
                    default -> System.out.println("Invalid choice. Please try again.");
                }

            }

        } while (choice != 4);

    }
}