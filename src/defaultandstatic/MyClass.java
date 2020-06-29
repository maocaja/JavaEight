package defaultandstatic;

public class MyClass implements InterfaceOne, InterfaceTwo {

    @Override
    public void methodOne(String str) {

    }

    @Override
    public void methodTwo() {

    }

    @Override
    public void log(String str) {
        System.out.println("MyClass logging:: " + str);
        InterfaceOne.print("abc");
    }
}
