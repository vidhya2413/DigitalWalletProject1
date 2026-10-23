package wallet;

public class Transfer extends Transaction {
    private String toUser;

    public Transfer(String id, double amt, String to) {
        super(id, amt);
        this.toUser = to;
    }
    @Override
    public String getType() { return "Transfer to " + toUser; }
}