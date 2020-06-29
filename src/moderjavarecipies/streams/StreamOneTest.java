package moderjavarecipies.streams;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class StreamOneTest {

    public EmployeeRepository employeerepository = new EmployeeRepository();

    public Employee[] arrayOfEmps = {
            new Employee(1, "Jeff Bezos", 100000.0)
            , new Employee(2, "Bill Gates", 200000.0)
            , new Employee(3, "Mark Zuckerberg", 300000.0)};

    public List<Employee> empList = Arrays.asList(arrayOfEmps);
    public Employee[] sortedEmployeesByName =
            {
                    new Employee(2, "Bill Gates", 200000.0)
                    , new Employee(1, "Jeff Bezos", 100000.0)
                    , new Employee(3, "Mark Zuckerberg", 300000.0)
            };


    @Test
    public void whenIncrementSalaryForEachEmployee_thenApplyNewSalary() {

        empList.stream().forEach(employee -> employee.rateSalaryIncrease(10.0));
        assertThat(empList, contains(hasProperty("salary", equalTo(110000.0)),
                hasProperty("salary", equalTo(220000.0)),
                hasProperty("salary", equalTo(330000.0))));
    }

    @Test
    public void whenMapIdToEmployees_thenGetEmployeeStream() {
        Integer[] employeesId = {1, 2, 3};
        List<Employee> employees = Stream.of(employeesId)
                .map(employeerepository::findById)
                .collect(Collectors.toList());

        assertThat(employees.size(), equalTo(employeesId.length));
    }

    @Test
    public void whenCollectStreamToList_thenGetList() {
        List<Employee> employees = empList.stream().collect(Collectors.toList());
        assertThat(empList, equalTo(employees));
    }

    //@Test
    public void whenFilterEmployees_thenGetFilteredStream() {
        Integer[] employeesId = {1, 2, 3};

        List<Employee> employees = Stream.of(employeesId)
                .map(employeerepository::findById)
                .filter(employee -> employee != null)
                .map(employee -> {
                    System.out.println(employee.getSalary());
                    return employee;
                })
                .filter(employee -> employee.getSalary() > 200000)
                .collect(Collectors.toList());

        assertThat(Arrays.asList(arrayOfEmps[2]), equalTo(employees));
    }

    @Test
    public void whenFindFirst_thenGetFirstEmployeeInStream() {
        Integer[] empIds = {1, 2, 3, 4};

        Employee employee = Stream.of(empIds)
                .map(employeerepository::findById)
                .filter(emp -> emp != null)
                .filter(emp -> emp.getSalary() > 100000)
                .findFirst()
                .orElse(null);
        assertThat(employee.getSalary(), equalTo(new Double(200000)));
    }

    @Test
    public void whenStreamToArray_thenGetArray() {
        Employee[] employees = empList.stream().toArray(Employee[]::new);
        //Employee[]::new creates an empty array of employee
        assertThat(empList.toArray(), equalTo(employees));
    }

    @Test
    public void whenFlatMapEmployeeNames_thenGetNameStream() {
        List<List<String>> nameNested = Arrays.asList(
                Arrays.asList("Jeff", "Bezos"),
                Arrays.asList("Bill", "Gates"),
                Arrays.asList("Mark", "Zuckerberg")
        );

        nameNested.forEach(System.out::println);

        List<String> nameFlatStream = nameNested.stream()
                .flatMap(Collection::stream)
                .map(n -> {
                    System.out.println(n);
                    return n;
                })
                .collect(Collectors.toList());

        nameFlatStream.forEach(System.out::println);

        assertThat(nameFlatStream.size(), equalTo(nameNested.size() * 2));

        //Notice how were able to convert the Stream<List<String>> to a simpler Stream<String> -> using flatMap() API
    }

    @Test
    public void whenIncrementSalaryUsingPeek_thenApplyNewSalary() {

        empList.stream()
                .peek(employee -> employee.rateSalaryIncrease(10.0))
                .peek(System.out::println)
                .collect(Collectors.toList());

        assertThat(empList, contains(
                hasProperty("salary", equalTo(110000.0)),
                hasProperty("salary", equalTo(220000.0)),
                hasProperty("salary", equalTo(330000.0))
        ));
    }

    @Test
    public void whenLimitInfiniteStream_thenGetFiniteElements() {
        Stream<Integer> infiniteStream = Stream.iterate(2, index -> index * 2);

        List<Integer> collect = infiniteStream
                .skip(3)
                .limit(5)
                .collect(Collectors.toList());

        assertThat(collect, equalTo(Arrays.asList(16, 32, 64, 128, 256)));


    }

    @Test
    public void whenSortStream_thenGetSortedStream() {

        List<Employee> employees = empList.stream()
                .sorted((employeeOne, employeeTwo) -> employeeOne.getName().compareTo(employeeTwo.getName()))
                .collect(Collectors.toList());

        assertThat(employees.get(0).getName(), equalTo("Bill Gates"));
        assertThat(employees.get(1).getName(), equalTo("Jeff Bezos"));
        assertThat(employees.get(2).getName(), equalTo("Mark Zuckerberg"));
    }

    //Compartor comparing
    @Test
    public void whenFindMax_thenGetMaxElementFromStream() {
        Employee maxSalaryEmployee = empList.stream()
                .max(Comparator.comparing(Employee::getSalary))
                .orElseThrow(NoSuchElementException::new);
        assertThat(maxSalaryEmployee.getSalary(), equalTo(new Double(300000.0)));
    }

    @Test
    public void whenApplyMatch_thenReturnBoolean() {
        List<Integer> integerList = Arrays.asList(2, 4, 5, 6, 8);
        boolean allEven = integerList.stream().allMatch(index -> index % 2 == 0); // false
        boolean oneEven = integerList.stream().anyMatch(index -> index % 2 == 0); // true
        boolean noneMultipleOfThree = integerList.stream().noneMatch(index -> index % 3 == 0);// false

        assertThat(allEven, equalTo(false));
        assertThat(oneEven, equalTo(true));
        assertThat(noneMultipleOfThree, equalTo(false));
    }

}
