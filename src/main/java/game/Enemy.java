package game;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class Enemy extends Character{

    public Enemy(CharacterClass characterClass, String name, int level) {
        super(characterClass, name, level);
    }

    //Pseudo EnemyAI here or in Battle Class? Need to first see Skills and char classes to begin working on it?
    public static void enemyAction(Character activeCharacter, Battle battle) {
        Random chance = new Random();
        double skillCheck = chance.nextDouble();
        if(skillCheck<=0.5) {
            battle.setCurrentAction(Action.BASICATTACK);
            int result = chance.nextInt(battle.heroArrayList.size());
            battle.setTarget(battle.heroArrayList.get(result));
            battle.doCurrentAction();
        } else {
            ArrayList<Skill> enemySkillList = new ArrayList<>();
            enemySkillList = activeCharacter.getAvailableSkills();
            battle.setCurrentAction(Action.SKILL);
            int skillChance = chance.nextInt(enemySkillList.size());
            battle.currentSkill = enemySkillList.get(skillChance);


            if(battle.currentSkill.getAOE()) {
                if(battle.currentSkill.getTargetingEnemies()) {
                    battle.setTarget(battle.heroArrayList);
                } else {
                    battle.setTarget(battle.enemyArrayList);
                }
            } else {
                int result = chance.nextInt(battle.heroArrayList.size());
                battle.setTarget(battle.heroArrayList.get(result));
            }
            battle.doCurrentAction();

            for (Map.Entry<Character, Double> e : battle.turnOrder.entrySet()) {
                Double value = e.getValue();
                value -= battle.timePassed;
                battle.turnOrder.put(e.getKey(),value);
            }
        }
    }
}
