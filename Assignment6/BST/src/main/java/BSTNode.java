// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP103 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP 103, Assignment 6
 * Name:
 * Usercode:
 * ID:
 */

import java.io.*;

import ecs100.*;

/**
 * Implements a binary search tree node.
 *
 * @author: Thomas Kuehne (based on previous code)
 */

class BSTNode<E extends Comparable<E>> {

    private E value;
    private BSTNode<E> left;
    private BSTNode<E> right;

    // constructs a node with a value
    BSTNode(E value) {
        this.value = value;
    }

    // Getters...

    public E getValue() {
        return value;
    }

    private BSTNode<E> getLeft() {
        return left;
    }

    private BSTNode<E> getRight() {
        return right;
    }

    /**
     * Returns true if the subtree formed by the receiver contains 'item'
     * <p>
     * CORE
     * <p>
     * ASSUMPTION: 'item' is not 'null'.
     * <p>
     * HINT: A recursive approach leads to a very short and simple code.
     * <p>
     * HINT: use 'compareTo(...)' in order to compare the parameter
     * with the data in the node.
     * <p>
     * HINT: Make sure that you invoke 'compareTo' by always using
     * the same receiver / argument ordering throughout the program, e.g.,
     * always use the item as the receiver of 'compareTo'.
     *
     * @param item - the item to check for
     * @returns true if the subtree contained 'item'
     */
    boolean contains(E item) {
        if (item == null) return false;
        if (this.value.equals(item)) {
            return true;
        }
        if (this.value.compareTo(item) < 0) {
             return left != null && left.contains(item);
        } else {
            return right != null && right.contains(item);
        }
    }

    /**
     * Adds an item to the subtree formed by the receiver.
     * <p>
     * CORE
     * <p>
     * Must not add an item, if it is already in the tree.
     * <p>
     * HINT: use 'compareTo(...)' in order to compare the parameter
     * with the data in the node.
     *
     * @param item - the value to be added
     * @returns false, if the item was in the subtree already. Returns true otherwise.
     */
    boolean add(E item) {
        if (this.value.equals(item)) {
            return false;
        }
        if (this.value.compareTo(item) < 0) {
            if (getLeft() == null) {
                left = new BSTNode<>(item);
            } else {
                return left.add(item);
            }
        } else {
            if (getRight() == null) {
                right = new BSTNode<>(item);
            } else {
                return right.add(item);
            }
        }
        return true;
    }

    /**
     * Returns the height of the receiver node.
     * <p>
     * CORE
     * <p>
     * HINT: The number of children the receiver node may have, implies
     * four cases to deal with (none, left, right, left & right).
     *
     * @returns the height of the receiver
     */
    int height() {
        int countL = 0, countR = 0;
        if (left == null && right == null) return 0;
        if (left != null) {
            countL = left.height()+1;
        }
        if (right != null) {
            countR = right.height()+1;
        }
        return Math.max(countL, countR);
    }

    /**
     * Returns the length of the shortest branch in the subtree formed by the receiver.
     * <p>
     * COMPLETION
     *
     * @returns the minimum of all branch lengths starting from the receiver.
     */
    int minDepth() {
        int countL = Integer.MAX_VALUE, countR = Integer.MAX_VALUE;
        if (getLeft() == null && getRight() == null) return 0;
        if (getLeft() != null) {
            countL = left.minDepth()+1;
        }
        if (getRight() != null) {
            countR = right.minDepth()+1;
        }
        return Math.min(countL, countR);
    }

    /**
     * Removes an item in the subtree formed by the receiver.
     * <p>
     * COMPLETION
     * <p>
     * ASSUMPTION: The item to be removed does exist.
     * The case that it cannot be found, should be dealt with before this method is called.
     * <p>
     * Performs two tasks:
     * 1. locates the node to be removed, and
     * 2. replaces the node with a suitable node from its subtrees.
     * <p>
     * HINT: use 'compareTo(...)' in order to compare the parameter
     * with the data in the node.
     * <p>
     * HINT: For task 2, you should use call method 'replacementSubtreeFromChildren'
     * to obtain this node.
     * <p>
     * HINT: When replacing a node, it is sufficient to change the value of the existing node
     * with the value of the node that conceptually replaces it. There is no need to actually
     * replace the node object as such.
     *
     * @param item - the item to be removed
     * @returns the reference to the subtree with the item removed.
     * <p>
     * HINT: Often the returned reference will be the receiver node, but it is possible that
     * the receiver itself needs to be removed. If you use a recursive approach, the
     * latter case is the base case.
     */
    BSTNode<E> remove(E item) {
        if (this.value.equals(item)) {
            return replacementSubtreeFromChildren();
        } else if (this.value.compareTo(item) < 0) {
            left = left.remove(item);
        } else {
            right = right.remove(item);
        }
        // there was no need to replace the receiver node
        return this;
    }

    /**
     * Returns a replacement subtree for the receiver node (which is to be removed).
     * <p>
     * COMPLETION
     * <p>
     * The replacement subtree is determined from the children of the node to be removed.
     * <p>
     * HINT: There are several cases:
     * - node has no children    => return null
     * - node has only one child => return the child
     * - node has two children   => return the current subtree but with
     * a) its (local) root replaced by the leftmost node in the right subtree, and
     * b) the leftmmost node in the right subtree removed.
     *
     * @returns a reference to a subtree which contains all items from 'left' and 'right' combined.
     */
    private BSTNode<E> replacementSubtreeFromChildren() {
        if (left == null && right == null) return null;
        if (left != null && right != null) {
            E value = right.getLeftmostNode().value;
            remove(value);
            this.value = value;
            return this;
        }
        if (left != null) return left;
        return right;
    }

    /**
     * Returns the leftmost node in the subtree formed by the receiver.
     * <p>
     * COMPLETION
     * <p>
     * HINT: The code is very simple. Just keep descending left branches,
     * until it is no longer possible.
     *
     * @returns a reference to the leftmost node, starting from the receiver.
     */
    private BSTNode<E> getLeftmostNode() {
        if (getLeft() == null) {
            return this;
        }
        return getLeft().getLeftmostNode();

    }

    /**
     * Prints all the nodes in a subtree to a stream.
     *
     * @param stream - the output stream
     */
    void printAllToStream(PrintStream stream) {
        if (left != null)
            left.printAllToStream(stream);

        stream.println(value);

        if (right != null)
            right.printAllToStream(stream);
    }

    /**
     * Prints all the nodes in a subtree on the text pane.
     * <p>
     * Can be useful for debugging purposes, but
     * is most useful on small sample trees.
     * <p>
     * Usage: node.printAll("").
     */
    void printAll(String indent) {
        if (right != null)
            right.printAll(indent + "    ");

        UI.println(indent + value);

        if (left != null)
            left.printAll(indent + "    ");
    }
}
