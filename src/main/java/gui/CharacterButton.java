package gui;

import game.Character;

import javax.swing.*;

public class CharacterButton extends JButton {
    private Character buttonCharacter;
    public CharacterButton() {
    }

    public CharacterButton(Icon icon) {
        super(icon);
    }

    public void setCharacter(Character character){
        buttonCharacter = character;
    }

    public Character getButtonCharacter() {
        return buttonCharacter;
    }
}
