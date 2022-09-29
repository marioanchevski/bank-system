public class FlatPercentProvisionTransaction extends Transaction {

    public FlatPercentProvisionTransaction(long fromId, long toId, double amount, String description, TransactionType type) {
        super(fromId, toId, amount, description, type);
    }

    @Override
    double getProvision() {
        return getAmount() * (Bank.getPercentFeeAmount() / 100.0);
    }
}
