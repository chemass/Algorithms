import java.util.Iterator;

/**
 * 
 */

/**
 * @author Che Coxshall
 * @login ccoxshall@gmail.com
 * @date 12 Feb 2013
 */
public class Deque<T> implements Iterable<T> {

    /**
     * The internal storage list
     */
    private Node headNode;
    private Node tailNode;
    /**
     * The count of populated list items
     */
    private int size = 0;

    /**
     * Creates a new, empty deque
     */
    public Deque() {

    }

    /**
     * Indicates if the deque is empty.
     * 
     * @return true if the deque is empty.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of items on the deque.
     * 
     * @return the number of items on the deque.
     */
    public int size() {
        return size;
    }

    /**
     * Inserts the specified item at the front of the deque,
     * 
     * @param item
     *            Item to insert
     */
    public void addFirst(T item) {
        if (item == null) {
            throw new java.lang.NullPointerException("Cannot add null items.");
        }

        try {
            Node newFirst = new Node();
            newFirst.item = item;

            if (headNode == null) {
                headNode = newFirst;
                tailNode = headNode;
            } else {
                newFirst.next = headNode;
                headNode = newFirst;
            }

            size++;
        } catch (OutOfMemoryError e) {
            System.out.println("out of memory after " + size + " items.");
            throw e;
        }
    }

    /**
     * Inserts the specified item at the end of the deque,
     * 
     * @param item
     *            Item to insert
     */
    public void addLast(T item) {
        if (item == null) {
            throw new java.lang.NullPointerException("Cannot add null items.");
        }

        Node newLast = new Node();
        newLast.item = item;

        if (headNode == null) {
            headNode = newLast;
        } else {
            Node node = headNode;
            while (node.next != null) {
                node = node.next;
            }
            node.next = newLast;
            newLast.prev = node;
        }

        tailNode = newLast;

        size++;
    }

    /**
     * Removes and returns the item at the front of the deque.
     * 
     * @return the item that was at the front of the deque.
     */
    public T removeFirst() {
        if (size == 0) {
            throw new java.util.NoSuchElementException(
                    "No elements in the collection");
        }

        Node oldHead = headNode;
        headNode = oldHead.next;
        T item = oldHead.item;
        oldHead = null;
        size--;
        return item;
    }

    /**
     * Removes and returns the item at the end of the deque.
     * 
     * @return the item that was at the end of the deque.
     */
    public T removeLast() {
        if (size == 0) {
            throw new java.util.NoSuchElementException(
                    "No elements in the collection");
        }

        Node node = tailNode;
        // while (node.next != null) {
        // prevNode = node;
        // node = node.next;
        // }

        T item = node.item;
        tailNode = node.prev;
        tailNode.next = null;
        node = null;
        size--;
        return item;
    }

    /**
     * An iterator that iterates from the front to the end of the deque
     */
    public Iterator<T> iterator() {

        return new Iterator<T>() {

            Node currentNode = null;

            @Override
            public boolean hasNext() {
                return currentNode == null ? headNode != null
                        : currentNode.next != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new java.util.NoSuchElementException();
                }
                if (currentNode == null) {
                    currentNode = headNode;
                    return currentNode.item;
                }
                currentNode = currentNode.next;
                return currentNode.item;
            }

            @Override
            public void remove() {
                throw new java.lang.UnsupportedOperationException();
            }
        };

    }

    private class Node {
        T item;
        Node next;
        Node prev;
    }
}
