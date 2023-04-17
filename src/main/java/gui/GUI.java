package gui;

import javax.swing.*;

public class GUI extends JFrame{

    private final MenuPanel menuPanel;
    private final OptionsPanel optionsPanel;
    private final BattlePanel battlePanel;

    public GUI() {
        menuPanel = new MenuPanel(this);
        optionsPanel = new OptionsPanel(this);
        battlePanel = new BattlePanel(this);

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
        switch (panel) {
            case Menu -> {
                optionsPanel.setVisible(false);
                battlePanel.setVisible(false);
                setContentPane(menuPanel);
                pack();
                menuPanel.setVisible(true);
            }
            case Options -> {
                menuPanel.setVisible(false);
                battlePanel.setVisible(false);
                setContentPane(optionsPanel);
                pack();
                optionsPanel.setVisible(true);
            }
            case Battle -> {
                menuPanel.setVisible(false);
                optionsPanel.setVisible(false);
                setContentPane(battlePanel);
                pack();
                battlePanel.setVisible(true);
            }
        }
    }

    public BattlePanel getBattlePanel() {
        return battlePanel;
    }

    public enum Panel {
        Menu,
        Options,
        Battle
    }
}
