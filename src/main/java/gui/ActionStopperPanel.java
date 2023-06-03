package gui;

import javax.swing.*;
import java.awt.*;

public class ActionStopperPanel extends JPanel {

    private final BattlePanel battlePanel;

    public ActionStopperPanel(BattlePanel battle) {
        this.battlePanel = battle;
        setBounds(0, 400, 800, 200);
        setBackground(Color.DARK_GRAY);
        setVisible(false);
        setLayout(null);
    }
}
