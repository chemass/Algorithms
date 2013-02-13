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
     * The internal storage array
     */
    @SuppressWarnings("unchecked")
    private T[] tArray    = (T[]) new Object[1];
    /**
     * The index of the item at the head of the list
     */
    private int headIndex = 0;
    /**
     * The index of the item at the tail of the list
     */
    private int tailIndex = 0;

    public Deque() {

    }

    public boolean isEmpty() {
        return headIndex == tailIndex;
    }

    public int size() {
        return headIndex <= tailIndex && tailIndex != 0 ? (tArray.length - tailIndex) + headIndex : headIndex - tailIndex;
    }

    public void addFirst(T item) {
        int firstIndex = headIndex == 0 && tailIndex == 0 ? headIndex++ : getNextIndex();
        tArray[firstIndex] = item;
    }

    private int getNextIndex() {

        if (size() == tArray.length) {
            resizeArray(size() << 1);
        }

        if (headIndex < tArray.length) {
            return headIndex++;
        }

        // loop to start of array
        headIndex = 0;
        return 0;
    }

    private int getPrevIndex() {

        if (size() == tArray.length) {
            resizeArray(size() << 1);
        }

        if (tailIndex > 0) {
            return tailIndex--;
        }
        // loop to start of array
        tailIndex = tArray.length - 1;
        return tailIndex;

    }

    @SuppressWarnings("unchecked")
    private void resizeArray(int newSize) {

        int newTail = (newSize - tArray.length) % 2 == 0 ? (newSize - tArray.length) / 2
                : ((newSize - tArray.length) / 2) - 1;

        if(newTail < 0)         {
            newTail = 0;
        }
        T[] swapArray = (T[]) new Object[newSize];

        for (int i = newTail; i < newTail + (tArray.length); i++) {
            if (tailIndex > 0 && tailIndex >= headIndex) {
                swapArray[i] = tArray[tailIndex++];
            }
            if (tailIndex == tArray.length) {
                tailIndex = 0;
            }

        }

        tailIndex = newTail;
        headIndex = newTail + tArray.length;
        tArray = swapArray;
    }

    public void addLast(T item) {
        int nextIndex = headIndex == 0 && tailIndex == 0 ? headIndex++ : getPrevIndex();
        tArray[nextIndex] = item;
    }

    public T removeFirst() {
        T item = tArray[headIndex - 1];
        tArray[headIndex - 1] = null;
        if (headIndex == 0 && headIndex != tailIndex) {
            headIndex = tArray.length - 1;
        }
        if (size() < tArray.length / 4) {
            resizeArray(size() >> 1);
        }
        return item;
    }

    public T removeLast() {
        T item = tArray[tailIndex];
        tArray[tailIndex] = null;
        if (tailIndex == tArray.length - 1 && tailIndex != headIndex) {
            tailIndex = 0;
        }
        if (size() < tArray.length / 4) {
            resizeArray(size() >> 1);
        }
        return item;
    }

    public Iterator<T> iterator() {

        return new Iterator<T>() {

            @Override
            public boolean hasNext() {
                // TODO Auto-generated method stub
                return false;
            }

            @Override
            public T next() {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public void remove() {
                // TODO Auto-generated method stub

            }
        };

    }
}
