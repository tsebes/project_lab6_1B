package gui;

import game.*;
import game.Action;
import game.Character;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ItemPanel extends JPanel {

    private final BattlePanel battlePanel;
    private Character character;
    private Battle battle;
    private int currentPage;
    private int numberOfPages;
    private JButton item1;
    private JButton item2;
    private JButton item3;
    private JButton item4;
    private JButton item1Info;
    private JButton item2Info;
    private JButton item3Info;
    private JButton item4Info;
    private JButton pageLeft;
    private JButton pageRight;

    public ItemPanel(BattlePanel battlePanel) {
        this.battlePanel = battlePanel;
        this.battle = battlePanel.getBattle();
        //TODO rework menu graphics
        setBounds(200, 400, 600, 200);
        setBackground(Color.BLUE);
        setLayout(null);
        addPageLeftButton();
        addPageRightButton();
        addItemButton(1);
        addItemButton(2);
        addItemButton(3);
        addItemButton(4);
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

    private void addItemButton(int buttonNumber) {
        JButton itemButton = new JButton("");
        itemButton.setBackground(new Color(178,17,17));
        itemButton.setForeground(Color.WHITE);

        JButton itemInfoButton = new JButton("?");
        itemInfoButton.setBackground(Color.BLACK);
        itemInfoButton.setForeground(Color.WHITE);
        itemInfoButton.setFont(new Font("Serif", Font.PLAIN, 5));

        switch (buttonNumber){
            case 1:
                itemButton.setBounds(100, 25, 175, 65);
                itemInfoButton.setBounds(275, 25, 10, 10);
                item1 = itemButton;
                item1Info = itemInfoButton;
                break;
            case 2:
                itemButton.setBounds(325, 25, 175, 65);
                itemInfoButton.setBounds(500, 25, 10, 10);
                item2 = itemButton;
                item2Info = itemInfoButton;
                break;
            case 3:
                itemButton.setBounds(100, 110, 175, 65);
                itemInfoButton.setBounds(275, 110, 10, 10);
                item3 = itemButton;
                item3Info = itemInfoButton;
                break;
            case 4:
                itemButton.setBounds(325, 110, 175, 65);
                itemInfoButton.setBounds(500, 110, 10, 10);
                item4 = itemButton;
                item4Info = itemInfoButton;
                break;
        }
        itemInfoButton.addActionListener(e -> {
            battlePanel.changePanel(BattlePanel.Panel.ItemInfo);
        });
        add(itemButton);
        add(itemInfoButton);
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

    private void setUpItemToButton(JButton button, int number){
        Item item = battle.getItemArrayList().get(currentPage*4 + number - 1);
        button.setText(item.getName());

        button.addActionListener(e -> {
            battlePanel.getBattle().setCurrentAction(Action.ITEM);
            battlePanel.getBattle().setCurrentItem(item);
            if(item.getAOE()){
                if(item.getTargetingEnemies()){
                    battlePanel.getBattle().setTarget(battlePanel.getBattle().getEnemyArrayList());
                }else{
                    battlePanel.getBattle().setTarget(battlePanel.getBattle().getHeroArrayList());
                }
                battlePanel.changePanel(BattlePanel.Panel.Confirmation);
            }else{
                battlePanel.changePanel(BattlePanel.Panel.Targeting);
                battlePanel.getTargeting().changeInformationPanel("Using Item: " + item.getName());
                if(item.getTargetingEnemies()){
                    battlePanel.getCharacters().addEnemyTargeting();
                }else{
                    battlePanel.getCharacters().addAllyTargeting();
                }
            }
        });
    }

    public void setUpItemInfoToButton(JButton button, int number){
        Item item = battle.getItemArrayList().get(currentPage*4 + number - 1);
        button.addActionListener(e -> {
            battlePanel.getItemInfo().setItem(item);
            battlePanel.changePanel(BattlePanel.Panel.ItemInfo);
        });
    }

    public void refresh(){
        Character activeCharacter = battlePanel.getBattle().getActiveCharacter();
        battle = battlePanel.getBattle();
        if(character == null || character != activeCharacter){
            character = activeCharacter;
            currentPage = 0;
            numberOfPages = (int)Math.ceil((double)battle.getItemArrayList().size()/4.0);
        }else{
            if(currentPage<0){
                currentPage = numberOfPages - 1;
            }else if(currentPage == numberOfPages){
                currentPage = 0;
            }
        }
        clearActionListeners(item1);
        clearActionListeners(item2);
        clearActionListeners(item3);
        clearActionListeners(item4);
        clearActionListeners(item1Info);
        clearActionListeners(item2Info);
        clearActionListeners(item3Info);
        clearActionListeners(item4Info);

        if(battle.getItemArrayList().isEmpty()) {
            item1.setVisible(false);
            item2.setVisible(false);
            item3.setVisible(false);
            item4.setVisible(false);
            item1Info.setVisible(false);
            item2Info.setVisible(false);
            item3Info.setVisible(false);
            item4Info.setVisible(false);
        }else if(currentPage < numberOfPages - 1 || battle.getItemArrayList().size()%4 == 0){
            item1.setVisible(true);
            item1Info.setVisible(true);
            setUpItemToButton(item1,1);
            setUpItemInfoToButton(item1Info, 1);
            item2.setVisible(true);
            item2Info.setVisible(true);
            setUpItemToButton(item2,2);
            setUpItemInfoToButton(item2Info, 2);
            item3.setVisible(true);
            item3Info.setVisible(true);
            setUpItemToButton(item3,3);
            setUpItemInfoToButton(item3Info, 3);
            item4.setVisible(true);
            item4Info.setVisible(true);
            setUpItemToButton(item4,4);
            setUpItemInfoToButton(item4Info, 4);
        }else if(battle.getItemArrayList().size()%4 == 3){
            item1.setVisible(true);
            item1Info.setVisible(true);
            setUpItemToButton(item1,1);
            setUpItemInfoToButton(item1Info, 1);
            item2.setVisible(true);
            item2Info.setVisible(true);
            setUpItemToButton(item2,2);
            setUpItemInfoToButton(item2Info, 2);
            item3.setVisible(true);
            item3Info.setVisible(true);
            setUpItemToButton(item3,3);
            setUpItemInfoToButton(item3Info, 3);
            item4.setVisible(false);
            item4Info.setVisible(false);
        }else if(battle.getItemArrayList().size()%4 == 2){
            item1.setVisible(true);
            item1Info.setVisible(true);
            setUpItemToButton(item1,1);
            setUpItemInfoToButton(item1Info, 1);
            item2.setVisible(true);
            item2Info.setVisible(true);
            setUpItemToButton(item2,2);
            setUpItemInfoToButton(item2Info, 2);
            item3.setVisible(false);
            item3Info.setVisible(false);
            item4.setVisible(false);
            item4Info.setVisible(false);
        }else{
            item1.setVisible(true);
            item1Info.setVisible(true);
            setUpItemToButton(item1,1);
            setUpItemInfoToButton(item1Info, 1);
            item2.setVisible(false);
            item2Info.setVisible(false);
            item3.setVisible(false);
            item3Info.setVisible(false);
            item4.setVisible(false);
            item4Info.setVisible(false);
        }
    }
}
