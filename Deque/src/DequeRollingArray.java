import java.util.Iterator;

/**
 * 
 */

/**
 * @author Che Coxshall
 * @login ccoxshall@gmail.com
 * @date 12 Feb 2013
 */
public class DequeRollingArray<T> implements Iterable<T> {

    /**
     * The internal storage array
     */
    private T[] tArray;
    /**
     * The count of populated list items
     */
    private int size = 0;
    /**
     * The index of the item at the tail of the list
     */
    private int tailIndex = 0;

    /**
     * Creates a new, empty deque
     */
    public DequeRollingArray() {

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

        if (tArray == null) {
            createAndAdd(item);
        } else {
            int firstIndex = getNextEmptyIndex();
            tArray[firstIndex] = item;
            size++;
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

        if (tArray == null) {
            createAndAdd(item);
        } else {
            int nextIndex = getPrevEmptyIndex();
            tArray[nextIndex] = item;
            size++;
        }
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

        int firstIndex = getFirstItemIndex();
        T item = tArray[firstIndex];
        tArray[firstIndex] = null;
        size--;

        checkIfResizeRequired();
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

        T item = tArray[tailIndex];
        tArray[tailIndex++] = null;
        size--;

        if (tailIndex == tArray.length) {
            tailIndex = 0;
        }

        checkIfResizeRequired();
        return item;
    }

    /**
     * An iterator that iterates from the front to the end of the deque
     */
    public Iterator<T> iterator() {

        return new Iterator<T>() {

            int index = getFirstItemIndex();
            int lastIndex = tailIndex;
            boolean hitTail = false;

            @Override
            public boolean hasNext() {
                return !hitTail;
            }

            @Override
            public T next() {
                if (hitTail) {
                    throw new java.util.NoSuchElementException();
                }

                if (index < 0 && lastIndex > 0) {
                    index = tArray.length - 1;
                }

                if (index == lastIndex) {
                    hitTail = true;
                }

                return tArray[index--];
            }

            @Override
            public void remove() {
                throw new java.lang.UnsupportedOperationException();
            }
        };

    }

    /**
     * Creates the array and adds a single item to it.
     * 
     * @param item
     *            Item to add to the newly created array.
     */
    @SuppressWarnings("unchecked")
    private void createAndAdd(T item) {
        tArray = (T[]) new Object[1];
        tArray[0] = item;
        size++;
    }

    /**
     * Gets the index of the item that is first in the queue.
     * 
     * @return index of the item that is first in the queue.
     */
    private int getFirstItemIndex() {
        int firstIndex = tailIndex + (size - 1);
        if (firstIndex > tArray.length - 1) {
            firstIndex = firstIndex - tArray.length;
        }
        return firstIndex;
    }

    /**
     * Gets the index of the next empty space in the array at the end of the
     * queue. This method will invoke a resize operation if there is no empty
     * space.
     * 
     * @return index of the next empty space in the array at the end of the
     *         queue.
     */
    private int getNextEmptyIndex() {

        checkIfResizeRequired();

        int index = tailIndex + size;

        if (index < tArray.length) {
            return index;
        }

        // loop to start of array
        return index - tArray.length;
    }

    /**
     * Gets the index of the next empty space in the array at the beginning of
     * the queue. This method will invoke a resize operation if there is no
     * empty space.
     * 
     * @return index of the next empty space in the array at the beginning of
     *         the queue.
     */
    private int getPrevEmptyIndex() {

        if (size == tArray.length) {
            resizeArray(size << 1);
        }

        tailIndex--;

        if (tailIndex < 0) {
            tailIndex = tArray.length - 1;
        }

        return tailIndex;

    }

    /**
     * Checks to see if an array resize is required and performs it if so.
     */
    private void checkIfResizeRequired() {

        if (size == tArray.length) {
            resizeArray(tArray.length << 1); // double the size
        }
        if (size <= tArray.length / 4) {
            resizeArray(tArray.length >> 1); // halve the size
        }
    }

    /**
     * Creates a new array of the specified size and copies all elements into
     * it.
     * 
     * @param newSize
     *            The new size of the array;
     */
    @SuppressWarnings("unchecked")
    private void resizeArray(int newSize) {

        int newTail = (newSize - size) % 2 == 0 ? (newSize - size) / 2
                : ((newSize - size) / 2) - 1;

        if (newTail < 0) {
            newTail = 0;
        }

        T[] swapArray = (T[]) new Object[newSize];

        int size2 = size;
        int tail2 = tailIndex;
        int i = 0;
        // iterate through items, adding to the new array
        while (size2 > 0) {
            swapArray[newTail + i++] = tArray[tail2++];
            if (tail2 > tArray.length - 1) {
                tail2 = 0;
            }
            size2--;
        }

        tailIndex = newTail;
        tArray = swapArray;
    }
}
