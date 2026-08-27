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

public class BankingManagement5 {

    static final String FILE_NAME = "accounts.csv";

    static Scanner sc = new Scanner(System.in);

    static void createAccount() {

        try {

            System.out.print("Enter Account Number: ");
            int accountNumber = Integer.parseInt(sc.nextLine());

            List<Account> accounts = readAccounts();

            for (Account account : accounts) {

                if (account.accountNumber == accountNumber) {

                    System.out.println("Account already exists.");

                    return;
                }
            }

            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Initial Deposit: ");
            double balance = Double.parseDouble(sc.nextLine());

            if (balance < 0) {

                System.out.println(
                        "Initial deposit cannot be negative.");

                return;
            }
            BufferedWriter writer =
                    new BufferedWriter(
                            new FileWriter(FILE_NAME, true));

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

            System.out.println(
                    "Account created successfully.");

        } catch (NumberFormatException e) {

            System.out.println(
                    "Please enter a valid number.");

        } catch (IOException e) {

            System.out.println(
                    "File error: " + e.getMessage());
        }
    }
    static void deposit() {

        try {

            System.out.print("Enter Account Number: ");
            int accountNumber =
                    Integer.parseInt(sc.nextLine());

            List<Account> accounts = readAccounts();

            Account foundAccount = null;
            for (Account account : accounts) {

                if (account.accountNumber == accountNumber) {

                    foundAccount = account;

                    break;
                }
            }

            if (foundAccount == null) {

                System.out.println(
                        "Account does not exist.");

                return;
            }

            System.out.print("Enter Deposit Amount: ");
            double amount =
                    Double.parseDouble(sc.nextLine());

            if (amount <= 0) {

                System.out.println(
                        "Deposit amount must be greater than zero.");

                return;
            }
            foundAccount.balance =
                    foundAccount.balance + amount;

            writeAccounts(accounts);

            System.out.println(
                    "Deposit successful.");

            System.out.println(
                    "New Balance: ₹" + foundAccount.balance);

        } catch (NumberFormatException e) {

            System.out.println(
                    "Please enter a valid number.");

        }
    }
    static void withdraw() {

        try {

            System.out.print("Enter Account Number: ");
            int accountNumber =
                    Integer.parseInt(sc.nextLine());

            List<Account> accounts = readAccounts();

            Account foundAccount = null;

            for (Account account : accounts) {

                if (account.accountNumber == accountNumber) {

                    foundAccount = account;

                    break;
                }
            }

            if (foundAccount == null) {

                System.out.println(
                        "Account does not exist.");

                return;
            }
            System.out.print("Enter Withdrawal Amount: ");
            double amount =
                    Double.parseDouble(sc.nextLine());
            if (amount <= 0) {

                System.out.println(
                        "Withdrawal amount must be greater than zero.");

                return;
            }
            if (amount > foundAccount.balance) {

                System.out.println(
                        "Insufficient balance.");

                System.out.println(
                        "Available Balance: ₹"
                                + foundAccount.balance);

                return;
            }
            foundAccount.balance =
                    foundAccount.balance - amount;

            writeAccounts(accounts);

            System.out.println(
                    "Withdrawal successful.");

            System.out.println(
                    "New Balance: ₹" + foundAccount.balance);

        } catch (NumberFormatException e) {

            System.out.println(
                    "Please enter a valid number.");
        }
    }

    static List<Account> readAccounts() {

        List<Account> accounts =
                new ArrayList<>();

        try {

            File file = new File(FILE_NAME);

            if (!file.exists()) {

                return accounts;
            }

            BufferedReader reader =
                    new BufferedReader(
                            new FileReader(FILE_NAME));

            reader.readLine();

            String line;

            while ((line = reader.readLine()) != null) {

                if (line.trim().isEmpty()) {

                    continue;
                }
                String[] data = line.split(",");

                int accountNumber =
                        Integer.parseInt(data[0]);

                String name = data[1];

                double balance =
                        Double.parseDouble(data[2]);

                Account account =
                        new Account(
                                accountNumber,
                                name,
                                balance);

                accounts.add(account);
            }

            reader.close();

        } catch (IOException e) {

            System.out.println(
                    "Error reading file: "
                            + e.getMessage());

        } catch (NumberFormatException e) {

            System.out.println(
                    "Invalid data in CSV file.");
        }

        return accounts;
    }

    static void writeAccounts(List<Account> accounts) {

        try {

            BufferedWriter writer =
                    new BufferedWriter(
                            new FileWriter(FILE_NAME));

            writer.write(
                    "AccountNumber,Name,Balance");

            writer.newLine();

            for (Account account : accounts) {

                writer.write(account.toCSV());

                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {

            System.out.println(
                    "Error writing file: "
                            + e.getMessage());
        }
    }
    public static void main(String[] args) {

        while (true) {

            System.out.println();
            System.out.println(
                    "===== BANKING SYSTEM =====");

            System.out.println(
                    "1. Create Account");

            System.out.println(
                    "2. Deposit");

            System.out.println(
                    "3. Withdraw");

            System.out.println(
                    "4. Exit");

            System.out.print(
                    "Enter your choice: ");

            try {

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

                        withdraw();

                        break;

                    case 4:

                        System.out.println(
                                "Thank you for using Banking System.");

                        sc.close();

                        return;

                    default:

                        System.out.println(
                                "Invalid choice.");
                }

            } catch (NumberFormatException e) {

                System.out.println(
                        "Please enter a valid choice.");
            }
        }
    }
}