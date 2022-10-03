package operation;

import javax.swing.*;

import operation.activities.OperationPanel;

public class MainOperation extends JFrame {
    public MainOperation() {
        setTitle("Main Operation Brick Game");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setFocusable(true);
        add(new OperationPanel());
        pack();
        setAlwaysOnTop(true);
        setLocationRelativeTo(null);
    }
}
