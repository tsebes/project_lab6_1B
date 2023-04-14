package src.main.java.gui;

import javax.swing.*;
import java.awt.*;

public class SkillsPanel  extends JPanel {

    private final BattlePanel battle;

    public SkillsPanel(BattlePanel battle) {
        this.battle = battle;
        //TODO rework menu graphics
        setBounds(200, 400, 600, 200);
        setBackground(Color.BLUE);
        setLayout(null);
        //TODO show possible skills
    }
}