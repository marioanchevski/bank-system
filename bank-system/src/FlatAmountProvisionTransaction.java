public class FlatAmountProvisionTransaction extends Transaction {

    public FlatAmountProvisionTransaction(long fromId, long toId, double amount, String description, TransactionType type) {
        super(fromId, toId, amount, description, type);
    }

    @Override
    double getProvision() {
        return Bank.getFlatFeeAmount();
    }


}
