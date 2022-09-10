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
            playerX, ballX, ballY;

    private Timer timer;

    public OperationPanel() {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setPreferredSize(new Dimension(width, height));
        setVisible(true);
        setFocusable(true);
        setBackground(Color.BLACK);
    }

    protected void start() {
        timer = new Timer(80, this);
        timer.start();
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
    }

    protected void drawPlayer(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(14 * unit, height - unit, 150, unit);
    }

    protected void drawBall(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(14 * unit, (height - (2 * unit)), unit, unit);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
