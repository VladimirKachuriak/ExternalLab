package education.Task1;

import java.util.Iterator;

public class LinkedListImpl<E> implements List<E>, Iterable<E> {
    private Node<E> head = null;

    @Override
    public int size() {
        if (head == null) return 0;
        int counter = 1;
        Node<E> node = head;
        while (node.next != null) {
            node = node.next;
            counter++;
        }
        return counter;
    }

    @Override
    public void clear() {
        head = null;
    }

    @Override
    public void add(E items) {
        if (head == null) {
            head = new Node<E>(items, null, null);
        } else {
            Node<E> prev = head;
            while (prev.next != null) {
                prev = prev.next;
            }
            prev.next = new Node<E>(items, prev, null);
        }
    }

    @Override
    public void add(E items, int index) {
        if (head == null) {
            head = new Node<E>(items, null, null);
        }
        if (index == 0) {
            head = new Node<E>(items, null, head);
        } else {
            Node<E> prev = head;
            for (int i = 0; i < index - 1 && prev.next != null; i++) {
                prev = prev.next;
            }
            Node<E> next = prev.next;
            prev.next = new Node<E>(items, prev, next);
            if (next != null) next.prev = prev.next;
        }
    }

    @Override
    public E getByIndex(int index) {
        if (index < 0 || index > size() - 1) return null;
        Node<E> curr = head;
        for (int i = 0; i < index && curr.next != null; i++) {
            curr = curr.next;
        }
        return curr.value;
    }

    @Override
    public E removeBYIndex(int index) {
        if (index > size() - 1 || index < 0) {
            return null;
        }
        if (index == 0) {
            E value = head.value;
            head = head.next;
            return value;
        }
        Node<E> curr = head;
        for (int i = 0; i < index && curr.next != null; i++) {
            curr = curr.next;
        }
        Node<E> next = curr.next;
        Node<E> prev = curr.prev;
        if (prev != null) prev.next = next;
        if (next != null) next.prev = prev;
        return curr.value;
    }


    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        Node<E> node = head;
        while (node != null) {
            result.append(node.value.toString());
            node = node.next;
            if (node != null) {
                result.append(", ");
                if (node.next == null) {
                    result.append(node.value).append("]");
                    return result.toString();
                }
            } else {
                result.append("]");
                return result.toString();
            }
        }
        result.append("]");
        return result.toString();
    }

    @Override
    public Iterator<E> iterator() {
        return new IteratorImpl();
    }


    public static class Node<E> {
        private E value;
        private Node<E> prev;
        private Node<E> next;

        public Node(E value, Node<E> prev, Node<E> next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(E value) {
            this.value = value;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }

        public Node<E> getPrev() {
            return prev;
        }

        public void setPrev(Node<E> prev) {
            this.prev = prev;
        }
    }

    class IteratorImpl implements Iterator<E> {
        private Node<E> node = head;
        private Node<E> prev;

        @Override
        public boolean hasNext() {
            return node != null;
        }

        @Override
        public E next() {
            prev = node;
            node = node.next;
            return prev.value;
        }

        @Override
        public void remove() {
            prev = node.next;
            node = node.next;
        }
    }
}
