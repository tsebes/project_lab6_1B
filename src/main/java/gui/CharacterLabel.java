package gui;

import game.Character;

import javax.swing.*;

public class CharacterLabel extends JLabel {
    private Character labelCharacter;

    public CharacterLabel(String text, int horizontalAlignment) {
        super(text, horizontalAlignment);
    }


    public Character getLabelCharacter() {
        return labelCharacter;
    }

    public void setLabelCharacter(Character labelCharacter) {
        this.labelCharacter = labelCharacter;
    }

    public void refresh(){
        String text = "";
        text += labelCharacter.getName();
        text += ": " + labelCharacter.getCurrentHealthPoints() + "/" + labelCharacter.getMaxHealthPoints();
        this.setText(text);
    }
}
