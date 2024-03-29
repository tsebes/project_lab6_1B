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
    private final ItemPanel items;
    private final SkillsInfoPanel skillInfo;
    private final ItemsInfoPanel itemInfo;
    private final ConfirmationPanel confirmation;
    private final ActionStopperPanel actionStopper;
    private JButton exitButton;

    public BattlePanel(GUI gui) {
        ImageIcon charactersImage = new ImageIcon(getClass().getResource("/background.png"));
        ImageIcon turnImage = new ImageIcon(getClass().getResource("/turn-background.png"));
        ImageIcon bOptionsImage = new ImageIcon(getClass().getResource("/battle options-background.png"));
        ImageIcon bActionImage = new ImageIcon(getClass().getResource("/battle action-background.png"));
        ImageIcon logsImage = new ImageIcon(getClass().getResource("/log-background.png"));
        turns = new TurnPanel(this){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(turnImage.getImage(), 0, 0, null);
            }
        };
        characters = new CharactersPanel(this){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(charactersImage.getImage(), 0, 0, null);
            }
        };
        bOptions = new BattleOptionsPanel(this){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bOptionsImage.getImage(), 0, 0, null);
            }
        };
        logs = new LogsPanel(this){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(logsImage.getImage(), 0, 0, null);
            }
        };
        skills = new SkillsPanel(this){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bActionImage.getImage(), 0, 0, null);
            }
        };
        targeting = new TargetingPanel(this){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bActionImage.getImage(), 0, 0, null);
            }
        };
        analyze = new AnalyzePanel(this){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bActionImage.getImage(), 0, 0, null);
            }
        };
        confirmation = new ConfirmationPanel(this, turns){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bActionImage.getImage(), 0, 0, null);
            }
        };
        items = new ItemPanel(this){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bActionImage.getImage(), 0, 0, null);
            }
        };
        skillInfo = new SkillsInfoPanel(this){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bActionImage.getImage(), 0, 0, null);
            }
        };
        itemInfo = new ItemsInfoPanel(this){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bActionImage.getImage(), 0, 0, null);
            }
        };
        actionStopper = new ActionStopperPanel(this);
        this.gui = gui;
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
        add(items);
        add(skillInfo);
        add(itemInfo);
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

    public void goToBattleEnd(){
        gui.changePanel(GUI.Panel.BattleEnd);
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
            exitButton.setVisible(true);
            turns.addLogActionListener();
            logs.setVisible(false);
            characters.setVisible(true);
            turns.setVisible(true);
            bOptions.setVisible(true);
            exitButton.setVisible(true);
            confirmation.setVisible(false);
            analyze.setVisible(false);
            targeting.setVisible(false);
            actionStopper.setVisible(false);
            skills.setVisible(false);
            items.setVisible(false);
            skillInfo.setVisible(false);
            itemInfo.setVisible(false);
            switch (panel) {
                case Skills -> {
                    skills.refresh();
                    skills.setVisible(true);
                }
                case Analyze -> {
                    analyze.refresh();
                    analyze.setVisible(true);
                }
                case Targeting -> {
                    targeting.setVisible(true);
                }
                case Items -> {
                    items.refresh();
                    items.setVisible(true);
                }
                case SkillInfo -> {
                    skillInfo.refresh();
                    skillInfo.setVisible(true);
                }
                case ItemInfo -> {
                    itemInfo.refresh();
                    itemInfo.setVisible(true);
                }
                case Confirmation -> {
                    confirmation.changeActionInfo();
                    confirmation.setVisible(true);
                }
                case ActionStopper -> {
                    exitButton.setVisible(false);
                    bOptions.setVisible(false);
                    actionStopper.setVisible(true);
                    turns.deleteLogActionListener();
                }
            }
        }
    }

    public void setUpNewBattle(Battle battle){
        characters.setUpCharacters(battle);
        logs.deleteLogs();
        turns.setLogsButtonText("");
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

    public ItemPanel getItems() {
        return items;
    }

    public SkillsInfoPanel getSkillInfo() {
        return skillInfo;
    }

    public ItemsInfoPanel getItemInfo() {
        return itemInfo;
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
        ActionStopper,
        SkillInfo,
        Items,
        ItemInfo
    }

}
