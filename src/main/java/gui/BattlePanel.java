package gui;

import game.Battle;

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
    private final ConfirmationPanel confirmation;
    private final ActionStopperPanel actionStopper;
    private JButton exitButton;

    public BattlePanel(GUI gui) {
        ImageIcon image = new ImageIcon(getClass().getResource("/background.png"));

        turns = new TurnPanel(this);
        characters = new CharactersPanel(this) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image.getImage(), 0, 0, null);
            }
        };

        skills = new SkillsPanel(this);
        bOptions = new BattleOptionsPanel(this);
        targeting = new TargetingPanel(this);
        analyze = new AnalyzePanel(this);
        logs = new LogsPanel(this);
        confirmation = new ConfirmationPanel(this);
        actionStopper = new ActionStopperPanel(this);
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
        add(confirmation);
        add(actionStopper);
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
            confirmation.setVisible(false);
            logs.setVisible(true);
        }else{
            turns.getLogsButton().setVisible(true);
            logs.setVisible(false);
            characters.setVisible(true);
            turns.setVisible(true);
            bOptions.setVisible(true);
            exitButton.setVisible(true);
            switch (panel) {
                case Skills -> {
                    confirmation.setVisible(false);
                    analyze.setVisible(false);
                    targeting.setVisible(false);
                    actionStopper.setVisible(false);
                    skills.setVisible(true);
                }
                case Analyze -> {
                    confirmation.setVisible(false);
                    targeting.setVisible(false);
                    skills.setVisible(false);
                    actionStopper.setVisible(false);
                    analyze.setVisible(true);
                }
                case Targeting -> {
                    confirmation.setVisible(false);
                    analyze.setVisible(false);
                    skills.setVisible(false);
                    actionStopper.setVisible(false);
                    targeting.setVisible(true);
                }
                case Confirmation -> {
                    confirmation.changeActionInfo();
                    analyze.setVisible(false);
                    skills.setVisible(false);
                    targeting.setVisible(false);
                    actionStopper.setVisible(false);
                    confirmation.setVisible(true);
                }
                case ActionStopper -> {
                    bOptions.setVisible(false);
                    analyze.setVisible(false);
                    skills.setVisible(false);
                    targeting.setVisible(false);
                    confirmation.setVisible(false);
                    actionStopper.setVisible(true);
                    turns.getLogsButton().setVisible(false);
                }
            }
        }
    }

    public TurnPanel getTurns() {
        return turns;
    }

    public CharactersPanel getCharacters() {
        return characters;
    }

    public BattleOptionsPanel getBOptions() {
        return bOptions;
    }

    public SkillsPanel getSkills() {
        return skills;
    }

    public TargetingPanel getTargeting() {
        return targeting;
    }

    public AnalyzePanel getAnalyze() {
        return analyze;
    }

    public LogsPanel getLogs() {
        return logs;
    }

    public ConfirmationPanel getConfirmation() {
        return confirmation;
    }

    public Battle getBattle(){
        return characters.getBattle();
    }

    public enum Panel {
        Skills,
        Analyze,
        Targeting,
        Logs,
        Confirmation,
        ActionStopper
    }

}
