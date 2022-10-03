package operation.activities;

import java.awt.*;

public class Brick {
    public int map[][];
    int brick_width, brick_height;

    public Brick(int row, int column) {
        map = new int[row][column];

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = 1;
            }
        }

        brick_width = 300 / row;
        brick_height = 250 / column;
    }

    public void draw(Graphics2D g) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] > 0) {
                    g.setColor(Color.WHITE);
                    g.fillRect(j * brick_width + 50, i * brick_height + 50, brick_width, brick_height);

                    g.setStroke(new BasicStroke(2));
                    g.setColor(Color.BLACK);
                    g.drawRect(j * brick_width + 50, i * brick_height + 50, brick_width, brick_height);
                }
            }
        }
    }

    public void setBrickValue(int value, int row, int column) {
        map[row][column] = value;
    }
}
