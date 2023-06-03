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
        setBounds(600, 0, 200, 400);
        setLayout(null);
        addTurnLabel();
        addLogsButton();
    }

    private void addLogsButton() {
        JButton logsButton = new JButton("");
        logsButton.setBounds(20, 350, 160, 50);
        logsButton.setFont(new Font("Serif", Font.BOLD, 10));
        logsButton.setForeground(Color.BLACK);
        logsButton.setOpaque(false);
        logsButton.setContentAreaFilled(false);
        logsButton.setBorderPainted(false);
        this.logsButton=logsButton;
        add(logsButton);
        logsButton.addActionListener(e -> {
            battlePanel.changePanel(BattlePanel.Panel.Logs);
        });
    }

    public void setLogsButtonText(String info){
        logsButton.setText(info);
    }

    private void addTurnLabel() {
        for (int i = 0; i < 8; i++) {
            JLabel turnOrder = new JLabel("", SwingConstants.CENTER);
            turnOrder.setFont(new Font("Serif", Font.BOLD, 20));
            turnOrder.setBounds(0, 43 * i + 5, 200, 40);
            turnOrder.setOpaque(false);

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
                   turnOrderLabels.get(i).setForeground(new Color(0, 100, 0));
               } else {
                   turnOrderLabels.get(i).setForeground(Color.RED);
               }
               turnOrderLabels.get(i).setVisible(true);
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