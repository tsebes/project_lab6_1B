package src.main.java.gui;

import javax.swing.*;
import java.awt.*;

public class CharactersPanel extends JPanel {

    private final BattlePanel battle;

    public CharactersPanel(BattlePanel battle) {
        this.battle = battle;
        //TODO rework menu graphics
        setBounds(0, 0, 600, 400);
        setBackground(Color.WHITE);
        setLayout(null);
    }
}
