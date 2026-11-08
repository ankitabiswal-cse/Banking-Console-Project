import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Bank {
    private Map<String, BankAccount> accounts = new HashMap<>();

    public String createAccount(String name, String pin,
                                double initialBalance) {

        if (name.trim().isEmpty()) {
            System.out.println("Name cannot be empty!");
            return null;
        }

        if (!pin.matches("\\d{4}")) {
            System.out.println("PIN must contain exactly 4 digits!");
            return null;
        }

        if (initialBalance < 0) {
            System.out.println("Initial balance cannot be negative!");
            return null;
        }

        String accountNumber;

        do {
            accountNumber = "100" + (10000 + new Random().nextInt(90000));
        } while (accounts.containsKey(accountNumber));

        BankAccount account = new BankAccount(
                name, accountNumber, pin, initialBalance);

        accounts.put(accountNumber, account);

        System.out.println("\nAccount created successfully!");
        System.out.println("Account Holder: " + name);
        System.out.println("Account Number: " + accountNumber);

        return accountNumber;
    }

    public BankAccount login(String accountNumber, String pin) {
        BankAccount account = accounts.get(accountNumber);

        if (account != null && account.checkPin(pin)) {
            return account;
        }

        return null;
    }

    public BankAccount findAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }
}