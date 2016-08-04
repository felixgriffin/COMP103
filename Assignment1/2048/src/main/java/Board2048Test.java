import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;

/**
 * Created by sanjay on 15/07/16.
 */
public class Board2048Test {
    @Test
    public void testLeft() {
        Board2048 board = new Board2048();
         /*
        Examples:
     2 2 4 2 will give 4 4 2 0 (the first 2s merge into a 4. Then the remaining
     4 and 2 follow, and the board is completed on the right with a 0)

     4 4 2 2 will give 8 4 0 0 (4 and 4 merge into a 8, 2 and 2 merge into a 4,
     completing with zeros on the right)

     4 4 4 4 will give 8 8 0 0 (First two 4s merge together, the last two 4s merge together)
         */
        board.setData(new int[][]{
                {2,2,4,2},
                {4,4,2,2},
                {4,4,4,4},
                {2,2,4,2},
        });
        board.left();
        assertTrue(Arrays.equals(board.getData()[0],new int[]{4,4,2,0}));
        assertTrue(Arrays.equals(board.getData()[1],new int[]{8,4,0,0}));
        assertTrue(Arrays.equals(board.getData()[2],new int[]{8,8,0,0}));
        assertTrue(Arrays.equals(board.getData()[3],new int[]{4,4,2,0}));
    }
    @Test
    public void testUp() {
        Board2048 board = new Board2048();
        board.setData(new int[][]{
                {2,4,4,2},
                {2,4,4,2},
                {4,2,4,4},
                {2,2,4,2},
        });
        board.up();
        assertTrue(compareVertStrip(0,board.getData(),new int[]{4,4,2,0}));
        assertTrue(compareVertStrip(1,board.getData(),new int[]{8,4,0,0}));
        assertTrue(compareVertStrip(2,board.getData(),new int[]{8,8,0,0}));
        assertTrue(compareVertStrip(3,board.getData(),new int[]{4,4,2,0}));
    }
    private boolean compareVertStrip(int x, int[][] data, int[] compare) {
        for (int i = 0; i < compare.length; i++) {
            if (compare[i] != data[i][x]) return false;
        }
        return true;
    }
    @Test
    public void testRight() {
        Board2048 board = new Board2048();
         /*
        * Examples:
     *   2 2 4 2 will give 0 4 4 2 (2 and 4 remain unchanged, then the last leftmost 2s merge
     *     into a 4, completing with a zero on the left.)
     *
     *   4 4 2 2 will give 0 0 8 4 (2 and 2 merge into a 4, 4 and 4 merge into a 8)
     *   4 4 4 4 will give 0 0 8 8 (First two 4s merge together, the last two 4s merge together)
         */
        board.setData(new int[][]{
                {2,2,4,2},
                {4,4,2,2},
                {4,4,4,4},
                {2,2,4,2},
        });
        board.right();
        assertTrue(Arrays.equals(board.getData()[0],new int[]{0,4,4,2}));
        assertTrue(Arrays.equals(board.getData()[1],new int[]{0,0,8,4}));
        assertTrue(Arrays.equals(board.getData()[2],new int[]{0,0,8,8}));
        assertTrue(Arrays.equals(board.getData()[3],new int[]{0,4,4,2}));
    }
    @Test
    public void testDown() {
        Board2048 board = new Board2048();
        board.setData(new int[][]{
                {2,4,4,2},
                {2,4,4,2},
                {4,2,4,4},
                {2,2,4,2},
        });
        board.down();
        assertTrue(compareVertStrip(0,board.getData(),new int[]{0,4,4,2}));
        assertTrue(compareVertStrip(1,board.getData(),new int[]{0,0,8,4}));
        assertTrue(compareVertStrip(2,board.getData(),new int[]{0,0,8,8}));
        assertTrue(compareVertStrip(3,board.getData(),new int[]{0,4,4,2}));
    }
}
