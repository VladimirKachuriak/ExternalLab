package education.Task1;

interface List<T> {
    int size();
    void clear();
    void add(T items);
    void add(T items, int index);
    T getByIndex(int index);
    T removeBYIndex(int index);
}
