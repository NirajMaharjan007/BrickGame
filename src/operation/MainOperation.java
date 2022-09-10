package operation;

import java.awt.*;
import javax.swing.*;

public class MainOperation extends JFrame {
    public MainOperation() {
        setTitle("Main Operation Brick Game");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.WHITE);
        setResizable(false);
        setFocusable(true);
        add(new OperationPanel());
        pack();
        setAlwaysOnTop(true);
        setLocationRelativeTo(null);
    }
}
