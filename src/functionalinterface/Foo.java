package functionalinterface;

//Not functional interface because equals is already an implicit member (Object class)
public interface Foo {
    boolean equals(Object object);
}
