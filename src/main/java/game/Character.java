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
    protected double basicStrength;
    protected double basicIntelligence;
    protected double basicSpeed;
    protected double basicLuck;
    // TODO: add buffs, debuffs, skills
    protected Map<Buff, Integer> buffs;
    protected Map<DeBuff, Integer> deBuffs;

    public Character(CharacterClass characterClass, String name, int level) {
        this.characterClass = characterClass;
        this.name = name;
        this.level = level;
        this.maxHealthPoints = characterClass.getLvl1HP() + characterClass.getGrowthHP() * (level - 1);
        this.currentHealthPoints = maxHealthPoints;
        this.basicStrength = characterClass.getLvl1Strength() + characterClass.getGrowthStrength() * (level - 1);
        this.currentStrength = this.basicStrength;
        this.basicIntelligence = characterClass.getLvl1Intelligence() + characterClass.getGrowthIntelligence() * (level - 1);
        this.currentIntelligence = this.basicIntelligence;
        this.basicSpeed = characterClass.getLvl1Speed() + characterClass.getGrowthSpeed() * (level - 1);
        this.currentSpeed = this.basicSpeed;
        this.basicLuck = characterClass.getLvl1Luck() + characterClass.getGrowthLuck() * (level - 1);
        this.currentLuck = this.basicLuck;
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
        return this.getCharacterClass().getLvl1Strength();
    }

    public double getBasicIntelligence() {
        return this.getCharacterClass().getLvl1Intelligence();
    }

    public double getBasicSpeed() {
        return this.getCharacterClass().getLvl1Speed();
    }

    public double getBasicLuck() {
        return this.getCharacterClass().getLvl1Luck();
    }

    public AttackResistanceType getBasicAttack() {
        return this.getCharacterClass().getBasicAttack();
    }

    public void getDamage(double amount, AttackResistanceType attackResistanceType) {

        //changing damage by attackResistanceType
        amount -= amount * (this.getBasicResistance().get(attackResistanceType) / 100);

        //making sure amount is ?.?? format
        amount*=100;
        amount = Math.round(amount);
        amount/=100;

        //lowering health by amount
        currentHealthPoints -= amount;

        //making sure currentHealthPoints is ?.?? format
        currentHealthPoints*=100;
        currentHealthPoints = Math.round(currentHealthPoints);
        currentHealthPoints/=100;

        //revealing resistances:
        if(!this.characterClass.discoveredResistances.get(attackResistanceType)) {
            this.characterClass.setResistanceDiscovered(attackResistanceType);
        }
        //TODO move this to log
        System.out.println(this.getName() + " was attacked for " + amount + " damage" );
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
        if(currentHealthPoints > maxHealthPoints){
            currentHealthPoints = maxHealthPoints;
        }
        //TODO: move this to log
        System.out.println(this.getName() + " was healed for " + amount + " points");
    }

    public void basicAttack(List<Character> targets) {
        double amount;

        if(this.getBasicAttack().equals(AttackResistanceType.PHYSICAL)) {
            if (checkIfCritical())
                amount = 2.0 * (currentStrength/10);
            else
                amount = currentStrength/10;
        } else {
            if (checkIfCritical())
                amount = 2.0 * (currentIntelligence/10);
            else
                amount = currentIntelligence/10;
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
}
