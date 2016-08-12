// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP103 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP 103, Assignment 4
 * Name: Shaun McLaren
 * Usercode: sinclashau
 * ID: 300383795
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class Images implements a list of images.
 * <p>
 * Each image is represented with an ImageNode object.
 * The ImageNode objects form a linked list.
 * <p>
 * An object of this class maintains the reference to the first image node and
 * delegates operations to image nodes as necessary.
 * <p>
 * An object of this class furthermore maintains a "cursor", i.e., a reference to a location in the list.
 * <p>
 * The references to both first node and cursor may be null, representing an empty collection.
 *
 * @author Thomas Kuehne
 */

class Images implements Iterable<String> {
    private ImageNode head;     // the first image node
    private ImageNode cursor;   // the current point for insertion, removal, etc.

    /**
     * Creates an empty list of images.
     */
    Images() {
        cursor = head = null;
    }

    /**
     * @return the fileName of the image at the current cursor position.
     * <p>
     * This method relieves clients of Images from knowing about image nodes and the 'getFileName()' method.
     */
    String getImageFileNameAtCursor() {
        // deal with an inappropriate call gracefully
        if (cursor == null)
            return "";  // the correct response would be to throw an exception.

        return cursor.getFileName();
    }

    /**
     * @return the current cursor position.
     * <p>
     * Used by clients that want to save the current selection in order to restore it after an iteration.
     */
    ImageNode getCursor() {
        return cursor;
    }

    /**
     * Sets the cursor to a new node.
     *
     * @param newCursor the new cursor position
     */
    void setCursor(ImageNode newCursor) {
        cursor = newCursor;
    }

    /**
     * Positions the cursor at the start
     * <p>
     * For the core part of the assignment.
     */
    void moveCursorToStart() {
        cursor = head;
    }

    /**
     * Positions the cursor at the end
     * <p>
     * For the core part of the assignment.
     * <p>
     * HINT: Consider the list could be empty.
     */
    void moveCursorToEnd() {
        while (cursor.getNext() != null) {
            cursor = cursor.getNext();
        }
    }

    /**
     * Moves the cursor position to the right.
     */
    void moveCursorRight() {
        // is it impossible for the cursor to move right?
        if (cursor == null || cursor.getNext() == null) {
            return;
        }
        // advance the cursor
        cursor = cursor.getNext();
    }

    /**
     * Moves the cursor position to the left.
     * <p>
     * Assumption: 'cursor' points to a node in the list!
     */
    void moveCursorLeft() {
        // is it impossible for the cursor to move left?
        if (head == null || cursor == head) {
            return;
        }

        // setup an initial attempt to a reference to the node before the current cursor
        ImageNode previous = head;

        // while the node before the cursor has not been found yet, keep advancing
        while (previous.getNext() != cursor) {
            previous = previous.getNext();
        }

        cursor = previous;
    }

    /**
     * Returns the number of images
     *
     * @return number of images
     */
    int count() {
        if (head == null) {
            return 0;
        }
        return head.count();
    }

    /**
     * Adds an image after the cursor position
     * For the core part of the assignment.
     *
     * @param imageFileName the file name of the image to be added
     *                      HINT: Consider that the current collection may be empty.
     *                      HINT: Create a new image node here and and delegate further work to method 'insertAfter' of class ImageNode.
     *                      HINT: Pay attention to the cursor position after the image has been added.
     */
    void addImageAfter(String imageFileName) {
        if (head == null) {
            head = cursor = new ImageNode(imageFileName, null);
        } else {
            cursor.setNext(cursor = new ImageNode(imageFileName, null));
        }
    }

    /**
     * Adds an image before the cursor position
     * For the completion part of the assignment.
     *
     * @param imageFileName the file name of the image to be added
     *                      HINT: Create a new image node here and then
     *                      1. Consider that the current collection may be empty.
     *                      2. Consider that the head may need to be adjusted.
     *                      3. if necessary, delegate further work to 'insertBefore' of class ImageNode.
     *                      HINT: Pay attention to the cursor position after the image has been added.
     */
    void addImageBefore(String imageFileName) {
        if (cursor != head) {
            moveCursorLeft();
            addImageAfter(imageFileName);
        }
    }

    /**
     * Removes all images.
     * <p>
     * For the core part of the assignment.
     */
    void removeAll() {
        head = cursor = null;
    }

    /**
     * Removes an image at the cursor position
     * For the core part of the assignment.
     * HINT: Consider that the list may be empty.
     * HINT: Handle removing at the very start of the list in this method and
     * delegate the removal of other nodes by using method 'removeNodeUsingPrevious' from class ImageNode.
     * HINT: Make sure that the cursor position after the removal is correct.
     */

    void remove() {
        ImageNode temp = cursor;
        if (head != null) {
            moveCursorLeft();
            temp.removeNodeUsingPrevious(cursor);
            moveCursorRight();
        }
    }

    /**
     * Reverses the order of the image list so that the last node is now the first node, and
     * and the second-to-last node is the second node, and so on
     * For the completion part of the assignment.
     * HINT: Make sure there is something worth reversing first.
     * HINT: You will have to use temporary variables.
     * HINT: Don't forget to update the head of the list.
     */
    void reverseImages() {
        if (head.count() >= 2) {
            ArrayList<ImageNode> temp = new ArrayList<>();
            while (cursor != null) {
                temp.add(cursor);
                moveCursorRight();
            }
            Collections.reverse(temp);
            for (int i = 1; i < temp.size(); i++) {
                temp.get(i - 1).setNext(temp.get(i));
            }
            head = null;
            head = temp.get(0);
            cursor = head;
        }
    }

    /**
     * @return an iterator over the items in this list of images.
     * For the completion part of the assignment.
     */
    public Iterator<String> iterator() {
        return new ImagesIterator(this);
    }

    /**
     * Support for iterating over all images in an "Images" collection.
     * For the completion part of the assignment.
     */
    private class ImagesIterator implements Iterator<String> {
        Images images;
        ImageNode current;

        // constructor
        private ImagesIterator(Images images) {
            this.images = images;
            this.current = head;
        }

        /**
         * @return true if iterator has at least one more item
         * <p>
         * For the completion part of the assignment.
         */
        public boolean hasNext() {
            return current != null;
        }

        /**
         * Returns the next element or throws a
         * NoSuchElementException exception if none exists.
         * <p>
         * For the completion part of the assignment.
         *
         * @return next item in the set
         */
        public String next() {
            if (hasNext()) {
                String temp =current.getFileName();
                current=current.getNext();
                return temp;
            }else{
                throw new NoSuchElementException();
            }
        }
        //do not need remove as java is good
    }
}
