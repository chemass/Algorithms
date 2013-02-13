import static org.junit.Assert.fail;

import java.util.Iterator;

import org.junit.Test;

public class DequeTest {

    @Test
    public final void testDeque() {
        Deque<String> dq = new Deque<String>();
        dq = null;
    }

    @Test
    public final void testIsEmpty() {
        Deque<String> dq = new Deque<String>();
        if (!dq.isEmpty())
            fail("Reporting not empty on creation.");

        dq.addFirst("first");

        if (dq.isEmpty())
            fail("Reporting empty after adding item.");

        dq = null;
    }

    @Test
    public final void testAddNull() {
        Deque<String> dq = new Deque<String>();
        try {
            dq.addFirst(null);
            fail("didn't throw NullPointerException");
        } catch (java.lang.NullPointerException e) {

        } catch (Exception e) {
            fail("caught the wrong exception type.");
        }

        try {
            dq.addLast(null);
            fail("didn't throw NullPointerException");
        } catch (java.lang.NullPointerException e) {

        } catch (Exception e) {
            fail("caught the wrong exception type.");
        }

        try {
            dq.removeFirst();
            fail("didn't throw NoSuchElementException");
        } catch (java.util.NoSuchElementException e) {

        } catch (Exception e) {
            fail("caught the wrong exception type.");
        }

        try {
            dq.removeLast();
            fail("didn't throw NoSuchElementException");
        } catch (java.util.NoSuchElementException e) {

        } catch (Exception e) {
            fail("caught the wrong exception type.");
        }

    }

    @Test
    public final void testSize() {
        Deque<String> dq = new Deque<String>();
        if (dq.size() > 0)
            fail("Reporting size of " + dq.size() + " on creation.");

        dq.addFirst("first");

        if (dq.size() == 0)
            fail("Reporting size of 0 after adding item.");

        dq = null;
    }

    @Test
    public final void testAddFirst() {
        Deque<String> dq = new Deque<String>();
        dq.addFirst("first");
        if (dq.size() == 0)
            fail("Reporting size of 0 after adding item first.");
    }

    @Test
    public final void testAddLast() {
        Deque<String> dq = new Deque<String>();
        dq.addLast("first");
        if (dq.size() == 0)
            fail("Reporting size of 0 after adding item last.");
    }

    @Test
    public final void testRemoveFirst() {
        Deque<String> dq = new Deque<String>();
        dq.addFirst("second");
        dq.addLast("third");
        dq.addFirst("first");
        String first = dq.removeFirst();
        if (first != "first") {
            fail("wrong result on RemoveFirst. Expected 'first', got '" + first
                    + "'.");
        }
    }

    @Test
    public final void testRemoveLast() {
        Deque<String> dq = new Deque<String>();
        dq.addFirst("second");
        dq.addLast("third");
        dq.addFirst("first");
        String last = dq.removeLast();
        if (last != "third") {
            fail("wrong result on RemoveLast. Expected 'third', got '" + last
                    + "'.");
        }
    }

    @Test
    public final void testRollingArray() {

        Deque<String> dq = new Deque<String>();
        dq.addLast("first");
        dq.addLast("second");
        dq.addLast("third");
        dq.addLast("fourth");
        dq.addLast("fifth");
        dq.addLast("sixth");
        dq.addLast("seventh");
        dq.addLast("eighth");

        if (dq.isEmpty())
            fail("empty after adding all items");

        String first = dq.removeFirst();
        if (first != "first") {
            fail("wrong result on > RollingArray. Expected 'first', got '"
                    + first + "'.");
        }
        String second = dq.removeFirst();
        if (second != "second") {
            fail("wrong result on > RollingArray. Expected 'second', got '"
                    + second + "'.");
        }
        String third = dq.removeFirst();
        if (third != "third") {
            fail("wrong result on > RollingArray. Expected 'third', got '"
                    + third + "'.");
        }
        String fourth = dq.removeFirst();
        if (fourth != "fourth") {
            fail("wrong result on > RollingArray. Expected 'fourth', got '"
                    + fourth + "'.");
        }
        String fifth = dq.removeFirst();
        if (fifth != "fifth") {
            fail("wrong result on > RollingArray. Expected 'fifth', got '"
                    + fifth + "'.");
        }
        String sixth = dq.removeFirst();
        if (sixth != "sixth") {
            fail("wrong result on > RollingArray. Expected 'sixth', got '"
                    + sixth + "'.");
        }
        String seventh = dq.removeFirst();
        if (seventh != "seventh") {
            fail("wrong result on > RollingArray. Expected 'seventh', got '"
                    + seventh + "'.");
        }
        String eighth = dq.removeFirst();
        if (eighth != "eighth") {
            fail("wrong result on > RollingArray. Expected 'eighth', got '"
                    + eighth + "'.");
        }

        if (!dq.isEmpty())
            fail("not empty after removing all items");

        dq.addFirst("eighth");
        dq.addFirst("seventh");
        dq.addFirst("sixth");
        dq.addFirst("fifth");
        dq.addFirst("fourth");
        dq.addFirst("third");
        dq.addFirst("second");
        dq.addFirst("first");

        first = dq.removeFirst();
        if (first != "first") {
            fail("wrong result on < RollingArray. Expected 'first', got '"
                    + first + "'.");
        }
        second = dq.removeFirst();
        if (second != "second") {
            fail("wrong result on < RollingArray. Expected 'second', got '"
                    + second + "'.");
        }
        third = dq.removeFirst();
        if (third != "third") {
            fail("wrong result on < RollingArray. Expected 'third', got '"
                    + third + "'.");
        }
        fourth = dq.removeFirst();
        if (fourth != "fourth") {
            fail("wrong result on < RollingArray. Expected 'fourth', got '"
                    + fourth + "'.");
        }
        fifth = dq.removeFirst();
        if (fifth != "fifth") {
            fail("wrong result on < RollingArray. Expected 'fifth', got '"
                    + fifth + "'.");
        }
        sixth = dq.removeFirst();
        if (sixth != "sixth") {
            fail("wrong result on < RollingArray. Expected 'sixth', got '"
                    + sixth + "'.");
        }
        seventh = dq.removeFirst();
        if (seventh != "seventh") {
            fail("wrong result on < RollingArray. Expected 'seventh', got '"
                    + seventh + "'.");
        }
        eighth = dq.removeFirst();
        if (eighth != "eighth") {
            fail("wrong result on < RollingArray. Expected 'eighth', got '"
                    + eighth + "'.");
        }

    }

    @Test
    public final void testHUUUUUUUUUUUUGE() {

        Deque<Integer> dq = new Deque<Integer>();
        for (int i = 1; i <= 5000000; i++) {
            dq.addFirst(i);
        }
        for (int i = 5000000; i > 0; i--) {
            int r = dq.removeFirst();
            if (r != i) {
                fail("incorrect result. Expected " + i + " actual " + r);
            }
        }
        if (!dq.isEmpty()) {
            fail("Reports not empty");
        }

        for (int i = 1; i <= 5000000; i++) {
            dq.addFirst(i);
        }
        for (int i = 5000000; i > 0; i--) {
            int r = dq.removeFirst();
            if (r != i) {
                fail("incorrect result. Expected " + i + " actual " + r);
            }
        }
    }

    @Test
    public final void testIterator() {

        int i = 1;
        Deque<Integer> dq = new Deque<Integer>();
        for (i = 1; i <= 5000; i++) {
            dq.addLast(i);
        }

        i = 1;
        Iterator<Integer> it = dq.iterator();
        while (it.hasNext()) {
            int ii = it.next();
            if (ii != i) {
                fail("iterator failed. expected " + i + " actual " + ii);
            }
            i++;
        }

        dq = null;

        dq = new Deque<Integer>();
        for (i = 1; i <= 8; i++) {
            dq.addFirst(i);
        }

        it = dq.iterator();
        i = 0;
        while (it.hasNext()) {
            System.out.println(it.next());
            i++;
        }
        if (i != 8) {
            fail("incorrect number of elements returned from iterator.");
        }

        try {
            it.next();
            fail("didn't throw NoSuchElementException");
        } catch (java.util.NoSuchElementException e) {

        } catch (Exception e) {
            fail("Wrong exception type thrown on next()");
        }

        try {
            it.remove();
            fail("didn't throw UnsupportedOperationException");
        } catch (java.lang.UnsupportedOperationException e) {

        } catch (Exception e) {
            fail("Wrong exception type thrown on remove()");
        }

        dq = null;

        dq = new Deque<Integer>();
        for (i = 1; i <= 8; i++) {
            if (i % 2 == 0) {
                dq.addLast(i);
            } else {
                dq.addFirst(i);
            }

        }

        it = dq.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}
