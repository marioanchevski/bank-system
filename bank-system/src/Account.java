import java.util.ArrayList;
import java.util.List;

public class Account {
    private Long id;
    private String name;
    private double balance;
    private static long idSeed = 10000;
    private List<String> transactions;

    public Account(String name, double balance) {
        this.id = idSeed++;
        this.name = name;
        this.balance = balance;
        transactions = new ArrayList<>();
        transactions.add(String.format("--account with the id %d was created with %.2f$--", id, balance));
    }

    public Long getId() {
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

}
