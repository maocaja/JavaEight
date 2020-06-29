package foreach;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class Java8ForEachExample {

    public static void main (String[] args){
        // creating a sample collection
        List<Integer> myList = new ArrayList<>();
        for (int index = 0; index < 10; index++) myList.add(index);

        //traversing using iterator
        Iterator<Integer> iterator = myList.iterator();
        while (iterator.hasNext()){
            Integer integer = iterator.next();
            System.out.println("Iterator Value: " + integer);
        }

        //traversing through forEach method of Iterable with anonymous class
        myList.forEach(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println("forEach anonymous class Value::"+ integer);
            }
        });

        //Traversing with consumer interface implementation
        MyConsumer myConsumer = new MyConsumer();
        myList.forEach(myConsumer);

    }
}
