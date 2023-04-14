package game;

import java.util.Map;

public abstract class Character {

    protected final String name;
    protected AttackResistanceType basicAttack;
    protected double healthPoints;
    protected double basicStrength;
    protected double currentStrength;
    protected double basicIntelligence;
    protected double currentIntelligence;
    protected double basicSpeed;
    protected double currentSpeed;
    protected double basicLuck;
    protected double currentLuck;
    protected Map<AttackResistanceType, Double> basicResistance;

    // TODO: add buffs, debuffs, skills
    //protected ArrayList<Buff> buffs;
    //protected ArrayList<Debuff> debuffs;
    //protected ArrayList<Skill> skills;


    public Character(String name, AttackResistanceType basicAttack, double healthPoints, double basicStrength, double basicIntelligence, double basicSpeed, double basicLuck, Map<AttackResistanceType, Double> basicResistance) {
        this.name = name;
        this.basicAttack = basicAttack;
        this.healthPoints = healthPoints;
        this.basicStrength = basicStrength;
        this.currentStrength = basicStrength;
        this.basicIntelligence = basicIntelligence;
        this.currentIntelligence = basicIntelligence;
        this.basicSpeed = basicSpeed;
        this.currentSpeed = basicSpeed;
        this.basicLuck = basicLuck;
        this.currentLuck = basicLuck;
        this.basicResistance = basicResistance;
    }

    public String getName() {
        return name;
    }

    public double getHealthPoints() {
        return healthPoints;
    }

    public AttackResistanceType getBasicAttack() {
        return basicAttack;
    }

    public void setBasicAttack(AttackResistanceType basicAttack) {
        this.basicAttack = basicAttack;
    }

    public void setHealthPoints(double healthPoints) {
        this.healthPoints = healthPoints;
    }

    public double getBasicStrength() {
        return basicStrength;
    }

    public void setBasicStrength(double basicStrength) {
        this.basicStrength = basicStrength;
    }

    public double getCurrentStrength() {
        return currentStrength;
    }

    public void setCurrentStrength(double currentStrength) {
        this.currentStrength = currentStrength;
    }

    public double getBasicIntelligence() {
        return basicIntelligence;
    }

    public void setBasicIntelligence(double basicIntelligence) {
        this.basicIntelligence = basicIntelligence;
    }

    public double getCurrentIntelligence() {
        return currentIntelligence;
    }

    public void setCurrentIntelligence(double currentIntelligence) {
        this.currentIntelligence = currentIntelligence;
    }

    public double getBasicSpeed() {
        return basicSpeed;
    }

    public void setBasicSpeed(double basicSpeed) {
        this.basicSpeed = basicSpeed;
    }

    public double getCurrentSpeed() {
        return currentSpeed;
    }

    public void setCurrentSpeed(double currentSpeed) {
        this.currentSpeed = currentSpeed;
    }

    public double getBasicLuck() {
        return basicLuck;
    }

    public void setBasicLuck(double basicLuck) {
        this.basicLuck = basicLuck;
    }

    public double getCurrentLuck() {
        return currentLuck;
    }

    public void setCurrentLuck(double currentLuck) {
        this.currentLuck = currentLuck;
    }

    public Map<AttackResistanceType, Double> getBasicResistance() {
        return basicResistance;
    }

    public void setBasicResistance(Map<AttackResistanceType, Double> basicResistance) {
        this.basicResistance = basicResistance;
    }

    public void getDamage(double amount, AttackResistanceType attackResistanceType) {
        healthPoints -= 1.0 * (amount - amount * basicResistance.get(attackResistanceType));
    }

    public void basicAttack(Character target) {
        target.getDamage(1.0 * currentStrength, basicAttack);
    }

    /*
    public void useSkill(Character target, Skill skill) {
        target.applySkill(skill);
    }

    public void useSkill(Character[] targets, Skill skill) {
        for (Character target : targets) {
            useSkill(target, skill);
        }
    }

    // abstract class to be overridden in subclass
    abstract public void applySkill(Skill skill);
    */
}
