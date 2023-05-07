package gui;

import javax.swing.*;
import java.awt.*;

public class ItemsInfoPanel extends JPanel {

    private final BattlePanel battlePanel;

    public ItemsInfoPanel(BattlePanel battle) {
        this.battlePanel = battle;
        //TODO rework menu graphics
        setBounds(200, 400, 600, 200);
        setBackground(Color.BLUE);
        setVisible(false);
        setLayout(null);

        JLabel basicInfo = new JLabel("Work in Progress", SwingConstants.CENTER);
        basicInfo.setFont(new Font("Serif", Font.PLAIN, 20));
        basicInfo.setForeground(Color.WHITE);
        basicInfo.setBounds(100, 50, 400, 30);
        add(basicInfo);
    }

    public void refresh(){
        //TODO set up to show info of chosen item
    }
}