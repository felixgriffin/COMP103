// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP103 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP 103, Assignment 9
 * Name:
 * Usercode:
 * ID:
 */

import java.util.*;

/**
 * ArrayQueue - a Queue collection;
 * <p>
 * THIS VERSION IS BROKEN - IT HAS SEVERAL ERRORS.
 * YOU NEED TO WRITE A TEST CLASS TO IDENTIFY THE ERRORS
 * <p>
 * The implementation uses an array to store the items.
 * It adds items at the back of the queue and removes them from the front
 * Removing items will leave space at the front of the array.
 * <p>
 * It has two fields to record the front and the back of the queue:
 * front is the index of the item at the front of the queue
 * back is the index of the place where the NEXT item will be added
 * (ie, one beyond the last item in the queue)
 * When front == back, then the queue is empty.
 * For example, if the queue currently has 3 items, A, B, and C,
 * The fields might be:
 * data:  | - | - | A | B | C | - | - | - |
 * 0   1   2   3   4   5   6   7
 * front: 2
 * back:  5
 * If we then called offer("D"), it should be:
 * data:  | - | - | A | B | C | D | - | - |
 * 0   1   2   3   4   5   6   7
 * front: 2
 * back:  6
 * If we then called poll(), it should return A and be:
 * data:  | - | - | - | B | C | D | - | - |
 * 0   1   2   3   4   5   6   7
 * front: 3
 * back:  6
 * With three more calls to poll(), it should be empty:
 * data: | - | - | - | - | - | - | - | - |
 * 0   1   2   3   4   5   6   7
 * front: 6
 * back:  6
 * <p>
 * When back gets to the end of the array, it will make room for more items.
 * If there is enough room at the beginning of the array (head > 1/2 array length)
 * it will just shift all the items back to the beginning of the array.
 * For example, if we called offer("G") in the following state:
 * data:  | - | - | - | - | - | D | E | F |
 * 0   1   2   3   4   5   6   7
 * front: 5
 * back:  8
 * It should move all the items down within the array, and then add "G":
 * data:  | D | E | F | G | - | - | - | - |
 * 0   1   2   3   4   5   6   7
 * front: 0
 * back:  4
 * Otherwise, it will create a new array of double the
 * current size, and copy all the items over to the new array,
 * <p>
 * ArrayQueue extends AbstractQueue, so it only needs to implement
 * size(),
 * peek(),
 * poll(),
 * offer(), and
 * iterator().
 */

public class ArrayQueue<E> extends AbstractQueue<E> {

    private E[] data;
    private int front = 0;    //the index of the first item in the queue
    private int back = 0;   //the index where the next new item will go
    // items are stored from front..back-1
    // if front == back, then the queue is empty.

    @SuppressWarnings("unchecked")
        // this will stop Java complaining
    ArrayQueue() {
        int InitialCapacity = 16;
        data = (E[]) new Object[InitialCapacity];
    }

    /**
     * Return the number of items in collection
     */
    public int size() {
        return back - front;
    }

    /**
     * Return the item at the front of the queue,
     * or null if the queue is empty, but does not change the queue
     */
    public E peek() {
        return isEmpty() ? null : data[front];
    }

    /**
     * Remove and return the item at the front of the queue,
     * or null if the queue is empty
     */
    public E poll() {
        if (isEmpty()) return null;
        return data[front++];
    }

    /**
     * Add an item onto the back for the queue, unless the item is null
     * If the item was added, it returns true,
     * if the item was null, it returns false
     */
    public boolean offer(E item) {
        if (item == null) return false;
        ensureCapacity();
        data[back] = item;
        back += 1;


        return true;

    }

    /**
     * Ensure data array has sufficient room to add a new item
     */
    @SuppressWarnings("unchecked") // this will stop Java complaining
    private void ensureCapacity() {
        if (back == data.length) {  // there is no room to add the next item
            // if we are at least 1/2 empty just shift items down 
            if (front > data.length / 2) {
                int j = 0;
                for (int i = front; i < back; i++) {
                    j++;
                    data[j] = data[i];
                }

                back = back - front;     // reset back and front.
                front = 0;
            } else { // need to get a bigger array
                E[] newArray = (E[]) (new Object[data.length * 2]);
                int j = 0;
                for (int i = front; i < back; i++) {
                    newArray[j++] = data[i];
                }
                back = back - front;     // reset back and front.
                front = 0;
                data = newArray;
            }
        }
    }

    /**
     * Return an iterator over the items in the list
     */
    public Iterator<E> iterator() {
        return new ArrayQueueIterator<>(this);
    }

    /**
     * Iterator for ArrayQueue.
     * This implementation is not smart, and may be corrupted if
     * any changes are made to the ArrayQueue that it is iterating down.
     * Note that because it is an inner class, it has access to the
     * ArrayQueue's private fields.
     */
    private static class ArrayQueueIterator<E> implements Iterator<E> {
        // needs fields, constructor, hasNext(), next(), and remove()

        private ArrayQueue<E> queue; // reference to the list it is iterating down
        private int nextIndex;       // the index of the next item the iterator will return

        private ArrayQueueIterator(ArrayQueue<E> q) {
            queue = q;
            nextIndex = q.front;
        }

        /**
         * Return true if iterator has at least one more item
         */
        public boolean hasNext() {
            return (nextIndex < queue.back);
        }

        /**
         * Return next item in the set
         */
        public E next() {
            if (nextIndex == queue.back)
                throw new NoSuchElementException();

            E item = queue.data[nextIndex];
            nextIndex = (nextIndex + 1);

            return item;
        }

        /**
         * Remove from the queue the last item returned by the iterator.
         * The queue does not permit this operation
         */
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
