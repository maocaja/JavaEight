package lambdaexpresion;

import java.util.Comparator;
import java.util.List;

//Higher Efficiency with Laziness
public class SampleThree {
    private static int findSquareOfMaxOdd(List<Integer> numbers) {
        int max = 0;
        for (int index : numbers) {
            if (index % 2 != 0 && index > 3 && index < 11 && index > max) {
                max = index;
            }
        }
        return max * max;
    }

    public static int findSquareOfMaxOddStream(List<Integer> numbers) {

        return numbers.stream()
                .filter(NumberTest::isOdd)
                .filter(NumberTest::isGreaterThan3)
                .filter(NumberTest::isLessThan11)
                .max(Comparator.naturalOrder())
                .map(number -> number * number)
                .get();
    }
}
