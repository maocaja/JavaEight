package lambdaexpresion;

import java.util.ArrayList;
import java.util.List;

public class Client {

    // Method that takes in list of trades and applies the lambda behaviour
    // for each of the trade in the collection
    private List<Trade> filterTrades(ITrade tradeLambda, List<Trade> trades){
        List<Trade> newTrades = new ArrayList<>();
        for (Trade trade : trades){
            if(tradeLambda.check(trade)){
                newTrades.add(trade);
            }
        }
        return newTrades;
    }
}
