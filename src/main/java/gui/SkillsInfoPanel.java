package gui;

import javax.swing.*;
import java.awt.*;

public class SkillsInfoPanel extends JPanel {

    private final BattlePanel battlePanel;

    public SkillsInfoPanel(BattlePanel battle) {
        this.battlePanel = battle;
        //TODO rework menu graphics
        setBounds(200, 400, 600, 200);
        setBackground(Color.BLUE);
        setVisible(false);
        setLayout(null);
        addTitle();
    }

    private void addTitle() {
        JLabel title = new JLabel("Work in progress, here will be skill info", SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 20));
        title.setForeground(Color.WHITE);
        title.setBounds(100, 0, 400, 40);
        add(title);
    }

    public void refresh(){
        //TODO set up to show info of chosen skill
    }
}