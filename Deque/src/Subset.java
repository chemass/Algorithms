/**
 * 
 */

/**
 * @author Che Coxshall
 * @login ccoxshall@gmail.com
 * @date 14 Feb 2013
 * 
 */
public class Subset {

    /**
     * @param args
     */
    public static void main(String[] args) {

        RandomizedQueue<String> queue = new RandomizedQueue<String>();

        int k = Integer.parseInt(args[0]);

        while (!StdIn.isEmpty()) {
            String input = StdIn.readString();
            if (input.equals("|")) {
                break;
            }
            queue.enqueue(input);
        }

        for (int i = 0; i < k; i++) {
            StdOut.println(queue.dequeue());
        }
    }

}
