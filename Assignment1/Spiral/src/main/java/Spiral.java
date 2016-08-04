import ecs100.UI;

import java.awt.*;

/**
 * Started by Shaun on 19/07/2016.
 */
public class Spiral {
    private int num = 25;
    private int[][] boxes = new int[10][10];


    private Spiral() {
        UI.addButton("Line", this::doLine);
        UI.addButton("Square", this::doSquare);
        UI.addButton("Spiral", this::doSpiral);
        UI.addButton("Clear", UI::clearGraphics);
        UI.addButton("Exit", UI::quit);
    }
    private void clear() {
        for (int x = 0; x < boxes.length; x++) {
            for (int y = 0; y < boxes[1].length; y++) {
                boxes[x][y] = -1;
            }
        }
    }
    private void doLine() {
        num = 25;
        for (int x = 0; x < boxes.length; x++) {
            boxes[x][0] = x;
            for (int y = 1; y < boxes[1].length; y++) {
                boxes[x][y] = -1;
            }
        }
        draw();
    }

    private void doSquare() {
        int count = 1;
        num = 2;
        for (int row = 0; row < boxes.length; row++) {
            for (int col = 0; col < boxes[row].length; col++) {
                boxes[col][row] = count;
                count++;
            }
        }
        draw();
    }

    private void doSpiral() {
        clear();
        num = 2;
        int count = 1, x = 0, y = 0, oldX, oldY, dir = 0;
        while (count <= 100) {
            oldX = x;
            oldY = y;
            boxes[x][y]=count;
            if (dir == 0) x++;
            if (dir == 1) y++;
            if (dir == 2) x--;
            if (dir == 3) y--;
            if (x >= boxes.length || y >= boxes[0].length || x < 0 || y < 0 || boxes[x][y]!=-1) {
                x = oldX;
                y = oldY;
                dir=++dir%4;
            } else {
                count++;
            }
        }
        draw();
    }


    private void draw() {
        UI.clearGraphics();
        int size = 50;
        for (int y = 0; y < boxes.length; y++) {
            for (int x = 0; x < boxes[y].length; x++) {
                UI.setColor(new Color(num * boxes[x][y]));
                if (boxes[x][y] != -1) {
                    UI.fillRect((size * (x) + 10), (size * y) + 10, size, size);
                    UI.setColor(new Color(255, 255, 255));
                    UI.drawString(boxes[x][y] + "", (size * (x) + (size / 2)), ((size * y) + 15) + (size / 2));
                }
            }
        }
    }

    public static void main(String[] arguments) {
        new Spiral();
    }
}
