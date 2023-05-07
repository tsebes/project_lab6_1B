package gui;

import javax.swing.*;
import java.awt.*;

public class ItemPanel extends JPanel {

    private final BattlePanel battlePanel;

    public ItemPanel(BattlePanel battlePanel) {
        this.battlePanel = battlePanel;
        //TODO rework menu graphics
        setBounds(200, 400, 600, 200);
        setBackground(Color.BLUE);
        setLayout(null);
        addTitle();
    }

    private void addTitle() {
        JLabel title = new JLabel("Work in progress, here will be usable items", SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 20));
        title.setForeground(Color.WHITE);
        title.setBounds(100, 0, 400, 40);
        add(title);
    }

    //TODO create possibilty of using items

    public void refresh(){
        //TODO
    }
}
