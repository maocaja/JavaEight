package lambdaexpresion;

import java.util.List;

public class SampleTrade {
    public static void main(String[] args) {

        ITrade bigTradeLambda = (Trade t) -> t.getQuantity() > 10000000;

        //lambda that checks if the trade is a new large ggogle trade.
        ITrade issuerBigNewTradeLambdaOne = (t) -> {
            return t.getIssuer().equals("GOOG") &&
                    t.getQuantity() > 10000000 &&
                    t.getStatus().equals("NEW");
        };
    }
}
