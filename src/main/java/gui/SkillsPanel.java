package gui;

import javax.swing.*;
import java.awt.*;

public class SkillsPanel  extends JPanel {

    private final BattlePanel battlePanel;

    public SkillsPanel(BattlePanel battlePanel) {
        this.battlePanel = battlePanel;
        //TODO rework menu graphics
        setBounds(200, 400, 600, 200);
        setBackground(Color.BLUE);
        setLayout(null);
        //TODO show possible skills
    }
}