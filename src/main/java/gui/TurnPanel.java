package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

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
        JButton logsButton = new JButton("");
        logsButton.setBackground(Color.DARK_GRAY);
        logsButton.setBounds(0, 350, 200, 50);
        logsButton.setFont(new Font("Serif", Font.BOLD, 10));
        logsButton.setForeground(Color.WHITE);
        this.logsButton=logsButton;
        add(logsButton);
        logsButton.addActionListener(e -> {
            battlePanel.changePanel(BattlePanel.Panel.Logs);
        });
    }

    public void setLogsButtonText(String info){
        logsButton.setText(info);
    }

    public void deleteLogActionListener(){
        ActionListener[] listeners = logsButton.getActionListeners();
        if (listeners == null) {
            return;
        }
        for (ActionListener listener : listeners) {
            logsButton.removeActionListener(listener);
        }
    }

    public void addLogActionListener(){
        deleteLogActionListener();
        logsButton.addActionListener(e -> {
            battlePanel.changePanel(BattlePanel.Panel.Logs);
        });
    }
}