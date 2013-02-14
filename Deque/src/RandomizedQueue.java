import java.util.Iterator;

/**
 * @author Che Coxshall
 * @login ccoxshall@gmail.com
 * @date 13 Feb 2013
 * 
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    /**
     * The internal storage array
     */
    private Item[] tArray;
    /**
     * The count of populated list items
     */
    private int size;

    /**
     * Creates a new, empty RandomizedQueue<T>
     */
    public RandomizedQueue() {

    }

    /**
     * Indicates if the queue is empty.
     * 
     * @return True if the queue is empty.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Return the number of items on the queue.
     * 
     * @return The number of items on the queue.
     */
    public int size() {
        return size;
    }

    /**
     * Add an item.
     * 
     * @param item
     *            Item to add.
     */
    public void enqueue(Item item) {
        if (item == null) {
            throw new java.lang.NullPointerException("Cannot add null items.");
        }

        if (tArray == null) {
            createArrayAndAddItem(item);
            return;
        }

        // Randomise on insert - saves headaches later!
        int index = StdRandom.uniform(++size);
        checkIfResizeRequired();
        tArray[size - 1] = tArray[index];
        tArray[index] = item;
    }

    /**
     * Delete and return a random item.
     * 
     * @return a random item.
     */
    public Item dequeue() {
        if (size == 0) {
            throw new java.util.NoSuchElementException();
        }

        Item item = tArray[--size];
        tArray[size] = null;
        checkIfResizeRequired();
        return item;
    }

    /**
     * Return a random item;
     * 
     * @return A random item.
     */
    public Item sample() {
        if (size == 0) {
            throw new java.util.NoSuchElementException();
        }
        int rnd = StdRandom.uniform(size);
        return tArray[rnd];
    }

    /**
     * Return an independent iterator over items in random order.
     * 
     * @return An independent iterator over items in random order.
     */
    @Override
    public Iterator<Item> iterator() {

        return new RndIterator(tArray, size);

    }

    /**
     * An independent random iterator implementation
     * 
     */
    private class RndIterator implements Iterator<Item> {

        private Item[] items;
        private int size;
        private int index;

        /**
         * Creates a new iterator utilising the object array and queue size.
         * 
         * @param m_items
         *            Object array.
         * @param m_size
         *            Queue size.
         */
        @SuppressWarnings("unchecked")
        public RndIterator(Item[] m_items, int m_size) {
            items = (Item[]) new Object[m_size];
            size = m_size;

            for (int i = 0; i < m_size; i++) {
                items[i] = m_items[i];
            }

            StdRandom.shuffle(items);
        }

        /**
         * Indicates if there is another item to be consumed.
         */
        @Override
        public boolean hasNext() {
            return index < size;
        }

        /**
         * Gets the next item to be consumed.
         */
        @Override
        public Item next() {
            if (index == size) {
                throw new java.util.NoSuchElementException();
            }

            return items[index++];
        }

        @Override
        public void remove() {
            throw new java.lang.UnsupportedOperationException();

        }

    }

    @SuppressWarnings("unchecked")
    private void createArrayAndAddItem(Item item) {
        tArray = (Item[]) new Object[2];
        tArray[0] = item;
        size++;
    }

    /**
     * Checks to see if an array resize is required and performs it if so.
     */
    private void checkIfResizeRequired() {

        if (size > tArray.length) {
            resizeArray(tArray.length << 1); // double the size
        }
        if (size <= tArray.length / 4 && tArray.length > 2) {
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
        Item[] swapArray = (Item[]) new Object[newSize];

        for (int i = 0; i < (newSize < tArray.length ? size : size - 1); i++) {
            swapArray[i] = tArray[i];
        }

        tArray = swapArray;
    }
}
