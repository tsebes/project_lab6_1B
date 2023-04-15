package src.main.java.gui;

import javax.swing.*;
import java.awt.*;

public class LogsPanel  extends JPanel {

    private final BattlePanel battlePanel;

    public LogsPanel(BattlePanel battlePanel) {
        this.battlePanel = battlePanel;
        //TODO rework menu graphics
        setBounds(0, 0, 800, 600);
        setBackground(Color.WHITE);
        setVisible(false);
        setLayout(null);
        addExitButton();
        //TODO make createLogLabel function
    }

    private void addExitButton() {
        JButton exitButton = new JButton("X");
        exitButton.setBackground(Color.DARK_GRAY);
        exitButton.setBounds(0, 0, 20, 20);
        exitButton.setForeground(Color.RED);
        add(exitButton);
        exitButton.addActionListener(e -> {
            battlePanel.changePanel(BattlePanel.Panel.Skills);
        });
    }
}
