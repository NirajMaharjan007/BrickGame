package operation.activities;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class OperationPanel extends JPanel implements ActionListener {
    private final int width = 800, height = 600;
    private static final int unit = 25;

    protected boolean play = false;

    public static int playerX;

    public static int ballX = 2 * unit,
            ballY = 10 * unit,
            ballXDir = -1,
            ballYDir = -2;

    private int total_bricks = 21;

    Timer timer = new Timer(1, this);

    Obstacle ob = new Obstacle(width, height);

    Brick brick;

    public OperationPanel() {
        System.out.println("Total Brick: " + total_bricks);
        start();
        brick = new Brick(3, 7);
        setFocusTraversalKeysEnabled(false);
        setPreferredSize(new Dimension(width, height));
        setVisible(true);
        setFocusable(true);
        setBackground(Color.BLACK);
        new Controller();
    }

    protected void start() {
        play = true;
        ob.setObstacle();
        timer.setRepeats(true);
        timer.start();
    }

    private void brickCollision() {
        for (int i = 0; i < brick.map.length; i++) {
            for (int j = 0; j < brick.map[0].length; j++) {
                int brickX = j * brick.brick_width + 50;
                int brickY = i * brick.brick_height + 50;
                int brickWidth = brick.brick_width;
                int brickHeight = brick.brick_height;

                Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
                Rectangle ball_rect = new Rectangle(ballX, ballY, unit, unit);
                if (brick.map[i][j] > 0) {
                    if (rect.intersects(ball_rect)) {
                        brick.setBrickValue(0, i, j);
                        total_bricks--;

                        if (ballX + 20 < rect.x || ballX + 1 >= rect.x + rect.width)
                            ballXDir = -ballXDir;
                        else
                            ballYDir = -ballYDir;
                    }
                }
            }
        }
    }

    protected void moveBall() {
        ballX += ballXDir;
        ballY += ballYDir;

        System.out.println("Ball X: " + ballX + ", Ball Y: " + ballY);

        Rectangle player = new Rectangle(ballX, ballY, unit, unit);
        Rectangle ball = new Rectangle(playerX, height - (2 * unit), (5 * unit), (5 * unit));

        if (player.intersects(ball) && ball.intersects(player))
            ballYDir = -ballYDir;

        if (ballX < 0)
            ballXDir = -ballXDir;

        if (ballY < 0)
            ballYDir = -ballYDir;

        if (ballX > (width - unit))
            ballXDir = -ballXDir;

        if (ballY > height)
            play = false;

        ob.isTouched();
    }

    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);
        if (play) {
            brick.draw((Graphics2D) g);
            drawPlayer(g);
            drawBall(g);

            ob.drawObstacle(g);

            if (total_bricks == 0)
                gameWin(g);

        } else {
            gameOver(g);
        }
    }

    protected void drawPlayer(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(playerX, height - (2 * unit), (5 * unit), unit);
    }

    protected void drawBall(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(ballX, ballY, unit, unit);
    }

    private void gameWin(Graphics g) {

        g.setColor(Color.RED);
        g.setFont(new Font("Times New Roman", Font.BOLD, 30));
        g.drawString("You Win!", 300, height / 2);
        timer.stop();
    }

    private void gameOver(Graphics g) {
        g.setColor(Color.RED);
        g.setFont(new Font("Times New Roman", Font.BOLD, 30));

        g.drawString("Game Over", 300, height / 2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (play) {
            moveBall();
            brickCollision();
        }
        repaint();
    }

    private void reset() {
        ballX = playerX + (2 * unit);
        ballY = height - (4 * unit);
        total_bricks = 21;
        ob.setObstacle();
    }

    private class Controller extends JFrame {
        TextField input = new TextField(8);
        JLabel label = new JLabel("", JLabel.CENTER);

        protected void moveLeft() {
            play = true;
            playerX -= unit;
        }

        protected void moveRight() {
            play = true;
            playerX += unit;
        }

        Controller() {
            setLayout(new FlowLayout(FlowLayout.CENTER, 2, 2));
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setResizable(false);

            input.setEditable(false);

            JPanel panel = new JPanel(new GridLayout(1, 2, 5, 5));

            panel.add(input);
            panel.add(label);

            add(panel);

            pack();

            setVisible(true);
            setLocation(100, 150);
            setFocusable(true);
            setFocusableWindowState(true);

            input.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_SPACE:
                            reset();
                            play = true;
                            break;

                        case KeyEvent.VK_LEFT:
                            label.setText("Left");
                            if (playerX <= 0)
                                playerX = 0;
                            else
                                moveLeft();

                            break;

                        case KeyEvent.VK_RIGHT:
                            label.setText("Right");
                            if (playerX >= 650)
                                playerX = 675;
                            else
                                moveRight();
                            break;
                    }
                }
            });
        }

    }
}
