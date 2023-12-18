package kollections.lists;

public interface List<T> {
    void add(int index, T element);
    void add(T element);
    void set(int index, T element);
    int size();
    T remove(int index);
    T get(int index);
     int indexOf(T element);

    boolean remove(T element);

    boolean contains(T element);
}
