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
    protected boolean gotDamaged;
    protected LogHandler logHandler;
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
        this.gotDamaged = false;
        this.deBuffs = new HashMap<>();
        this.buffs = new HashMap<>();
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

    public boolean getGotDamaged() {
        return gotDamaged;
    }

    public void setGotDamaged(boolean gotDamaged) {
        this.gotDamaged = gotDamaged;
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

    public Map<Buff, Integer> getBuffs() {
        return buffs;
    }

    public void setBuffs(Map<Buff, Integer> buffs) {
        this.buffs = buffs;
    }

    public Map<DeBuff, Integer> getDeBuffs() {
        return deBuffs;
    }

    public void setDeBuffs(Map<DeBuff, Integer> deBuffs) {
        this.deBuffs = deBuffs;
    }

    public boolean isGuarding(){
        return false;
    }

    public void getDamage(double amount, AttackResistanceType attackResistanceType) {

        //changing damage by attackResistanceType
        amount -= amount * (this.getBasicResistance().get(attackResistanceType) / 100);

        if (isGuarding()) {
            amount *= 0.5;
        }

        //making sure amount is ?.?? format
        amount*=100;
        amount = Math.round(amount);
        amount/=100;

        if(amount > 0){
            gotDamaged = true;
        }

        logHandler.getInstance().addDamageDealt(amount);

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
        String log = this.getName() + " was attacked for " + amount + " damage";
        if(isGuarding()){
            log += " (" + this.getName() + " was guarding)";
        }
        if(currentHealthPoints <= 0){
            log += " and died";
        }
        logHandler.getInstance().setReaction(log);
    }

    public void getDamagePercentage(double amount, AttackResistanceType attackResistanceType, DeBuff deBuff) {
        //Changing percentage amount into actual amount
        amount *= maxHealthPoints;
        //changing damage by attackResistanceType
        amount -= amount * (this.getBasicResistance().get(attackResistanceType) / 100);

        //making sure amount is ?.?? format
        amount*=100;
        amount = Math.round(amount);
        amount/=100;

        if(amount > 0){
            gotDamaged = true;
        }

        //lowering health by amount
        currentHealthPoints -= amount;

        //making sure currentHealthPoints is ?.?? format
        currentHealthPoints*=100;
        if(currentHealthPoints <= 0){
            currentHealthPoints = 1;
        }
        currentHealthPoints = Math.round(currentHealthPoints);
        currentHealthPoints/=100;

        //revealing resistances:
        if(!this.characterClass.discoveredResistances.get(attackResistanceType)) {
            this.characterClass.setResistanceDiscovered(attackResistanceType);
        }
        String log = this.getName() + " was damaged by " + deBuff + " for " + amount + " damage";
        if(isGuarding()){
            log += " (" + this.getName() + " was guarding)";
        }
        logHandler.getInstance().setStatusEffects(log);
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

        //making sure currentHealthPoints is ?.?? format
        currentHealthPoints*=100;
        currentHealthPoints = Math.round(currentHealthPoints);
        currentHealthPoints/=100;

        logHandler.getInstance().setReaction(this.getName() + " was healed for " + amount + " points");
    }

    public void restoreHealthPercentage(double amount, Buff buff) {
        //Changing percentage amount into actual amount
        amount *= maxHealthPoints;
        currentHealthPoints += amount;
        if(currentHealthPoints > maxHealthPoints){
            currentHealthPoints = maxHealthPoints;
        }

        //making sure currentHealthPoints is ?.?? format
        currentHealthPoints*=100;
        currentHealthPoints = Math.round(currentHealthPoints);
        currentHealthPoints/=100;

        logHandler.getInstance().setStatusEffects(this.getName() + " was healed by " + buff +" for " + amount + " points");
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

    public void addBuffs(Map<Buff, Integer> newBuffs, boolean onlyAction) {
        for(Map.Entry<Buff,Integer> entry: newBuffs.entrySet()){
            if(buffs == null || !buffs.containsKey(entry.getKey()) || buffs.get(entry.getKey())<entry.getValue()){
                buffs.put(entry.getKey(), entry.getValue());
                if(onlyAction){
                    logHandler.getInstance().setReaction(this.getName() + " was inflicted with " + entry.getKey() + " for " + entry.getValue() + " turns");
                }else{
                    logHandler.getInstance().setReaction("and was inflicted with " + entry.getKey() + " for " + entry.getValue() + " turns", true);
                }
            }
        }
    }

    public void addDeBuffs(Map<DeBuff, Integer> newDeBuffs, boolean onlyAction) {
        for(Map.Entry<DeBuff,Integer> entry: newDeBuffs.entrySet()){
            if(deBuffs == null || !deBuffs.containsKey(entry.getKey()) || deBuffs.get(entry.getKey())<entry.getValue()){
                deBuffs.put(entry.getKey(), entry.getValue());
                if(onlyAction){
                    logHandler.getInstance().setReaction(this.getName() + " was inflicted with " + entry.getKey() + " for " + entry.getValue() + " turns");
                }else{
                    logHandler.getInstance().setReaction("and was inflicted with " + entry.getKey() + " for " + entry.getValue() + " turns", true);
                }}
        }
    }
}
