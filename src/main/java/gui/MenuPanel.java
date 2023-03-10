package src.main.java.gui;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {

    public MenuPanel() {
        //TODO rework menu graphics
        setPreferredSize(new Dimension(540, 440));
        setBackground(Color.GRAY);
        setLayout(null);
        addTitle();
        addTutorialButton();
        //TODO add other buttons
    }

    private void addTitle() {
        JLabel title = new JLabel("RPG turn fight");
        title.setFont(new Font("Serif", Font.PLAIN, 40));
        title.setBounds(80, 120, 400, 50);
        add(title);
    }

    private void addTutorialButton() {
        JButton tutorialButton = new JButton("Tutorial");
        tutorialButton.setBackground(new java.awt.Color(113, 167, 148));
        tutorialButton.setBounds(100, 240, 100, 50);
        add(tutorialButton);
        tutorialButton.addActionListener(e -> {
            //TODO show tutorial panel
        });
    }
}