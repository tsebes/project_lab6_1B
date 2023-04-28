package game;

import java.util.Map;

public class Enemy extends Character{

    public Enemy(CharacterClass characterClass, String name, int level, double maxHealthPoints) {
        super(characterClass, name, level, maxHealthPoints);
    }

    //Pseudo EnemyAI here or in Battle Class? Need to first see Skills and char classes to begin working on it?
}
