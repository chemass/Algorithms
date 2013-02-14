import static org.junit.Assert.fail;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Test;

public class RandomizedQueueTest {

    @Test
    public final void testRandomizedQueue() {
        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
        rq = null;
    }

    @Test
    public final void testIsEmpty() {
        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
        if (!rq.isEmpty())
            fail("Fresh RandomizedQueue reports not empty.");
        rq.enqueue(0);
        if (rq.isEmpty())
            fail("Fresh RandomizedQueue reports empty.");
        rq = null;
    }

    @Test
    public final void testSize() {
        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();

        if (rq.size() != 0)
            fail("wrong size. expected 0 actual " + rq.size());

        rq.enqueue(0);
        rq.enqueue(1);
        rq.enqueue(2);
        rq.enqueue(3);
        rq.enqueue(4);

        if (rq.size() != 5)
            fail("wrong size. expected 5 actual " + rq.size());

        rq.dequeue();
        if (rq.size() != 4)
            fail("wrong size. expected 4 actual " + rq.size());

        rq.dequeue();
        if (rq.size() != 3)
            fail("wrong size. expected 3 actual " + rq.size());

        rq.dequeue();
        if (rq.size() != 2)
            fail("wrong size. expected 2 actual " + rq.size());

        rq.dequeue();
        if (rq.size() != 1)
            fail("wrong size. expected 1 actual " + rq.size());

        rq.dequeue();
        if (rq.size() != 0)
            fail("wrong size. expected 0 actual " + rq.size());

        rq.enqueue(0);
        if (rq.size() != 1)
            fail("wrong size. expected 1 actual " + rq.size());

        rq.enqueue(1);
        if (rq.size() != 2)
            fail("wrong size. expected 2 actual " + rq.size());

        rq = null;
    }

    @Test
    public final void testEnqueue() {
        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();

        try {
            rq.enqueue(null);
        } catch (NullPointerException e) {
            // TODO: handle exception
        } catch (Exception e) {
            fail("wrong exception type when adding null object.");
        }

        rq.enqueue(0);
        rq.enqueue(1);
        rq.enqueue(2);
        rq.enqueue(3);
        rq.enqueue(4);

        rq = null;
    }

    @Test
    public final void testDequeue() {
        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();

        rq.enqueue(0);
        rq.enqueue(1);
        rq.enqueue(2);
        rq.enqueue(3);
        rq.enqueue(4);

        boolean[] test = new boolean[5];

        for (int i = 0; i < 5; i++) {
            int x = rq.dequeue();
            if (test[x])
                fail("dequeued item has already been dequeued");
            test[x] = true;
        }

        try {
            rq.dequeue();
            fail("no exception when dequeueing from empty queue.");
        } catch (NoSuchElementException e) {
        } catch (Exception e) {
            fail("wrong exception type when dequeueing from empty queue.");
        }
    }

    @Test
    public final void testSample() {
        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();

        rq.enqueue(0);

        int x = rq.sample();

        if (rq.sample() != 0)
            fail("sample failed.");

        if (rq.size() != 1)
            fail("wrong size after sample");
    }

    @Test
    public final void testIterator() {
        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();

        int testCount = 10000;
        for (int i = 0; i < testCount; i++) {
            rq.enqueue(i);
        }

        Iterator<Integer> i1 = rq.iterator();
        Iterator<Integer> i2 = rq.iterator();

        boolean[] results = new boolean[testCount];
        int i = 0;
        while (i1.hasNext()) {
            results[i++] = i1.next() == i2.next();
        }
        int j = 0;
        for (j = 0; j < results.length; j++) {
            if (!results[j])
                break;
        }
        if (j == testCount) {
            fail("both iterators return the same values.");
        }
    }

    @Test
    public final void testEnqueueDequeue() {
        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();

        int rnd = 0;
        for (int i = 0; i < 5; i++) {
            rnd = StdRandom.uniform(2);
            if (rnd < 1 || rq.isEmpty()) {
                rq.enqueue(i);
            } else {
                rq.dequeue();
            }
        }
        for (int i = 0; i < 50; i++) {
            rnd = StdRandom.uniform(2);
            if (rnd < 1 || rq.isEmpty()) {
                rq.enqueue(i);
            } else {
                rq.dequeue();
            }
        }

        for (int i = 0; i < 50; i++) {
            rnd = StdRandom.uniform(2);
            if (rnd < 1 || rq.isEmpty()) {
                rq.enqueue(i);
            } else {
                rq.dequeue();
            }
        }

        for (int i = 0; i < 500; i++) {
            rnd = StdRandom.uniform(2);
            if (rnd < 1 || rq.isEmpty()) {
                rq.enqueue(i);
            } else {
                rq.dequeue();
            }
        }

        for (int i = 0; i < 500; i++) {
            rnd = StdRandom.uniform(2);
            if (rnd < 1 || rq.isEmpty()) {
                rq.enqueue(i);
            } else {
                rq.dequeue();
            }
        }

        for (int i = 0; i < 1000; i++) {
            rnd = StdRandom.uniform(2);
            if (rnd < 1 || rq.isEmpty()) {
                rq.enqueue(i);
            } else {
                rq.dequeue();
            }
        }

        for (int i = 0; i < 1000; i++) {
            rnd = StdRandom.uniform(2);
            if (rnd < 1 || rq.isEmpty()) {
                rq.enqueue(i);
            } else {
                rq.dequeue();
            }
        }
    }
}
