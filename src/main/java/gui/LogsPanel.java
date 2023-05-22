package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class LogsPanel  extends JPanel {

    private final BattlePanel battlePanel;
    private List<JLabel> logsLabels = new ArrayList<>();
    private JPanel subPanel;
    private JScrollPane scrollPane;
    private int y;

    public LogsPanel(BattlePanel battlePanel) {
        this.battlePanel = battlePanel;
        //TODO rework menu graphics
        setBounds(0, 0, 800, 600);
        setBackground(new Color(135, 56, 27));
        subPanel =new JPanel(){
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(750, logsLabels.size() * 55 + 5);
            }
        };
        scrollPane = new JScrollPane(subPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(20, 20, 770, 560);
        subPanel.setBackground(new Color(135, 56, 27));
        add(scrollPane);
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
            battlePanel.changePanel(BattlePanel.Panel.Skills);
        });
    }

    public void deleteLogs(){
        Component[] components = subPanel.getComponents();
        for (Component component : components) {
            if(component instanceof JLabel){
                subPanel.remove(component);
            }
        }
        logsLabels.clear();
    }

    public void addLog(String information){
        JLabel logLabel = new JLabel("<html>" + information + "</html>", SwingConstants.LEFT);
        logLabel.setFont(new Font("Serif", Font.BOLD, 10));
        logLabel.setForeground(Color.BLACK);
        logLabel.setBackground(new Color(181, 88, 54));
        logLabel.setOpaque(true);
        logLabel.setPreferredSize(new Dimension(750, 50));;
        logsLabels.add(logLabel);
        subPanel.add(logLabel);
    }
}
