// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP103 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP 103 Assignment 2  */

import java.util.Objects;

/**
 *  The possible squares, along with useful methods.
 *  Would be better represented by an Enum class.
 */

class Square {
    private String type;

    Square(String t){
        type = t;
    }

    String imageName() {
        return type +".gif";
    }

    /** Does this shelf still miss its box? */
    boolean isEmptyShelf() {
        return Objects.equals(type, "emptyShelf");
    }

    /** Whether there is a box on this square */
    boolean hasBox() {
        return (type.contains("box"));
    }

    /** Whether the square is free to move onto */
    boolean isFree() {
        return (type.contains("empty"));    }

    /** The square you get if you push a box off this square */
    void moveBoxOff() {
        if (Objects.equals(type, "box")) {
            type = "empty";
            return;
        }

        if (Objects.equals(type, "boxOnShelf")) {
            type = "emptyShelf";
        }
    }

    /** The square you get if you push a box on to this square */
    void moveBoxOn() {
        if (Objects.equals(type, "empty")) {
            type = "box";
            return;
        }

        if (Objects.equals(type, "emptyShelf")) {
            type = "boxOnShelf";
        }
    }
}
