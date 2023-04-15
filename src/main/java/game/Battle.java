package src.main.java.game;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class Battle {
    //protected List<Hero> heroArrayList = new ArrayList<>();
    protected List<Enemy> enemyArrayList = new ArrayList<>();
    protected Character activeCharacter;
    Map<Character, Double> turnOrder = new HashMap<>();
    //protected ArrayList<Item> itemArrayList = new ArrayList<>();
    protected List<Character> targetsArrayList = new ArrayList<>();

    /*
    public Battle(List<Hero> heroArrayList, List<Enemy> enemyArrayList) {
    //    this.heroArrayList = heroArrayList;
        this.enemyArrayList = enemyArrayList;
    }


    public List<Hero> getHeroArrayList() {
        return heroArrayList;
    }

    public void setHeroArrayList(List<Hero> heroArrayList) {
        this.heroArrayList = heroArrayList;
    }
    */

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

    /*
    public ArrayList<Item> getItemArrayList() {
        return itemArrayList;
    }

    public void setItemArrayList(ArrayList<Item> itemArrayList) {
        this.itemArrayList = itemArrayList;
    }
     */

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

    public static <Character, Double> Map.Entry<Character, Double> getFirst(Map<Character, Double> map) {
        if (map.isEmpty()) return null;
        return map.entrySet().iterator().next();
    }

    public void initializeTurnOrder(List<Enemy> enemyArrayList) {
    //public void initializeTurnOrder(List<Hero> heroArrayList, List<Enemy> enemyArrayList) {
        /*
        for(int i = 0;i < heroArrayList.size();i++) {
            turnOrder.put(heroArrayList.get(i),heroArrayList.get(i).basicSpeed);
        }
        */

        for(int i = 0;i < enemyArrayList.size();i++) {
            turnOrder.put(enemyArrayList.get(i),enemyArrayList.get(i).basicSpeed);
        }
    //needs test
        turnOrder.entrySet().stream().sorted(Map.Entry.<Character,Double>comparingByValue());

        activeCharacter = getFirst(turnOrder).getKey();
    }

    // setAction,doAction,cleanAction do implementacji
    public void endTurn() {
        Double timePassed = getFirst(turnOrder).getValue();
        for (Map.Entry<Character, Double> e : turnOrder.entrySet()) {
            Double value = e.getValue();
            value -= timePassed;
        }

        turnOrder.entrySet().stream().sorted(Map.Entry.<Character,Double>comparingByValue());

        activeCharacter = getFirst(turnOrder).getKey();

        //TBC
    }
}