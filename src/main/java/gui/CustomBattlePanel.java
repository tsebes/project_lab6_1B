package gui;

import game.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomBattlePanel extends JPanel{

    private final GUI gui;
    private Battle battle;
    private DataProvider dataProvider;
    private BattleDataProvider battleDataProvider;
    private Map<String,String> battleMap = new HashMap<>();
    private String battleName;
    private String [] singleHeroData = new String[] {};
    private String [] singleEnemyData = new String[] {};
    private String [] charactersNameList = new String[]{};

    public CustomBattlePanel(GUI gui) {
        this.gui = gui;
        //TODO rework options graphics
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.DARK_GRAY);
        setLayout(null);
        addTitle();
        setUpCustomBattlePanel();
    }

    private void addTitle() {
        JLabel title = new JLabel("Custom Battle Creator", SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.PLAIN, 40));
        title.setForeground(Color.WHITE);
        title.setBounds(200, 10, 400, 40);
        add(title);
    }

    private void setUpCustomBattlePanel() {
        JLabel title = new JLabel("Import battle:", SwingConstants.LEFT);
        title.setFont(new Font("Serif", Font.PLAIN, 24));
        title.setForeground(Color.WHITE);
        title.setBounds(20, 70, 200, 24);
        add(title);

        battleMap = battleDataProvider.getInstance().getBattleMap();
        List<String> battleList = new ArrayList<>();

        for(String key: battleMap.keySet()){
            battleList.add(key);
        }

        String[] array = new String[battleList.size()];
        for(int i = 0; i < array.length; i++) {
            array[i] = battleList.get(i);
        }

        JComboBox ib = new JComboBox(array);
        ib.setBounds(175,75,200,20);
        ib.setSelectedIndex(-1);
        add(ib);

        JButton importButton = new JButton("Import");
        importButton.setBackground(new Color(178,17,17));
        importButton.setBounds(400, 70, 100, 30);
        importButton.setForeground(Color.WHITE);
        add(importButton);

        List<CharacterClass> charactersClassesList = new ArrayList<>();
        Map<Item, Integer> allItemsMap;
        charactersClassesList = dataProvider.getInstance().getCharactersClassesList();
        allItemsMap = dataProvider.getInstance().getAllItemsMap();

        String [] characterNames = new String[200];
        String [] itemNames = new String[100];

        for (int counter = 0; counter < charactersClassesList.size(); counter++) {
            characterNames[counter] = charactersClassesList.get(counter).getCharacterClassName();
        }

        if(allItemsMap.size() > 0){
            int counter = 0;
            for(Map.Entry<Item,Integer> entry: allItemsMap.entrySet()){
                itemNames[counter] = entry.getKey().getName();
                counter++;
            }
        }
        //for (int counter = 0; counter < allItemsMap.size(); counter++) {
        //    itemNames[counter] = allItemsMap.get(counter).getName();
        //}

        JLabel bn = new JLabel("Battle Name:", SwingConstants.LEFT);
        bn.setFont(new Font("Serif", Font.PLAIN, 25));
        bn.setForeground(Color.WHITE);
        bn.setBounds(20, 110, 300, 25);
        add(bn);

        JTextField bnt = new JTextField("Insert Battle Name");
        bnt.setBounds(175,110,200,25);
        add(bnt);

        JLabel pt = new JLabel("Player Team Composition:", SwingConstants.LEFT);
        pt.setFont(new Font("Serif", Font.PLAIN, 25));
        pt.setForeground(Color.WHITE);
        pt.setBounds(20, 150, 300, 25);
        add(pt);

        JComboBox c1 = new JComboBox(characterNames);
        c1.setBounds(40,200,150,20);
        c1.setSelectedIndex(-1);
        add(c1);

        JComboBox c2 = new JComboBox(characterNames);
        c2.setBounds(40,230,150,20);
        c2.setSelectedIndex(-1);
        add(c2);

        JComboBox c3 = new JComboBox(characterNames);
        c3.setBounds(40,260,150,20);
        c3.setSelectedIndex(-1);
        add(c3);

        JComboBox c4 = new JComboBox(characterNames);
        c4.setBounds(40,290,150,20);
        c4.setSelectedIndex(-1);
        add(c4);

        JLabel et = new JLabel("Enemy Team Composition:", SwingConstants.LEFT);
        et.setFont(new Font("Serif", Font.PLAIN, 25));
        et.setForeground(Color.WHITE);
        et.setBounds(20, 340, 300, 30);
        add(et);

        JComboBox c5 = new JComboBox(characterNames);
        c5.setBounds(40,390,150,20);
        c5.setSelectedIndex(-1);
        add(c5);

        JComboBox c6 = new JComboBox(characterNames);
        c6.setBounds(40,420,150,20);
        c6.setSelectedIndex(-1);
        add(c6);

        JComboBox c7 = new JComboBox(characterNames);
        c7.setBounds(40,450,150,20);
        c7.setSelectedIndex(-1);
        add(c7);

        JComboBox c8 = new JComboBox(characterNames);
        c8.setBounds(40,480,150,20);
        c8.setSelectedIndex(-1);
        add(c8);

        //level textboxes
        JTextField l1 = new JTextField("");
        l1.setBounds(210,200,40,20);
        add(l1);

        JTextField l2 = new JTextField("");
        l2.setBounds(210,230,40,20);
        add(l2);

        JTextField l3 = new JTextField("");
        l3.setBounds(210,260,40,20);
        add(l3);

        JTextField l4 = new JTextField("");
        l4.setBounds(210,290,40,20);
        add(l4);

        JTextField l5 = new JTextField("");
        l5.setBounds(210,390,40,20);
        add(l5);

        JTextField l6 = new JTextField("");
        l6.setBounds(210,420,40,20);
        add(l6);

        JTextField l7 = new JTextField("");
        l7.setBounds(210,450,40,20);
        add(l7);

        JTextField l8 = new JTextField("");
        l8.setBounds(210,480,40,20);
        add(l8);

        JLabel ai = new JLabel("Add Battle Item:", SwingConstants.LEFT);
        ai.setFont(new Font("Serif", Font.PLAIN, 25));
        ai.setForeground(Color.WHITE);
        ai.setBounds(380, 150, 300, 25);
        add(ai);

        JComboBox ic = new JComboBox(itemNames);
        ic.setBounds(555,155,150,20);
        ic.setSelectedIndex(-1);
        add(ic);

        JButton addItems = new JButton("Add");
        addItems.setBackground(new Color(178,17,17));
        addItems.setBounds(715, 150, 70, 30);
        addItems.setForeground(Color.WHITE);
        add(addItems);

        JLabel ll = new JLabel("Battle item list:", SwingConstants.LEFT);
        ll.setFont(new Font("Serif", Font.PLAIN, 25));
        ll.setForeground(Color.WHITE);
        ll.setBounds(380, 200, 300, 25);
        add(ll);

        DefaultListModel<String> itemList = new DefaultListModel<>();
        JList<String> list1 = new JList<>(itemList);
        list1.setFont(new Font("Serif",Font.BOLD,20));
        list1.setBounds(400,245, 250,255);
        add(list1);

        //Add Item to the list button
        addItems.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                itemList.addElement((String) ic.getItemAt(ic.getSelectedIndex()));
            }
        });

        JButton deleteItems = new JButton("Reset List");
        deleteItems.setBackground(new Color(178,17,17));
        deleteItems.setBounds(675, 450, 100, 50);
        deleteItems.setForeground(Color.WHITE);
        add(deleteItems);

        //Reset Item List
        deleteItems.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==deleteItems) {
                    DefaultListModel itemList = (DefaultListModel) list1.getModel();
                    itemList.removeAllElements();
                }
            }
        });

        //Go back button
        JButton goBackButton = new JButton("Go back");
        goBackButton.setBackground(new Color(178,17,17));
        goBackButton.setBounds(50, 520, 200, 50);
        goBackButton.setForeground(Color.WHITE);
        add(goBackButton);
        goBackButton.addActionListener(e -> {
            ib.setSelectedIndex(-1);
            ic.setSelectedIndex(-1);
            c1.setSelectedIndex(-1);
            c2.setSelectedIndex(-1);
            c3.setSelectedIndex(-1);
            c4.setSelectedIndex(-1);
            c5.setSelectedIndex(-1);
            c6.setSelectedIndex(-1);
            c7.setSelectedIndex(-1);
            c8.setSelectedIndex(-1);
            bnt.setText("");
            l1.setText("");
            l2.setText("");
            l3.setText("");
            l4.setText("");
            l5.setText("");
            l6.setText("");
            l7.setText("");
            l8.setText("");

            itemList.removeAllElements();

            gui.changePanel(GUI.Panel.Menu);
        });

        //import button actionListener
        importButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                battleName = String.valueOf(ib.getItemAt(ib.getSelectedIndex()));

                String [] battleData = battleMap.get(battleName).trim().split("\\s*" + ":" + "\\s*");
                String [] heroData = battleData[0].trim().split("\\s*" + "," + "\\s*");
                String [] enemyData = battleData[1].trim().split("\\s*" + "," + "\\s*");
                String [] itemData = battleData[2].trim().split("\\s*" + "," + "\\s*");

                String [] singleHeroNames = new String[100];
                String [] singleEnemyNames = new String[100];
                String [] singleHeroLevels = new String[100];
                String [] singleEnemyLevels = new String[100];

                for(int i = 0;i < heroData.length;i++) {
                    singleHeroData = heroData[i].trim().split("\\s*" + ";" + "\\s*");
                    singleHeroNames[i]=singleHeroData[0];
                    singleHeroLevels[i]=singleHeroData[2];
                }

                for(int i = 0;i < enemyData.length;i++) {
                    singleEnemyData = enemyData[i].trim().split("\\s*" + ";" + "\\s*");
                    singleEnemyNames[i]=singleEnemyData[0];
                    singleEnemyLevels[i]=singleHeroData[2];
                }

                c1.setSelectedItem(singleHeroNames[0]);
                c2.setSelectedItem(singleHeroNames[1]);
                c3.setSelectedItem(singleHeroNames[2]);
                c4.setSelectedItem(singleHeroNames[3]);
                c5.setSelectedItem(singleEnemyNames[0]);
                c6.setSelectedItem(singleEnemyNames[1]);
                c7.setSelectedItem(singleEnemyNames[2]);
                c8.setSelectedItem(singleEnemyNames[3]);

                bnt.setText(battleName);

                l1.setText(singleHeroLevels[0]);
                l2.setText(singleHeroLevels[1]);
                l3.setText(singleHeroLevels[2]);
                l4.setText(singleHeroLevels[3]);
                l5.setText(singleEnemyLevels[0]);
                l6.setText(singleEnemyLevels[1]);
                l7.setText(singleEnemyLevels[2]);
                l8.setText(singleEnemyLevels[3]);

                itemList.removeAllElements();

                for(int i=0;i<itemData.length;i++) {
                    itemList.addElement(itemData[i]);
                }

            }
        });

        //save battle button
        JButton save = new JButton("Save");
        save.setBackground(new Color(178,17,17));
        save.setBounds(300, 520, 200, 50);
        save.setForeground(Color.WHITE);
        add(save);
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String [] heroes = {(String) c1.getItemAt(c1.getSelectedIndex()),(String)c2.getItemAt(c2.getSelectedIndex()),
                        (String)c3.getItemAt(c3.getSelectedIndex()),(String)c4.getItemAt(c4.getSelectedIndex())};

                String [] enemies = {(String) c5.getItemAt(c5.getSelectedIndex()),(String)c6.getItemAt(c6.getSelectedIndex()),
                        (String)c7.getItemAt(c7.getSelectedIndex()),(String)c8.getItemAt(c8.getSelectedIndex())};

                String [] levels = {l1.getText(),l2.getText(),l3.getText(),l4.getText(),
                        l5.getText(),l6.getText(),l7.getText(),l8.getText()};

                int checker=0;

                String characters="";

                for(int i=0;i<heroes.length;i++) {
                    for(int j=i;j>=0;j--) {
                        if(heroes[i]==heroes[j])
                            checker++;
                    }
                    if(heroes[i]!=null) {
                        if (i != heroes.length - 1 && heroes[i+1]!=null)
                            characters += heroes[i] + ";" + heroes[i] + " " + checker + ";" + levels[i] + ",";
                        else
                            characters += heroes[i] + ";" + heroes[i] + " " + checker + ";" + levels[i];
                    }
                    checker=0;
                }
                checker=0;
                characters+=":";

                for(int i=0;i<enemies.length;i++) {
                    for(int j=i;j>=0;j--) {
                        if(enemies[i]==enemies[j])
                            checker++;
                    }
                    if(enemies[i]!=null) {
                        if (i != enemies.length - 1 && enemies[i+1]!=null)
                            characters += enemies[i] + ";" + enemies[i] + " " + checker + ";" + levels[i+4] + ",";
                        else
                            characters += enemies[i] + ";" + enemies[i] + " " + checker + ";" + levels[i+4];
                    }
                    checker=0;
                }
                characters+=":";

                String items = "";

                for(int i = 0; i< list1.getModel().getSize();i++){
                    if(i!=list1.getModel().getSize()-1 && list1.getModel().getElementAt(i)!=null)
                        items+=list1.getModel().getElementAt(i)+",";
                    else if(list1.getModel().getElementAt(i)!=null)
                        items+=list1.getModel().getElementAt(i);
                }

                //LVL1BATTLE-Knight;knight;1:Skeleton;skeleton;1:Potion,Great potion,Elixir,Throwing dagger,Burning dagger
                String battleDataString = bnt.getText()+"-"+characters+items;

                try {
                    File file = new File("src/main/java/battleData.txt");
                    file.setWritable(true);
                    file.setReadable(true);
                    FileWriter fw = new FileWriter(file, true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write(battleDataString);
                    bw.newLine();
                    bw.close();
                } catch (IOException err) {
                    err.printStackTrace();
                }

                ib.addItem((String)bnt.getText());
                ib.setSelectedIndex(-1);
                battleMap.put(bnt.getText(),characters+items);
                System.out.println(battleMap);
            }
        });

        //start battle button
        JButton start = new JButton("Start Battle");
        start.setBackground(new Color(178,17,17));
        start.setBounds(550, 520, 200, 50);
        start.setForeground(Color.WHITE);
        add(start);
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                battle = battleDataProvider.getInstance().setUpBattle(bnt.getText(), gui);
                gui.changePanel(GUI.Panel.Battle);
            }
        });
    }

}