import java.util.*;

class BankAccount {
    private int accNo;
    private String name;
    private double balance;

    public BankAccount(int accNo, String name, double balance) {
        this.accNo = accNo;
        this.name = name;
        this.balance = balance;
    }

    public int getAccNo() {
        return accNo;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}

public class BankingManagement3 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        LinkedHashMap<Integer, BankAccount> accounts = new LinkedHashMap<>();

        // Stores transaction/activity details
        ArrayList<String> history = new ArrayList<>();

        while (true) {

            System.out.println("\n===== BANKING MANAGEMENT SYSTEM =====");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Check Balance");
            System.out.println("5. View All Details");
            System.out.println("6. View all transaction");
            System.out.println("7. Exit");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter Account Number: ");
                    int accNo = sc.nextInt();

                    if (accounts.containsKey(accNo)) {
                        System.out.println("Account number already exists!");
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

                    history.add("Account created - Account No: " + accNo);

                    System.out.println("Account created successfully!");
                    break;


                case 2:
                    System.out.print("Enter Account Number: ");
                    accNo = sc.nextInt();

                    if (!accounts.containsKey(accNo)) {
                        System.out.println("Account not found!");
                        break;
                    }

                    System.out.print("Enter Deposit Amount: ");
                    double deposit = sc.nextDouble();

                    account = accounts.get(accNo);

                    account.setBalance(
                            account.getBalance() + deposit
                    );

                    history.add("Deposit - Account No: "
                            + accNo + ", Amount: " + deposit);

                    System.out.println("Amount deposited successfully!");
                    break;
                case 3:
                    System.out.print("Enter Account Number: ");
                    accNo = sc.nextInt();

                    if (!accounts.containsKey(accNo)) {
                        System.out.println("Account not found!");
                        break;
                    }

                    System.out.print("Enter Withdrawal Amount: ");
                    double withdraw = sc.nextDouble();

                    account = accounts.get(accNo);

                    if (withdraw > account.getBalance()) {
                        System.out.println("Insufficient balance!");
                        break;
                    }

                    account.setBalance(
                            account.getBalance() - withdraw
                    );

                    history.add("Withdraw - Account No: "
                            + accNo + ", Amount: " + withdraw);

                    System.out.println("Amount withdrawn successfully!");
                    break;
                case 4:
                    System.out.print("Enter Account Number: ");
                    accNo = sc.nextInt();

                    account = accounts.get(accNo);

                    if (account == null) {
                        System.out.println("Account not found!");
                        break;
                    }

                    System.out.println("\n----- ACCOUNT DETAILS -----");
                    System.out.println("Account Number : "
                            + account.getAccNo());
                    System.out.println("Name           : "
                            + account.getName());
                    System.out.println("Balance        : ₹"
                            + account.getBalance());
                    break;

                case 5:
                    displayDetails(accounts);
                    break;

                case 6:
                    displayHistory(history);
                    break;

                
                case 7:
                    System.out.println("Thank you!");
                    sc.close();
                    return;


                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
    static void displayHistory(ArrayList<String> history) {

        if (history.isEmpty()) {
            System.out.println("No transactions available!");
            return;
        }

        System.out.println("\n===== TRANSACTION HISTORY =====");

        for (String data : history) {
            System.out.println(data);
        }
    }
    static void displayDetails(
            LinkedHashMap<Integer, BankAccount> accounts) {

        if (accounts.isEmpty()) {
            System.out.println("No accounts available!");
            return;
        }

        System.out.println("\n===== ALL ACCOUNT DETAILS =====");

        for (BankAccount account : accounts.values()) {

            System.out.println("Account Number : "
                    + account.getAccNo());

            System.out.println("Name           : "
                    + account.getName());

            System.out.println("Balance        : ₹"
                    + account.getBalance());

            System.out.println("-----------------------------");
        }
    }


}


   