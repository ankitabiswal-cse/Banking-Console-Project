import java.util.ArrayList;
import java.util.List;

public class BankAccount {
    private String name;
    private String accountNumber;
    private String pin;
    private double balance;
    private List<String> transactions;

    public BankAccount(String name, String accountNumber,
                       String pin, double initialBalance) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = initialBalance;
        this.transactions = new ArrayList<>();

        transactions.add("Account created. Balance: Rs. " + initialBalance);
    }

    public String getName() {
        return name;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public boolean checkPin(String enteredPin) {
        return pin.equals(enteredPin);
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount!");
            return;
        }

        balance += amount;
        transactions.add("Deposited: Rs. " + amount);
        System.out.println("Amount deposited successfully!");
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount!");
        } else if (amount > balance) {
            System.out.println("Insufficient balance!");
        } else {
            balance -= amount;
            transactions.add("Withdrawn: Rs. " + amount);
            System.out.println("Withdrawal successful!");
        }
    }

    public void transfer(BankAccount receiver, double amount) {
        if (receiver == null) {
            System.out.println("Receiver account not found!");
        } else if (receiver == this) {
            System.out.println("Cannot transfer to the same account!");
        } else if (amount <= 0) {
            System.out.println("Invalid amount!");
        } else if (amount > balance) {
            System.out.println("Insufficient balance!");
        } else {
            balance -= amount;
            receiver.balance += amount;

            transactions.add("Transferred Rs. " + amount
                    + " to " + receiver.accountNumber);

            receiver.transactions.add("Received Rs. " + amount
                    + " from " + accountNumber);

            System.out.println("Money transferred successfully!");
        }
    }

    public void showTransactions() {
        System.out.println("\n--- Transaction History ---");

        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            for (String transaction : transactions) {
                System.out.println(transaction);
            }
        }
    }
}