import java.util.List;
import java.util.Scanner;

public class BankSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankService bankService = new BankService();

        Bank bank = new Bank("LinkPlus-Bank",10.00,2,10000.00);
        bankService.addAccount(new Account("Mario", 5.00), bank);
        bankService.addAccount(new Account("Vojo", 1000.00), bank);
        bankService.addAccount(new Account("Filip and Ata", 5000.50), bank);

        //Bank bank = init(scanner);

        System.out.println(bankService.printBank(bank));

        while (true) {
            ValidationUtil.showMenu();
            String decision = scanner.nextLine();
            switch (decision) {
                case "1":
                    String name = ValidationUtil.getValidName(scanner);
                    double balance = ValidationUtil.getValidAmountInput(scanner, ValidationUtil.AMOUNT_BALANCE_MSG);
                    bankService.addAccount(new Account(name, balance), bank);
                    break;
                case "2":

                    // should this method be part of ValidationUtil?
                    if (ValidationUtil.bankHasNoAccounts(bank))
                        break;
                    ValidationUtil.showTransactionMenu();
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
                        idFrom = ValidationUtil.getValidAccountId(scanner);

                        if (transactionType == TransactionType.NORMAL) {
                            idTo = ValidationUtil.getValidAccountId(scanner);
                            if (idFrom == idTo)
                                transactionType = TransactionType.DEPOSIT;
                        } else
                            idTo = idFrom;
                        double amount = ValidationUtil.getValidAmountInput(scanner, ValidationUtil.TRANSACTION_AMOUNT_MSG);
                        Transaction t1 = ValidationUtil.getTransactionType(transactionType, idFrom, idTo, amount, bank);
                        try {
                            bankService.makeTransaction(t1, bank);
                            System.out.println("Transaction successful.");
                        } catch (InsufficientFundsException | NonExistentAccountException e) {
                            System.out.println(e.getMessage());
                            System.out.println("Transaction failed.");
                        }
                    }
                    break;
                case "3":
                    if (ValidationUtil.bankHasNoAccounts(bank))
                        break;
                    bankService.printAccounts(bank);
                    break;
                case "4":
                    idFrom = ValidationUtil.getValidAccountId(scanner);
                    try {
                        bankService.getAccountDetails(idFrom, bank);
                    } catch (NonExistentAccountException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "5":
                    System.out.println(bankService.printBank(bank));
                    break;
                case "6":
                    System.out.println(bankService.getTotalTransactionFeeAmount(bank));
                    break;
                case "7":
                    System.out.println(bankService.getTotalTotalTransferAmount(bank));
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
