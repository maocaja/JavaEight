package functionalinterface;

//Functional because Comparator has only one abstract non-object method.
@FunctionalInterface
public interface Comparator<T> {
    boolean equals(Object object);
    int compare(T objectOne, T objectTwo);
}
