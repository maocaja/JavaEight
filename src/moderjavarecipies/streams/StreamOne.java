package moderjavarecipies.streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamOne {

    private static Employee[] arrayOfEmps = { new Employee(1, "Jef Bezos", 100000.0)
                                             ,new Employee(2, "Bill Gates", 200000.0)
                                             ,new Employee(3, "Mark Zuckerberg", 300000.0) };

    private static List<Employee> emptyList = Arrays.asList(arrayOfEmps);

    public static void main (String [] args){
        //Stream creation
        Stream.of(arrayOfEmps);

        //We can obtain a stream from an existing list
        emptyList.stream();

        // we can create a stream from individual objects usinf stream.of
        Stream.of(arrayOfEmps[0], arrayOfEmps[1],arrayOfEmps[2]);

        // We can create a Stream with Stream builder.
        Stream.Builder<Employee> employeeBuilder = Stream.builder();
        employeeBuilder.accept(arrayOfEmps[0]);
        employeeBuilder.accept(arrayOfEmps[1]);
        employeeBuilder.accept(arrayOfEmps[2]);
    }

}
