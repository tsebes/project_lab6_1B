package game;

import java.util.Map;

public class Enemy extends Character{

    public Enemy(String name, int level, AttackResistanceType basicAttack, double maxHealthPoints, double basicStrength, double basicIntelligence, double basicSpeed, double basicLuck, Map<AttackResistanceType, Double> basicResistance) {
        super(name, level, basicAttack, maxHealthPoints, basicStrength, basicIntelligence, basicSpeed, basicLuck, basicResistance);
        this.currentHealthPoints = maxHealthPoints;
        this.currentStrength = basicStrength;
        this.currentIntelligence = basicIntelligence;
        this.currentSpeed = basicSpeed;
        this.currentLuck = basicLuck;
    }
    //Pseudo EnemyAI here or in Battle Class? Need to first see Skills and char classes to begin working on it?
}
