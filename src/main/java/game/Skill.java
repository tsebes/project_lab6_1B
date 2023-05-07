package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Skill {

    protected final String name;
    protected final Boolean isAOE;
    protected final Boolean targetingEnemies;
    protected final AttackResistanceType attackType;
    protected final double skillPoints;
    protected final Map<Buff, Integer> buffs;
    protected final Map<DeBuff, Integer> deBuffs;
    protected ArrayList<SpecialEffect> specialEffects;
    protected final double coolDownTime;

    public Skill(String name, Boolean isAOE, Boolean targetingEnemies, AttackResistanceType attackType, double skillPoints, Map<Buff, Integer> buffs, Map<DeBuff, Integer> deBuffs, double coolDownTime) {
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

    public Map<Buff, Integer> getBuffs() {
        return buffs;
    }

    public Map<DeBuff, Integer> getDeBuffs() {
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
        if(specialEffects!=null){
            for (SpecialEffect specialEffect : specialEffects) {
                // TODO: add special effects actions
                switch (specialEffect) {
                    default -> {}
                }
            }
        }
    }

    public double use(Character executor, List<Character> targets) {

        double amount;

        if(executor.getBasicAttack().equals(AttackResistanceType.PHYSICAL)) {
            if (executor.checkIfCritical())
                amount = 2.0 * (executor.getCurrentStrength()/10);
            else
                amount = executor.getCurrentStrength()/10;
        } else {
            if (executor.checkIfCritical())
                amount = 2.0 * (executor.getCurrentIntelligence()/10);
            else
                amount = executor.getCurrentIntelligence()/10;
        }

        amount *= skillPoints;

        for(Character target: targets){
            if (targetingEnemies) {
                if (amount > 0) {
                    target.getDamage(amount, attackType);
                }
                target.addDeBuffs(deBuffs);
            } else {
                target.restoreHealth(amount);
                target.addBuffs(buffs);
            }
            executeSpecialEffects();
        }
        return amount;
    }
}
