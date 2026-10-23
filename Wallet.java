package wallet;

import java.util.ArrayList;

public class Wallet {
    private User user;
    private double balance;
    private ArrayList<StatementEntry> statementList;

    public Wallet(User user) {
        this.user = user;
        this.balance = 0.0;
        this.statementList = new ArrayList<>();
    }
    public User getUser() { return user; }
    public double getBalance() { return balance; }
    public ArrayList<StatementEntry> getStatementList() { return statementList; }
    
    public void addMoney(double amount) { this.balance += amount; }
    public void deductMoney(double amount) { this.balance -= amount; }
    public void addStatementEntry(Transaction txn) { statementList.add(new StatementEntry(txn, this.balance)); }
}