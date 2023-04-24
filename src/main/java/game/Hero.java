package game;

import java.util.Map;

public class Hero extends Character{

    protected boolean isGuardEnable = false;
    public Hero(String name, int level, AttackResistanceType basicAttack, double maxHealthPoints, double basicStrength, double basicIntelligence, double basicSpeed, double basicLuck, Map<AttackResistanceType, Double> basicResistance) {
        super(name, level, basicAttack, maxHealthPoints, basicStrength, basicIntelligence, basicSpeed, basicLuck, basicResistance);
    }

    public void useItem(Item item, Character[] targets) {
        for (Character target : targets) {
            item.use(target);
        }
    }

    @Override
    public void getDamage(double amount, AttackResistanceType attackResistanceType) {
        if (isGuardEnable) {
            amount *= 0.5;
        }

        currentHealthPoints -= amount * basicResistance.get(attackResistanceType);

        if (isGuardEnable) {
            System.out.println(this.getName() + " was attacked for " + amount * basicResistance.get(attackResistanceType) + " damage (" + this.getName() + " is guarded)" );
        } else {
            System.out.println(this.getName() + " was attacked for " + amount * basicResistance.get(attackResistanceType) + " damage" );
        }

        if(currentHealthPoints <= 0){
            System.out.println(this.getName() + " died");
        }
    }

    public void enableGuard() {
        //TODO move this to logHandler
        System.out.println("Started guarding");
        this.isGuardEnable = true;
    }

    public void disableGuard() {
        this.isGuardEnable = false;
    }
}
