package game;

import java.util.List;
import java.util.Map;

public class Item {

    protected final String name;
    protected final Boolean isAOE;
    protected final Boolean targetingEnemies;
    protected final AttackResistanceType attackType;
    protected final double itemPoints;
    protected final Map<Buff, Integer> buffs;
    protected final Map<DeBuff, Integer> deBuffs;
    protected final double coolDownTime;

    public Item(String name, Boolean isAOE, Boolean targetingEnemies, AttackResistanceType attackType, double itemPoints, Map<Buff, Integer> buffs, Map<DeBuff, Integer> deBuffs, double coolDownTime) {
        this.name = name;
        this.isAOE = isAOE;
        this.targetingEnemies = targetingEnemies;
        this.attackType = attackType;
        this.itemPoints = itemPoints;
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

    public Boolean getTargetingEnemies() {
        return targetingEnemies;
    }

    public AttackResistanceType getAttackType() {
        return attackType;
    }

    public double getItemPoints() {
        return itemPoints;
    }

    public Map<Buff, Integer> getBuffs() {
        return buffs;
    }

    public Map<DeBuff, Integer> getDeBuffs() {
        return deBuffs;
    }

    public double getCoolDownTime() {
        return coolDownTime;
    }

    public void use(List<Character> targets) {
        for(Character target: targets){
            if (targetingEnemies) {
                target.getDamage(itemPoints, attackType);
                target.addDeBuffs(deBuffs);
            } else {
                target.restoreHealth(itemPoints);
                target.addBuffs(buffs);
            }
        }
    }
}
