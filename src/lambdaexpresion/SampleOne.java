package lambdaexpresion;

import java.util.function.IntPredicate;
import java.util.stream.IntStream;

public class SampleOne {
    //traditional approach
    private static boolean isPrime(int number) {
        if (number < 2) return false;
        for (int index = 0; index < number; index++) {
            if (number % index == 0) return false;
        }
        return true;
    }

    //Declarative approach
    private static boolean IsPrimeDeclarative(int number) {
        return number > 1
                && IntStream.range(2, number).noneMatch(
                index -> number % index == 0);
    }

    //Functional interface approach
    private static boolean isPrimePredicate(int number) {
        IntPredicate intPredicate = index ->
                number % index == 0;

        return number > 1
                && IntStream.range(2, number).noneMatch(intPredicate);
    }

    public static void main(String[] args) {
        isPrimePredicate(5);
    }

}
