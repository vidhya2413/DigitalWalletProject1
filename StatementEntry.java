package wallet;

public class StatementEntry {
    private Transaction transaction;
    private double runningBalance;

    public StatementEntry(Transaction t, double bal) {
        this.transaction = t;
        this.runningBalance = bal;
    }
    public Transaction getTransaction() { return transaction; }
    public double getRunningBalance() { return runningBalance; }
}