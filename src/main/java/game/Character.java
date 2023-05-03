package game;

import java.util.*;

public abstract class Character {

    protected final CharacterClass characterClass;
    protected final String name;
    protected final int level;
    protected double maxHealthPoints;
    protected double currentHealthPoints;
    protected double currentStrength;
    protected double currentIntelligence;
    protected double currentSpeed;
    protected double currentLuck;

    // TODO: add buffs, debuffs, skills
    protected Map<Buff, Integer> buffs;
    protected Map<DeBuff, Integer> deBuffs;
    //protected ArrayList<Skill> skills;

    public Character(CharacterClass characterClass, String name, int level, double maxHealthPoints) {
        this.characterClass = characterClass;
        this.name = name;
        this.level = level;
        this.maxHealthPoints = maxHealthPoints;
        this.currentHealthPoints = maxHealthPoints;
        this.currentStrength = characterClass.getBasicStrength();
        this.currentIntelligence = characterClass.getBasicIntelligence();
        this.currentSpeed = characterClass.getBasicSpeed();
        this.currentLuck = characterClass.getBasicLuck();
    }

    public String getName() {
        return name;
    }

    public CharacterClass getCharacterClass() {
        return characterClass;
    }

    public int getLevel() {
        return level;
    }

    public double getMaxHealthPoints() {
        return maxHealthPoints;
    }

    public void setMaxHealthPoints(double maxHealthPoints) {
        this.maxHealthPoints = maxHealthPoints;
    }

    public double getCurrentHealthPoints() {
        return currentHealthPoints;
    }

    public void setCurrentHealthPoints(double currentHealthPoints) {
        this.currentHealthPoints = currentHealthPoints;
    }

    public double getCurrentStrength() {
        return currentStrength;
    }

    public void setCurrentStrength(double currentStrength) {
        this.currentStrength = currentStrength;
    }

    public double getCurrentIntelligence() {
        return currentIntelligence;
    }

    public void setCurrentIntelligence(double currentIntelligence) {
        this.currentIntelligence = currentIntelligence;
    }

    public double getCurrentSpeed() {
        return currentSpeed;
    }

    public void setCurrentSpeed(double currentSpeed) {
        this.currentSpeed = currentSpeed;
    }

    public double getCurrentLuck() {
        return currentLuck;
    }

    public void setCurrentLuck(double currentLuck) {
        this.currentLuck = currentLuck;
    }

    public String getCharacterClassName() {
        return this.getCharacterClass().getCharacterClassName();
    }

    public ArrayList<Skill> getAvailableSkills() {
        return this.getCharacterClass().getAvailableSkills();
    }

    public Map<AttackResistanceType, Double> getBasicResistance() {
        return this.getCharacterClass().getBasicResistances();
    }

    public double getBasicStrength() {
        return this.getCharacterClass().getBasicStrength();
    }

    public double getBasicIntelligence() {
        return this.getCharacterClass().getBasicIntelligence();
    }

    public double getBasicSpeed() {
        return this.getCharacterClass().getBasicSpeed();
    }

    public double getBasicLuck() {
        return this.getCharacterClass().getBasicLuck();
    }

    public AttackResistanceType getBasicAttack() {
        return this.getCharacterClass().getBasicAttack();
    }

    public void getDamage(double amount, AttackResistanceType attackResistanceType) {
        currentHealthPoints -= amount * this.getBasicResistance().get(attackResistanceType);
        System.out.println(this.getName() + " was attacked for " + amount * this.getBasicResistance().get(attackResistanceType) + " damage" );
        if(currentHealthPoints <= 0){
            System.out.println(this.getName() + " died");
        }
    }

    public boolean checkIfCritical() {
        Random chance = new Random();
        int result = chance.nextInt(100);
        return !(result >= currentLuck);
    }

    public void restoreHealth(double amount) {
        currentHealthPoints += amount;
        //TODO: move this to log
        System.out.println(this.getName() + " was healed for " + amount + " points");
    }

    public void basicAttack(List<Character> targets) {
        double amount;

        if(this.getBasicAttack().equals(AttackResistanceType.PHYSICAL)) {
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
            target.getDamage(amount, this.getBasicAttack());
        }
    }

    public void addBuffs(Map<Buff, Integer> newBuffs) {
        for(Map.Entry<Buff,Integer> entry: newBuffs.entrySet()){
            if(!buffs.containsKey(entry.getKey())||buffs.get(entry.getKey())<entry.getValue()){
                buffs.put(entry.getKey(), entry.getValue());
            }
        }
    }

    public void addDeBuffs(Map<DeBuff, Integer> newDeBuffs) {
        for(Map.Entry<DeBuff,Integer> entry: newDeBuffs.entrySet()){
            if(!deBuffs.containsKey(entry.getKey())||deBuffs.get(entry.getKey())<entry.getValue()){
                deBuffs.put(entry.getKey(), entry.getValue());
            }
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
