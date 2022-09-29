import java.util.Scanner;

public class BankSystem {

    private static void showMenu() {
        System.out.println("\n=============MAIN MENU==============");
        System.out.println("1.Create a new account\n2.Make a new transaction\n3.See list of account's\n4.Check account details\n5.Check bank details" +
                "\n6.Check total transaction fee amount\n7.Check total bank transfer amount\n8.Exit");
        System.out.println("====================================\n");
    }

    private static void showTransactionMenu() {
        System.out.println("\n========TRANSACTION MENU========");
        System.out.println("1.Withdraw\n2.Deposit\n3.Send money to another account\n4.Back");
        System.out.println("================================\n");
    }

    private static Transaction getTransactionType(TransactionType type, long fromId, long toId, double amount) {
        if (amount < Bank.getThresholdAmount())
            return new FlatAmountProvisionTransaction(fromId, toId, amount, "FlatAmount", type);
        else
            return new FlatPercentProvisionTransaction(fromId, toId, amount, "FlatPercent", type);
    }

    private static double getValidAmountInput(Scanner scanner, int msgtype) {
        String[] promptMsg = {"Enter account balance.",
                "Enter amount.",
                "Enter flat fee amount.",
                "Enter threshold amount.\nIf the amount of a transaction is greater then the threshold amount bank charges you a flat percent, otherwise a flat amount."};
        String[] errMsgs = {"Enter account balance with two decimal places and a dollar sign at the end. Example 15.50$",
                "Enter the amount with two decimal places and a dollar sign at the end. Example 9.50$",
                "Enter the flat fee amount with two decimal places and a dollar sign at the end. Example 7.50$",
                "Enter threshold amount with two decimal places and a dollar sign at the end. Example 10000.00$"};
        String correctAmountFormatRegex = "[0-9]+.[0-9]{2}\\$";
        System.out.println(promptMsg[msgtype]);
        String balanceCheck = scanner.nextLine().trim();
        while (!balanceCheck.matches(correctAmountFormatRegex)) {
            System.out.println(errMsgs[msgtype]);
            balanceCheck = scanner.nextLine().trim();
        }
        balanceCheck = balanceCheck.replace(",", ".");
        return Double.parseDouble(balanceCheck.substring(0, balanceCheck.length() - 1));
    }

    private static long getValidAccountId(Scanner scanner) {
        String numberRegex = "[0-9]+";
        System.out.println("Enter the account id you wish to perform the transaction on.");
        String idFrom = scanner.nextLine().trim();
        while (!idFrom.matches(numberRegex)) {
            System.out.println("The account id must be a number. Plese try again.");
            idFrom = scanner.nextLine().trim();
        }
        return Long.parseLong(idFrom);
    }

    private static String getValidName(Scanner scanner) {
        String name = scanner.nextLine();
        while (name.trim().isEmpty()) {
            System.out.println("You must provide a name.");
            name = scanner.nextLine().trim();
        }
        return name;
    }

    private static boolean bankHasNoAccounts(Bank bank) {
        if (bank.getAccounts().isEmpty()) {
            System.out.println("Currently there are no accounts in this bank.\nPlease create a new account.");
            return true;
        }
        return false;
    }

    private static int getValidFlatPercent(Scanner scanner) {
        String flatPercentRegex = "^[0-9]{1,2}$";
        System.out.println("Please enter a flat percent amount for your bank.");
        String line = scanner.nextLine();
        while (!line.matches(flatPercentRegex)) {
            System.out.println("Flat fee must be whole number. Not greater than 99.");
            line = scanner.nextLine().trim();
        }
        return Integer.parseInt(line);
    }

    private static Bank init(Scanner scanner) {
        final int FLAT_AMOUNT_MSG = 2;
        final int FLAT_PERCENT_MSG = 3;
        System.out.println("\nPlease enter a name for your bank.");
        String bankName = getValidName(scanner);
        double flatFee = getValidAmountInput(scanner, FLAT_AMOUNT_MSG);
        int percentFee = getValidFlatPercent(scanner);
        double thresholdAmount = getValidAmountInput(scanner, FLAT_PERCENT_MSG);
        return new Bank(bankName, flatFee, percentFee, thresholdAmount);
    }

    public static void main(String[] args) {
        final int AMOUNT_BALANCE_MSG = 0;
        final int TRANSACTION_AMOUNT_MSG = 1;
        Scanner scanner = new Scanner(System.in);
        Bank bank = init(scanner);
        System.out.println(bank);

        while (true) {
            showMenu();
            String decision = scanner.nextLine();
            switch (decision) {
                case "1":
                    System.out.println("Enter account owner.");
                    String name = getValidName(scanner);
                    double balance = getValidAmountInput(scanner, AMOUNT_BALANCE_MSG);
                    bank.addAccount(new Account(name, balance));
                    break;
                case "2":
                    if (bankHasNoAccounts(bank))
                        break;
                    showTransactionMenu();
                    TransactionType transactionType = null;

                    String transactionMenuDecision = scanner.nextLine().trim();
                    boolean backOptionOrInvalidInput = false;
                    switch (transactionMenuDecision) {
                        case "1":
                            transactionType = TransactionType.WITHDRAW;
                            break;
                        case "2":
                            transactionType = TransactionType.DEPOSIT;
                            break;
                        case "3":
                            transactionType = TransactionType.NORMAL;
                            break;
                        case "4":
                            backOptionOrInvalidInput = true;
                            break;
                        default:
                            System.out.println("Please select a valid option.");
                            backOptionOrInvalidInput = true;
                            break;
                    }

                    long idFrom, idTo;
                    if (!backOptionOrInvalidInput) {
                        idFrom = getValidAccountId(scanner);

                        if (transactionType == TransactionType.NORMAL) {
                            idTo = getValidAccountId(scanner);
                            if (idFrom == idTo)
                                transactionType = TransactionType.DEPOSIT;
                        } else
                            idTo = idFrom;
                        double amount = getValidAmountInput(scanner, TRANSACTION_AMOUNT_MSG);
                        Transaction t1 = getTransactionType(transactionType, idFrom, idTo, amount);
                        try {
                            bank.makeTransaction(t1);
                            System.out.println("Transaction successful.");
                        } catch (InsufficientFundsException | NonExistentAccountException e) {
                            System.out.println(e.getMessage());
                            System.out.println("Transaction failed.");
                        }
                    }
                    break;
                case "3":
                    if (bankHasNoAccounts(bank))
                        break;
                    System.out.println(bank.printAccounts());
                    break;
                case "4":
                    idFrom = getValidAccountId(scanner);
                    try {
                        bank.getAccountDetails(idFrom);
                    } catch (NonExistentAccountException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "5":
                    System.out.println(bank);
                    break;
                case "6":
                    System.out.println(bank.getTotalTransactionFeeAmount());
                    break;
                case "7":
                    System.out.println(bank.totalTotalTransferAmount());
                    break;
                case "8":
                    System.out.println("Thank you for using " + bank.getName());
                    return;
                default:
                    System.out.println("Please select a valid option.");
                    break;
            }
        }

    }
}
