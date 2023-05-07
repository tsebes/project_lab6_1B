package gui;

import game.Enemy;

import javax.swing.*;

public class GUI extends JFrame{

    private final MenuPanel menuPanel;
    private final CreditsPanel creditsPanel;
    private final BattlePanel battlePanel;
    private final TutorialPanel tutorialPanel;
    private final CustomBattlePanel customBattlePanel;

    public GUI() {
        menuPanel = new MenuPanel(this);
        creditsPanel = new CreditsPanel(this);
        battlePanel = new BattlePanel(this);
        tutorialPanel = new TutorialPanel(this);
        customBattlePanel = new CustomBattlePanel(this);

        //TODO add other panels
        configureFrame();
        setVisible(true);
    }

    private void configureFrame() {
        add(menuPanel);
        pack();
        setTitle("RPG turn Game");
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void changePanel(Panel panel) {
        creditsPanel.setVisible(false);
        battlePanel.setVisible(false);
        menuPanel.setVisible(false);
        switch (panel) {
            case Menu -> {
                setContentPane(menuPanel);
                pack();
                menuPanel.setVisible(true);
            }
            case Credits -> {
                setContentPane(creditsPanel);
                pack();
                creditsPanel.setVisible(true);
            }
            case Battle -> {
                setContentPane(battlePanel);
                pack();
                battlePanel.setVisible(true);
                if(battlePanel.getBattle().getActiveCharacter() instanceof Enemy){
                    battlePanel.changePanel(BattlePanel.Panel.ActionStopper);
                }
                else{
                    battlePanel.changePanel(BattlePanel.Panel.Skills);
                }
            }
            case Tutorial -> {
                setContentPane(tutorialPanel);
                pack();
                tutorialPanel.setVisible(true);
            }
            case CustomBattle -> {
                setContentPane(customBattlePanel);
                pack();
                customBattlePanel.setVisible(true);
            }
        }
    }

    public BattlePanel getBattlePanel() {
        return battlePanel;
    }

    public enum Panel {
        Menu,
        Credits,
        Battle,
        Tutorial,
        CustomBattle
    }
}
