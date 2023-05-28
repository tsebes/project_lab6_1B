package gui;

import game.Character;
import game.Hero;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class TurnPanel extends JPanel {

    private final BattlePanel battlePanel;
    private final ArrayList<JLabel> turnOrderLabels = new ArrayList<>();
    private JButton logsButton;

    public TurnPanel(BattlePanel battlePanel) {
        this.battlePanel = battlePanel;
        //TODO rework menu graphics
        setBounds(600, 0, 200, 400);
        setBackground(Color.GRAY);
        setLayout(null);
        addTurnPanel();
        addLogsButton();
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

    private void addTurnPanel() {
        for (int i = 0; i < 8; i++) {
            JLabel turnOrder = new JLabel("", SwingConstants.CENTER);
            turnOrder.setFont(new Font("Serif", Font.PLAIN, 20));
            turnOrder.setBounds(0, 43 * i + 5, 200, 40);
            turnOrder.setOpaque(true);

            turnOrderLabels.add(turnOrder);
            add(turnOrder);
        }
    }

    public void refresh() {
        List<Map.Entry<Character, Double>> entryList = new ArrayList<>(battlePanel.getBattle().getTurnOrder().entrySet());
        entryList.sort(Map.Entry.comparingByValue());

        for (int i = 0; i < turnOrderLabels.size(); i++) {
            if (i < entryList.size()) {
               turnOrderLabels.get(i).setText(entryList.get(i).getKey().getName() + ": " + entryList.get(i).getValue().toString());
                if(entryList.get(i).getKey() instanceof Hero) {
                    turnOrderLabels.get(i).setBackground(Color.GREEN);
                    turnOrderLabels.get(i).setForeground(Color.BLACK);
                } else {
                    turnOrderLabels.get(i).setBackground(Color.RED);
                    turnOrderLabels.get(i).setForeground(Color.WHITE);
                }
            } else {
                turnOrderLabels.get(i).setVisible(false);
            }
        }
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