package operation;

import java.awt.*;
import java.util.Random;

public class Obstacle {

    private int x, y;

    public Obstacle() {

    }

    public void setObstacle() {
        Random random = new Random();
        int x = random.nextInt();
        int y = random.nextInt();
    }

}
