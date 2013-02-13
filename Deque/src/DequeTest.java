import static org.junit.Assert.*;

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
        if(!dq.isEmpty())
            fail("Reporting not empty on creation.");
        
        dq.addFirst("first");
        
        if(dq.isEmpty())
            fail("Reporting empty after adding item.");
        
        dq = null;
    }

    @Test
    public final void testSize() {
        Deque<String> dq = new Deque<String>();
        if(dq.size() > 0)
            fail("Reporting size of " + dq.size() + " on creation.");
        
        dq.addFirst("first");
        
        if(dq.size() == 0)
            fail("Reporting size of 0 after adding item.");
        
        dq = null;
    }

    @Test
    public final void testAddFirst() {
        Deque<String> dq = new Deque<String>();        
        dq.addFirst("first");
        if(dq.size() == 0)
            fail("Reporting size of 0 after adding item first.");
    }

    @Test
    public final void testAddLast() {
        Deque<String> dq = new Deque<String>();        
        dq.addLast("first");
        if(dq.size() == 0)
            fail("Reporting size of 0 after adding item last.");
    }

    @Test
    public final void testRemoveFirst() {
        Deque<String> dq = new Deque<String>();       
        dq.addFirst("second");       
        dq.addLast("third");        
        dq.addFirst("first");
        String first =dq.removeFirst();
        if(first != "first"){
            fail("wrong result on RemoveFirst. Expected 'first', got '" + first + "'." );
        }
    }

    @Test
    public final void testRemoveLast() {
        Deque<String> dq = new Deque<String>();    
        dq.addFirst("second");       
        dq.addLast("third");        
        dq.addFirst("first");
        String last =dq.removeLast(); 
        if(last != "last"){
            fail("wrong result on RemoveLast. Expected 'third', got '" + last + "'." );
        }
    }

    @Test
    public final void testIterator() {

    }

}
