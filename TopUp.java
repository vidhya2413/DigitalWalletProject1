package wallet;

public class TopUp extends Transaction {
    public TopUp(String id, double amt) {
        super(id, amt);
    }
    @Override
    public String getType() { return "Top-Up"; }
}