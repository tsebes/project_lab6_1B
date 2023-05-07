package gui;

import javax.swing.*;
import java.awt.*;

public class AnalyzePanel extends JPanel {

    private final BattlePanel battlePanel;

    public AnalyzePanel(BattlePanel battle) {
        this.battlePanel = battle;
        //TODO rework menu graphics
        setBounds(200, 400, 600, 200);
        setBackground(Color.BLACK);
        setVisible(false);
        setLayout(null);
        addTitle();
        //TODO add label with known chosen enemy information
    }

    private void addTitle() {
        JLabel title = new JLabel("Work in progress, here will be result of analyze", SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 20));
        title.setForeground(Color.WHITE);
        title.setBounds(100, 0, 400, 40);
        add(title);
    }
}