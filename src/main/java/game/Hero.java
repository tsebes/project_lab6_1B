package game;

import java.util.Map;

public class Hero extends Character{

    protected boolean isGuardEnable = false;

    public Hero(CharacterClass characterClass, String name, int level, double maxHealthPoints) {
        super(characterClass, name, level, maxHealthPoints);
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

        currentHealthPoints -= amount * this.getBasicResistance().get(attackResistanceType);

        if (isGuardEnable) {
            System.out.println(this.getName() + " was attacked for " + amount * this.getBasicResistance().get(attackResistanceType) + " damage (" + this.getName() + " is guarded)" );
        } else {
            System.out.println(this.getName() + " was attacked for " + amount * this.getBasicResistance().get(attackResistanceType) + " damage" );
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
