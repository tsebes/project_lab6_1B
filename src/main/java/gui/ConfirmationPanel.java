package gui;

import game.Action;
import game.Character;

import javax.swing.*;
import java.awt.*;

public class ConfirmationPanel extends JPanel {

    private final BattlePanel battlePanel;
    private final TurnPanel turnPanel;
    private JLabel actionTitle;
    private JLabel actionInfo;
    private JLabel actionTargets;

    public ConfirmationPanel(BattlePanel battlePanel, TurnPanel turnPanel) {
        this.battlePanel = battlePanel;
        this.turnPanel = turnPanel;
        //TODO rework menu graphics
        setBounds(200, 400, 600, 200);
        setVisible(false);
        setLayout(null);
        addActionTitle();
        addActionInfo();
        addActionTargets();
        addCancelButton();
        addConfirmButton();
    }

    private void addActionTitle() {
        JLabel title = new JLabel("Title", SwingConstants.CENTER);
        actionTitle = title;
        title.setFont(new Font("Serif", Font.PLAIN, 30));
        title.setForeground(Color.BLACK);
        title.setBounds(0, 10, 600, 30);
        add(title);
    }

    private void addActionInfo() {
        JLabel information = new JLabel("Title", SwingConstants.CENTER);
        actionInfo = information;
        information.setFont(new Font("Serif", Font.PLAIN, 20));
        information.setForeground(Color.BLACK);
        information.setBounds(0, 40, 600, 35);
        add(information);
    }

    private void addActionTargets() {
        JLabel targets = new JLabel("Targets", SwingConstants.CENTER);
        actionTargets = targets;
        targets.setFont(new Font("Serif", Font.PLAIN, 20));
        targets.setForeground(Color.BLACK);
        targets.setBounds(0, 80, 600, 35);
        add(targets);
    }

    public void changeActionInfo(){
        String actionTitle = "";
        String actionDescription = "";
        String actionTargets = "";
        actionTitle += battlePanel.getBattle().getCurrentAction().toString();
        if(battlePanel.getBattle().getCurrentAction() == Action.SKILL){
            actionDescription += "Using skill: " + battlePanel.getBattle().getCurrentSkill().getName();
        }
        if(battlePanel.getBattle().getCurrentAction() == Action.ITEM){
            actionDescription += "Using item: " + battlePanel.getBattle().getCurrentItem().getName();
        }
        if(battlePanel.getBattle().getCurrentAction() != Action.GUARD){
            actionTargets += "Targets: ";
            for(Character character: battlePanel.getBattle().getTargetsArrayList()){
                actionTargets += character.getName() + ", ";
            }
            actionTargets = actionTargets.substring(0, actionTargets.length() - 2);
        }
        this.actionTitle.setText(actionTitle);
        this.actionInfo.setText(actionDescription);
        this.actionTargets.setText(actionTargets);
    }

    private void addCancelButton() {
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setOpaque(false);
        cancelButton.setContentAreaFilled(false);
        cancelButton.setBorderPainted(false);
        cancelButton.setBounds(75, 125, 150, 50);
        cancelButton.setFont(new Font("Serif", Font.BOLD, 20));
        cancelButton.setForeground(Color.BLACK);
        add(cancelButton);
        cancelButton.addActionListener(e -> {
            battlePanel.changePanel(BattlePanel.Panel.Skills);
            battlePanel.getBattle().clearCurrentAction();
        });
    }

    private void addConfirmButton() {
        JButton confirmButton = new JButton("Confirm");
        confirmButton.setOpaque(false);
        confirmButton.setContentAreaFilled(false);
        confirmButton.setBorderPainted(false);
        confirmButton.setBounds(375, 125, 150, 50);
        confirmButton.setForeground(Color.BLACK);
        confirmButton.setFont(new Font("Serif", Font.BOLD, 20));
        add(confirmButton);
        confirmButton.addActionListener(e -> {
            battlePanel.changePanel(BattlePanel.Panel.Skills);
            battlePanel.getBattle().doCurrentAction();
            });
    }

}