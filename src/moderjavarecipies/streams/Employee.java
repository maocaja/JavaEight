package moderjavarecipies.streams;

public class Employee {

    private int code;
    private String name;
    private double salary;

    public Employee(int code, String name, double salary) {
        this.code = code;
        this.name = name;
        this.salary = salary;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public void rateSalaryIncrease(double rate) {
        this.salary += (this.salary * rate) / 100;
    }

    @Override
    public String toString() {
        return "code: " + code + " name: "+ name + " salary: " + salary;
    }
}
