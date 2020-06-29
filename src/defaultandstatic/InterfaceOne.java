package defaultandstatic;
@FunctionalInterface
public interface InterfaceOne {

    void methodOne(String str);

    default void log(String str){
        System.out.println("InterfaceOne logging: " + str);
    }

    default void logTwo(String str){
        System.out.println("InterfaceOne logging: " + str);
    }

    static void print(String str){
        System.out.println("Printing: " + str);
    }

    static void printTwo(String str){
        System.out.println("Printing: " + str);
    }

}
