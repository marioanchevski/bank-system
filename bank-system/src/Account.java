import java.util.ArrayList;
import java.util.List;

public class Account {
    private long id;
    private String name;
    private double balance;
    private static long idSeed = 10000;
    private List<String> transactions;

    public Account(String name, double balance) {
        this.id = idSeed++;
        this.name = name;
        this.balance = balance;
        transactions = new ArrayList<>();
        addTransaction(String.format("--account with the id %d was created with %.2f$--", id, balance));
    }

    public void addTransaction(String transactionDescription) {
        transactions.add(transactionDescription);
    }

    public long getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public List<String> getTransactions() {
        return transactions;
    }

    public static void printDetailedAccount(Account account) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Account ID: %d", account.getId()));
        stringBuilder.append(String.format("\nBelongs to: %s", account.getName()));
        stringBuilder.append(String.format("\nBalance: %.2f$", account.getBalance()));
        stringBuilder.append(String.format("\nTransactions:\n%s", String.join("\n", account.getTransactions())));
        System.out.println(stringBuilder.toString());
    }

    @Override
    public String toString() {
        return String.format("AccountID: %d\tAccount owner: %-15s\tCurrent balance: %10.2f$", id, name, balance);
    }
}
