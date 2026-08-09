import java.util.ArrayList;
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

public class BankingManagement1{
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<BankAccount> accounts = new ArrayList<>();

        while (true) {
            System.out.println("\n===== Banking Management System =====");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Check Balance");
            System.out.println("5. Exit");
            System.out.println("6. View All Account");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    int f1=0;
                    System.out.print("Enter Account Number: ");
                    int accNo = sc.nextInt();
                    for(BankAccount acc:accounts){
                        if(acc.getAccountNumber()==accNo){
                            System.out.print("Account Number is already here");
                            f1=1;
                            break;
                        }
                    }
                    sc.nextLine();
                    if(f1==0){

                    System.out.print("Enter Account Holder Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Initial Balance: ");
                    double balance = sc.nextDouble();

                    accounts.add(new BankAccount(accNo, name, balance));
                    System.out.println("Account Created Successfully.");
                    }
                    break;

                case 2:
                    int f2=0;
                    System.out.print("Enter Account Number: ");
                    accNo = sc.nextInt();

                    for (BankAccount acc : accounts) {
                        if (acc.getAccountNumber() == accNo) {
                            System.out.print("Enter Deposit Amount: ");
                            double amount = sc.nextDouble();
                            acc.setBalance(acc.getBalance() + amount);
                            System.out.println("Deposit Successful.");
                            System.out.println("Current Balance: " + acc.getBalance());
                            f2=1
                            break;
                        }
                    }
                    if(f2==0){
                        System.out.print("Account Number is Invalid");
                    }
                    break;

                case 3:
                    int f3=0;
                    System.out.print("Enter Account Number: ");
                    accNo = sc.nextInt();

                    for (BankAccount acc : accounts) {
                        if (acc.getAccountNumber() == accNo) {
                            System.out.print("Enter Withdraw Amount: ");
                            double amount = sc.nextDouble();

                            if (amount <= acc.getBalance()) {
                                acc.setBalance(acc.getBalance() - amount);
                                System.out.println("Withdrawal Successful.");
                                System.out.println("Current Balance: " + acc.getBalance());
                            } else {
                                System.out.println("Insufficient Balance.");
                            }
                            f3=1;
                            break;
                        }
                    }
                    if(f3==0){
                        System.out.print("Account Number is Invalid");
                    }
                    break;

                case 4:
                    int f4=0;
                    System.out.print("Enter Account Number: ");
                    accNo = sc.nextInt();

                    for (BankAccount acc : accounts) {
                        if (acc.getAccountNumber() == accNo) {
                            System.out.println("Account Number : " + acc.getAccountNumber());
                            System.out.println("Account Holder : " + acc.getAccountHolderName());
                            System.out.println("Balance : " + acc.getBalance());
                            f4=1;
                            break;
                        }
                    }
                    if(f4==0){
                        System.out.print("Account Number is Invalid");
                    }
                    break;

                case 5:
                    System.out.println("Thank You!");
                    sc.close();
                    System.exit(0);
                case 6:
                    System.out.println("All bank account");
                    if(accounts.isEmpty()){
                        System.out.println("NO account available");
                    }else{
                        for(BankAccount acc:accounts){
                            System.out.println("Account Number: "+acc.getAccountNumber());
                            System.out.println("Account Holder: "+acc.getAccountHolderName());
                            System.out.println("Balance: "+acc.getBalance());
                        }
                    }
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }
}
