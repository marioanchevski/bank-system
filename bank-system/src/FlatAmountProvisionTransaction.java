public class FlatAmountProvisionTransaction extends Transaction {
    private double flatAmount;

    public FlatAmountProvisionTransaction(long fromId, long toId, double amount, String description, double flatAmount, TransactionType type) {
        super(fromId, toId, amount, description, type);
        this.flatAmount = flatAmount;
    }

    @Override
    double getProvision() {
        return flatAmount;
    }


}
