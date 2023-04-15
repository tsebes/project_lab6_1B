package src.main.java.gui;

import javax.swing.*;
import java.awt.*;

public class AnalyzePanel extends JPanel {

    private final BattlePanel battlePanel;

    public AnalyzePanel(BattlePanel battle) {
        this.battlePanel = battle;
        //TODO rework menu graphics
        setBounds(200, 400, 600, 200);
        setBackground(Color.BLUE);
        setVisible(false);
        setLayout(null);
        //TODO add label with known chosen enemy information
    }
}