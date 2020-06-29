package lambdaexpresion;


import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

//Passing behaviours into methods
public class SampleTwo {

    public static int sumWithCondition(List<Integer> numbers, Predicate<Integer> predicate) {
        return numbers.parallelStream()
                .filter(predicate)
                .mapToInt(index -> index)
                .sum();
    }

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 0);
        System.out.println(sumWithCondition(numbers, n -> true));
        System.out.println(sumWithCondition(numbers, n -> n % 2 == 0));
        System.out.println(sumWithCondition(numbers, n -> n > 5));
    }
}
