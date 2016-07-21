import ecs100.UI;

import java.awt.*;
import java.util.Arrays;

/**
 * Started by Shaun on 19/07/2016.
 */
public class Spiral {
    private int num = 25;
    private int[][] boxes = new int[10][10];


    private Spiral() {
        UI.addButton("Line", this::line);
        UI.addButton("Square", this::square);
        UI.addButton("Spiral", this::doSpiral);
        UI.addButton("Clear", UI::clearGraphics);
        UI.addButton("Exit", UI::quit);
    }

    private void line() {
        num = 25;
        for (int x = 0; x < boxes.length; x++) {
            boxes[x][0] = x;
            for (int y = 1; y < boxes[1].length; y++) {
                boxes[x][y] = -1;
            }
        }
        draw();
    }

    private void square() {
        int count = 1;
        num = 2;
        for (int row = 0; row < boxes.length; row++) {
            for (int col = 0; col < boxes[row].length; col++) {
                boxes[col][row] = count;
                count++;
            }
        }
        draw();
        System.out.print(Arrays.deepToString(boxes));
    }

    private void draw() {
        UI.clearGraphics();
        int size = 50;
        for (int y = 0; y < boxes.length; y++) {
            for (int x = 0; x < boxes[y].length; x++) {
                UI.setColor(new Color(num * boxes[x][y]));
                if (boxes[x][y] != -1) {
                    UI.fillRect((size * (x) + 10), (size * y) + 10, size, size);
                    UI.drawString(boxes[x][y]+"",(size * (x) + 10)+(size /2), (size * y) + 10)+(size /2);
                }
            }
        }

    }

    private void doSpiral() {

    }

    public static void main(String[] arguments) {
        new Spiral();
    }
}
