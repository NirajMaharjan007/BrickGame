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
        y = random.nextInt(5, 7) * 35;
    }

    public void isTouched() {
        Rectangle ball_rect = new Rectangle(OperationPanel.ballX, OperationPanel.ballY, 25, 25);
        Rectangle rect = new Rectangle(x, y, 80, 20);

        if (ball_rect.intersects(rect)) {
            if (OperationPanel.ballX + 80 <= rect.x || OperationPanel.ballX + 1 >= rect.x + rect.width)
                OperationPanel.ballXDir = -OperationPanel.ballXDir;
            else
                OperationPanel.ballYDir = -OperationPanel.ballYDir;
        }
    }
}
