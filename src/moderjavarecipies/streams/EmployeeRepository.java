package moderjavarecipies.streams;


public class EmployeeRepository {

    private static Employee[] arrayOfEmps = {new Employee(1, "Jef Bezos", 100000.0)
            , new Employee(2, "Bill Gates", 200000.0)
            , new Employee(3, "Mark Zuckerberg", 300000.0)};

    public Employee findById(int id) {
        return arrayOfEmps[id-1];
    }


}
