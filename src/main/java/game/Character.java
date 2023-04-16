package game;

import javax.swing.*;
import java.util.List;
import java.util.Map;
import java.util.Random;

public abstract class Character {

    protected final String name;
    protected final int level;
    protected AttackResistanceType basicAttack;
    protected double maxHealthPoints;
    protected double currentHealthPoints;
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

    public Character(String name, int level, AttackResistanceType basicAttack, double maxHealthPoints, double basicStrength, double basicIntelligence, double basicSpeed, double basicLuck, Map<AttackResistanceType, Double> basicResistance) {
        this.name = name;
        this.level = level;
        this.basicAttack = basicAttack;
        this.maxHealthPoints = maxHealthPoints;
        this.currentHealthPoints = maxHealthPoints;
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

    public int getLevel() { return level; }

    public double getMaxHealthPoints() {
        return maxHealthPoints;
    }

    public AttackResistanceType getBasicAttack() {
        return basicAttack;
    }

    public void setBasicAttack(AttackResistanceType basicAttack) {
        this.basicAttack = basicAttack;
    }

    public void setMaxHealthPoints(double maxHealthPoints) {
        this.maxHealthPoints = maxHealthPoints;
    }

    public double getCurrentHealthPoints() { return currentHealthPoints; }

    public void setCurrentHealthPoints(double currentHealthPoints) { this.currentHealthPoints = currentHealthPoints; }

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
        currentHealthPoints -= amount * basicResistance.get(attackResistanceType);
    }

    public boolean checkIfCritical() {
        Random chance = new Random();
        int result = chance.nextInt(100);
        return !(result >= currentLuck);
    }

    public void basicAttack(List<Character> targets) {
        double amount;

        if(this.basicAttack.equals(AttackResistanceType.PHYSICAL)) {
            if (checkIfCritical())
                amount = 2.0 * (level * currentStrength);
            else
                amount = level * currentStrength;
        } else {
            if (checkIfCritical())
                amount = 2.0 * (level * currentIntelligence);
            else
                amount = level * currentIntelligence;
        }

        for (Character target : targets) {
            target.getDamage(amount, basicAttack);
        }
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
