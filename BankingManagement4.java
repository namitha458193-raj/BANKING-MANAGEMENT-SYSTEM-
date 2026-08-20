import java.util.*;

class BankAccount {
    int accountNumber;
    String name;
    double balance;

    BankAccount(int accountNumber, String name, double balance) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.balance = balance;
    }

    void display() {
        System.out.println("Account Number : " + accountNumber);
        System.out.println("Account Holder : " + name);
        System.out.println("Balance        : ₹" + balance);
    }
}

public class BankingManagement4 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // TreeMap stores account number in sorted order
        TreeMap<Integer, BankAccount> accounts = new TreeMap<>();

        while (true) {

            System.out.println("\n===== BANKING MANAGEMENT SYSTEM =====");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3.withdraw");
            System.out.println("4. Display Account");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter Account Number: ");
                    int accNo = sc.nextInt();

                    if (accounts.containsKey(accNo)) {
                        System.out.println("Account already exists!");
                        break;
                    }

                    sc.nextLine();

                    System.out.print("Enter Account Holder Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Initial Balance: ");
                    double balance = sc.nextDouble();

                    BankAccount account =
                            new BankAccount(accNo, name, balance);

                    accounts.put(accNo, account);

                    System.out.println("Account created successfully!");
                    break;

                case 2:
                    System.out.print("Enter Account Number: ");
                    accNo = sc.nextInt();

                    BankAccount acc = accounts.get(accNo);

                    if (acc == null) {
                        System.out.println("Account not found!");
                        break;
                    }

                    System.out.print("Enter Deposit Amount: ");
                    double amount = sc.nextDouble();

                    if (amount <= 0) {
                        System.out.println("Invalid deposit amount!");
                        break;
                    }

                    acc.balance = acc.balance + amount;

                    System.out.println("Deposit successful!");
                    System.out.println("Current Balance: "
                            + acc.balance);
                    break;
                                case 3:

                    System.out.print("Enter Account Number: ");
                    accNo = sc.nextInt();

                    acc = accounts.get(accNo);

                    if (acc == null) {

                        System.out.println(
                                "Account not found!");

                        break;
                    }

                    System.out.print("Enter Withdraw Amount: ");
                    amount = sc.nextDouble();

                    if (amount <= 0) {

                        System.out.println(
                                "Invalid withdrawal amount!");

                        break;
                    }

                    if (amount > acc.balance) {

                        System.out.println(
                                "Insufficient balance!");

                        break;
                    }

                    acc.balance = acc.balance - amount;

                    System.out.println(
                            "Withdrawal successful!");

                    System.out.println(
                            "Current Balance: "
                            + acc.balance);

                    break;

                case 4:

                    System.out.print("Enter Account Number: ");
                    accNo = sc.nextInt();

                    acc = accounts.get(accNo);

                    if (acc == null) {

                        System.out.println(
                                "Account not found!");

                    } else {

                        System.out.println(
                                "\n----- ACCOUNT DETAILS -----");

                        acc.display();
                    }

                    break;

                case 5:
                    System.out.println("Thank you for using the Banking System!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}