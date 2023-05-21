package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class LogsPanel  extends JPanel {

    private final BattlePanel battlePanel;
    private List<JLabel> logsLabels = new ArrayList<>();
    private int y;

    public LogsPanel(BattlePanel battlePanel) {
        this.battlePanel = battlePanel;
        //TODO rework menu graphics
        setBounds(0, 0, 800, 600);
        setBackground(new Color(135, 56, 27));
        setVisible(false);
        setLayout(null);
        addExitButton();
        y = 10;
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

    public void deleteLogs(){
        Component[] components = this.getComponents();
        for (Component component : components) {
            if(component instanceof JLabel){
                this.remove(component);
            }
        }
        logsLabels.clear();
        y = 10;
    }

    public void addLog(String information, int numberOfLines){
        JLabel logLabel = new JLabel("<html>" + information + "</html>", SwingConstants.LEFT);
        logLabel.setFont(new Font("Serif", Font.BOLD, 10));
        logLabel.setForeground(Color.BLACK);
        logLabel.setBackground(new Color(181, 88, 54));
        logLabel.setOpaque(true);
        logLabel.setBounds(25, y, 750, 10 * numberOfLines);
        logLabel.setBorder(new EmptyBorder(3,3,3,3));
        y += 10 * numberOfLines + 10;
        logsLabels.add(logLabel);
        add(logLabel);
    }
}
