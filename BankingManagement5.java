import java.io.*;
import java.util.*;

public class BankingManagement5 {

    static String fileName = "accounts.txt";
    static Scanner sc = new Scanner(System.in);
    static void createAccount() throws IOException {

        System.out.print("Enter Account Number: ");
        int accNo = Integer.parseInt(sc.nextLine());
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line;

        while ((line = br.readLine()) != null) {
            String[] data = line.split("\\|");

            if (Integer.parseInt(data[0]) == accNo) {
                System.out.println("Account already exists.");
                br.close();
                return;
            }
        }

        br.close();

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Initial Deposit: ");
        double balance = Double.parseDouble(sc.nextLine());

        if (balance < 0) {
            System.out.println("Invalid amount.");
            return;
        }

        BufferedWriter bw =
                new BufferedWriter(new FileWriter(fileName, true));

        bw.write(accNo + "|" + name + "|" + balance);
        bw.newLine();

        bw.close();

        System.out.println("Account created successfully.");
    }
    static void deposit() throws IOException {

    System.out.print("Enter Account Number: ");
    int accNo = Integer.parseInt(sc.nextLine());
    BufferedReader br =
            new BufferedReader(new FileReader(fileName));

    String line;
    boolean found = false;

    ArrayList<String> accounts = new ArrayList<>();

    while ((line = br.readLine()) != null) {

        String[] data = line.split("\\|");

        int number = Integer.parseInt(data[0]);

        if (number == accNo) {
            found = true;

            System.out.print("Enter Deposit Amount: ");
            double amount = Double.parseDouble(sc.nextLine());
            if (amount <= 0) {
                System.out.println("Deposit amount must be greater than 0.");
                br.close();
                return;
            }

            double balance = Double.parseDouble(data[2]);

            balance = balance + amount;

            line = data[0] + "|" + data[1] + "|" + balance;
        }

        accounts.add(line);
    }

    br.close();

    if (!found) {
        System.out.println("Account does not exist.");
        return;
    }
    BufferedWriter bw =
            new BufferedWriter(new FileWriter(fileName));

    for (String account : accounts) {
        bw.write(account);
        bw.newLine();
    }

    bw.close();

    System.out.println("Deposit successful.");
}

    static void withdraw() throws IOException {

    System.out.print("Enter Account Number: ");
    int accNo = Integer.parseInt(sc.nextLine());
    BufferedReader br =
            new BufferedReader(new FileReader(fileName));

    String line;
    boolean found = false;

    ArrayList<String> accounts = new ArrayList<>();

    while ((line = br.readLine()) != null) {

        String[] data = line.split("\\|");

        int number = Integer.parseInt(data[0]);

        if (number == accNo) {
            found = true;

            System.out.print("Enter Withdrawal Amount: ");
            double amount = Double.parseDouble(sc.nextLine());
            if (amount <= 0) {
                System.out.println(
                        "Withdrawal amount must be greater than 0.");
                br.close();
                return;
            }

            double balance = Double.parseDouble(data[2]);
            if (amount > balance) {
                System.out.println("Insufficient balance.");
                br.close();
                return;
            }

            balance = balance - amount;

            line = data[0] + "|" + data[1] + "|" + balance;
        }

        accounts.add(line);
    }

    br.close();
    if (!found) {
        System.out.println("Account does not exist.");
        return;
    }
    BufferedWriter bw =
            new BufferedWriter(new FileWriter(fileName));

    for (String account : accounts) {
        bw.write(account);
        bw.newLine();
    }

    bw.close();

    System.out.println("Withdrawal successful.");
}
    static void displayAccount() throws IOException {

        System.out.print("Enter Account Number: ");
        int accNo = Integer.parseInt(sc.nextLine());

        BufferedReader br =
                new BufferedReader(new FileReader(fileName));

        String line;
        boolean found = false;

        while ((line = br.readLine()) != null) {

            String[] data = line.split("\\|");

            if (Integer.parseInt(data[0]) == accNo) {

                System.out.println("\n--- Account Details ---");
                System.out.println("Account Number: " + data[0]);
                System.out.println("Name: " + data[1]);
                System.out.println("Balance: " + data[2]);

                found = true;
                break;
            }
        }

        br.close();

        if (!found) {
            System.out.println("Account does not exist.");
        }
    }

    // Main Method
    public static void main(String[] args) {

        // Create file if it doesn't exist
        try {
            File file = new File(fileName);

            if (!file.exists()) {
                file.createNewFile();
            }

            while (true) {

                System.out.println("\n===== BANKING MANAGEMENT SYSTEM =====");
                System.out.println("1. Create Account");
                System.out.println("2. Deposit");
                System.out.println("3. Withdraw");
                System.out.println("4. Display Account");
                System.out.println("5. Exit");

                System.out.print("Enter your choice: ");

                int choice = Integer.parseInt(sc.nextLine());

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
                        displayAccount();
                        break;

                    case 5:
                        System.out.println("Thank you!");
                        sc.close();
                        return;

                    default:
                        System.out.println("Invalid choice.");
                }
            }

        } catch (IOException e) {
            System.out.println("File error: " + e.getMessage());
        }
    }
}