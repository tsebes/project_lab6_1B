package gui;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class TutorialPanel extends JPanel {

    private final GUI gui;
    int pageCount = 0;
    List<String> imageNameList = Arrays.asList("/tutorial_1.png","/tutorial_2.png","/tutorial_3.png","/tutorial_4.png",
            "/tutorial_5.png", "/tutorial_6.png","/tutorial_7.png","/tutorial_8.png","/tutorial_9.png","/tutorial_10.png");
    List<String> tutorialTitleDesc = Arrays.asList("Tutorial 1: Elementy UI","Tutorial 2: Panel akcji Basic Attack",
            "Tutorial 3: Panel potwierdzający atak","Tutorial 4: Panel akcji Skill","Tutorial 5: Panel Skills Info",
            "Tutorial 6: Panel potwierdzający użycie skilla","Tutorial 7: Panel akcji Guard",
            "Tutorial 8: Panel akcji Analyze","Tutorial 9: Panel akcji Item","Tutorial 10: Panel Items Info");

    JLabel image = new JLabel();
    JLabel title = new JLabel(tutorialTitleDesc.get(0), SwingConstants.CENTER);

    public TutorialPanel(GUI gui) {
        this.gui = gui;
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.DARK_GRAY);
        setLayout(null);
        addTitle();
        addImage();
        addPageLeftButton();
        addGoBackButton();
        addPageRightButton();
        //TODO rework tutorial
    }

    private void addTitle() {
        title.setFont(new Font("Serif", Font.PLAIN, 30));
        title.setForeground(Color.WHITE);
        title.setBounds(50, 5, 700, 32);
        add(title);
    }

    private void addImage() {
        image.setIcon(new ImageIcon(getClass().getResource(imageNameList.get(0))));
        image.setBounds(80, 50, 640, 480);
        add(image);
    }

    private void addGoBackButton() {
        JButton goBackButton = new JButton("Go back");
        goBackButton.setBackground(new Color(178,17,17));
        goBackButton.setBounds(300, 540, 200, 50);
        goBackButton.setForeground(Color.WHITE);
        add(goBackButton);
        goBackButton.addActionListener(e -> {
            gui.changePanel(GUI.Panel.Menu);
        });
    }

    private void addPageLeftButton() {
        JButton leftButton = new JButton("<");
        leftButton.setBackground(new Color(178,17,17));
        leftButton.setBounds(225, 540, 50, 50);
        leftButton.setForeground(Color.WHITE);
        //pageLeft = leftButton;
        add(leftButton);
        leftButton.addActionListener(e -> {
            if(pageCount>0) {
                pageCount--;
                title.setText(tutorialTitleDesc.get(pageCount));
                image.setIcon(new ImageIcon(getClass().getResource(imageNameList.get(pageCount))));
            } else {
                pageCount = 9;
                title.setText(tutorialTitleDesc.get(pageCount));
                image.setIcon(new ImageIcon(getClass().getResource(imageNameList.get(pageCount))));
            }
        });
    }

    private void addPageRightButton() {
        JButton rightButton = new JButton(">");
        rightButton.setBackground(new Color(178,17,17));
        rightButton.setBounds(525, 540, 50, 50);
        rightButton.setForeground(Color.WHITE);
        //pageRight = rightButton;
        add(rightButton);
        rightButton.addActionListener(e -> {
            if(pageCount<9) {
                pageCount++;
                title.setText(tutorialTitleDesc.get(pageCount));
                image.setIcon(new ImageIcon(getClass().getResource(imageNameList.get(pageCount))));
            } else {
                pageCount = 0;
                title.setText(tutorialTitleDesc.get(pageCount));
                image.setIcon(new ImageIcon(getClass().getResource(imageNameList.get(pageCount))));
            }
        });
    }

}