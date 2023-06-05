package gui;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;

public class CreditsPanel extends JPanel  {

    private final GUI gui;
    private List<JLabel> creditsList;
    static final String[] creditsArray = {
            "PROGRAMMERS:",
            " - Damian Mizera",
            " - Mikołaj Wach",
            " - Sebastian Zych",
            "",
            "CHARACTER GRAPHICS:",
            " - Craftpix.net",
            " - https://chierit.itch.io/boss-demon-slime",
            " - https://rvros.itch.io/pixel-art-animated-slime",
            " - https://opengameart.org/content/lpc-wolf-animation",
            " - https://sanctumpixel.itch.io/imp-axe-demon-pixel-art-character",
            "",
            "UI interface: ",
            " - freepik (aopsan)",
            " - freepik (upklyak)",
            " - freepik (brgfx)",
            " - Icon archive"
    };

    public CreditsPanel(GUI gui) {
        this.gui = gui;
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.DARK_GRAY);
        setLayout(null);
        addTitle();
        addGoBackButton();
        addCredits();
    }

    private void addTitle() {
        JLabel title = new JLabel("Credits", SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.PLAIN, 40));
        title.setForeground(Color.WHITE);
        title.setBounds(200, 50, 400, 50);
        add(title);
    }

    private void addCredits(){
        creditsList = new ArrayList<>();
        int x = 100;
        int y;
        for(String string: creditsArray){

            if(creditsList.size()>22){
                x = 500;
                y = 100 + (creditsList.size() - 23) * 15;
            }else{
                y = 100 + creditsList.size() * 15;
            }

            JLabel credit = new JLabel(string, SwingConstants.LEFT);
            credit.setFont(new Font("Serif", Font.PLAIN, 15));
            credit.setForeground(Color.WHITE);
            credit.setBounds(x, y, 600, 15);
            add(credit);
            creditsList.add(credit);
        }
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
