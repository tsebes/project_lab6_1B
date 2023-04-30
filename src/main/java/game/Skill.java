package game;

import java.util.ArrayList;

public abstract class Skill {

    protected final String name;
    protected final Boolean isAOE;
    protected final TargetType targetType;
    protected final AttackResistanceType attackType;
    protected final double skillPoints;
    protected final ArrayList<Buff> buffs;
    protected final ArrayList<DeBuff> deBuffs;
    protected final double coolDownTime;

    public Skill(String name, Boolean isAOE, TargetType targetType, AttackResistanceType attackType, double skillPoints, ArrayList<Buff> buffs, ArrayList<DeBuff> deBuffs, double coolDownTime) {
        this.name = name;
        this.isAOE = isAOE;
        this.targetType = targetType;
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

    public TargetType getTargetType() {
        return targetType;
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

    public double getCoolDownTime() {
        return coolDownTime;
    }

    public abstract void use(Character target);
}
