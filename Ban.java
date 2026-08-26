import java.io.*;
import java.util.*;

class Account {
    int accountNumber;
    String name;
    double balance;

    Account(int accountNumber, String name, double balance) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.balance = balance;
    }

    String toCSV() {
        return accountNumber + "," + name + "," + balance;
    }
}

public class BankingM{

    static final String FILE_NAME = "accounts.csv";
    static Scanner sc = new Scanner(System.in);

    // Case 1: Create Account
    static void createAccount() {

        try {
            System.out.print("Enter Account Number: ");
            int accountNumber = Integer.parseInt(sc.nextLine());

            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Initial Deposit: ");
            double balance = Double.parseDouble(sc.nextLine());

            if (balance < 0) {
                System.out.println("Deposit cannot be negative.");
                return;
            }

            // Check existing accounts
            List<Account> accounts = readAccounts();

            for (Account account : accounts) {
                if (account.accountNumber == accountNumber) {
                    System.out.println("Account already exists.");
                    return;
                }
            }

            // Add account to CSV
            BufferedWriter writer =
                    new BufferedWriter(new FileWriter(FILE_NAME, true));

            // If file is empty, add header
            File file = new File(FILE_NAME);

            if (file.length() == 0) {
                writer.write("AccountNumber,Name,Balance");
                writer.newLine();
            }

            Account account =
                    new Account(accountNumber, name, balance);

            writer.write(account.toCSV());
            writer.newLine();

            writer.close();

            System.out.println("Account created successfully.");

        } catch (IOException e) {
            System.out.println("File error: " + e.getMessage());
        }
    }

    // Case 2: Deposit
    static void deposit() {

        System.out.print("Enter Account Number: ");
        int accountNumber = Integer.parseInt(sc.nextLine());

        System.out.print("Enter Deposit Amount: ");
        double amount = Double.parseDouble(sc.nextLine());

        if (amount <= 0) {
            System.out.println("Amount must be greater than zero.");
            return;
        }

        List<Account> accounts = readAccounts();

        boolean found = false;

        for (Account account : accounts) {

            if (account.accountNumber == accountNumber) {

                account.balance += amount;
                found = true;
                break;
            }
        }

        if (found) {

            writeAccounts(accounts);

            System.out.println("Deposit successful.");

        } else {

            System.out.println("Account not found.");
        }
    }

    // Read accounts from CSV
    static List<Account> readAccounts() {

        List<Account> accounts = new ArrayList<>();

        try {

            File file = new File(FILE_NAME);

            if (!file.exists()) {
                return accounts;
            }

            BufferedReader reader =
                    new BufferedReader(new FileReader(FILE_NAME));

            // Skip header
            String line = reader.readLine();

            while ((line = reader.readLine()) != null) {

                if (line.trim().isEmpty()) {
                    continue;
                }

                // Comma is the delimiter
                String[] data = line.split(",");

                int accountNumber =
                        Integer.parseInt(data[0]);

                String name = data[1];

                double balance =
                        Double.parseDouble(data[2]);

                accounts.add(
                        new Account(accountNumber, name, balance)
                );
            }

            reader.close();

        } catch (IOException e) {

            System.out.println("File reading error: "
                    + e.getMessage());
        }

        return accounts;
    }

    // Update CSV file
    static void writeAccounts(List<Account> accounts) {

        try {

            BufferedWriter writer =
                    new BufferedWriter(new FileWriter(FILE_NAME));

            writer.write("AccountNumber,Name,Balance");
            writer.newLine();

            for (Account account : accounts) {

                writer.write(account.toCSV());
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {

            System.out.println("File writing error: "
                    + e.getMessage());
        }
    }

    public static void main(String[] args) {

        while (true) {

            System.out.println("\n===== BANKING SYSTEM =====");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Exit");

            System.out.print("Enter your choice: ");

            int choice =
                    Integer.parseInt(sc.nextLine());

            switch (choice) {

                case 1:
                    createAccount();
                    break;

                case 2:
                    deposit();
                    break;

                case 3:
                    System.out.println(
                            "Thank you for using Banking System.");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}