package game;

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
    protected List<SpecialEffect> specialEffects;
    protected final double coolDownTime;
    protected final String description;

    public Skill(String name, Boolean isAOE, Boolean targetingEnemies, AttackResistanceType attackType, double skillPoints, Map<Buff, Integer> buffs, Map<DeBuff, Integer> deBuffs, double coolDownTime, String description, List<SpecialEffect> specialEffects) {
        this.name = name;
        this.isAOE = isAOE;
        this.targetingEnemies = targetingEnemies;
        this.attackType = attackType;
        this.skillPoints = skillPoints;
        this.buffs = buffs;
        this.deBuffs = deBuffs;
        this.coolDownTime = coolDownTime;
        this.description = description;
        this.specialEffects = specialEffects;
    }

    public String getName() {
        return name;
    }

    public Boolean getAOE() {
        return isAOE;
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

    public String getDescription() {
        return description;
    }

    public Boolean getTargetingEnemies() {
        return targetingEnemies;
    }

    public List<SpecialEffect> getSpecialEffects() {
        return specialEffects;
    }

    public void setSpecialEffects(List<SpecialEffect> specialEffects) {
        this.specialEffects = specialEffects;
    }

    public double getCoolDownTime() {
        return coolDownTime;
    }

    private void executeSpecialEffects(List<Character> targets) {
        if(specialEffects!=null){
            for (SpecialEffect specialEffect : specialEffects) {
                switch (specialEffect) {
                    case AnalyzeEnemy -> {
                        for(Character target: targets) {
                            for (AttackResistanceType attackResistanceType : AttackResistanceType.values()) {
                                target.getCharacterClass().discoveredResistances.put(attackResistanceType, true);
                            }
                        }
                    }
                    default -> {}
                }
            }
        }
    }

    public double use(Character executor, List<Character> targets) {

        double amount;

        if(this.getAttackType().equals(AttackResistanceType.PHYSICAL)) {
            if (executor.checkIfCritical()){
                amount = 2.0 * (executor.getCurrentStrength()/10);
            }
            else{
                amount = executor.getCurrentStrength()/10;
            }
        } else {
            if (executor.checkIfCritical()){
                amount = 2.0 * (executor.getCurrentIntelligence()/10);
            }
            else{
                amount = executor.getCurrentIntelligence()/10;
            }
        }

        amount *= skillPoints;

        for(Character target: targets){
            if (targetingEnemies) {
                if (amount > 0) {
                    target.getDamage(amount, attackType);
                }
                target.addDeBuffs(deBuffs, amount <= 0);
            } else {
                if (amount > 0) {
                    target.restoreHealth(amount);
                }
                target.addBuffs(buffs, amount <= 0);
            }
            executeSpecialEffects(targets);
        }
        return amount;
    }
}
