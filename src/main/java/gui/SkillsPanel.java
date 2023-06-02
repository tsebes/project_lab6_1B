package gui;

import game.Action;
import game.Character;
import game.Skill;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SkillsPanel  extends JPanel {

    private final BattlePanel battlePanel;
    private Character character;
    private int currentPage;
    private int numberOfPages;
    private JButton skill1;
    private JButton skill2;
    private JButton skill3;
    private JButton skill4;
    private JButton skill1Info;
    private JButton skill2Info;
    private JButton skill3Info;
    private JButton skill4Info;
    private JButton pageLeft;
    private JButton pageRight;

    public SkillsPanel(BattlePanel battlePanel) {
        this.battlePanel = battlePanel;
        //TODO rework menu graphics
        setBounds(200, 400, 600, 200);
        setLayout(null);
        addPageLeftButton();
        addPageRightButton();
        addSkillButton(1);
        addSkillButton(2);
        addSkillButton(3);
        addSkillButton(4);
    }

    private void addPageLeftButton() {
        JButton leftButton = new JButton("<");
        leftButton.setBackground(new Color(178,17,17));
        leftButton.setBounds(25, 75, 50, 50);
        leftButton.setForeground(Color.WHITE);
        pageLeft = leftButton;
        add(leftButton);
        leftButton.addActionListener(e -> {
            currentPage -= 1;
            refresh();
        });
    }

    private void addPageRightButton() {
        JButton rightButton = new JButton(">");
        rightButton.setBackground(new Color(178,17,17));
        rightButton.setBounds(525, 75, 50, 50);
        rightButton.setForeground(Color.WHITE);
        pageRight = rightButton;
        add(rightButton);
        rightButton.addActionListener(e -> {
            currentPage += 1;
            refresh();
        });
    }

    private void addSkillButton(int buttonNumber) {
        JButton skillButton = new JButton("");
        skillButton.setBackground(new Color(178,17,17));
        skillButton.setForeground(Color.WHITE);

        JButton skillInfoButton = new JButton("?");
        skillInfoButton.setBackground(Color.BLACK);
        skillInfoButton.setForeground(Color.WHITE);
        skillInfoButton.setFont(new Font("Serif", Font.PLAIN, 5));

        switch (buttonNumber){
            case 1:
                skillButton.setBounds(100, 25, 175, 65);
                skillInfoButton.setBounds(275, 25, 10, 10);
                skill1 = skillButton;
                skill1Info = skillInfoButton;
                break;
            case 2:
                skillButton.setBounds(325, 25, 175, 65);
                skillInfoButton.setBounds(500, 25, 10, 10);
                skill2 = skillButton;
                skill2Info = skillInfoButton;
                break;
            case 3:
                skillButton.setBounds(100, 110, 175, 65);
                skillInfoButton.setBounds(275, 110, 10, 10);
                skill3 = skillButton;
                skill3Info = skillInfoButton;
                break;
            case 4:
                skillButton.setBounds(325, 110, 175, 65);
                skillInfoButton.setBounds(500, 110, 10, 10);
                skill4 = skillButton;
                skill4Info = skillInfoButton;
                break;
        }
        add(skillButton);
        add(skillInfoButton);
    }

    private void clearActionListeners(JButton button){
        ActionListener[] listeners = button.getActionListeners();
        if (listeners == null) {
            return;
        }
        for (ActionListener listener : listeners) {
            button.removeActionListener(listener);
        }
    }

    private void setUpSkillToButton(JButton button, int number){
        Skill skill = character.getAvailableSkills().get(currentPage*4 + number - 1);
        button.setText(skill.getName());

        button.addActionListener(e -> {
            battlePanel.getBattle().setCurrentAction(Action.SKILL);
            battlePanel.getBattle().setCurrentSkill(skill);
            if(skill.getAOE()){
                if(skill.getTargetingEnemies()){
                    battlePanel.getBattle().setTarget(battlePanel.getBattle().getEnemyArrayList());
                }else{
                    battlePanel.getBattle().setTarget(battlePanel.getBattle().getHeroArrayList());
                }
                battlePanel.changePanel(BattlePanel.Panel.Confirmation);
            }else{
                battlePanel.changePanel(BattlePanel.Panel.Targeting);
                battlePanel.getTargeting().changeInformationPanel("Using Skill: " + skill.getName());
                if(skill.getTargetingEnemies()){
                    battlePanel.getCharacters().addEnemyTargeting();
                }else{
                    battlePanel.getCharacters().addAllyTargeting();
                }
            }
        });
    }

    public void setUpSkillInfoToButton(JButton button, int number){
        Skill skill = character.getAvailableSkills().get(currentPage*4 + number - 1);
        button.addActionListener(e -> {
            battlePanel.getSkillInfo().setSkill(skill);
            battlePanel.changePanel(BattlePanel.Panel.SkillInfo);
        });
    }

    public void refresh(){
        Character activeCharacter = battlePanel.getBattle().getActiveCharacter();
        if(character == null || character != activeCharacter){
            character = activeCharacter;
            currentPage = 0;
            numberOfPages = (int)Math.ceil((double)character.getAvailableSkills().size()/4.0);
        }else{
            if(currentPage<0){
                currentPage = numberOfPages - 1;
            }else if(currentPage == numberOfPages){
                currentPage = 0;
            }
        }
        clearActionListeners(skill1);
        clearActionListeners(skill2);
        clearActionListeners(skill3);
        clearActionListeners(skill4);
        clearActionListeners(skill1Info);
        clearActionListeners(skill2Info);
        clearActionListeners(skill3Info);
        clearActionListeners(skill4Info);

        if(currentPage < numberOfPages - 1 || character.getAvailableSkills().size()%4 == 0){
            skill1.setVisible(true);
            setUpSkillToButton(skill1,1);
            setUpSkillInfoToButton(skill1Info,1);
            skill1Info.setVisible(true);
            skill2.setVisible(true);
            setUpSkillToButton(skill2,2);
            setUpSkillInfoToButton(skill2Info,2);
            skill2Info.setVisible(true);
            skill3.setVisible(true);
            setUpSkillToButton(skill3,3);
            setUpSkillInfoToButton(skill3Info,3);
            skill3Info.setVisible(true);
            skill4.setVisible(true);
            setUpSkillToButton(skill4,4);
            setUpSkillInfoToButton(skill4Info,4);
            skill4Info.setVisible(true);

        }else if(character.getAvailableSkills().size()%4 == 3){
            skill1.setVisible(true);
            setUpSkillToButton(skill1,1);
            setUpSkillInfoToButton(skill1Info,1);
            skill1Info.setVisible(true);
            skill2.setVisible(true);
            setUpSkillToButton(skill2,2);
            setUpSkillInfoToButton(skill2Info,2);
            skill2Info.setVisible(true);
            skill3.setVisible(true);
            setUpSkillToButton(skill3,3);
            setUpSkillInfoToButton(skill3Info,3);
            skill3Info.setVisible(true);
            skill4.setVisible(false);
            skill4Info.setVisible(false);
        }else if(character.getAvailableSkills().size()%4 == 2){
            skill1.setVisible(true);
            setUpSkillToButton(skill1,1);
            setUpSkillInfoToButton(skill1Info,1);
            skill1Info.setVisible(true);
            skill2.setVisible(true);
            setUpSkillToButton(skill2,2);
            setUpSkillInfoToButton(skill2Info,2);
            skill2Info.setVisible(true);
            skill3.setVisible(false);
            skill3Info.setVisible(false);
            skill4.setVisible(false);
            skill4Info.setVisible(false);
        }else{
            skill1.setVisible(true);
            setUpSkillToButton(skill1,1);
            setUpSkillInfoToButton(skill1Info,1);
            skill1Info.setVisible(true);
            skill2.setVisible(false);
            skill2Info.setVisible(false);
            skill3.setVisible(false);
            skill3Info.setVisible(false);
            skill4.setVisible(false);
            skill4Info.setVisible(false);
        }
    }

}