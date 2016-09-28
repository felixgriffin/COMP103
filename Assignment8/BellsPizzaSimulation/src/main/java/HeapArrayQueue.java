// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP103 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP 103, Assignment 8
 * Name: Shaun Sinclair
 * Usercode: sinclashau
 * ID: 300383795
 */

import java.util.AbstractQueue;
import java.util.Iterator;

/**
 * Implements a priority queue based on a heap that is
 * represented with an array.
 */
class HeapArrayQueue<E extends Comparable<? super E>> extends AbstractQueue<E> {

    @SuppressWarnings("unchecked")
    private E[] data = (E[]) (new Comparable[7]);
    private int count = 0;

    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns the element with the top priority in the queue.
     * <p>
     * HINT: This is like 'poll()' without the removal of the element.
     *
     * @returns the next element if present, or 'null' if the queue is empty.
     */
    public E peek() {
        return data[1];
    }

    /**
     * Removes the element with the top priority from the queue and returns it.
     * <p>
     * HINT: The 'data' array should contain a heap so the element with the top priority
     * sits at index '0'. After its removal, you need to restore the heap property again,
     * using 'sinkDownFromIndex(...)'.
     *
     * @returns the next element in the queue, or 'null' if the queue is empty.
     */
    public E poll() {
        E temp = data[1];
        data[1] = data[count];
        count--;
        sinkDownFromIndex(1);
        return temp;
    }

    /**
     * Enqueues an element.
     * <p>
     * If the element to be added is 'null', it is not added.
     * <p>
     * HINT: Make use of 'ensureCapacity' to make sure that the array can
     * accommodate one more element.
     *
     * @param element - the element to be added to the queue
     * @returns true, if the element could be added
     */
    public boolean offer(E element) {
        if (element == null) {
            return false;
        }
        count++;
        ensureCapacity();
        data[count] = element;
        bubbleUpFromIndex(count);
        return true;
    }

    private void sinkDownFromIndex(int nodeIndex) {
        while (true) {
            int max = getChild(nodeIndex);
            if (max == -1) return;
            if (data[max].compareTo(data[nodeIndex]) < 0) {
                swap(max, nodeIndex);
                nodeIndex = max;
            } else {
                return;
            }
        }
    }

    private int getChild(int idx) {
        int left = idx * 2;
        int right = idx * 2 + 1;
        if (left >= data.length || data[left] == null) return -1;
        if (right >= data.length || data[right] == null) return left;
        if (data[left].compareTo(data[right]) > 0) return left;
        return right;
    }

//    private void bubbleUpFromIndex(int nodeIndex) {
//        int parent = getParent(nodeIndex);
//        while(data[nodeIndex].compareTo(data[parent]) < 0) {
//            swap(nodeIndex,parent);
//            nodeIndex = parent;
//            parent = getParent(nodeIndex);
//        }
//    }

    private void bubbleUpFromIndex(int nodeIndex) {
        E parent = data[getParent(nodeIndex)];
        E child = data[nodeIndex];
        while (getParent(nodeIndex) >= 1) {
            if (parent.compareTo(child) > 0) {
                swap(nodeIndex, getParent(nodeIndex));
                nodeIndex = nodeIndex / 2;
            } else {
                return;
            }
        }
    }

    private int getParent(int nodeIndex) {
        if (nodeIndex == 1)return 1;
        else return nodeIndex / 2;
    }

    /**
     * Swaps two elements in the supporting array.
     */
    private void swap(int from, int to) {
        E temp = data[from];
        data[from] = data[to];
        data[to] = temp;
    }

    /**
     * Increases the size of the supporting array, if necessary
     */
    private void ensureCapacity() {
        if (count == data.length) {
            @SuppressWarnings("unchecked")
            E[] newData = (E[]) new Comparable[data.length * 2];

            // copy data elements
            System.arraycopy(data, 0, newData, 0, count);
            data = newData;
        }
    }

    // no iterator implementation required for this assignment
    @SuppressWarnings("ConstantConditions")
    public Iterator<E> iterator() {
        return null;
    }
}
