package src.main.java.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class CharactersPanel extends JPanel {

    private final BattlePanel battle;
    private JButton[] enemyButtons = new JButton[4];
    private JButton[] allyButtons = new JButton[4];


    public CharactersPanel(BattlePanel battle) {
        this.battle = battle;
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
        //temporary button to test targeting
        JButton gifEndButton = new JButton("g hit");
        gifEndButton.setBounds(300, 10, 100, 20);
        gifEndButton.setOpaque(false);
        gifEndButton.setContentAreaFilled(false);
        gifEndButton.setBorderPainted(false);

        gifEndButton.addActionListener(e -> {
            showSingleGif(allyButtons[2],"knight-hit.gif");
        });
        add(gifEndButton);
        //end of temporary button
    }

    public void setUpCharacters(){
        //TODO rework function to get characters from battle
        addEnemyButton(0,"unknown");
        addEnemyButton(1,"unknown");
        addEnemyButton(2,"unknown");
        addEnemyButton(3,"unknown");
        addAllyButton(0,"unknown");
        addAllyButton(1,"unknown");
        addAllyButton(2,"knight");
        addAllyButton(3,"unknown");
    }

    public void addAllyTargeting(){
        for(JButton allyButton: allyButtons){
            if(allyButton!=null){
                allyButton.addActionListener(e -> {
                    //TODO delete information popup
                    System.out.println("Clicked ally");
                    battle.getConfirmation().changeActionInfo();
                    battle.changePanel(BattlePanel.Panel.Confirmation);
                    clearTargetingAll();
                    //TODO change target to this enemy/ally
                });
            }
        }
    }

    public void addEnemyTargeting(){
        for(JButton enemyButton: enemyButtons) {
            if (enemyButton != null) {
                enemyButton.addActionListener(e -> {
                    //TODO delete information popup
                    System.out.println("Clicked enemy");
                    battle.getConfirmation().changeActionInfo();
                    battle.changePanel(BattlePanel.Panel.Confirmation);
                    clearTargetingAll();
                    //TODO change target to this enemy/ally
                });
            }
        }
    }

    public void showSingleGif(JButton button, String url){
        Icon priorIcon = button.getIcon();
        ImageIcon characterImage = new ImageIcon(getClass().getResource("images//" + url));
        button.setIcon(characterImage);
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                button.setIcon(priorIcon);
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    public void clearTargetingAll(){
        for(JButton enemyButton: enemyButtons){
            if(enemyButton != null) clearTargetingButton(enemyButton);
        }
        for(JButton allyButton: allyButtons){
            if(allyButton != null) clearTargetingButton(allyButton);
        }
    }

    private void clearTargetingButton(JButton button){
        ActionListener[] listeners = button.getActionListeners();
        if (listeners == null) {
            return;
        }
        for (ActionListener listener : listeners) {
            button.removeActionListener(listener);
        }
    }

    private void addEnemyButton(int number, String url) {
        ImageIcon characterImage = new ImageIcon(getClass().getResource("images//"+ url +".gif"));
        JButton characterButton = new JButton(characterImage);
        switch(number){
            case 0:
                characterButton.setBounds(180, 50, 100, 100);
                break;
            case 1:
                characterButton.setBounds(320, 50, 100, 100);
                break;
            case 2:
                characterButton.setBounds(40, 50, 100, 100);
                break;
            case 3:
                characterButton.setBounds(460, 50, 100, 100);
                break;
        }
        characterButton.setOpaque(false);
        characterButton.setContentAreaFilled(false);
        characterButton.setBorderPainted(false);
        enemyButtons[number] = characterButton;
        add(characterButton);
    }

    private void addAllyButton(int number, String url) {
        ImageIcon characterImage = new ImageIcon(getClass().getResource("images//"+ url +".gif"));
        JButton characterButton = new JButton(characterImage);
        switch(number){
            case 0:
                characterButton.setBounds(180, 250, 100, 100);
                break;
            case 1:
                characterButton.setBounds(320, 250, 100, 100);
                break;
            case 2:
                characterButton.setBounds(40, 250, 100, 100);
                break;
            case 3:
                characterButton.setBounds(460, 250, 100, 100);
                break;
        }
        characterButton.setOpaque(false);
        characterButton.setContentAreaFilled(false);
        characterButton.setBorderPainted(false);
        allyButtons[number] = characterButton;
        add(characterButton);
    }

    public JButton getEnemyButton(int number) {
        return enemyButtons[number];
    }

    public JButton getAllyButton(int number) {
        return allyButtons[number];
    }
}
