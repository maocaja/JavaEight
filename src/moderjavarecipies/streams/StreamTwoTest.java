package moderjavarecipies.streams;

import org.junit.Test;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasProperty;

public class StreamTwoTest {

    public Employee[] arrayOfEmps = {
            new Employee(1, "Jeff Bezos", 100000.0)
            , new Employee(2, "Bill Gates", 200000.0)
            , new Employee(4, "Bill Gates", 400000.0)
            , new Employee(3, "Mark Zuckerberg", 300000.0)};

    public List<Employee> empList = Arrays.asList(arrayOfEmps);

    @Test
    public void whenFindMaxonIntStream_thenGetMaxInteger() {
        /*
        Employee maxSalaryEmployee = empList.stream()
                .max(Comparator.comparing(Employee::getSalary))
                .orElseThrow(NoSuchElementException::new);
                in this case we use a comparator
         */

        Integer lastestEmpId = empList.stream()
                .mapToInt(Employee::getCode)
                .max()
                .orElseThrow(NoSuchElementException::new);

        assertThat(lastestEmpId, equalTo(3));
    }

    @Test
    public void whenApplyReduceOnStream_thenGetValue() {
        Double sumSalary = empList.stream()
                .map(Employee::getSalary)
                .reduce(0.0, Double::sum);

        assertThat(sumSalary, equalTo(new Double(600000)));
    }

    @Test
    public void whenCollectByJonning_thenGetJoinedString() {
        String employeeNames = empList.stream()
                .map(Employee::getName)
                .collect(Collectors.joining(", "));

        assertThat(employeeNames, equalTo("Jeff Bezos, Bill Gates, Mark Zuckerberg"));
    }

    @Test
    public void whenCollectBySet_thenGetSet() {
        Set<String> employeeNames = empList.stream()
                .map(Employee::getName)
                .collect(Collectors.toSet());

        assertThat(employeeNames.size(), equalTo(3));
    }

    @Test
    public void whenToVectorCollection_thenGetVector() {
        Vector<String> employeeNames = empList.stream()
                .map(Employee::getName)
                .collect(Collectors.toCollection(Vector::new));

        assertThat(employeeNames.size(), equalTo(3));
    }

    @Test
    public void whenApplySummarizing_thenBasicStats() {

        DoubleSummaryStatistics summaryStatistics = empList.stream()
                .collect(Collectors.summarizingDouble(Employee::getSalary));

        assertThat(summaryStatistics.getCount(), equalTo(3L));
        assertThat(summaryStatistics.getSum(), equalTo(600000.0));
        assertThat(summaryStatistics.getAverage(), equalTo(200000.0));

    }

    @Test
    public void whenStreamPartition_thenGetMap() {
        List<Integer> integerList = Arrays.asList(2, 4, 5, 6, 8);
        Map<Boolean, List<Integer>> isEven = integerList.stream().collect(
                Collectors.partitioningBy(index -> index % 2 == 0)
        );

        assertThat(isEven.get(true).size(), equalTo(4));
        assertThat(isEven.get(false).size(), equalTo(1));
    }

    @Test
    public void whenStreamGroupingBy_thenGetMap() {
        Map<Character, List<Employee>> groupByAlphabet = empList.stream()
                .collect(
                        Collectors.groupingBy(e -> (e.getName().charAt(0)))
                );

        groupByAlphabet.forEach((k, v) -> System.out.println("Item : " + k + " value : " + v));


        assertThat(groupByAlphabet.get('B').get(0).getName(), equalTo("Bill Gates"));
        assertThat(groupByAlphabet.get('B').get(1).getName(), equalTo("Bill Gates"));
    }

    @Test
    public void whenStreamMapping_thenGetMap() {
        Map<Character, List<Integer>> idGroupedByAlphabet = empList.stream().collect(
                Collectors.groupingBy(e -> e.getName().charAt(0),
                        Collectors.mapping(Employee::getCode, Collectors.toList())));

        idGroupedByAlphabet.forEach((k, v) -> System.out.println("Item : " + k + " value : " + v));
        assertThat(idGroupedByAlphabet.get('B').get(0), equalTo(2));
        assertThat(idGroupedByAlphabet.get('B').get(1), equalTo(4));
    }

    @Test
    public void whenStreamReducing_thenGetValue() {
        Double percentage = 10.0D;
        Double salIncrOverhead = empList.stream()
                .collect(
                        Collectors.reducing(100.0, employee -> employee.getSalary() * percentage / 100, (s1, s2) -> s1 + s2)
                );
        assertThat(salIncrOverhead, equalTo(100100.0D));
    }

    @Test
    public void whenStreamGroupingAndReducing_thenGetMap() {
        Comparator<Employee> byNameLength = Comparator.comparing(Employee::getName);

        /*Map<Character, Optional<Employee>> longestNameByAphabet = empList.stream()
                .collect(
                        Collectors.groupingBy(employee -> employee.getName().charAt(0)),
                        Collectors.reducing(BinaryOperator.maxBy(byNameLength))
                );*/
    }

    @Test
    public void whenParallelStream_thenPerformOperationsInParallel() {
        empList.stream().forEach(employee -> employee.getSalary());
        empList.stream().parallel().forEach(employee -> employee.rateSalaryIncrease(10.0));

        assertThat(empList, contains(
                hasProperty("salary", equalTo(110000.0)),
                hasProperty("salary", equalTo(220000.0)),
                hasProperty("salary", equalTo(330000.0))
        ));
    }

    @Test
    public void whenTakeWhile_thenTakeWhileList() {

        List<Integer> integerList = Stream.of(1, 2, 9, 3, 4, 5, 6, 7, 8, 9, 0, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0)
                .takeWhile(index -> index <= 5)
                .collect(Collectors.toList());

        assertThat(integerList, equalTo(Arrays.asList(1, 2)));

    }

    @Test
    public void whenFilter_thenFilterList() {

        List<Integer> integerList = Stream.of(1, 2, 9, 3, 4, 5, 6, 7, 8, 9, 0, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0)
                .filter(index -> index <= 5)
                .collect(Collectors.toList());

        assertThat(integerList, equalTo(Arrays.asList(1, 2, 3, 4, 5, 0, 5, 4, 3, 2, 1, 0)));

    }

    @Test
    public void whenDropWhile_thenDropElementsUntilTheConditionIsTrue() {
        List<Integer> integerList = Stream.of(1, 6, 3, 4, 5, 6, 7, 8, 9, 0, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0)
                .dropWhile(x -> x <= 5)
                .collect(Collectors.toList());

        //integerList.forEach(System.out::println);

        assertThat(integerList, equalTo(Arrays.asList(6, 3, 4, 5, 6, 7, 8, 9, 0, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0)));
    }

    @Test
    public void whenIterate_thenTheSameFor() {
        List<Integer> integerList = Stream.iterate(5, index -> index < 256, index -> index * 2)
                .collect(Collectors.toList());

        for (int i = 1; i < 256; i *= 2) {
            System.out.println(i);
        }
        assertThat(integerList, equalTo(Arrays.asList(5, 10, 20, 40, 80, 160)));
    }

    @Test
    public void whenOfNullable_then() {
        Integer number = 5;
        Stream<Integer> resultOne = number != null ? Stream.of(number) : Stream.empty();
        Stream<Integer> resultTwo = Stream.ofNullable(number);
    }

}

