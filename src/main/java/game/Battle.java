package game;

import java.util.*;

public class Battle {
    protected List<Hero> heroArrayList = new ArrayList<>();
    protected List<Enemy> enemyArrayList = new ArrayList<>();
    protected Character activeCharacter;
    Map<Character, Double> turnOrder = new HashMap<>();
    protected List<Item> itemArrayList = new ArrayList<>();
    protected List<Character> targetsArrayList = new ArrayList<>();
    protected Action currentAction;

    public enum Action {
        BASICATTACK,
        SKILL,
        GUARD,
        ITEM,
        ANALYZE
    }

    public Battle(List<Hero> heroArrayList, List<Enemy> enemyArrayList) {
        this.heroArrayList = heroArrayList;
        this.enemyArrayList = enemyArrayList;
    }

    public List<Hero> getHeroArrayList() {
        return heroArrayList;
    }

    public void setHeroArrayList(List<Hero> heroArrayList) {
        this.heroArrayList = heroArrayList;
    }

    public List<Enemy> getEnemyArrayList() {
        return enemyArrayList;
    }

    public void setEnemyArrayList(List<Enemy> enemyArrayList) {
        this.enemyArrayList = enemyArrayList;
    }

    public Character getActiveCharacter() {
        return activeCharacter;
    }

    public void setActiveCharacter(Character activeCharacter) {
        this.activeCharacter = activeCharacter;
    }

    public List<Item> getItemArrayList() {
        return itemArrayList;
    }

    public void setItemArrayList(List<Item> itemArrayList) {
        this.itemArrayList = itemArrayList;
    }

    public Map<Character, Double> getTurnOrder() {
        return turnOrder;
    }

    public List<Character> getTargetsArrayList() {
        return targetsArrayList;
    }
    //set target
    public void setTarget(Character target) {
        this.targetsArrayList.add(target);
    }

    public Enum<Action> getCurrentAction() {
        return currentAction;
    }

    //set action
    public void setCurrentAction(Action action) {
        this.currentAction = action;
    }
    //used when cancelling an action
    public void clearCurrentAction() {
        this.targetsArrayList.clear();
        this.currentAction = null;
    }

    public void doCurrentAction() {
        switch(this.currentAction) {
            case BASICATTACK:
                activeCharacter.basicAttack(this.targetsArrayList);
                turnOrder.put(activeCharacter,turnOrder.get(activeCharacter)+(10.0*(activeCharacter.currentSpeed/100)));
                clearCurrentAction();
            case SKILL:
                //
            case GUARD:
                //
            case ITEM:
                //
            case ANALYZE:
                //
        }
    }

    public static <Character, Double> Map.Entry<Character, Double> getFirst(Map<Character, Double> map) {
        if (map.isEmpty()) return null;
        return map.entrySet().iterator().next();
    }

    public void initializeTurnOrder(List<Hero> heroArrayList, List<Enemy> enemyArrayList) {
        for(int i = 0;i < heroArrayList.size();i++) {
            turnOrder.put(heroArrayList.get(i),heroArrayList.get(i).basicSpeed);
        }

        for(int i = 0;i < enemyArrayList.size();i++) {
            turnOrder.put(enemyArrayList.get(i),enemyArrayList.get(i).basicSpeed);
        }
    //needs test
        turnOrder.entrySet().stream().sorted(Map.Entry.<Character,Double>comparingByValue());

        activeCharacter = getFirst(turnOrder).getKey();
    }

    public void endTurn() {
        Double timePassed = getFirst(turnOrder).getValue();
        for (Map.Entry<Character, Double> e : turnOrder.entrySet()) {
            Double value = e.getValue();
            value -= timePassed;
        }
        clearCurrentAction();

        turnOrder.entrySet().stream().sorted(Map.Entry.<Character,Double>comparingByValue());

        activeCharacter = getFirst(turnOrder).getKey();

        if(activeCharacter instanceof Enemy) {
            setCurrentAction(Action.BASICATTACK);

            Random chance = new Random();
            int result = chance.nextInt(heroArrayList.size());
            setTarget(heroArrayList.get(result));

            doCurrentAction();

            endTurn();
        }

        //TBC
    }
}
