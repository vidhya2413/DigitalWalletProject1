package wallet;

public class Payment extends Transaction {
    private String merchant;

    public Payment(String id, double amt, String merchant) {
        super(id, amt);
        this.merchant = merchant;
    }
    @Override
    public String getType() { return "Payment to " + merchant; }
}