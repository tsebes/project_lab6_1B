package src.main.java.gui;

import javax.swing.*;
import java.awt.*;

public class TargetingPanel   extends JPanel {

    private final BattlePanel battle;

    public TargetingPanel(BattlePanel battle) {
        this.battle = battle;
        //TODO rework menu graphics
        setBounds(200, 400, 600, 200);
        setBackground(Color.GREEN);
        setVisible(false);
        setLayout(null);
    }
}
