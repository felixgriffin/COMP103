// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP103 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP103, Assignment 9
 * Name:
 * Usercode:
 * ID:
 */

import java.util.Queue;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/** ArrayQueueTest
 *  A JUnit class for testing an ArrayQueue
 */

public class ArrayQueueTest {

    private Queue<String> queue;

    /**
     * initialise queue to be an empty queue before each test is run
     */
    @Before
    public void initialiseEmptySet() {
        queue = new ArrayQueue<>();
    }

    /**
     * method to initialise the queue with the n values "v1", "v2", ...
     */
    private void fillQueue(int n) {
        for (int i = 1; i <= n; i++) {
            queue.add("v" + i);
        }
    }
    //-------------------------------------------------------------------

    @Test
    public void testIsEmptyOnEmptySet() {
        assertTrue("A new array queue should be empty", queue.isEmpty());
    }

    @Test
    public void testEmptySetHasSizeZero() {
        assertEquals("An empty queue should have size zero", 0, queue.size());
    }

    @Test
    public void testEmptySetDoesNotContainNull() {
        assertFalse("An empty queue should not contain null", queue.contains(null));
    }

    @Test
    public void testAddingToSet() {
        for (int i = 1; i <= 20; i++) {
            assertTrue("Set should successfully add item " + i, queue.add("v" + i));
            assertFalse("Set should not be empty after add", queue.isEmpty());
            assertEquals("Size should be " + i + " after " + i + " adds", i, queue.size());
        }
    }

    // The expected parameter in the Test annotation indicates
    // that this test case expects an IllegalArgumentException
    // The Unit test will fails if the given exception is not thrown.
    // Since JUnit 4.
    @Test(expected = IllegalArgumentException.class)
    public void testAddingNull() {
        queue.add(null);
    }

    @Test
    public void testAddingDuplicates() {
        fillQueue(20);

        for (int i = 1; i <= 20; i++) {
            String value = "v" + i;
            assertFalse("Set should not add duplicate item " + value, queue.add(value));
            assertFalse("Set should not be empty after add.", queue.isEmpty());
            assertEquals("Size should still be 20 after adding duplicate.", 20, queue.size());
        }
    }

    @Test
    public void testContains() {
        fillQueue(20);
        for (int i = 1; i <= 20; i++) {
            String goodValue = "v" + i;
            String badValue = "u" + i;
            assertTrue("Set should contain item " + goodValue, queue.contains(goodValue));
            assertFalse("Set should not contain item " + badValue, queue.contains(badValue));
        }
    }

    @Test
    public void testContainsNull() {
        fillQueue(15);
        assertFalse("Set should not contain null.", queue.contains(null));
    }

    @Test
    public void testRemovingAllItems() {
        fillQueue(15);

        for (int i = 1; i <= 15; i++) {
            String check=queue.poll();
            assertTrue("fail",check.equals("v"+i));
        }

    }

    public static void main(String args[]) {
        org.junit.runner.JUnitCore.main("ArrayQueueTest");
    }

}
