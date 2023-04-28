package game;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Map;

public class CharacterClass {

    protected final CharacterClassType characterClassType;
    protected final ArrayList<Skill> availableSkills;
    protected final Map<AttackResistanceType, Double> basicResistances;
    protected Map<AttackResistanceType, Boolean> discoveredResistances;
    protected final double basicStrength;
    protected final double basicIntelligence;
    protected final double basicSpeed;
    protected final double basicLuck;
    protected final AttackResistanceType basicAttack;

    public CharacterClass(CharacterClassType characterClassType, ArrayList<Skill> availableSkills, Map<AttackResistanceType, Double> basicResistances, double basicStrength, double basicIntelligence, double basicSpeed, double basicLuck, AttackResistanceType basicAttack) {
        this.characterClassType = characterClassType;
        this.availableSkills = availableSkills;
        this.basicResistances = basicResistances;
        this.basicStrength = basicStrength;
        this.basicIntelligence = basicIntelligence;
        this.basicSpeed = basicSpeed;
        this.basicLuck = basicLuck;
        this.basicAttack = basicAttack;

        this.discoveredResistances = new EnumMap<>(AttackResistanceType.class);
        // initialize discoveredResistances map with false values
        for (AttackResistanceType attackResistanceType : AttackResistanceType.values()) {
            discoveredResistances.put(attackResistanceType, false);
        }
    }

    public CharacterClassType getCharacterClassType() {
        return characterClassType;
    }

    public ArrayList<Skill> getAvailableSkills() {
        return availableSkills;
    }

    public Map<AttackResistanceType, Double> getBasicResistances() {
        return basicResistances;
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

    public Map<AttackResistanceType, Boolean> getDiscoveredResistances() {
        return discoveredResistances;
    }

    public void setDiscoveredResistances(Map<AttackResistanceType, Boolean> discoveredResistances) {
        this.discoveredResistances = discoveredResistances;
    }

    public void setResistanceDiscovered(AttackResistanceType resistanceType) {
        this.discoveredResistances.replace(resistanceType, true);
    }
}
