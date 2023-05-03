package game;


import gui.BattlePanel;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class Battle {
    protected List<Hero> heroArrayList = new ArrayList<>();
    protected List<Enemy> enemyArrayList = new ArrayList<>();
    protected List<Character> graveyardList = new ArrayList<>();
    protected Character activeCharacter;
    protected Map<Character, Double> turnOrder = new HashMap<>();
    protected List<Item> itemArrayList = new ArrayList<>();
    protected List<Character> targetsArrayList = new ArrayList<>();
    protected Action currentAction;
    protected BattlePanel battlePanel;
    protected Double timePassed;


    public Battle(List<Hero> heroArrayList, List<Enemy> enemyArrayList, BattlePanel battlePanel) {
        this.heroArrayList = heroArrayList;
        this.enemyArrayList = enemyArrayList;
        this.battlePanel = battlePanel;
        battlePanel.getCharacters().setUpCharacters(this);
        initializeTurnOrder(heroArrayList, enemyArrayList);

        //adding border to current character
        battlePanel.getCharacters().addActiveBorder();

        if (activeCharacter instanceof Enemy){
            setCurrentAction(Action.BASICATTACK);
            Random chance = new Random();
            int result = chance.nextInt(heroArrayList.size());
            setTarget(heroArrayList.get(result));
            doCurrentAction();
        }
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

    public Double getTimePassed() {
        return timePassed;
    }

    public void setTimePassed(Double timePassed) {
        this.timePassed = timePassed;
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


    //TODO delete this function once skillPanel is implemented
    public void showActiveCharacterSkills(){
        System.out.println("\n" + activeCharacter.getName() + " skills are: ");
        for(Skill skill :activeCharacter.getAvailableSkills()){
            System.out.print(skill.getName() + ",\t");
        }
        System.out.print("\n");
    }

    public void doCurrentAction() {


        //Ending possible guard
        if(activeCharacter instanceof Hero){
            Hero activeHero = (Hero) activeCharacter;
            activeHero.disableGuard();
        }

        //TODO move this information to logs
        System.out.println("\nAction of " + activeCharacter.getName());

        battlePanel.getCharacters().deleteBorder();

        int delay = 1;


        switch(this.currentAction) {
            case BASICATTACK:
                delay += 2000;
                activeCharacter.basicAttack(this.targetsArrayList);
                setTimePassed(turnOrder.get(activeCharacter));
                turnOrder.put(activeCharacter,turnOrder.get(activeCharacter)+10.0*(activeCharacter.currentSpeed));
                battlePanel.getCharacters().animate();
                break;
            case SKILL:
                break;
            case GUARD:
                Hero activeHero = (Hero) activeCharacter;
                activeHero.enableGuard();
                setTimePassed(turnOrder.get(activeCharacter));
                //TODO update changing turnOrder to make it work better
                turnOrder.put(activeCharacter,turnOrder.get(activeCharacter) + 5.0*(activeCharacter.currentSpeed));
                break;
            case ITEM:
                break;
            case ANALYZE:
                break;
        }

        //deleting any character who died
        for(Character character: targetsArrayList) {
            if (character.currentHealthPoints <= 0) {
                if (delay < 3000) {
                    delay += 1000;
                }
                turnOrder.remove(character);
                graveyardList.add(character);
            }
        }

            Timer endTurnTimer = new Timer(delay, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    endTurn();
                }
            });
            endTurnTimer.setRepeats(false);
            endTurnTimer.start();
    }

    public static <Character, Double> Map.Entry<Character, Double> getFirst(Map<Character, Double> map) {
        if (map.isEmpty()) return null;
        return map.entrySet().iterator().next();
    }

    public void initializeTurnOrder(List<Hero> heroArrayList, List<Enemy> enemyArrayList) {
        for(int i = 0;i < heroArrayList.size();i++) {
            turnOrder.put(heroArrayList.get(i),heroArrayList.get(i).getBasicSpeed());
        }

        for(int i = 0;i < enemyArrayList.size();i++) {
            turnOrder.put(enemyArrayList.get(i),enemyArrayList.get(i).getBasicSpeed());
        }

        //needs test
        turnOrder.entrySet().stream().sorted(Map.Entry.<Character,Double>comparingByValue());

        activeCharacter = getFirst(turnOrder).getKey();
    }

    public void endTurn() {
        for (Map.Entry<Character, Double> e : turnOrder.entrySet()) {
            Double value = e.getValue();
            value -= timePassed;
            turnOrder.put(e.getKey(),value);
        }

        //TODO change getFirst to work - fragment of code below might help

        Map.Entry<Character, Double> minEntry = null;
        for (Map.Entry<Character, Double> entry : turnOrder.entrySet()) {
                if (minEntry == null || entry.getValue().compareTo(minEntry.getValue()) < 0) {
                    minEntry = entry;
                }
            }

            activeCharacter = minEntry.getKey();

            //showing which character has its turn
            battlePanel.getCharacters().addActiveBorder();

            clearCurrentAction();
            //TODO move below fragment of code to enemy
            if (activeCharacter instanceof Enemy) {
                setCurrentAction(Action.BASICATTACK);

                Random chance = new Random();
                int result = chance.nextInt(heroArrayList.size());
                setTarget(heroArrayList.get(result));

                doCurrentAction();

                for (Map.Entry<Character, Double> e : turnOrder.entrySet()) {
                    Double value = e.getValue();
                    value -= timePassed;
                    turnOrder.put(e.getKey(),value);
                }
                setTimePassed(0.0);
            }
            setTimePassed(0.0);

            //TBC
        }
}
