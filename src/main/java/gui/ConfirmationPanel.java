package gui;

import javax.swing.*;
import java.awt.*;

public class ConfirmationPanel extends JPanel {

    private final BattlePanel battlePanel;
    private JLabel ActionInfo;

    public ConfirmationPanel(BattlePanel battlePanel) {
        this.battlePanel = battlePanel;
        //TODO rework menu graphics
        setBounds(200, 400, 600, 200);
        setBackground(Color.CYAN);
        setVisible(false);
        setLayout(null);
        addActionInfo();
        addCancelButton();
        addConfirmButton();
    }

    private void addActionInfo() {
        JLabel information = new JLabel("Title", SwingConstants.CENTER);
        ActionInfo = information;
        information.setFont(new Font("Serif", Font.PLAIN, 30));
        information.setForeground(Color.WHITE);
        information.setBounds(200, 0, 200, 40);
        add(information);
    }

    public void changeActionInfo(){
        String actionDescription = "Attack/Analyze enemy ?";
        //TODO getting information about action and target
        ActionInfo.setText(actionDescription);
    }

    private void addCancelButton() {
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBackground(new Color(178,17,17));
        cancelButton.setBounds(75, 50, 150, 50);
        cancelButton.setForeground(Color.WHITE);
        add(cancelButton);
        cancelButton.addActionListener(e -> {
            //TODO cancel action and character
            battlePanel.changePanel(BattlePanel.Panel.Skills);
        });
    }

    private void addConfirmButton() {
        JButton confirmButton = new JButton("Confirm");
        confirmButton.setBackground(new Color(178,17,17));
        confirmButton.setBounds(325, 50, 150, 50);
        confirmButton.setForeground(Color.WHITE);
        add(confirmButton);
        confirmButton.addActionListener(e -> {
            //TODO do chosen action
            battlePanel.changePanel(BattlePanel.Panel.Skills);
            battlePanel.getBattle().doCurrentAction();
            });
    }

}