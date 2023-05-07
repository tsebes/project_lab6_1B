package game;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Map;

public class CharacterClass {

    protected final String characterClassName;
    protected final ArrayList<Skill> availableSkills;
    protected final Map<AttackResistanceType, Double> basicResistances;
    protected Map<AttackResistanceType, Boolean> discoveredResistances;
    protected final double lvl1Strength;
    protected final double lvl1Intelligence;
    protected final double lvl1Speed;
    protected final double lvl1Luck;
    protected final double lvl1HP;
    protected final double growthStrength;
    protected final double growthIntelligence;
    protected final double growthSpeed;
    protected final double growthLuck;
    protected final double growthHP;
    protected final AttackResistanceType basicAttack;

    public CharacterClass(String characterClassName, ArrayList<Skill> availableSkills, Map<AttackResistanceType, Double> basicResistances, double lvl1Strength, double lvl1Intelligence, double lvl1Speed, double lvl1Luck, double lvl1HP, double growthStrength, double growthIntelligence, double growthSpeed, double growthLuck, double growthHP, AttackResistanceType basicAttack) {
        this.characterClassName = characterClassName;
        this.availableSkills = availableSkills;
        this.basicResistances = basicResistances;
        this.lvl1Strength = lvl1Strength;
        this.lvl1Intelligence = lvl1Intelligence;
        this.lvl1Speed = lvl1Speed;
        this.lvl1Luck = lvl1Luck;
        this.lvl1HP = lvl1HP;
        this.growthStrength = growthStrength;
        this.growthIntelligence = growthIntelligence;
        this.growthSpeed = growthSpeed;
        this.growthLuck = growthLuck;
        this.growthHP = growthHP;
        this.basicAttack = basicAttack;

        this.discoveredResistances = new EnumMap<>(AttackResistanceType.class);
        // initialize discoveredResistances map with false values
        for (AttackResistanceType attackResistanceType : AttackResistanceType.values()) {
            discoveredResistances.put(attackResistanceType, false);
        }
    }

    public String getCharacterClassName() {
        return characterClassName;
    }

    public ArrayList<Skill> getAvailableSkills() {
        return availableSkills;
    }

    public Map<AttackResistanceType, Double> getBasicResistances() {
        return basicResistances;
    }

    public double getLvl1Strength() {
        return lvl1Strength;
    }

    public double getLvl1Intelligence() {
        return lvl1Intelligence;
    }

    public double getLvl1Speed() {
        return lvl1Speed;
    }

    public double getLvl1Luck() {
        return lvl1Luck;
    }

    public AttackResistanceType getBasicAttack() {
        return basicAttack;
    }

    public double getLvl1HP() {
        return lvl1HP;
    }

    public double getGrowthStrength() {
        return growthStrength;
    }

    public double getGrowthIntelligence() {
        return growthIntelligence;
    }

    public double getGrowthSpeed() {
        return growthSpeed;
    }

    public double getGrowthLuck() {
        return growthLuck;
    }

    public double getGrowthHP() {
        return growthHP;
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
