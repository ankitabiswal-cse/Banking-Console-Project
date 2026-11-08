import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static Bank bank = new Bank();

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n================================");
            System.out.println("     WELCOME TO JAVA BANK");
            System.out.println("================================");
            System.out.println("1. Create Account");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            choice = readInt();

            switch (choice) {
                case 1:
                    createAccount();
                    break;

                case 2:
                    login();
                    break;

                case 3:
                    System.out.println("Thank you for using Java Bank!");
                    break;

                default:
                    System.out.println("Invalid choice! Try again.");
            }

        } while (choice != 3);

        sc.close();
    }

    static void createAccount() {
        System.out.print("Enter your name: ");
        String name = sc.nextLine();

        System.out.print("Create 4-digit PIN: ");
        String pin = sc.nextLine();

        System.out.print("Enter initial deposit: Rs. ");
        double amount = readDouble();

        bank.createAccount(name, pin, amount);
    }

    static void login() {
        System.out.print("Enter account number: ");
        String accountNumber = sc.nextLine();

        System.out.print("Enter your PIN: ");
        String pin = sc.nextLine();

        BankAccount account = bank.login(accountNumber, pin);

        if (account == null) {
            System.out.println("Invalid account number or PIN!");
            return;
        }

        System.out.println("\nWelcome, " + account.getName() + "!");

        int choice;

        do {
            System.out.println("\n--------- ACCOUNT MENU ---------");
            System.out.println("1. Deposit Money");
            System.out.println("2. Withdraw Money");
            System.out.println("3. Check Balance");
            System.out.println("4. Transfer Money");
            System.out.println("5. Transaction History");
            System.out.println("6. Logout");
            System.out.print("Enter your choice: ");

            choice = readInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter deposit amount: Rs. ");
                    account.deposit(readDouble());
                    break;

                case 2:
                    System.out.print("Enter withdrawal amount: Rs. ");
                    account.withdraw(readDouble());
                    break;

                case 3:
                    System.out.println("Available Balance: Rs. "
                            + account.getBalance());
                    break;

                case 4:
                    System.out.print("Enter receiver account number: ");
                    String receiverNumber = sc.nextLine();

                    BankAccount receiver =
                            bank.findAccount(receiverNumber);

                    System.out.print("Enter transfer amount: Rs. ");
                    double amount = readDouble();

                    account.transfer(receiver, amount);
                    break;

                case 5:
                    account.showTransactions();
                    break;

                case 6:
                    System.out.println("Logged out successfully!");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 6);
    }

    static int readInt() {
        while (true) {
            try {
                int value = Integer.parseInt(sc.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.print("Enter a valid number: ");
            }
        }
    }

    static double readDouble() {
        while (true) {
            try {
                double value = Double.parseDouble(sc.nextLine().trim());

                if (Double.isFinite(value)) {
                    return value;
                }

                System.out.print("Enter a valid amount: ");
            } catch (NumberFormatException e) {
                System.out.print("Enter a valid amount: ");
            }
        }
    }
}