// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP103 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP 103 Assignment 2
 */

/** 
 *  A pair of row and column representing a coordinate in the warehouse.
 *  Also has a method to return the next CoOrd in a given direction.
 */

class CoOrd {

    public final int row;  // because they are final (can't be changed), it is
    public final int col;  // safe to make these fields public.

    CoOrd(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /** Return the next coord in the specified direction */
    CoOrd next(String direction) {
        if (direction.equals("up"))    return new CoOrd(row-1, col);
        if (direction.equals("down"))  return new CoOrd(row+1, col);
        if (direction.equals("left"))  return new CoOrd(row, col-1);
        if (direction.equals("right")) return new CoOrd(row, col+1);
        return this;
    }

    public String toString() {
        return String.format("(%d,%d)", row, col);
    }
}
