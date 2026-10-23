package wallet;

public abstract class Transaction {
    private String transactionId;
    private double amount;

    public Transaction(String id, double amt) {
        this.transactionId = id;
        this.amount = amt;
    }
    public String getTransactionId() { return transactionId; }
    public double getAmount() { return amount; }
    
    public abstract String getType();
}