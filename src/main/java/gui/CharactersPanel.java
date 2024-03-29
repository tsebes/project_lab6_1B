package gui;

import game.*;
import game.Action;
import game.Character;

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
    private List<CharacterLabel> enemyButtonsInfo = new ArrayList<>();
    private List<CharacterButton> allyButtons = new ArrayList<>();
    private List<CharacterLabel> allyButtonsInfo = new ArrayList<>();
    private Battle battle;
    private JButton attackerButton;
    private Image bgImage;

    public CharactersPanel(BattlePanel battlePanel) {
        this.battlePanel = battlePanel;
        setBounds(0, 0, 600, 400);
        setBackground(Color.WHITE);
        setLayout(null);
    }

    public void setUpCharacters(Battle battle){
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
        add(attackerButton);
        refresh();
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
                    if(battle.getCurrentAction() == Action.ANALYZE){

                    }
                    battle.setTarget(allyButton.getButtonCharacter());
                    battlePanel.changePanel(BattlePanel.Panel.Confirmation);
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
                    battle.setTarget(enemyButton.getButtonCharacter());
                    battlePanel.changePanel(BattlePanel.Panel.Confirmation);
                    clearTargetingAll();
                });
            }
        }
    }

    public void addTargetingAll(){
        clearTargetingAll();
        for(CharacterButton allyButton: allyButtons){
            if(allyButton!=null){
                allyButton.addActionListener(e -> {
                    battle.setTarget(allyButton.getButtonCharacter());
                    if(battle.getCurrentAction() == Action.ANALYZE){
                        battlePanel.changePanel(BattlePanel.Panel.Analyze);
                    }else{
                        battlePanel.changePanel(BattlePanel.Panel.Confirmation);
                    }
                    clearTargetingAll();
                });
            }
        }
        for(CharacterButton enemyButton: enemyButtons) {
            if (enemyButton != null) {
                enemyButton.addActionListener(e -> {
                    battle.setTarget(enemyButton.getButtonCharacter());
                    if(battle.getCurrentAction() == Action.ANALYZE){
                        battlePanel.changePanel(BattlePanel.Panel.Analyze);
                    }else {
                        battlePanel.changePanel(BattlePanel.Panel.Confirmation);
                    }
                    clearTargetingAll();
                });
            }
        }
    }

    public void animate(boolean dealsDamage){
        Character aCharacter = battle.getActiveCharacter();
        CharacterButton button = getButton(aCharacter);
        ImageIcon idleImage = new ImageIcon(getClass().getResource("/" + aCharacter.getCharacterClassName() + ".gif"));
        ImageIcon attackImage = new ImageIcon(getClass().getResource("/" + aCharacter.getCharacterClassName()+ "-attack.gif"));
        button.setIcon(null);

        //Setting up where active character does action
        if(battle.getCurrentAction() == Action.SKILL){
            //if attacks AOE does it from the middle
            if(battle.getCurrentSkill().getAOE() && battle.getCurrentSkill().getTargetingEnemies()){
                attackerButton.setBounds(250, 200, 100, 100);
            //if attack single opponent goes next to him
            }else if(!battle.getCurrentSkill().getAOE() && battle.getCurrentSkill().getTargetingEnemies()){
                CharacterButton targetButton = getButton(battlePanel.getBattle().getTargetsArrayList().get(0));
                Rectangle bounds = targetButton.getBounds();
                if(battle.getActiveCharacter() instanceof Hero){
                    attackerButton.setBounds(bounds.x - 100, bounds.y, 100, 100);
                }else{
                    attackerButton.setBounds(bounds.x + 100, bounds.y, 100, 100);
                }
            //if uses skill on allies doesn't move
            }else{
                CharacterButton activeCharacterButton = getButton(battlePanel.getBattle().getActiveCharacter());
                attackerButton.setBounds(activeCharacterButton.getBounds());
            }
        }else if(battle.getCurrentAction() == Action.ITEM){
            //if attacks AOE does it from the middle
            if(battle.getCurrentItem().getAOE() && battle.getCurrentItem().getTargetingEnemies()){
                attackerButton.setBounds(250, 200, 100, 100);
            //if attack single opponent goes next to him
            }else if(!battle.getCurrentItem().getAOE() && battle.getCurrentItem().getTargetingEnemies()){
                CharacterButton targetButton = getButton(battlePanel.getBattle().getTargetsArrayList().get(0));
                Rectangle bounds = targetButton.getBounds();
                if(battle.getActiveCharacter() instanceof Hero){
                    attackerButton.setBounds(bounds.x - 100, bounds.y, 100, 100);
                }else{
                    attackerButton.setBounds(bounds.x + 100, bounds.y, 100, 100);
                }
            //if uses item on allies doesn't move
            }else{
                CharacterButton activeCharacterButton = getButton(battlePanel.getBattle().getActiveCharacter());
                attackerButton.setBounds(activeCharacterButton.getBounds());
            }
        }else{
            //if basic attack moves next to opponent
            CharacterButton targetButton = getButton(battlePanel.getBattle().getTargetsArrayList().get(0));
            Rectangle bounds = targetButton.getBounds();
            if(battle.getActiveCharacter() instanceof Hero){
                attackerButton.setBounds(bounds.x - 100, bounds.y, 100, 100);
            }else{
                attackerButton.setBounds(bounds.x + 100, bounds.y, 100, 100);
            }
        }
        attackerButton.setIcon(attackImage);

        Timer activeTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                button.setIcon(idleImage);
                attackerButton.setIcon(null);
                for(Character character: battle.getTargetsArrayList()){
                    ImageIcon characterIdleImage = new ImageIcon(getClass().getResource("/" +character.getCharacterClassName() + ".gif"));
                    ImageIcon characterHitImage;
                    if(character.getGotDamaged()){
                        characterHitImage = new ImageIcon(getClass().getResource("/" + character.getCharacterClassName() + "-hit.gif"));
                        character.setGotDamaged(false);
                    }else{
                        characterHitImage = characterIdleImage;
                    }

                    ImageIcon damageTypeImage = new ImageIcon();
                    AttackResistanceType type;
                    boolean attacking = true;
                    if(battle.getCurrentAction() == Action.BASICATTACK){
                        type = battle.getActiveCharacter().getBasicAttack();
                    }else if(battle.getCurrentAction() == Action.SKILL){
                        type = battle.getCurrentSkill().getAttackType();
                        attacking = battle.getCurrentSkill().getTargetingEnemies();
                    }else{
                        type = battle.getCurrentItem().getAttackType();
                        attacking = battle.getCurrentItem().getTargetingEnemies();
                    }
                    switch (type){
                        case PHYSICAL -> {}
                        case FIRE -> {
                            damageTypeImage = new ImageIcon(getClass().getResource("/fire.gif"));
                        }
                        case WATER -> {
                            damageTypeImage = new ImageIcon(getClass().getResource("/water.gif"));
                        }
                        case EARTH -> {
                            damageTypeImage = new ImageIcon(getClass().getResource("/earth.gif"));
                        }
                        case AIR -> {
                            damageTypeImage = new ImageIcon(getClass().getResource("/air.gif"));
                        }
                        case ENERGY ->{
                            damageTypeImage = new ImageIcon(getClass().getResource("/energy.gif"));
                        }
                        case LIGHT -> {
                            damageTypeImage = new ImageIcon(getClass().getResource("/light.gif"));
                        }
                        case DARK -> {
                            damageTypeImage = new ImageIcon(getClass().getResource("/dark.gif"));
                        }
                    }
                    if(attacking){
                        JLabel damageLabel = new JLabel(damageTypeImage, SwingConstants.CENTER);
                        damageLabel.setBounds(getButton(character).getBounds());
                        damageLabel.setVisible(true);
                        add(damageLabel);
                        setComponentZOrder(damageLabel, 1);
                        Timer deleteDamageTypeTimer = new Timer(1000, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent arg0) {
                                damageLabel.setVisible(false);
                                remove(damageLabel);
                                if(getButton(character).getIcon() != null){
                                    getButton(character).setIcon(characterIdleImage);
                                }
                            }
                        });
                        deleteDamageTypeTimer.setRepeats(false);
                        deleteDamageTypeTimer.start();
                    }

                    getButton(character).setIcon(characterHitImage);
                    if(character.getCurrentHealthPoints() > 0){
                        Timer returnToIdleTimer = new Timer(1000, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent arg0) {
                                if(getButton(character).getIcon() != null){
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
                                ImageIcon characterDieImage = new ImageIcon(getClass().getResource("/" + character.getCharacterClassName() + "-die.gif"));
                                getButton(character).setIcon(characterDieImage);
                                Timer deleteButtonTimer = new Timer(1000, new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent arg0) {
                                        getButton(character).setIcon(null);
                                        getLabel(character).setVisible(false);
                                        if(enemyButtons.contains(getButton(character))){
                                            enemyButtons.remove(getButton(character));
                                        }else if(allyButtons.contains(getButton(character))){
                                            allyButtons.remove(getButton(character));
                                        }
                                        if(enemyButtonsInfo.contains(getLabel(character))){
                                            enemyButtonsInfo.remove(getLabel(character));
                                        }else if(allyButtonsInfo.contains(getLabel(character))){
                                            allyButtonsInfo.remove(getLabel(character));
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
        ImageIcon characterImage = new ImageIcon(getClass().getResource("/"+ character.getCharacterClassName() +".gif"));
        CharacterButton characterButton = new CharacterButton(characterImage);
        characterButton.setCharacter(character);
        CharacterLabel characterLabel = new CharacterLabel("", SwingConstants.CENTER);
        characterLabel.setLabelCharacter(character);
        characterLabel.setFont(new Font("Serif", Font.BOLD, 10));
        characterLabel.setForeground(Color.WHITE);
        switch(location){
            case 1:
                characterButton.setBounds(350, 275, 100, 100);
                characterLabel.setBounds(350, 375, 100, 10);
                break;
            case 2:
                characterButton.setBounds(350, 115, 100, 100);
                characterLabel.setBounds(350, 215, 100, 10);
                break;
            case 3:
                characterButton.setBounds(475, 225, 100, 100);
                characterLabel.setBounds(475, 325, 100, 10);
                break;
            case 4:
                characterButton.setBounds(475, 75, 100, 100);
                characterLabel.setBounds(475, 175, 100, 10);
                break;
        }
        characterButton.setOpaque(false);
        characterButton.setContentAreaFilled(false);
        characterButton.setBorderPainted(false);
        enemyButtons.add(characterButton);
        enemyButtonsInfo.add(characterLabel);
        add(characterLabel);
        add(characterButton);
    }

    private void addAllyButton(int location, Character character) {
        ImageIcon characterImage = new ImageIcon(getClass().getResource("/"+ character.getCharacterClassName() +".gif"));
        CharacterButton characterButton = new CharacterButton(characterImage);
        characterButton.setCharacter(character);
        CharacterLabel characterLabel = new CharacterLabel("", SwingConstants.CENTER);
        characterLabel.setLabelCharacter(character);
        characterLabel.setFont(new Font("Serif", Font.BOLD, 10));
        characterLabel.setForeground(Color.WHITE);
        switch(location){
            case 1:
                characterButton.setBounds(150, 275, 100, 100);
                characterLabel.setBounds(150, 375, 100, 10);
                break;
            case 2:
                characterButton.setBounds(150, 115, 100, 100);
                characterLabel.setBounds(150, 215, 100, 10);
                break;
            case 3:
                characterButton.setBounds(25, 225, 100, 100);
                characterLabel.setBounds(25, 325, 100, 10);
                break;
            case 4:
                characterButton.setBounds(25, 75, 100, 100);
                characterLabel.setBounds(25, 175, 100, 10);
                break;
        }
        characterButton.setOpaque(false);
        characterButton.setContentAreaFilled(false);
        characterButton.setBorderPainted(false);
        allyButtons.add(characterButton);
        allyButtonsInfo.add(characterLabel);
        add(characterLabel);
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
        return null;
    }

    public CharacterLabel getLabel(Character character) {
        for(CharacterLabel label: enemyButtonsInfo){
            if(label.getLabelCharacter() == character){
                return label;
            }
        }
        for(CharacterLabel label: allyButtonsInfo){
            if(label.getLabelCharacter() == character){
                return label;
            }
        }
        return null;
    }

    public void refresh(){
        for(CharacterLabel characterLabel: enemyButtonsInfo){
            characterLabel.refresh();
        }
        for(CharacterLabel characterLabel: allyButtonsInfo){
            characterLabel.refresh();
        }
    }

    public Battle getBattle() {
        return battle;
    }

    public void endBattle(){
        battle = null;
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.drawImage(bgImage, 0, 0, null);
    }
}
