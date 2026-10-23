package wallet;

import java.util.ArrayList;

public class WalletService {
    public void addUser(ArrayList<User> users, String id, String name) throws Exception {
        if (id.isEmpty() || name.isEmpty()) throw new Exception("ID and Name cannot be empty.");
        for (User u : users) if (u.getUserId().equals(id)) throw new Exception("User ID already exists.");
        users.add(new User(id, name));
        System.out.println("User added successfully.");
    }

    public void createWallet(ArrayList<User> users, ArrayList<Wallet> wallets, String userId) throws Exception {
        User user = findUser(users, userId);
        for (Wallet w : wallets) if (w.getUser().getUserId().equals(userId)) throw new Exception("Wallet already exists.");
        wallets.add(new Wallet(user));
        System.out.println("Wallet created successfully.");
    }

    public void topUp(ArrayList<Wallet> wallets, String userId, double amount, int txnId) throws Exception {
        if (amount <= 0) throw new Exception("Amount must be > 0.");
        Wallet w = findWallet(wallets, userId);
        w.addMoney(amount);
        w.addStatementEntry(new TopUp("TXN" + txnId, amount));
        System.out.println("Receipt: Top-up of " + amount + " successful. New Balance: " + w.getBalance());
    }

    public void transfer(ArrayList<Wallet> wallets, String fromId, String toId, double amount, int txnId) throws Exception {
        if (amount <= 0) throw new Exception("Amount must be > 0.");
        if (fromId.equals(toId)) throw new Exception("Cannot transfer to yourself.");
        Wallet from = findWallet(wallets, fromId); 
        Wallet to = findWallet(wallets, toId);
        
        if (from.getBalance() < amount) throw new InsufficientFundsException("Insufficient funds. Available: " + from.getBalance());
        
        from.deductMoney(amount); 
        to.addMoney(amount);
        
        from.addStatementEntry(new Transfer("TXN" + txnId, amount, toId));
        to.addStatementEntry(new Transfer("TXN" + txnId, amount, fromId));
        
        System.out.println("Receipt: Transfer of " + amount + " successful. New Balance: " + from.getBalance());
    }

    public void payMerchant(ArrayList<Wallet> wallets, String userId, double amount, String merchant, int txnId) throws Exception {
        if (amount <= 0) throw new Exception("Amount must be > 0.");
        Wallet w = findWallet(wallets, userId);
        if (w.getBalance() < amount) throw new InsufficientFundsException("Insufficient funds. Available: " + w.getBalance());
        w.deductMoney(amount);
        w.addStatementEntry(new Payment("TXN" + txnId, amount, merchant));
        System.out.println("Receipt: Payment of " + amount + " to " + merchant + " successful. New Balance: " + w.getBalance());
    }

    public void refund(ArrayList<Wallet> wallets, String userId, double amount, String origId, int txnId) throws Exception {
        if (amount <= 0) throw new Exception("Amount must be > 0.");
        Wallet w = findWallet(wallets, userId);
        w.addMoney(amount);
        w.addStatementEntry(new Refund("TXN" + txnId, amount, origId));
        System.out.println("Receipt: Refund of " + amount + " processed. New Balance: " + w.getBalance());
    }

    public void printStatement(ArrayList<Wallet> wallets, String userId) throws Exception {
        Wallet w = findWallet(wallets, userId);
        System.out.println("\n--- Statement for " + w.getUser().getName() + " ---");
        System.out.println("TXN ID  | TYPE                      | AMOUNT   | BALANCE");
        System.out.println("-----------------------------------------------------------------");
        for (StatementEntry entry : w.getStatementList()) {
            System.out.printf("%-7s | %-25s | %-8.2f | %.2f\n", entry.getTransaction().getTransactionId(), 
                entry.getTransaction().getType(), entry.getTransaction().getAmount(), entry.getRunningBalance());
        }
    }

    private User findUser(ArrayList<User> users, String id) throws Exception {
        for (User u : users) if (u.getUserId().equals(id)) return u;
        throw new Exception("User not found: " + id);
    }

    private Wallet findWallet(ArrayList<Wallet> wallets, String id) throws Exception {
        for (Wallet w : wallets) if (w.getUser().getUserId().equals(id)) return w;
        throw new Exception("Wallet not found for user: " + id);
    }
}