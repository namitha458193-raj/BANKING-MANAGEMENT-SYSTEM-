import java.util.Scanner;
import java.util.TreeMap;

public class BankingManagement4 {

    // Transaction Class
    static class Transaction {

        String type;
        double amount;
        String description;

        Transaction(String type, double amount, String description) {
            this.type = type;
            this.amount = amount;
            this.description = description;
        }
    }

    // Account Class
    static class Account {

        int id;
        String name;
        double balance;

        TreeMap<Integer, Transaction> transactions;

        Account(int id, String name, double balance) {
            this.id = id;
            this.name = name;
            this.balance = balance;

            transactions = new TreeMap<>();
        }
    }

    // Bank Class
    static class Bank {

        TreeMap<Integer, Account> accounts;

        Bank() {
            accounts = new TreeMap<>();
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Bank bank = new Bank();

        while (true) {

            System.out.println("\n===== SECURE BANK =====");
            System.out.println("1. Add Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Display Statement");
            System.out.println("5. Exit");

            System.out.print("Enter choice: ");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {

                // ADD ACCOUNT
                case 1:

                    System.out.print("Enter Account ID: ");
                    int id = Integer.parseInt(sc.nextLine());
                    Account depositAccount =
                            bank.accounts.get(id);

                    if (depositAccount != null) {

                        System.out.println("Account is already exit");

                    } else {

                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Initial Balance: ");
                    double balance =
                            Double.parseDouble(sc.nextLine());

                    Account account =
                            new Account(id, name, balance);

                    bank.accounts.put(id, account);

                    System.out.println(
                            "Account added successfully.");
                    }

                    break;


                // DEPOSIT
                case 2:

                    System.out.print("Enter Account ID: ");
                    int depositId =
                            Integer.parseInt(sc.nextLine());

                    Account depositAccount1 =
                            bank.accounts.get(depositId);

                    if (depositAccount1 == null) {

                        System.out.println(
                                "Account not found.");

                    } else {

                        System.out.print("Enter Amount: ");
                        double amount =
                                Double.parseDouble(sc.nextLine());

                        depositAccount1.balance += amount;

                        Transaction t =
                                new Transaction(
                                        "CREDIT",
                                        amount,
                                        "Deposit");

                        depositAccount1.transactions.put(
                                depositAccount1.transactions.size() + 1,
                                t);

                        System.out.println(
                                "Deposit successful.");

                        System.out.println(
                                "Balance: "
                                + depositAccount1.balance);
                    }

                    break;


                // WITHDRAW
                case 3:

                    System.out.print("Enter Account ID: ");
                    int withdrawId =
                            Integer.parseInt(sc.nextLine());

                    Account withdrawAccount =
                            bank.accounts.get(withdrawId);

                    if (withdrawAccount == null) {

                        System.out.println(
                                "Account not found.");

                    } else {

                        System.out.print("Enter Amount: ");
                        double amount =
                                Double.parseDouble(sc.nextLine());

                        if (withdrawAccount.balance >= amount) {

                            withdrawAccount.balance -= amount;

                            Transaction t =
                                    new Transaction(
                                            "DEBIT",
                                            amount,
                                            "Withdrawal");

                            withdrawAccount.transactions.put(
                                    withdrawAccount.transactions.size() + 1,
                                    t);

                            System.out.println(
                                    "Withdrawal successful.");

                            System.out.println(
                                    "Balance: "
                                    + withdrawAccount.balance);

                        } else {

                            System.out.println(
                                    "Insufficient balance.");
                        }
                    }

                    break;


                // DISPLAY STATEMENT
                case 4:

                    System.out.print("Enter Account ID: ");
                    int statementId =
                            Integer.parseInt(sc.nextLine());

                    Account statementAccount =
                            bank.accounts.get(statementId);

                    if (statementAccount == null) {

                        System.out.println(
                                "Account not found.");

                    } else {

                        System.out.println(
                                "\n===== ACCOUNT STATEMENT =====");

                        System.out.println(
                                "Account ID: "
                                + statementAccount.id);

                        System.out.println(
                                "Name: "
                                + statementAccount.name);

                        System.out.println(
                                "-----------------------------");

                        for (Transaction t :
                                statementAccount.transactions.values()) {

                            System.out.println(
                                    t.type + " | "
                                    + t.amount + " | "
                                    + t.description);
                        }

                        System.out.println(
                                "-----------------------------");

                        System.out.println(
                                "Balance: "
                                + statementAccount.balance);
                    }

                    break;


                // EXIT
                case 5:

                    System.out.println(
                            "Exiting SecureBank...");

                    sc.close();
                    return;


                default:

                    System.out.println(
                            "Invalid choice.");
            }
        }
    }
}