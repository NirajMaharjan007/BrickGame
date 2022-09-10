package operation;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class OperationPanel extends JPanel implements ActionListener {
    private final int width = 800, height = 600;
    private final int unit = 25;

    protected boolean play = false;
    private int score = 0;

    private int totalBrick = 20,
            playerX,
            ballX, ballY,
            ballXDir, ballYDir;

    private Timer timer;

    public OperationPanel() {
        setFocusTraversalKeysEnabled(false);
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setPreferredSize(new Dimension(width, height));
        setVisible(true);
        setFocusable(true);
        start();
        new Controller();
        setBackground(Color.BLACK);
    }

    protected void start() {
        play = true;
        timer = new Timer(80, this);
        timer.start();
    }

    protected void checkCollision() {
        if (playerX < 0)
            playerX = 0;

        if (playerX > width - unit)
            playerX = width - unit;

    }

    protected void moveBall() {
        ballX += ballXDir;
        ballY += ballYDir;
        if (ballX < 0) {
            ballXDir = -ballXDir;
        }

        if (ballY < 0) {
            ballYDir = -ballYDir;
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(230, 230, 230));
        for (int i = 0; i < height; i++) {
            g.drawLine(i * unit, 0, i * unit, height);
            g.drawLine(0, i * unit, width, i * unit);
        }

        drawPlayer(g);
        drawBall(g);
        g.dispose();
    }

    protected void drawPlayer(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(playerX, height - unit, 150, 20);
        checkCollision();
    }

    protected void drawBall(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(ballX, ballY, unit, unit);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if (play) {
            moveBall();
        }
        repaint();
    }

    private class Controller extends JFrame {
        TextField input = new TextField(8);
        JLabel label = new JLabel("", JLabel.CENTER);

        Controller() {
            setVisible(true);
            setLocation(100, 150);
            setLayout(new FlowLayout(FlowLayout.CENTER, 2, 2));
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            input.setEditable(false);

            JPanel panel = new JPanel(new GridLayout(1, 2, 5, 5));

            panel.add(input);
            panel.add(label);

            add(panel);

            pack();

            input.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_LEFT:
                            label.setText("Left");
                            playerX -= unit;
                            repaint();
                            break;

                        case KeyEvent.VK_RIGHT:
                            label.setText("Right");
                            playerX += unit;
                            repaint();
                            break;
                    }
                }
            });
        }

    }
}
