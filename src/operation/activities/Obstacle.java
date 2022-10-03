package operation.activities;

import java.awt.*;
import java.util.Random;

public class Obstacle {

    private int x, y;
    private int screen_width, screen_height;

    public Obstacle() {
    }

    public String getScreenSize() {
        String str = "screen_width: " + screen_width + " and screen_height: " + screen_height;
        return str;
    }

    public Obstacle(int width, int height) {
        this.screen_height = height;
        this.screen_width = width;
    }

    public void drawObstacle(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, 80, 20);
    }

    public void setObstacle() {
        Random random = new Random();
        x = random.nextInt(screen_width / 35) * 35;
        y = random.nextInt(3, 8) * 35;
    }

    public void isTouched() {
        if (new Rectangle(OperationPanel.ballX, OperationPanel.ballY, 25, 25)
                .intersects(new Rectangle(x, y, 80, 20))) {
            OperationPanel.ballYDir = -OperationPanel.ballYDir;
        }
    }
}
