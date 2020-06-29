package foreach;

import java.util.function.Consumer;

/*
 * Consumer implementation that can be reused
 */
public class MyConsumer implements Consumer<Integer> {

    @Override
    public void accept(Integer integer) {
        System.out.println("Consumer impl Value: " + integer);
    }
}
