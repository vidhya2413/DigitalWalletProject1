package wallet;

public class Refund extends Transaction {
    private String originalId;

    public Refund(String id, double amt, String orig) {
        super(id, amt);
        this.originalId = orig;
    }
    @Override
    public String getType() { return "Refund for " + originalId; }
}