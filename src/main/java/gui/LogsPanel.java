package src.main.java.gui;

import javax.swing.*;
import java.awt.*;

public class LogsPanel  extends JPanel {

    private final BattlePanel battle;

    public LogsPanel(BattlePanel battle) {
        this.battle = battle;
        //TODO rework menu graphics
        setBounds(0, 0, 800, 600);
        setBackground(Color.WHITE);
        setVisible(false);
        setLayout(null);
        addExitButton();
    }

    private void addExitButton() {
        JButton exitButton = new JButton("X");
        exitButton.setBackground(Color.DARK_GRAY);
        exitButton.setBounds(0, 0, 20, 20);
        exitButton.setForeground(Color.RED);
        add(exitButton);
        exitButton.addActionListener(e -> {
            battle.changePanel(BattlePanel.Panel.Skills);
        });
    }
}
