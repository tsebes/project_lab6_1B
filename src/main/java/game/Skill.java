package game;

import java.util.ArrayList;

public class Skill {

    protected final String name;
    protected final Boolean isAOE;
    protected final Boolean targetingEnemies;
    protected final AttackResistanceType attackType;
    protected final double skillPoints;
    protected final ArrayList<Buff> buffs;
    protected final ArrayList<DeBuff> deBuffs;
    protected ArrayList<SpecialEffect> specialEffects;
    protected final double coolDownTime;

    public Skill(String name, Boolean isAOE, Boolean targetingEnemies, AttackResistanceType attackType, double skillPoints, ArrayList<Buff> buffs, ArrayList<DeBuff> deBuffs, double coolDownTime) {
        this.name = name;
        this.isAOE = isAOE;
        this.targetingEnemies = targetingEnemies;
        this.attackType = attackType;
        this.skillPoints = skillPoints;
        this.buffs = buffs;
        this.deBuffs = deBuffs;
        this.coolDownTime = coolDownTime;
    }

    public String getName() {
        return name;
    }

    public Boolean getAOE() {
        return isAOE;
    }

    public Boolean isTargetingEnemies() {
        return targetingEnemies;
    }

    public AttackResistanceType getAttackType() {
        return attackType;
    }

    public double getSkillPoints() {
        return skillPoints;
    }

    public ArrayList<Buff> getBuffs() {
        return buffs;
    }

    public ArrayList<DeBuff> getDeBuffs() {
        return deBuffs;
    }

    public Boolean getTargetingEnemies() {
        return targetingEnemies;
    }

    public ArrayList<SpecialEffect> getSpecialEffects() {
        return specialEffects;
    }

    public void setSpecialEffects(ArrayList<SpecialEffect> specialEffects) {
        this.specialEffects = specialEffects;
    }

    public double getCoolDownTime() {
        return coolDownTime;
    }

    private void executeSpecialEffects() {
        for (SpecialEffect specialEffect : specialEffects) {
            // TODO: add special effects actions
            switch (specialEffect) {
                default -> {}
            }
        }
    }

    public void use(Character executor, Character target) {
        double amount;

        if(executor.getBasicAttack().equals(AttackResistanceType.PHYSICAL)) {
            if (executor.checkIfCritical())
                amount = 2.0 * (executor.getLevel() * executor.getCurrentStrength());
            else
                amount = executor.getLevel() * executor.getCurrentStrength();
        } else {
            if (executor.checkIfCritical())
                amount = 2.0 * (executor.getLevel() * executor.getCurrentIntelligence());
            else
                amount = executor.getLevel() * executor.getCurrentIntelligence();
        }

        if (targetingEnemies) {
            target.getDamage(amount, attackType);
            target.addDeBuffs(deBuffs);
        } else {
            target.restoreHealth(amount);
            target.addBuffs(buffs);
        }

        executeSpecialEffects();
    }
}
