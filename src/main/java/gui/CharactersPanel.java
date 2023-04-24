package gui;

import game.Battle;
import game.Character;
import game.Enemy;
import game.Hero;

import javax.swing.*;
import javax.swing.Timer;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;


public class CharactersPanel extends JPanel {

    private final BattlePanel battlePanel;
    private List<CharacterButton> enemyButtons = new ArrayList<>();
    private List<CharacterButton> allyButtons = new ArrayList<>();
    private Battle battle;
    private JButton attackerButton;
    private Image bgImage;

    public CharactersPanel(BattlePanel battlePanel) {
        this.battlePanel = battlePanel;
        //TODO rework menu graphics
        setBounds(0, 0, 600, 400);
        setBackground(Color.WHITE);
        setLayout(null);
    }

    public void setUpCharacters(Battle battle){
        //TODO rework function to get characters from battle

        //Removing everything from previous fight
        Component[] components = this.getComponents();
        for (Component component : components) {
            this.remove(component);
        }
        enemyButtons.clear();
        allyButtons.clear();

        //Creating new fight
        this.battle = battle;
        int i = 1;
        for(Hero currentHero: battle.getHeroArrayList()){
            addAllyButton(i, currentHero);
            i++;
        }
        i = 1;
        for(Enemy currentEnemy: battle.getEnemyArrayList()){
            addEnemyButton(i, currentEnemy);
            i++;
        }

        attackerButton = new JButton();
        attackerButton.setOpaque(false);
        attackerButton.setContentAreaFilled(false);
        attackerButton.setBorderPainted(false);
        attackerButton.setBounds(250, 200, 100, 100);
        add(attackerButton);
    }

    public void addActiveBorder(){
        deleteBorder();
        CharacterButton activeButton = getButton(battle.getActiveCharacter());
        activeButton.setBorderPainted(true);
        activeButton.setBorder(new LineBorder(Color.BLUE));
    }

    public void deleteBorder(){
        Component[] components = this.getComponents();
        for (Component component : components) {
            CharacterButton tempButton;
            if(component instanceof CharacterButton){
                tempButton = (CharacterButton) component;
                tempButton.setBorderPainted(false);
            }
        }
    }

    public void addAllyTargeting(){
        clearTargetingAll();
        for(CharacterButton allyButton: allyButtons){
            if(allyButton!=null){
                allyButton.addActionListener(e -> {
                    battlePanel.getConfirmation().changeActionInfo();
                    battlePanel.changePanel(BattlePanel.Panel.Confirmation);
                    battle.setTarget(allyButton.getButtonCharacter());
                    clearTargetingAll();
                });
            }
        }
    }

    public void addEnemyTargeting(){
        clearTargetingAll();
        for(CharacterButton enemyButton: enemyButtons) {
            if (enemyButton != null) {
                enemyButton.addActionListener(e -> {
                    battlePanel.getConfirmation().changeActionInfo();
                    battlePanel.changePanel(BattlePanel.Panel.Confirmation);
                    battle.setTarget(enemyButton.getButtonCharacter());
                    clearTargetingAll();
                });
            }
        }
    }

    public void animate(){
        Character aCharacter = battle.getActiveCharacter();
        CharacterButton button = getButton(aCharacter);
        ImageIcon idleImage = new ImageIcon(getClass().getResource("/" + aCharacter.getName() + ".gif"));
        ImageIcon attackImage = new ImageIcon(getClass().getResource("/" + aCharacter.getName() + "-attack.gif"));
        button.setIcon(null);
        attackerButton.setIcon(attackImage);

        Timer activeTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                button.setIcon(idleImage);
                attackerButton.setIcon(null);
                //TODO check here if action actually attacks anyone
                for(Character character: battle.getTargetsArrayList()){
                    ImageIcon characterIdleImage = new ImageIcon(getClass().getResource("/" +character.getName() + ".gif"));
                    ImageIcon characterHitImage = new ImageIcon(getClass().getResource("/" + character.getName() + "-hit.gif"));
                    getButton(character).setIcon(characterHitImage);
                    if(character.getCurrentHealthPoints() > 0){
                        Timer returnToIdleTimer = new Timer(1000, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent arg0) {
                                if(getButton(character).getIcon()!=null){
                                    getButton(character).setIcon(characterIdleImage);
                                }
                            }
                        });
                        returnToIdleTimer.setRepeats(false);
                        returnToIdleTimer.start();
                    }
                    else{
                        Timer showDieTimer = new Timer(1000, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent arg0) {
                                ImageIcon characterDieImage = new ImageIcon(getClass().getResource("/" + character.getName() + "-die.gif"));
                                getButton(character).setIcon(characterDieImage);
                                Timer deleteButtonTimer = new Timer(1000, new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent arg0) {
                                        getButton(character).setIcon(null);
                                        if(enemyButtons.contains(getButton(character))){
                                            enemyButtons.remove(getButton(character));
                                        }
                                        if(allyButtons.contains(getButton(character))){
                                            allyButtons.remove(getButton(character));
                                        }
                                    }
                                });
                                deleteButtonTimer.setRepeats(false);
                                deleteButtonTimer.start();
                            }
                        });
                        showDieTimer.setRepeats(false);
                        showDieTimer.start();
                    }
                }
            }
        });
        activeTimer.setRepeats(false);
        activeTimer.start();
    }

    public void clearTargetingAll(){
        for(CharacterButton enemyButton: enemyButtons){
            if(enemyButton != null) clearTargetingButton(enemyButton);
        }
        for(CharacterButton allyButton: allyButtons){
            if(allyButton != null) clearTargetingButton(allyButton);
        }
    }

    private void clearTargetingButton(CharacterButton button){
        ActionListener[] listeners = button.getActionListeners();
        if (listeners == null) {
            return;
        }
        for (ActionListener listener : listeners) {
            button.removeActionListener(listener);
        }
    }

    private void addEnemyButton(int location, Character character) {
        ImageIcon characterImage = new ImageIcon(getClass().getResource("/"+ character.getName() +".gif"));
        CharacterButton characterButton = new CharacterButton(characterImage);
        characterButton.setCharacter(character);
        switch(location){
            case 1:
                characterButton.setBounds(350, 275, 100, 100);
                break;
            case 2:
                characterButton.setBounds(350, 125, 100, 100);
                break;
            case 3:
                characterButton.setBounds(475, 250, 100, 100);
                break;
            case 4:
                characterButton.setBounds(475, 100, 100, 100);
                break;
        }
        characterButton.setOpaque(false);
        characterButton.setContentAreaFilled(false);
        characterButton.setBorderPainted(false);
        enemyButtons.add(characterButton);
        add(characterButton);
    }

    private void addAllyButton(int location, Character character) {
        ImageIcon characterImage = new ImageIcon(getClass().getResource("/"+ character.getName() +".gif"));
        CharacterButton characterButton = new CharacterButton(characterImage);
        characterButton.setCharacter(character);
        switch(location){
            case 1:
                characterButton.setBounds(150, 275, 100, 100);
                break;
            case 2:
                characterButton.setBounds(150, 125, 100, 100);
                break;
            case 3:
                characterButton.setBounds(25, 250, 100, 100);
                break;
            case 4:
                characterButton.setBounds(25, 100, 100, 100);
                break;
        }
        characterButton.setOpaque(false);
        characterButton.setContentAreaFilled(false);
        characterButton.setBorderPainted(false);
        allyButtons.add(characterButton);
        add(characterButton);
    }

    public CharacterButton getButton(Character character) {
        for(CharacterButton button: enemyButtons){
            if(button.getButtonCharacter() == character){
                return button;
            }
        }
        for(CharacterButton button: allyButtons){
            if(button.getButtonCharacter() == character){
                return button;
            }
        }
        //TODO delete error information popup
        System.out.println("failed to find button");
        return null;
    }

    public Battle getBattle() {
        return battle;
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.drawImage(bgImage, 0, 0, null);
    }
}
