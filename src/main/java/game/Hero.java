package game;

public class Hero extends Character{

    protected boolean isGuardEnable = false;

    public Hero(CharacterClass characterClass, String name, int level) {
        super(characterClass, name, level);
        for (AttackResistanceType attackResistanceType : AttackResistanceType.values()) {
            characterClass.discoveredResistances.put(attackResistanceType, true);
        }
    }

    @Override
    public void getDamage(double amount, AttackResistanceType attackResistanceType) {

        //changing damage by attackResistanceType
        amount -= amount * (this.getBasicResistance().get(attackResistanceType) / 100);

        //changing damage if guarding
        if (isGuardEnable) {
            amount *= 0.5;
        }

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
        currentHealthPoints = Math.round(currentHealthPoints);
        currentHealthPoints/=100;

        //TODO move this to logHandler
        if (isGuardEnable) {
            System.out.println(this.getName() + " was attacked for " + amount + " damage (" + this.getName() + " is guarded)" );
        } else {
            System.out.println(this.getName() + " was attacked for " + amount + " damage" );
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
