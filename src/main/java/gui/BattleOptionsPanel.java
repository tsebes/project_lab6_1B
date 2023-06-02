package gui;

import javax.swing.*;
import javax.swing.border.LineBorder;
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
        JButton attackButton = new JButton("Attack");
        attackButton.setBackground(Color.WHITE);
        attackButton.setBounds(0, 0, 200, 40);
        attackButton.setForeground(Color.RED);
        attackButton.setBorder(new LineBorder(Color.BLACK));
        add(attackButton);
        attackButton.addActionListener(e -> {
            battlePanel.getBattle().clearCurrentAction();
            battlePanel.getBattle().setCurrentAction(Action.BASICATTACK);
            battlePanel.changePanel(BattlePanel.Panel.Targeting);
            battlePanel.getTargeting().changeInformationPanel("Using basic attack");
            battlePanel.getCharacters().addEnemyTargeting();
        });
    }

    private void addSkillsButton() {
        JButton skillsButton = new JButton("Skill");
        skillsButton.setBackground(Color.WHITE);
        skillsButton.setBounds(0, 40, 200, 40);
        skillsButton.setForeground(Color.RED);
        skillsButton.setBorder(new LineBorder(Color.BLACK));
        add(skillsButton);
        skillsButton.addActionListener(e -> {
            battlePanel.getBattle().clearCurrentAction();
            battlePanel.getBattle().setCurrentAction(Action.SKILL);
            battlePanel.changePanel(BattlePanel.Panel.Skills);
        });
    }

    private void addGuardButton() {
        JButton guardButton = new JButton("Guard");
        guardButton.setBackground(Color.WHITE);
        guardButton.setBounds(0, 80, 200, 40);
        guardButton.setForeground(Color.RED);
        guardButton.setBorder(new LineBorder(Color.BLACK));
        add(guardButton);
        guardButton.addActionListener(e -> {
            battlePanel.getBattle().clearCurrentAction();
            battlePanel.getBattle().setCurrentAction(Action.GUARD);
            battlePanel.changePanel(BattlePanel.Panel.Confirmation);
        });
    }

    private void addAnalyzeButton() {
        JButton analyzeButton = new JButton("Analyze");
        analyzeButton.setBackground(Color.WHITE);
        analyzeButton.setBounds(0, 120, 200, 40);
        analyzeButton.setForeground(Color.RED);
        analyzeButton.setBorder(new LineBorder(Color.BLACK));
        add(analyzeButton);
        analyzeButton.addActionListener(e -> {
            battlePanel.getBattle().clearCurrentAction();
            battlePanel.getBattle().setCurrentAction(Action.ANALYZE);
            battlePanel.changePanel(BattlePanel.Panel.Targeting);
            battlePanel.getTargeting().changeInformationPanel("Using analyze");
            battlePanel.getCharacters().addTargetingAll();
        });
    }

    private void addItemButton() {
        JButton itemButton = new JButton("Item");
        itemButton.setBackground(Color.WHITE);
        itemButton.setBounds(0, 160, 200, 40);
        itemButton.setForeground(Color.RED);
        itemButton.setBorder(new LineBorder(Color.BLACK));
        add(itemButton);
        itemButton.addActionListener(e -> {
            battlePanel.getBattle().clearCurrentAction();
            battlePanel.getBattle().setCurrentAction(Action.ITEM);
            battlePanel.changePanel(BattlePanel.Panel.Items);
        });
    }


}