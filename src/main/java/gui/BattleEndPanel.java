package gui;

import game.DeBuff;
import game.LogHandler;

import javax.swing.*;
import java.util.List;
import java.util.ArrayList;
import java.awt.*;
import java.util.Map;

public class BattleEndPanel extends JPanel {
    private final GUI gui;
    private JLabel resultLabel;
    private List<JLabel> gainedEXPLabels = new ArrayList();
    private LogHandler logHandler;

    public BattleEndPanel(GUI gui) {
        this.gui = gui;
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.DARK_GRAY);
        setLayout(null);
        addLabels();
        addCloseButton();
    }

    private void addCloseButton() {
        JButton closeButton = new JButton("Close");
        closeButton.setBackground(new Color(178,17,17));
        closeButton.setBounds(300, 450, 200, 50);
        closeButton.setForeground(Color.WHITE);
        add(closeButton);
        closeButton.addActionListener(e -> {
            gui.getBattlePanel().getCharacters().endBattle();
            gui.changePanel(GUI.Panel.Menu);
        });
    }

    private void addLabels() {
        JLabel resultLabel = new JLabel("", SwingConstants.CENTER);
        resultLabel.setBounds(100, 50, 600, 100);
        resultLabel.setForeground(Color.BLACK);
        resultLabel.setFont(new Font("Serif", Font.BOLD, 40));
        add(resultLabel);
        this.resultLabel = resultLabel;
        for(int i = 0;i < 5;i++){
            JLabel characterLabel = new JLabel("",SwingConstants.LEFT);
            characterLabel.setBounds(100, 150 + i * 60,600,50);
            characterLabel.setForeground(Color.BLACK);
            characterLabel.setFont(new Font("Serif", Font.PLAIN, 30));
            add(characterLabel);
            gainedEXPLabels.add(characterLabel);
        }
    }

    public void refresh(){
        if(gui.getBattlePanel().getBattle()!=null){
            if(gui.getBattlePanel().getBattle().getWon()){
                resultLabel.setText("You won!!!");
            }else{
                resultLabel.setText("You lost...");
            }
            int i = 1;
            for(Map.Entry<String, Double> entry: logHandler.getInstance().getDamageDealtMap().entrySet()){
                if(entry.getKey() == "Enemy"){
                    gainedEXPLabels.get(0).setText("Enemies dealt " + entry.getValue() + " damage");
                }else{
                    gainedEXPLabels.get(i).setText(entry.getKey() + " has dealt " + entry.getValue() + " damage");
                    i++;
                }
            }
            while(i < 5){
                gainedEXPLabels.get(i).setText("");
                i++;
            }
        }else{
            resultLabel.setText("How did you get here?!??!");
        }
    }
}
