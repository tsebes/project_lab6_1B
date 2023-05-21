package gui;

import game.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class MenuPanel extends JPanel {

    private final GUI gui;
    private Battle battle;
    private BattleDataProvider battleDataProvider;

    public MenuPanel(GUI gui) {
        this.gui = gui;
        //TODO rework menu graphics
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.DARK_GRAY);
        setLayout(null);
        addTitle();
        addLvl1BattleButton();
        addLvl5BattleButton();
        addLvl10BattleButton();
        addBossBattleButton();
        addTutorialButton();
        addCustomBattleButton();
        addCreditsButton();
    }

    private void addTitle() {
        JLabel title = new JLabel("RPG turn fight", SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.PLAIN, 40));
        title.setForeground(Color.WHITE);
        title.setBounds(200, 50, 400, 50);
        add(title);
    }

    private void addLvl1BattleButton() {
        JButton lvl1Button = new JButton("Lvl 1 Battle");
        lvl1Button.setBackground(new Color(178,17,17));
        lvl1Button.setBounds(150, 150, 175, 50);
        lvl1Button.setForeground(Color.WHITE);
        add(lvl1Button);
        lvl1Button.addActionListener(e -> {
            battleDataProvider.getInstance().setUpBattle("LVL1BATTLE", gui);
        });
    }

    private void addLvl5BattleButton() {
        JButton lvl5Button = new JButton("Lvl 5 Battle");
        lvl5Button.setBackground(new Color(178,17,17));
        lvl5Button.setBounds(475, 150, 175, 50);
        lvl5Button.setForeground(Color.WHITE);
        add(lvl5Button);
        lvl5Button.addActionListener(e -> {
            battleDataProvider.getInstance().setUpBattle("LVL5BATTLE", gui);
        });
    }

    private void addLvl10BattleButton() {
        JButton lvl10Button = new JButton("Lvl 10 Battle");
        lvl10Button.setBackground(new Color(178,17,17));
        lvl10Button.setBounds(150, 225, 175, 50);
        lvl10Button.setForeground(Color.WHITE);
        add(lvl10Button);
        lvl10Button.addActionListener(e -> {
            battleDataProvider.getInstance().setUpBattle("LVL10BATTLE", gui);
        });
    }

    private void addBossBattleButton() {
        JButton bossButton = new JButton("Boss Battle");
        bossButton.setBackground(new Color(178,17,17));
        bossButton.setBounds(475, 225, 175, 50);
        bossButton.setForeground(Color.WHITE);
        add(bossButton);
        bossButton.addActionListener(e -> {
            //TODO set up battle and start it
            //gui.changePanel(GUI.Panel.Battle);
        });
    }

    private void addTutorialButton() {
        JButton tutorialButton = new JButton("Tutorial");
        tutorialButton.setBackground(new Color(178,17,17));
        tutorialButton.setBounds(300, 300, 200, 50);
        tutorialButton.setForeground(Color.WHITE);
        add(tutorialButton);
        tutorialButton.addActionListener(e -> {
            gui.changePanel(GUI.Panel.Tutorial);
        });
    }

    private void addCustomBattleButton() {
        JButton CustomBattleButton = new JButton("Custom Battle");
        CustomBattleButton.setBackground(new Color(178,17,17));
        CustomBattleButton.setBounds(300, 375, 200, 50);
        CustomBattleButton.setForeground(Color.WHITE);
        add(CustomBattleButton);
        CustomBattleButton.addActionListener(e -> {
            gui.changePanel(GUI.Panel.CustomBattle);
        });
    }

    private void addCreditsButton() {
        JButton creditsButton = new JButton("Credits");
        creditsButton.setBackground(new Color(178,17,17));
        creditsButton.setBounds(300, 450, 200, 50);
        creditsButton.setForeground(Color.WHITE);
        add(creditsButton);
        creditsButton.addActionListener(e -> {
            gui.changePanel(GUI.Panel.Credits);
        });
    }

}