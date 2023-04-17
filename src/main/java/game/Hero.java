package game;

import java.util.Map;

public class Hero extends Character{
    public Hero(String name, int level, AttackResistanceType basicAttack, double maxHealthPoints, double basicStrength, double basicIntelligence, double basicSpeed, double basicLuck, Map<AttackResistanceType, Double> basicResistance) {
        super(name, level, basicAttack, maxHealthPoints, basicStrength, basicIntelligence, basicSpeed, basicLuck, basicResistance);
    }
}
