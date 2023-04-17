package gui;

import javax.swing.*;
import java.awt.*;

public class TurnPanel extends JPanel {

    private final BattlePanel battlePanel;

    public TurnPanel(BattlePanel battlePanel) {
        this.battlePanel = battlePanel;
        //TODO rework menu graphics
        setBounds(600, 0, 200, 400);
        setBackground(Color.GRAY);
        setLayout(null);
        addLogsButton();
        //TODO add turn order
    }

    private void addLogsButton() {
        JButton addLogsButton = new JButton("Logs");
        addLogsButton.setBackground(Color.DARK_GRAY);
        addLogsButton.setBounds(0, 350, 200, 50);
        addLogsButton.setForeground(Color.RED);
        add(addLogsButton);
        addLogsButton.addActionListener(e -> {
            battlePanel.changePanel(BattlePanel.Panel.Logs);
        });
    }
}