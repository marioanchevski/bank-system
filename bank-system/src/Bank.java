import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Bank {
    private String name;
    private List<Account> accounts;
    private double totalTransactionFeeAmount;
    private double totalTransferAmount;

    private static double thresholdAmount;
    private static double flatFeeAmount;
    private static int percentFeeAmount;

    public Bank(String name, double flatFee, int percentFee, double threshold) {
        this.name = name;
        this.accounts = new ArrayList<>();
        totalTransactionFeeAmount = 0.0;
        totalTransferAmount = 0.0;
        flatFeeAmount = flatFee;
        percentFeeAmount = percentFee;
        thresholdAmount = threshold;
    }

    public void makeTransaction(Transaction transaction) throws InsufficientFundsException, NonExistentAccountException {
        Optional<Account> from = getAccount(transaction.getFromId());
        Optional<Account> to = getAccount(transaction.getToId());

        if (from.isEmpty() || to.isEmpty())
            throw new NonExistentAccountException(from.isEmpty() ? transaction.getFromId() : transaction.getToId());

        Account fromAccount = from.get();
        Account toAccount = to.get();
        double amountToTransfer = transaction.getAmount();
        double provision = transaction.getProvision();
        double fromAccountBalance = fromAccount.getBalance();
        double toAccountBalance = toAccount.getBalance();
        double totalTransferAmount = amountToTransfer + provision;

        if (fromAccount.getId() == toAccount.getId()) {
            if (transaction.getType().equals(TransactionType.DEPOSIT)) {
                if (fromAccountBalance + amountToTransfer < provision)
                    throw new InsufficientFundsException(amountToTransfer);

                fromAccount.addTransaction(String.format("--deposited %.2f$ to my account, paid: %.2f$ provision of type %s--", amountToTransfer, provision, transaction.getDescription()));
                amountToTransfer *= -1;
            } else if (transaction.getType().equals(TransactionType.WITHDRAW)) {

                if (fromAccountBalance < totalTransferAmount)
                    throw new InsufficientFundsException(fromAccountBalance, amountToTransfer);

                fromAccount.addTransaction(String.format("--withdrew %.2f$ from my account, paid: %.2f$ provision of type %s--", amountToTransfer, provision, transaction.getDescription()));
            }
            fromAccount.setBalance(fromAccountBalance - (amountToTransfer + provision));
        } else {
            if (fromAccountBalance < totalTransferAmount)
                throw new InsufficientFundsException(fromAccountBalance, amountToTransfer);

            fromAccount.setBalance(fromAccountBalance - totalTransferAmount);
            fromAccount.addTransaction(String.format("--deposited %.2f$ to account:%d, paid: %.2f$ provision of type %s--", amountToTransfer, toAccount.getId(), provision, transaction.getDescription()));

            toAccount.setBalance(toAccountBalance + amountToTransfer);
            toAccount.addTransaction(String.format("--received %.2f$ from account:%d--", amountToTransfer, fromAccount.getId()));
        }


        this.totalTransactionFeeAmount += provision;
        this.totalTransferAmount += Math.abs(amountToTransfer);

    }

    private Optional<Account> getAccount(long id) {
        return accounts.stream().filter(account -> account.getId() == id).findAny();
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public void getAccountDetails(long id) throws NonExistentAccountException {
        if (getAccount(id).isEmpty())
            throw new NonExistentAccountException(id);
        Account.printDetailedAccount(getAccount(id).get());
    }

    public String printAccounts() {
        return accounts.stream().map(Account::toString).collect(Collectors.joining("\n"));
    }

    public static double getThresholdAmount() {
        return thresholdAmount;
    }

    public static double getFlatFeeAmount() {
        return flatFeeAmount;
    }

    public static int getPercentFeeAmount() {
        return percentFeeAmount;
    }

    public String getTotalTransactionFeeAmount() {
        return String.format("Total transaction fee: %10.2f$", totalTransactionFeeAmount);
    }

    public String totalTotalTransferAmount() {
        return String.format("Total transfer amount: %10.2f$", totalTransferAmount);

    }

    public String getName() {
        return name;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    @Override
    public String toString() {
        return String.format("Bank: %s\nFlatFee: %.2f$ \nPercentFee: %d%%\nThresholdAmount: %.2f$\n",
                name, flatFeeAmount, percentFeeAmount, thresholdAmount);
    }
}