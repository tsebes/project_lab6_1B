package gui;

import javax.swing.*;
import java.awt.*;

public class TurnPanel extends JPanel {

    private final BattlePanel battlePanel;
    private JButton logsButton;

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
        JButton logsButton = new JButton("Logs");
        logsButton.setBackground(Color.DARK_GRAY);
        logsButton.setBounds(0, 350, 200, 50);
        logsButton.setForeground(Color.RED);
        this.logsButton=logsButton;
        add(logsButton);
        logsButton.addActionListener(e -> {
            battlePanel.changePanel(BattlePanel.Panel.Logs);
        });
    }

    public JButton getLogsButton() {
        return logsButton;
    }
}