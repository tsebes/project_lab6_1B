package src.main.java.gui;

import src.main.java.game.AttackResistanceType;
import src.main.java.game.Character;
import src.main.java.game.Enemy;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;


public class CharactersPanel extends JPanel {

    private final BattlePanel battlePanel;
    private List<CharacterButton> enemyButtons = new ArrayList<>();
    private List<CharacterButton> allyButtons = new ArrayList<>();
    //TODO delete public knightCharacter and skeletonCharacter - used to test showing single gif
    public Character knightCharacter;
    public Character skeletonCharacter;

    public CharactersPanel(BattlePanel battlePanel) {
        this.battlePanel = battlePanel;
        //TODO rework menu graphics
        setBounds(0, 0, 600, 400);
        setBackground(Color.WHITE);
        setLayout(null);

        //TODO call this function when battle starts not in constructor
        setUpCharacters();


        //TODO delete temporary buttons
        //temporary button to test targeting
        JButton targetingAllyStartButton = new JButton("t a start");
        targetingAllyStartButton.setBounds(100, 10, 100, 20);
        targetingAllyStartButton.setOpaque(false);
        targetingAllyStartButton.setContentAreaFilled(false);
        targetingAllyStartButton.setBorderPainted(false);
        targetingAllyStartButton.addActionListener(e -> {
            clearTargetingAll();
            addAllyTargeting();
        });
        add(targetingAllyStartButton);
        //end of temporary button
        //temporary button to test targeting
        JButton targetingEnemyStartButton = new JButton("t e start");
        targetingEnemyStartButton.setBounds(200, 10, 100, 20);
        targetingEnemyStartButton.setOpaque(false);
        targetingEnemyStartButton.setContentAreaFilled(false);
        targetingEnemyStartButton.setBorderPainted(false);
        targetingEnemyStartButton.addActionListener(e -> {
            clearTargetingAll();
            addEnemyTargeting();
        });
        add(targetingEnemyStartButton);
        //end of temporary button
    }

    public void setUpCharacters(){
        //TODO rework function to get characters from battle
        Map<AttackResistanceType, Double> tempMap = null;
        Character unknownCharacter = new Enemy("unknown", 1, AttackResistanceType.PHYSICAL, 10, 10,10,10,10, tempMap);
        knightCharacter = new Enemy("knight", 1, AttackResistanceType.PHYSICAL, 10, 10,10,10,10, tempMap);
        skeletonCharacter = new Enemy("skeleton", 1, AttackResistanceType.PHYSICAL, -1, 10,10,10,10, tempMap);
        addEnemyButton(1, unknownCharacter);
        addEnemyButton(2, skeletonCharacter);
        addEnemyButton(3, unknownCharacter);
        addEnemyButton(4, unknownCharacter);
        addAllyButton(1, knightCharacter);
        addAllyButton(2, unknownCharacter);
        addAllyButton(3, unknownCharacter);
        addAllyButton(4, unknownCharacter);
    }

    public void addAllyTargeting(){
        for(CharacterButton allyButton: allyButtons){
            if(allyButton!=null){
                allyButton.addActionListener(e -> {
                    //TODO delete information popup
                    System.out.println("Clicked ally");
                    battlePanel.getConfirmation().changeActionInfo();
                    battlePanel.changePanel(BattlePanel.Panel.Confirmation);
                    clearTargetingAll();
                    //TODO change target to this enemy/ally
                });
            }
        }
    }

    public void addEnemyTargeting(){
        for(CharacterButton enemyButton: enemyButtons) {
            if (enemyButton != null) {
                enemyButton.addActionListener(e -> {
                    //TODO delete information popup
                    System.out.println("Clicked enemy");
                    battlePanel.getConfirmation().changeActionInfo();
                    battlePanel.changePanel(BattlePanel.Panel.Confirmation);
                    clearTargetingAll();
                    //TODO change target to this enemy/ally
                });
            }
        }
    }

    public void showSingleGif(CharacterButton button, String action){
        Icon priorIcon = button.getIcon();
        ImageIcon characterImage = new ImageIcon(getClass().getResource("images//" + button.getButtonCharacter().getName() + "-" + action + ".gif"));
        button.setIcon(characterImage);
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if(button.getButtonCharacter().getCurrentHealthPoints() > 0){
                    button.setIcon(priorIcon);
                }
                else{
                    button.setIcon(new ImageIcon(getClass().getResource("images//" + button.getButtonCharacter().getName() + "-die" + ".gif")));
                    Timer timer2 = new Timer(1000, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent arg0) {
                            button.setIcon(null);
                            if(enemyButtons.contains(button)){
                                enemyButtons.remove(button);
                            }
                            if(allyButtons.contains(button)){
                                allyButtons.remove(button);
                            }
                            }
                        });
                    timer2.setRepeats(false);
                    timer2.start();
                    }
                }
        });
        timer.setRepeats(false);
        timer.start();
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
        ImageIcon characterImage = new ImageIcon(getClass().getResource("images//"+ character.getName() +".gif"));
        CharacterButton characterButton = new CharacterButton(characterImage);
        characterButton.setCharacter(character);
        switch(location){
            case 1:
                characterButton.setBounds(180, 50, 100, 100);
                break;
            case 2:
                characterButton.setBounds(320, 50, 100, 100);
                break;
            case 3:
                characterButton.setBounds(40, 50, 100, 100);
                break;
            case 4:
                characterButton.setBounds(460, 50, 100, 100);
                break;
        }
        characterButton.setOpaque(false);
        characterButton.setContentAreaFilled(false);
        characterButton.setBorderPainted(false);
        enemyButtons.add(characterButton);
        add(characterButton);
    }

    private void addAllyButton(int location, Character character) {
        ImageIcon characterImage = new ImageIcon(getClass().getResource("images//"+ character.getName() +".gif"));
        CharacterButton characterButton = new CharacterButton(characterImage);
        characterButton.setCharacter(character);
        switch(location){
            case 1:
                characterButton.setBounds(180, 250, 100, 100);
                break;
            case 2:
                characterButton.setBounds(320, 250, 100, 100);
                break;
            case 3:
                characterButton.setBounds(40, 250, 100, 100);
                break;
            case 4:
                characterButton.setBounds(460, 250, 100, 100);
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

}
