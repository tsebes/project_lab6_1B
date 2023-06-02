package gui;

import javax.swing.*;
import java.awt.*;

public class CreditsPanel extends JPanel  {

    private final GUI gui;

    public CreditsPanel(GUI gui) {
        this.gui = gui;
        //TODO rework options graphics
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.DARK_GRAY);
        setLayout(null);
        addTitle();
        addGoBackButton();
        //TODO add credits (programmers and pages we took graphics from)
    }

    private void addTitle() {
        JLabel title = new JLabel("Credits", SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.PLAIN, 40));
        title.setForeground(Color.WHITE);
        title.setBounds(200, 50, 400, 50);
        add(title);
    }

    private void addGoBackButton() {
        JButton goBackButton = new JButton("Go back");
        goBackButton.setBackground(new Color(178,17,17));
        goBackButton.setBounds(300, 450, 200, 50);
        goBackButton.setForeground(Color.WHITE);
        add(goBackButton);
        goBackButton.addActionListener(e -> {
            gui.changePanel(GUI.Panel.Menu);
        });
    }

}
