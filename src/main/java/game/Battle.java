package game;


import gui.BattlePanel;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class Battle {
    protected List<Hero> heroArrayList;
    protected List<Enemy> enemyArrayList;
    protected List<Character> graveyardList = new ArrayList<>();
    protected Character activeCharacter;
    protected Map<Character, Double> turnOrder = new HashMap<>();
    protected List<Item> itemArrayList = new ArrayList<>();
    protected List<Character> targetsArrayList = new ArrayList<>();
    protected Action currentAction;
    protected Skill currentSkill;
    protected Item currentItem;
    protected BattlePanel battlePanel;
    protected Double timePassed;
    protected LogHandler logHandler;


    public Battle(List<Hero> heroArrayList, List<Enemy> enemyArrayList, BattlePanel battlePanel) {
        this.heroArrayList = heroArrayList;
        this.enemyArrayList = enemyArrayList;
        this.battlePanel = battlePanel;

        //sets up graphics for new battle
        battlePanel.setUpNewBattle(this);

        initializeTurnOrder(heroArrayList, enemyArrayList);

        //adding border to current character
        battlePanel.getCharacters().addActiveBorder();

        if (activeCharacter instanceof Enemy){
            //Random action of enemy
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

    public void addItemToList(Item item) {
        this.itemArrayList.add(item);
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

    public Skill getCurrentSkill() {
        return currentSkill;
    }

    public void setCurrentSkill(Skill currentSkill) {
        this.currentSkill = currentSkill;
    }

    public Item getCurrentItem() {
        return this.currentItem;
    }

    public void setCurrentItem(Item item) {
        this.currentItem = item;
    }

    public List<Character> getTargetsArrayList() {
        return targetsArrayList;
    }

    //set target
    public void setTarget(Character target) {
        this.targetsArrayList.add(target);
    }

    public <T extends Character> void setTarget(List<T> targetList) {
        this.targetsArrayList.addAll(targetList);
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
        this.currentSkill = null;
    }


    public void doCurrentAction() {
        //Ending possible guard
        if(activeCharacter instanceof Hero){
            Hero activeHero = (Hero) activeCharacter;
            activeHero.disableGuard();
        }

        logHandler.getInstance().setActiveCharacterName(activeCharacter.getName());

        battlePanel.getCharacters().deleteBorder();

        int delay = 1;

        setTimePassed(turnOrder.get(activeCharacter));
        boolean dealingDamage;
        switch(this.currentAction) {
            case BASICATTACK:
                logHandler.getInstance().setCurrentAction(Action.BASICATTACK, currentSkill, currentItem);

                delay += 2000;
                activeCharacter.basicAttack(this.targetsArrayList);
                turnOrder.put(activeCharacter,turnOrder.get(activeCharacter)+10.0*(50 - activeCharacter.currentSpeed/10));
                battlePanel.getCharacters().animate(true);
                break;
            case SKILL:
                logHandler.getInstance().setCurrentAction(Action.SKILL, currentSkill, currentItem);

                delay += 1000;
                dealingDamage = currentSkill.use(activeCharacter, targetsArrayList) > 0;
                if(currentSkill.targetingEnemies && dealingDamage){
                    delay += 1000;
                }
                turnOrder.put(activeCharacter,turnOrder.get(activeCharacter)+currentSkill.getCoolDownTime()*(50 - activeCharacter.currentSpeed/10));
                battlePanel.getCharacters().animate(dealingDamage);
                break;
            case GUARD:
                logHandler.getInstance().setCurrentAction(Action.GUARD, currentSkill, currentItem);

                Hero activeHero = (Hero) activeCharacter;
                activeHero.enableGuard();

                turnOrder.put(activeCharacter,turnOrder.get(activeCharacter) + 5.0*(50 - activeCharacter.currentSpeed/10));
                break;
            case ITEM:
                logHandler.getInstance().setCurrentAction(Action.ITEM, currentSkill, currentItem);

                delay += 1000;
                dealingDamage = currentItem.use(targetsArrayList) > 0;
                if(currentItem.targetingEnemies && dealingDamage){
                    delay += 1000;
                }
                turnOrder.put(activeCharacter, turnOrder.get(activeCharacter)+currentItem.getCoolDownTime()*(50 - activeCharacter.currentSpeed/10));
                battlePanel.getCharacters().animate(dealingDamage);
                // removing used item from available items
                this.itemArrayList.remove(currentItem);
                break;
        }

        //Stopping any action until next player turn
        battlePanel.changePanel(BattlePanel.Panel.ActionStopper);

        //deleting any character who died
        for(Character character: targetsArrayList) {
            if (character.currentHealthPoints <= 0) {
                if (delay < 3000) {
                    delay += 1000;
                }
                turnOrder.remove(character);
                if(character instanceof Enemy){
                    enemyArrayList.remove(character);
                }else{
                    heroArrayList.remove(character);
                }
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

    private void lowerBuffsCount(){
        Map<Buff, Integer> buffs = new HashMap<>();
        if(activeCharacter.getBuffs() != null){
            for(Map.Entry<Buff,Integer> entry: activeCharacter.getBuffs().entrySet()){
                if(entry.getValue() > 1){
                    buffs.put(entry.getKey(), entry.getValue()-1);
                }
            }
        }
        activeCharacter.setBuffs(buffs);
    }

    private void lowerDeBuffsCount(){
        Map<DeBuff, Integer> deBuffs = new HashMap<>();
        if(activeCharacter.getDeBuffs() != null){
            for(Map.Entry<DeBuff,Integer> entry: activeCharacter.getDeBuffs().entrySet()){
                if(entry.getValue() > 1){
                    deBuffs.put(entry.getKey(), entry.getValue() - 1);
                }
            }
        }
        activeCharacter.setDeBuffs(deBuffs);
    }

    private void changeStats(){
        for(Character character: targetsArrayList){
            changeCharacterStats(character);
        }
        changeCharacterStats(activeCharacter);
    }

    private void changeCharacterStats(Character character){
        double strength = character.getBasicStrength();
        double intelligence = character.getBasicIntelligence();
        double speed = character.getBasicSpeed();
        double luck = character.getBasicLuck();
        if(character.getBuffs()!=null){
            for(Map.Entry<Buff,Integer> entry: character.getBuffs().entrySet()){
                switch(entry.getKey()){
                    case STR_UP -> {
                        strength *= 2;
                    }
                    case INT_UP -> {
                        intelligence *= 2;
                    }
                    case SPD_UP -> {
                        speed *= 2;
                    }
                    case LUC_UP -> {
                        luck *= 2;
                    }
                    default -> {
                    }
                }
            }
        }

        if(character.getDeBuffs()!=null){
            for(Map.Entry<DeBuff,Integer> entry: character.getDeBuffs().entrySet()){
                switch(entry.getKey()){
                    case STR_DOWN -> {
                        strength /= 2;
                    }
                    case INT_DOWN -> {
                        intelligence /= 2;
                    }
                    case SPD_DOWN -> {
                        speed /= 2;
                    }
                    case LUC_DOWN -> {
                        luck /= 2;
                    }
                    default -> {
                    }
                }
            }
        }
        character.setCurrentStrength(strength);
        character.setCurrentIntelligence(intelligence);
        character.setCurrentSpeed(speed);
        character.setCurrentLuck(luck);
    }

    private void buffsAndDeBuffsActions(){
        if(activeCharacter.getDeBuffs() != null){
            for(Map.Entry<DeBuff,Integer> entry: activeCharacter.getDeBuffs().entrySet()){
                switch(entry.getKey()){
                    default -> {

                    }
                    case BURN -> {
                        activeCharacter.getDamagePercentage(0.05,AttackResistanceType.FIRE, DeBuff.BURN);
                    }
                    case CURSE -> {
                        activeCharacter.getDamagePercentage(0.5,AttackResistanceType.DARK, DeBuff.CURSE);
                    }
                }
            }
        }

        if(activeCharacter.getBuffs() != null){
            for(Map.Entry<Buff,Integer> entry: activeCharacter.getBuffs().entrySet()){
                switch(entry.getKey()){
                    default -> {

                    }
                    case REGEN ->{
                        activeCharacter.restoreHealthPercentage(0.1, Buff.REGEN);
                    }
                }
            }
        }
    }

    public void endTurn() {
        //Making not stat changing buffs and deBuffs work
        buffsAndDeBuffsActions();

        //Lowering counts of buffs and deBuffs of activeCharacter
        lowerBuffsCount();
        lowerDeBuffsCount();

        //Changing current stats to correct values
        changeStats();

        //Changing info (health) in labels under characters
        battlePanel.getCharacters().refresh();

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

            //updating logs and log button
            logHandler.getInstance().makeFullLog();
            battlePanel.getLogs().addLog(logHandler.getInstance().getFullLog());
            String basicLog = logHandler.getInstance().getBasicLog();
            if(currentAction != Action.GUARD){
                basicLog += " on: ";
                for(Character character: targetsArrayList){
                    basicLog += character.getName() + ", ";
                }
                basicLog = basicLog.substring(0, basicLog.length() - 2);
            }
            battlePanel.getTurns().setLogsButtonText("<html>" + basicLog + "</html>");

            clearCurrentAction();

            //TODO move below fragment of code to enemy
            if (activeCharacter instanceof Enemy) {

                //Random action of enemy
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
            else{
                //Making choosing action possible again
                battlePanel.changePanel(BattlePanel.Panel.Skills);
            }
            setTimePassed(0.0);

            //TBC
        }
}
