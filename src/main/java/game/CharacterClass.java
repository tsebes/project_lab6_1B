package game;

import java.util.ArrayList;
import java.util.Map;

public class CharacterClass {

    protected final CharacterClassType characterClassType;
    protected final ArrayList<Skill> availableSkills;
    protected final Map<AttackResistanceType, Double> basicResistance;
    protected final double basicStrength;
    protected final double basicIntelligence;
    protected final double basicSpeed;
    protected final double basicLuck;
    protected final AttackResistanceType basicAttack;

    public CharacterClass(CharacterClassType characterClassType, ArrayList<Skill> availableSkills, Map<AttackResistanceType, Double> basicResistance, double basicStrength, double basicIntelligence, double basicSpeed, double basicLuck, AttackResistanceType basicAttack) {
        this.characterClassType = characterClassType;
        this.availableSkills = availableSkills;
        this.basicResistance = basicResistance;
        this.basicStrength = basicStrength;
        this.basicIntelligence = basicIntelligence;
        this.basicSpeed = basicSpeed;
        this.basicLuck = basicLuck;
        this.basicAttack = basicAttack;
    }

    public CharacterClassType getCharacterClassType() {
        return characterClassType;
    }

    public ArrayList<Skill> getAvailableSkills() {
        return availableSkills;
    }

    public Map<AttackResistanceType, Double> getBasicResistance() {
        return basicResistance;
    }

    public double getBasicStrength() {
        return basicStrength;
    }

    public double getBasicIntelligence() {
        return basicIntelligence;
    }

    public double getBasicSpeed() {
        return basicSpeed;
    }

    public double getBasicLuck() {
        return basicLuck;
    }

    public AttackResistanceType getBasicAttack() {
        return basicAttack;
    }
}
