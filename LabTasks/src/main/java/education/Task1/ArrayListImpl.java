package education.Task1;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayListImpl<E> implements List<E>, Iterable<E> {
    private Object[] arr = new Object[10];
    private int curr_inx = -1;


    @Override
    public int size() {
        return curr_inx + 1;
    }

    @Override
    public void clear() {
        arr = new Object[10];
        curr_inx = -1;
    }

    @Override
    public void add(E items) {
        if (curr_inx + 1 <= arr.length) {
            arr = Arrays.copyOf(arr, arr.length + 1);
        }
        arr[++curr_inx] = items;
    }

    @Override
    public void add(E items, int index) {
        if (curr_inx + 1 <= arr.length) {
            arr = Arrays.copyOf(arr, arr.length + 1);
        }
        for (int i = arr.length - 1; i > index; i--) {
            arr[i] = arr[i - 1];
        }
        arr[index] = items;
        curr_inx++;
    }

    @Override
    public E getByIndex(int index) {
        return (E) arr[index];
    }

    @Override
    public E removeBYIndex(int index) {
        E temp = (E) arr[index];
        if (index > arr.length - 1) throw new NoSuchElementException();
        for (int i = index; i < arr.length - 1; i++) {
            arr[i] = arr[i + 1];
        }
        curr_inx--;
        arr = Arrays.copyOf(arr, arr.length - 1);
        return temp;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("[");
        for (int i = 0; i <= curr_inx; i++) {
            if (i != 0) res.append(" ");
            res.append(arr[i]);
            if (i <= curr_inx - 1) res.append(",");
        }
        res.append("]");
        return res.toString();
    }

    @Override
    public Iterator<E> iterator() {
        return new IteratorImpl();
    }

    class IteratorImpl implements Iterator<E> {
        private int counter = -1;

        @Override
        public boolean hasNext() {
            return counter < curr_inx;
        }

        @Override
        public E next() {
            return (E) arr[++counter];
        }

    }
}
