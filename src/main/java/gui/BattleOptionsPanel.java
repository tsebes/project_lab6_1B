package gui;

import javax.swing.*;
import java.awt.*;
import game.Action;

public class BattleOptionsPanel extends JPanel {

    private final BattlePanel battlePanel;

    public BattleOptionsPanel(BattlePanel battlePanel) {
        this.battlePanel = battlePanel;
        //TODO rework menu graphics
        setBounds(0, 400, 200, 200);
        setBackground(Color.GRAY);
        setLayout(null);
        addAttackButton();
        addSkillsButton();
        addGuardButton();
        addAnalyzeButton();
        addItemButton();
    }

    private void addAttackButton() {
        JButton addAttackButton = new JButton("Attack");
        //addAttackButton.setBackground(Color.DARK_GRAY);
        addAttackButton.setBounds(0, 0, 200, 40);
        addAttackButton.setForeground(Color.RED);
        add(addAttackButton);
        addAttackButton.addActionListener(e -> {
            //TODO change action to basic attack
            battlePanel.getBattle().clearCurrentAction();
            battlePanel.getBattle().setCurrentAction(Action.BASICATTACK);
            battlePanel.changePanel(BattlePanel.Panel.Targeting);
            battlePanel.getTargeting().changeInformationPanel("Using basic attack");
            battlePanel.getCharacters().addEnemyTargeting();
        });
    }

    private void addSkillsButton() {
        JButton addSkillsButton = new JButton("Skill");
        //addSkillsButton.setBackground(Color.DARK_GRAY);
        addSkillsButton.setBounds(0, 40, 200, 40);
        addSkillsButton.setForeground(Color.RED);
        add(addSkillsButton);
        addSkillsButton.addActionListener(e -> {
            battlePanel.getBattle().clearCurrentAction();
            battlePanel.getBattle().setCurrentAction(Action.SKILL);
            //TODO show current character skills
            battlePanel.changePanel(BattlePanel.Panel.Skills);
        });
    }

    private void addGuardButton() {
        JButton addGuardButton = new JButton("Guard");
        //addGuardButton.setBackground(Color.DARK_GRAY);
        addGuardButton.setBounds(0, 80, 200, 40);
        addGuardButton.setForeground(Color.RED);
        add(addGuardButton);
        addGuardButton.addActionListener(e -> {
            battlePanel.getBattle().clearCurrentAction();
            battlePanel.getBattle().setCurrentAction(Action.GUARD);
            //TODO show confirmation screen
        });
    }

    private void addAnalyzeButton() {
        JButton addAnalyzeButton = new JButton("Analyze");
        //addAnalyzeButton.setBackground(Color.DARK_GRAY);
        addAnalyzeButton.setBounds(0, 120, 200, 40);
        addAnalyzeButton.setForeground(Color.RED);
        add(addAnalyzeButton);
        addAnalyzeButton.addActionListener(e -> {
            battlePanel.getBattle().clearCurrentAction();
            battlePanel.getBattle().setCurrentAction(Action.ANALYZE);
            battlePanel.changePanel(BattlePanel.Panel.Targeting);
            battlePanel.getTargeting().changeInformationPanel("Using analyze");
            battlePanel.getCharacters().addEnemyTargeting();
            //TODO make analyze work
        });
    }

    private void addItemButton() {
        JButton addItemButton = new JButton("Item");
        //addItemButton.setBackground(Color.DARK_GRAY);
        addItemButton.setBounds(0, 160, 200, 40);
        addItemButton.setForeground(Color.RED);
        add(addItemButton);
        addItemButton.addActionListener(e -> {
            battlePanel.getBattle().clearCurrentAction();
            battlePanel.getBattle().setCurrentAction(Action.ITEM);
            //TODO show items panel
        });
    }


}