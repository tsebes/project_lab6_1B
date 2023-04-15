package src.main.java.gui;

import src.main.java.game.Character;

import javax.swing.*;

public class CharacterButton extends JButton {
    private Character buttonCharacter;
    public CharacterButton() {
    }

    public CharacterButton(Icon icon) {
        super(icon);
    }

    public CharacterButton(String text) {
        super(text);
    }

    public CharacterButton(Action a) {
        super(a);
    }

    public CharacterButton(String text, Icon icon) {
        super(text, icon);
    }

    public void setCharacter(Character character){
        buttonCharacter = character;
    }

    public Character getButtonCharacter() {
        return buttonCharacter;
    }
}
