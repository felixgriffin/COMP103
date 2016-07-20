import ecs100.UI;

import java.awt.*;

/**
 * Started by Shaun on 19/07/2016.
 */
public class Spiral {
    private int size = 50;
    private int num = 25;
    private int[][] boxes = new int[10][10];


    private Spiral() {
        UI.addButton("Line", () -> fill("line"));
        UI.addButton("Square", () -> fill("Square"));
        UI.addButton("Spiral", this::doSpiral);
        UI.addButton("Clear", UI::clearGraphics);
        UI.addButton("Exit", UI::quit);
    }

    private void fill(String type) {
        int count = 1;
        if (type.equals("line")) {
            num = 25;
            for (int x = 0; x < boxes.length; x++) {
                boxes[x][0]=x;
                for (int y = 1; y < boxes[1].length; y++) {
                    boxes[x][y]=-1;
                }
            }
        } else if (type.equals("Square")) {
            num = 2;
            for (int i = 0; i < boxes.length; i++) {
                for (int j = 0; j <= boxes.length; j++) {
                    boxes[i][j]= count;
                    count++;
                }
            }
        }
        draw();
    }

    private void draw() {
        for (int y = 0; y < boxes[1].length; y++) {
            for (int x = 1; x < boxes.length; x++) {
                UI.setColor(new Color(num * boxes[x][0]));
                if (boxes[x][y]!=-1){
                    UI.fillRect((size * (x-1) + 10), (size * y) + 10, size, size);
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
