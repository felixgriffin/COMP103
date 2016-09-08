// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP103 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP103, Assignment 5
 * Name:
 * Usercode:
 * ID:
 */

import ecs100.*;

import java.awt.Color;
import java.util.*;

/**
 * Mondrian Painting Generator
 * <p>
 * Generates Mondrian Paintings by recursively dividing frames into
 * sub-frames and filling them with the typical Mondrian colours.
 */
public class Mondrian {
    // constants

    private static final int MondrianLineWidth = 12;    // width of the black separating lines
    private static final int MondrianSideMinimum = 48;  // minimum side below which no more splitting takes place

    // Mondrian Colours
    private static final Color mondrianBlack = new Color(25, 23, 26);
    private static final Color mondrianWhite = new Color(229, 227, 228);
    private static final Color mondrianYellow = new Color(255, 221, 10);
    private static final Color mondrianRed = new Color(223, 12, 29);
    private static final Color mondrianBlue = new Color(8, 56, 138);

    // fields
    private static int level = 2;   // how deeply sub-Mondrians may be nested
    private static int chance = 45; // chance to create a sub-Mondrian in %

    private static List<Color> mondrianColours = new ArrayList<>(); // collection to pick Mondrian colours from

    // create UI and initialise Mondrian colours
    private Mondrian() {
        UI.setWindowSize(900, 600);
        UI.setDivider(0.1); // leave some space for printing level and chance values

        // create buttons
        UI.addButton("Create", this::drawMondrian);
        UI.addButton("Level+", this::increaseLevel);
        UI.addButton("Level-", this::decreaseLevel);
        UI.addButton("More", this::increaseChance);
        UI.addButton("Less", this::decreaseChance);

        // make white three times more likely to occur than the other colours
        mondrianColours.add(mondrianWhite);
        mondrianColours.add(mondrianWhite);
        mondrianColours.add(mondrianWhite);
        mondrianColours.add(mondrianYellow);
        mondrianColours.add(mondrianRed);
        mondrianColours.add(mondrianBlue);

        drawMondrian();
    }

    /**
     * Increases the level, up to a maximum of 9.
     */
    private void increaseLevel() {
        if (level < 9)
            level++;

        drawMondrian();
    }

    /**
     * Decreases the level, up to a minimum of 0.
     */
    private void decreaseLevel() {
        if (level > 0)
            level--;

        drawMondrian();
    }

    /**
     * Increases the likelihood of creating a sub-Mondrian
     * up to a maximum of 1.
     */
    private void increaseChance() {
        if (chance <= 95)
            chance += 5;    // increase by 5%

        drawMondrian();
    }

    /**
     * Decreases the likelihood of creating a sub-Mondrian
     * up to a minimum of 0.
     */
    private void decreaseChance() {
        if (chance >= 5)
            chance -= 5;  // decrease by 5%

        drawMondrian();
    }

    /**
     * @return a random Mondrian colour.
     */
    private Color randomMondrianColour() {
        return mondrianColours.get((int) (mondrianColours.size() * Math.random()));
    }

    /**
     * Draws a Mondrian within a frame specified by
     * the coordinates (x1, y1) and (x2, y2).
     * <p>
     * Always fills the specified frame with one (random) Mondrian colour.
     * <p>
     * Generates a split point within the frame to potentially
     * draw further sub-Mondrian. The split point is within a sub-frame that
     * is centred in the original frame with margins extending from 25%-75%
     * of the original frame.
     * <p>
     * Only draws the dividing black lines and further sub-Mondrian's, if all of the following hold:
     * 1. the currentLevel is not zero yet.
     * 2. the filled rectangle has no sides that are smaller than MondrianSideMinimum.
     * 3. the chance of creating a sub-Mondrian is > 0%.
     */
    private void drawMondrian(int x, int y, int x2, int y2, int currentLevel) {
        // draw the colour patch that may or may not become divided
        UI.setColor(randomMondrianColour());
        int xDiff = x2 - x;
        int yDiff = y2 - y;
        if(xDiff<MondrianSideMinimum||yDiff<MondrianSideMinimum){
           return;
        }
        UI.fillRect(x + MondrianLineWidth / 2, y + MondrianLineWidth / 2,    // do not paint over already painted lines
                xDiff, yDiff);
        if (currentLevel == 0) {
            return;
        }
        int xSplit = generateRandom(x+xDiff/4, x2-xDiff/4);
        int ySplit = generateRandom(y+yDiff/4, y2-yDiff/4);
        for (int i = 0; i < 4; i++) {
            if (Math.random() * 100 <= chance) {
                drawMondrian(x, y, xSplit, ySplit, currentLevel - 1);
                break;
            }
        }
        for (int i = 0; i < 4; i++) {
            if (Math.random() * 100 <= chance) {
                drawMondrian(xSplit, y, x2, ySplit, currentLevel - 1);
                break;
            }
        }
        for (int i = 0; i < 4; i++) {
            if (Math.random() * 100 <= chance) {
                drawMondrian(x, ySplit, xSplit, y2, currentLevel - 1);
                break;
            }
        }
        for (int i = 0; i < 4; i++) {
            if (Math.random() * 100 <= chance) {
                drawMondrian(xSplit, ySplit, x2, y2, currentLevel - 1);
                break;
            }
        }
        UI.setColor(mondrianBlack);
        UI.drawLine(xSplit,y,xSplit,y2);
        UI.drawLine(x,ySplit,x2,ySplit);
    }

    private int generateRandom(int min, int max) {
        return (int) (Math.random() * (max - min)) + min;
    }

    /**
     * Paints a Mondrian painting by drawing the outer frame and
     * calling drawMondrian.
     */
    private void drawMondrian() {
        int margin = 20;    // margin to canvas edges

        // get canvas size
        int width = UI.getCanvasWidth() - margin;
        int height = UI.getCanvasHeight();

        UI.clearGraphics();
        UI.setLineWidth(MondrianLineWidth);

        UI.clearText();
        UI.print("Level: " + level + "\n");
        UI.print("Chance: " + chance + "%");

        // calculate coordinates of the first, largest Mondrian patch
        int x2 = margin + (width - margin);
        int y2 = margin + (height - 2 * margin);

        // draw the first, largest Mondrian patch and all smaller ones
        drawMondrian(margin, margin, x2, y2, level);

        // draw the frame last to cover unfinished areas
        UI.setColor(mondrianBlack);
        UI.drawRect(margin, margin, x2 - margin, y2 - margin);
    }

    // Create a new masterpiece
    public static void main(String[] arguments) {
        new Mondrian();
    }
}
