package gui;

import javax.swing.*;
import java.awt.*;

public class ActionStopperPanel extends JPanel {

    private final BattlePanel battlePanel;

    public ActionStopperPanel(BattlePanel battle) {
        this.battlePanel = battle;
        //TODO rework menu graphics
        setBounds(0, 400, 800, 200);
        setBackground(Color.CYAN);
        setVisible(false);
        setLayout(null);
        //TODO add label with known chosen enemy information
    }
}
