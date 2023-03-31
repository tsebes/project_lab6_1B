package src.main.java.gui;

import javax.swing.*;
import java.awt.*;

public class BattlePanel extends JPanel {

    private final GUI gui;
    private final TurnPanel turns;
    private final CharactersPanel characters;
    private final BattleOptionsPanel bOptions;
    private final SkillsPanel skills;
    private final TargetingPanel targeting;
    private final AnalyzePanel analyze;
    private final LogsPanel logs;
    private JButton exitButton;

    public BattlePanel(GUI gui) {
        turns = new TurnPanel(this);
        characters = new CharactersPanel(this);
        skills = new SkillsPanel(this);
        bOptions = new BattleOptionsPanel(this);
        targeting = new TargetingPanel(this);
        analyze = new AnalyzePanel(this);
        logs = new LogsPanel(this);
        this.gui = gui;
        //TODO rework menu graphics
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.DARK_GRAY);
        setLayout(null);
        addExitButton();
        add(turns);
        add(characters);
        add(bOptions);
        add(skills);
        add(targeting);
        add(analyze);
        add(logs);
    }

    private void addExitButton() {
        exitButton = new JButton("X");
        exitButton.setBackground(Color.DARK_GRAY);
        exitButton.setBounds(0, 0, 20, 20);
        exitButton.setForeground(Color.RED);
        add(exitButton);
        exitButton.addActionListener(e -> {
            gui.changePanel(GUI.Panel.Menu);
        });
    }

    public void changePanel(Panel panel) {
        if(panel == Panel.Logs){
            skills.setVisible(false);
            analyze.setVisible(false);
            targeting.setVisible(false);
            characters.setVisible(false);
            turns.setVisible(false);
            bOptions.setVisible(false);
            exitButton.setVisible(false);
            logs.setVisible(true);
        }else{
            logs.setVisible(false);
            characters.setVisible(true);
            turns.setVisible(true);
            bOptions.setVisible(true);
            exitButton.setVisible(true);
            switch (panel) {
                case Skills -> {
                    analyze.setVisible(false);
                    targeting.setVisible(false);
                    skills.setVisible(true);
                }
                case Analyze -> {
                    targeting.setVisible(false);
                    skills.setVisible(false);
                    analyze.setVisible(true);
                }
                case Targeting -> {
                    analyze.setVisible(false);
                    skills.setVisible(false);
                    targeting.setVisible(true);
                }
            }
        }
    }

    public enum Panel {
        Skills,
        Analyze,
        Targeting,
        Logs
    }

}
