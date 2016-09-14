// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP103 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP 103, Assignment 6
 * Name:
 * Usercode:
 * ID:
 */

import java.util.*;
import java.io.*;

import ecs100.*;

/**
 * Implementation of a Set type using a Binary Search Tree to store
 * the items.
 * <p>
 * The Set does not allow null elements and obviously no duplicates.
 * <p>
 * Attempting to add null should throw an exception.
 * An attempt at adding an element which is already present
 * should simply return false, without changing the set.
 *
 * @author: Thomas Kuehne (based on previous code)
 */
public class BSTSet<E extends Comparable<E>> extends AbstractSet<E> {

    // the root of the supporting binary search tree
    private BSTNode<E> root;

    // number of elements in the set
    private int count = 0;

    /**
     * Returns true if the set is empty
     */
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * Returns the number of elements in set
     */
    public int size() {
        return count;
    }



    /**
     * Returns true if the set contains 'item'
     * <p>
     * CORE
     * <p>
     * HINT: There is no need to search for 'null' arguments.
     * <p>
     * HINT: After checking special cases, delegate the work to the root
     * node of the supporting tree.
     *
     * @param item - the item to check for
     */
    boolean contains(E item) throws ClassCastException {
        return root != null && root.contains(item);
    }

    /**
     * Adds the specified element to the set, as long as it is not
     * null or is not already in the set.
     * <p>
     * CORE
     * <p>
     * HINT: Remember to update the count field as well.
     * <p>
     * HINT: You can check whether the item exists already in an extra step
     * or perform the checking while attempting to add the item. The latter is
     * more efficient.
     *
     * @param item - the item to be added
     * @returns true if the collection changes, and false if it does not change.
     */
    public boolean add(E item) {
        if (item == null) {
            throw new IllegalArgumentException("Null cannot be added to a BSTSet");
        }
        if (root == null) {
            count++;
            root = new BSTNode<>(item);
            return true;
        }
        if (root.add(item)) {
            count++;
            return true;
        }
        return false;
    }

    /**
     * Returns the height of the tree.
     * <p>
     * CORE
     * <p>
     * HINT: You probably want to make use of method 'height()' in
     * class BSTNode.
     *
     * @returns the height of the tree
     * -1 if the tree is empty,
     * 0 if there is just a root node, and
     * greater than 0 in all other cases.
     */
    int height() {
        if (root == null) {
            return -1;
        }
        return root.height();
    }

    /**
     * Returns the minimum depth of the tree.
     * <p>
     * COMPLETION
     * <p>
     * HINT: You probably want to make use of method 'minDepth()' in
     * class BSTNode.
     *
     * @returns the length of the shortest branch in the tree
     * -1 if the tree is empty,
     * 0 if there is just a root node, and
     * greater than 0 in all other cases.
     */
    int minDepth() {
        if (root == null) {
            return -1;
        }
        return root.minDepth();
    }

    /**
     * Removes the element matching a given item.
     * <p>
     * COMPLETION
     * <p>
     * Note that any children of the removed node must be kept in the tree.
     * <p>
     * HINT: Remember to update the count field, if necessary.
     * HINT: A null item need not be removed.
     * HINT: You may use 'remove(...)' from class BSTNode as a helper,
     * but you do not have to.
     *
     * @param item - the item to remove
     * @returns true if the collection changes, and false if it did not change.
     */
    boolean remove(E item) {
        /*# YOUR CODE HERE */

        return false;
    }

    /**
     * @returns an iterator over the elements in this set.
     */
    public Iterator<E> iterator() {
        // start a new iteration on the root node
        return new BSTSetIterator(root);
    }

    /**
     * Iterator for BST.
     * <p>
     * CHALLENGE
     * <p>
     * Iterates through the tree in sorted order, i.e. using in-order traversal.
     * Uses a stack to keep track of the current position (iteration state).
     * <p>
     * HINTS:
     * To accomplish this, the iterator must process
     * the complete left subtree of a node before it processes the node.
     * Therefore, every time it pushes a node on the stack, it can
     * also immediately push all the left descendents of the node on
     * to the stack, all the way to the leftmost descendent.
     * <p>
     * When it pops a node from the stack, it processes the node and then
     * pushes its right child onto the stack (and all that child's
     * left descendents).
     * <p>
     * There is a dedicated video on Blackboard
     * -- "Comp103, Iterative In-Order Traversal, Variant 2" --
     * explaining this particular approach.
     * <p>
     * You may choose another approach as well.
     */
    private class BSTSetIterator implements Iterator<E> {

        Stack<BSTNode<E>> nodeStack = new Stack<BSTNode<E>>();

        /**
         * Creates an iterator.
         * <p>
         * CHALLENGE
         * <p>
         * HINT: Populate the 'to do' stack as much as necessary already.
         */
        public BSTSetIterator(BSTNode<E> root) {
            /*# YOUR CODE HERE */

        }

        /**
         * Returns whether there is still something to iterate
         * <p>
         * CHALLENGE
         */
        public boolean hasNext() {
            /*# YOUR CODE HERE */

            return false;  // DOESN'T WORK - Just to make this stub compile
        }

        /**
         * Returns the next element of the current iteration.
         * <p>
         * CHALLENGE
         * <p>
         * HINT: Remove an element from the 'to do' stack and return it, but before
         * returning, make sure you add new elements to the stack, if appropriate.
         */
        public E next() {
            /*# YOUR CODE HERE */

            return null;  // DOESN'T WORK - Just to make this stub compile
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /**
     * Prints all the elements to a stream.
     *
     * @param stream - the output stream
     */
    void printAllToStream(PrintStream stream) {
        // if there is nothing to do...
        if (isEmpty())
            return;

        // traverse tree, passing on the output stream
        root.printAllToStream(stream);
    }

    public static void main(String[] args) {
        testing();
    }

    private static void testing() {
        BSTSet<String> testBST = new BSTSet<String>();

        String[] testItems = new String[]{"N", "G", "T", "C", "W", "O", "K", "U", "V", "E", "Y", "Z", "S", "A", "M", "P", "R", "I", "L", "F", "J", "B", "D", "H", "X", "Q"};

        for (String item : testItems) {
            UI.println("Adding " + item);
            testBST.add(item);
            UI.println("----------------------------");

            if (testBST.isEmpty())
                UI.println("Empty Tree");
            else
                testBST.printAll();

            UI.println("Height: " + testBST.height());
            UI.println("Min Depth: " + testBST.minDepth());
            UI.println("----------------------------");
        }
    }

    public void printAll() {
        root.printAll("");
    }
}
