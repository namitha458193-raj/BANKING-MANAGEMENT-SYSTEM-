import java.util.HashMap;
import java.util.Scanner;

class BankAccount {
    private int accountNumber;
    private String accountHolderName;
    private double balance;

    public BankAccount(int accountNumber, String accountHolderName, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}

public class BankingManagement2 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        HashMap<Integer, BankAccount> accounts = new HashMap<>();

        while (true) {
            System.out.println("\n===== Banking Management System =====");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Check Balance");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter Account Number: ");
                    int accNo = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Account Holder Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Initial Balance: ");
                    double balance = sc.nextDouble();

                    accounts.put(accNo, new BankAccount(accNo, name, balance));
                    System.out.println("Account Created Successfully.");
                    break;

                case 2:
                    System.out.print("Enter Account Number: ");
                    accNo = sc.nextInt();

                    BankAccount acc = accounts.get(accNo);

                    if (acc != null) {
                        System.out.print("Enter Deposit Amount: ");
                        double amount = sc.nextDouble();
                        acc.setBalance(acc.getBalance() + amount);
                        System.out.println("Deposit Successful.");
                        System.out.println("Current Balance: " + acc.getBalance());
                    } else {
                        System.out.println("Account Not Found.");
                    }
                    break;

                case 3:
                    System.out.print("Enter Account Number: ");
                    accNo = sc.nextInt();

                    acc = accounts.get(accNo);

                    if (acc != null) {
                        System.out.print("Enter Withdraw Amount: ");
                        double amount = sc.nextDouble();

                        if (amount <= acc.getBalance()) {
                            acc.setBalance(acc.getBalance() - amount);
                            System.out.println("Withdrawal Successful.");
                            System.out.println("Current Balance: " + acc.getBalance());
                        } else {
                            System.out.println("Insufficient Balance.");
                        }
                    } else {
                        System.out.println("Account Not Found.");
                    }
                    break;

                case 4:
                    System.out.print("Enter Account Number: ");
                    accNo = sc.nextInt();

                    acc = accounts.get(accNo);

                    if (acc != null) {
                        System.out.println("Account Number : " + acc.getAccountNumber());
                        System.out.println("Account Holder : " + acc.getAccountHolderName());
                        System.out.println("Balance : " + acc.getBalance());
                    } else {
                        System.out.println("Account Not Found.");
                    }
                    break;

                case 5:
                    System.out.println("Thank You!");
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }
}