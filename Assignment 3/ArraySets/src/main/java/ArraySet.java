import java.util.*;

/**
 * ArraySet - a Set collection;
 * <p>
 * The implementation uses an array to store the items
 * and a count variable to store the number of items in the array.
 * <p>
 * The items in the set should be stored in positions
 * 0, 1,... (count-1) of the array.
 * <p>
 * The length of the array when the set is first created should be 10.
 * <p>
 * The items need not be in any particular order, and may change the
 * order when an item is removed. There is no need to shift all the items
 * up or down to keep them in a specific order.
 * <p>
 * Note that a set does not allow null items or duplicates.
 * Attempting to add null should throw an IllegalArgumentException exception
 * Adding an item which is already present should simply return false, without
 * changing the set.
 * It should always compare items using equals()  (not using ==)
 * When the array is full, it will create a new array of double the current
 * size, and copy all the items over to the new array
 */

public class ArraySet<E> extends AbstractSet<E> {

    // Data fields
    private int count = 0;
    private Object[] data;
    // --- Constructors --------------------------------------

    @SuppressWarnings("unchecked")
        // this will stop Java complaining
    ArraySet() {
        data = new Object[10];

    }

    // --- Methods --------------------------------------

    /**
     * @return the number of items in the set
     */
    public int size() {
        return count;
    }

    /**
     * Adds the specified item to this set
     * (if it is not already in the set).
     * Will not add a null value (throws an IllegalArgumentException in this case).
     *
     * @param item the item to be added to the set
     * @return true if the collection changes, and false if it did not change.
     */
    public boolean add(E item) {
        if (item == null) {throw new IllegalArgumentException("Cannot add null to arraySet");}
        if (contains(item)) {
            System.out.println("test");
            return false;
        }
        ensureCapacity();
        data[count++] = item;
        System.out.println(Arrays.toString(data));
        return true;
    }

    /**
     * @return true if this set contains the specified item.
     */
    public boolean contains(Object item) {
        return indexOf(item) >= 0;
    }

    /**
     * Removes an item matching a given item.
     *
     * @return true if the item was present and then removed.
     * Makes no changes to the set and returns false if the item is not present.
     */
    public boolean remove(Object item) {
        int i = indexOf(item);
        if (i < 0) {
            return false;
        }
        count--;
        data[i] = data[count];
        data[count] = null;
        return true;
    }

    /**
     * Ensures data array has sufficient capacity (length)
     * to accommodate a new item
     */
    @SuppressWarnings("unchecked")  // this will stop Java complaining
    private void ensureCapacity() {
        if (count + 1 > data.length) {
            data = Arrays.copyOf(data, data.length * 2);
        }
    }

    // You may find it convenient to define the following method and use it in
    // the methods above, but you don't need to do it this way.

    /**
     * Finds the index of an item in the data.
     * Assumes that the item is not null.
     *
     * @return the index of the item, or -1 if not present
     */
    private int indexOf(Object item) {
        if (item == null) {
            return -1;
        }
        for (int i = 0; i < count; i++) {
            if (item.equals(data[i])) {
                return i;
            }
        }
        return -1;
    }

    /* ---------- The code below is already written for you ---------- **/

    /**
     * @return an iterator over the items in this set.
     */
    public Iterator<E> iterator() {
        return new ArraySetIterator<>(this);
    }

    private class ArraySetIterator<D> implements Iterator<D> {

        // needs fields, constructor, hasNext(), next(), and remove()

        // fields
        private ArraySet<D> set;
        private int nextIndex = 0;
        private boolean canRemove = false;

        // constructor
        private ArraySetIterator(ArraySet<D> s) {
            set = s;
        }

        /**
         * @return true if iterator has at least one more item
         */
        public boolean hasNext() {
            return (nextIndex < set.count);
        }

        /**
         * Returns the next element or throws a
         * NoSuchElementException exception if none exists.
         *
         * @return next item in the set
         */
        @SuppressWarnings("unchecked")
        public D next() {
            if (nextIndex >= set.count)
                throw new NoSuchElementException();

            canRemove = true;
            return (D) set.data[nextIndex++];
        }

        /**
         * Removes the last item returned by the iterator from the set.
         * Can only be called once per call to next.
         */
        public void remove() {
            if (!canRemove)
                throw new IllegalStateException();

            nextIndex--;
            set.count--;
            set.data[nextIndex] = set.data[set.count];
            set.data[set.count] = null;
            canRemove = false;
        }
    }
}

