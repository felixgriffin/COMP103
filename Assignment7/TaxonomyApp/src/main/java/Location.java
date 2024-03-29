// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP103 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP 103, Assignment 7
 * Name: Shaun  Sinclair
 * Usercode: sinclashau
 * ID: 300383795
 */
/* Name: pondy & marcus 
 */

/**
 * Represents a position on the window
 */

class Location {

    // Fields

    private double x;
    private double y;

    // Constructor

    Location(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Methods

    double getX() {
        return x;
    }

    double getY() {
        return y;
    }

//    public double distance(Location other) {
//        return Math.hypot((x - other.x), (y - other.y));
//    }

}
