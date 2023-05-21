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
    public boolean isGuarding() {
        return isGuardEnable;
    }

    public void enableGuard() {
        this.isGuardEnable = true;
    }

    public void disableGuard() {
        this.isGuardEnable = false;
    }
}
