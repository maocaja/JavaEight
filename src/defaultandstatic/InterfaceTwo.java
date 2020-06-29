package defaultandstatic;

@FunctionalInterface
public interface InterfaceTwo {
    void methodTwo();
    default void log(String str){
        System.out.println("InterfaceTwo logging: " + str);
    }
}
