package gui;

import game.Action;
import game.Character;

import javax.swing.*;
import java.awt.*;

public class ConfirmationPanel extends JPanel {

    private final BattlePanel battlePanel;
    private JLabel actionTitle;
    private JLabel actionInfo;
    private JLabel actionTargets;

    public ConfirmationPanel(BattlePanel battlePanel) {
        this.battlePanel = battlePanel;
        //TODO rework menu graphics
        setBounds(200, 400, 600, 200);
        setBackground(Color.CYAN);
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
        title.setFont(new Font("Serif", Font.PLAIN, 35));
        title.setForeground(Color.BLACK);
        title.setBounds(0, 0, 600, 35);
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
        cancelButton.setBackground(new Color(178,17,17));
        cancelButton.setBounds(75, 125, 150, 50);
        cancelButton.setForeground(Color.WHITE);
        add(cancelButton);
        cancelButton.addActionListener(e -> {
            battlePanel.changePanel(BattlePanel.Panel.Skills);
            battlePanel.getBattle().clearCurrentAction();
        });
    }

    private void addConfirmButton() {
        JButton confirmButton = new JButton("Confirm");
        confirmButton.setBackground(new Color(178,17,17));
        confirmButton.setBounds(375, 125, 150, 50);
        confirmButton.setForeground(Color.WHITE);
        add(confirmButton);
        confirmButton.addActionListener(e -> {
            //TODO do chosen action
            battlePanel.changePanel(BattlePanel.Panel.Skills);
            battlePanel.getBattle().doCurrentAction();
            });
    }

}