package functionalinterface;

//Not functional because method object.clone is not public
public interface FooTwo {
    int m();
    Object clone();
}
