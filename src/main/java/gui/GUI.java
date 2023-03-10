package src.main.java.gui;

import javax.swing.*;

public class GUI extends JFrame{

    private final MenuPanel menuPanel;

    public GUI() {
        menuPanel = new MenuPanel();
        //TODO add other panels
        configureFrame();
        setVisible(true);
    }

    private void configureFrame() {
        setTitle("RPG turn Game");
        add(menuPanel);
        pack();
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void changePanel(Panel panel) {
        switch (panel) {
            case Menu -> {
                setContentPane(menuPanel);
                pack();
                menuPanel.setVisible(true);
            }
        }
    }

    public enum Panel {
        Menu
    }
}
