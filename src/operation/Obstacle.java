package operation;

import java.awt.*;
import java.util.Random;

public class Obstacle {

    private int x, y;
    private int screen_width, screen_height;

    public Obstacle() {
    }

    public Obstacle(int width, int height) {
        this.screen_height = height;
        this.screen_width = width;
    }

    public void drawObstacle(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, 35, 35);
    }

    public void setObstacle() {
        Random random = new Random();
        x = random.nextInt(screen_width / 35) * 35;
        y = random.nextInt(screen_height / 35) * 35;
    }

    public void isTouched() {
        if (OperationPanel.ballX == x)
            OperationPanel.ballXDir = -OperationPanel.ballXDir;

        if (OperationPanel.ballY == y)
            OperationPanel.ballYDir = -OperationPanel.ballYDir;
    }

}
