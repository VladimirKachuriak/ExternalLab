package education.Task1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayListImplTest {

    @Test
    void size() {
        List<Integer> list = new ArrayListImpl<>();
        assertEquals(0, list.size());
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals(3, list.size());
        list.removeBYIndex(1);
        assertEquals(2, list.size());
    }

    @Test
    void clear() {
        List<Integer> list = new ArrayListImpl<>();
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals(3, list.size());
        list.clear();
        assertEquals(0, list.size());
    }

    @Test
    void add() {
        List<Integer> list = new ArrayListImpl<>();
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals("[1, 2, 3]", list.toString());
    }

    @Test
    void testAdd() {
        List<Integer> list = new ArrayListImpl<>();
        list.add(1);
        list.add(2);
        list.add(3, 1);
        assertEquals("[1, 3, 2]", list.toString());
        list.add(4, 0);
        assertEquals("[4, 1, 3, 2]", list.toString());
    }

    @Test
    void getByIndex() {
        List<Integer> list = new ArrayListImpl<>();
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals(3, list.getByIndex(2));
        assertNull(list.getByIndex(4));
    }

    @Test
    void removeBYIndex() {
        List<Integer> list = new ArrayListImpl<>();
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals(2, list.removeBYIndex(1));
        assertEquals(2, list.size());
        assertEquals("[1, 3]", list.toString());
        assertEquals(null, list.removeBYIndex(4));
    }

    @Test
    void iterator() {
        ArrayListImpl<Integer> list = new ArrayListImpl<>();
        list.add(1);
        list.add(2);
        list.add(3);
        StringBuilder res = new StringBuilder();
        for (Integer i : list) {
            res.append(i);
        }
        assertEquals("123", res.toString());
    }
}