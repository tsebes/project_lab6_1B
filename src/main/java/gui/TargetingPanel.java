package gui;

import javax.swing.*;
import java.awt.*;

public class TargetingPanel   extends JPanel {

    private final BattlePanel battlePanel;
    private JLabel panelTitle;
    private JLabel basicInfo;

    public TargetingPanel(BattlePanel battlePanel) {
        this.battlePanel = battlePanel;
        setBounds(200, 400, 600, 200);
        setVisible(false);
        setLayout(null);
        addTitle();
        addInformationPanel();
    }

    private void addTitle() {
        JLabel title = new JLabel("Choose target", SwingConstants.CENTER);
        panelTitle = title;
        title.setFont(new Font("Serif", Font.PLAIN, 30));
        title.setForeground(Color.BLACK);
        title.setBounds(200, 10, 200, 30);
        add(title);
    }

    private void addInformationPanel(){
        JLabel basicInfo = new JLabel("", SwingConstants.CENTER);
        this.basicInfo = basicInfo;
        basicInfo.setFont(new Font("Serif", Font.PLAIN, 20));
        basicInfo.setForeground(Color.BLACK);
        basicInfo.setBounds(100, 50, 400, 30);
        add(basicInfo);
    }

    public void changeInformationPanel(String info){
        basicInfo.setText(info);
    }

}
