package lambdaexpresion;

public class Trade {
    private int Quantity;
    private String issuer;
    private String status;

    public Trade(int quantity, String issuer, String status) {
        Quantity = quantity;
        this.issuer = issuer;
        this.status = status;
    }

    public int getQuantity() {
        return Quantity;
    }

    public String getIssuer() {
        return issuer;
    }

    public String getStatus() {
        return status;
    }



}
