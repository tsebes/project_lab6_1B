package game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataProvider {
    static List<CharacterClass> charactersClassesList = new ArrayList<>();
    static List<Skill> allSkillsList = new ArrayList<>();
    private static volatile DataProvider instance;

    public static final String DATA_SEPARATOR = ",";

    private DataProvider(){
        createSkills();
        createCharacterClasses();
    }

    public static DataProvider getInstance() {
        if (instance == null) {
            synchronized (DataProvider.class) {
                if (instance == null) {
                    instance = new DataProvider();
                }
            }
        }
        return instance;
    }


    public static void createCharacterClasses() {
        for (String characterClass : Data.charactersArray) {
            String [] s = characterClass.trim().split("\\s*" + DATA_SEPARATOR + "\\s*");
            ArrayList<Skill> skills = getCharacterSkills(s[1]);
            Map<AttackResistanceType, Double> resistances = getCharacterResistances(s[2]);
            AttackResistanceType basicAttack = Enum.valueOf(AttackResistanceType.class, s[13]);
            charactersClassesList.add(new CharacterClass(s[0], skills, resistances, Double.parseDouble(s[3]), Double.parseDouble(s[4]), Double.parseDouble(s[5]), Double.parseDouble(s[6]), Double.parseDouble(s[7]), Double.parseDouble(s[8]), Double.parseDouble(s[9]), Double.parseDouble(s[10]), Double.parseDouble(s[11]), Double.parseDouble(s[12]), basicAttack));
        }
    }

    public static void createSkills(){
        for (String skill : Data.skillsArray) {
            String [] s = skill.trim().split("\\s*" + DATA_SEPARATOR + "\\s*");
            boolean AOE = Boolean.parseBoolean(s[1]);
            boolean targetingEnemies = Boolean.parseBoolean(s[2]);
            AttackResistanceType attackType = Enum.valueOf(AttackResistanceType.class, s[3]);
            double skillPoints = Double.parseDouble(s[4]);
            Map<Buff, Integer> buffs = getBuffs(s[5]);
            Map<DeBuff, Integer> deBuffs = getDeBuffs(s[6]);
            double coolDownTime =  Double.parseDouble(s[7]);
            allSkillsList.add(new Skill(s[0], AOE, targetingEnemies, attackType, skillPoints, buffs, deBuffs, coolDownTime, s[8]));
        }
    }

    private static Map<DeBuff, Integer> getDeBuffs(String deBuffs) {
        Map<DeBuff, Integer> deBuffsMap = new HashMap<>();
        //adding elements of list
        return deBuffsMap;
    }

    private static Map<Buff, Integer> getBuffs(String buffs) {
        Map<Buff, Integer> buffsMap = new HashMap<>();
        //adding elements of list
        return buffsMap;
    }

    private static Map<AttackResistanceType, Double> getCharacterResistances(String resistances) {
        Map<AttackResistanceType, Double> resistancesMap = new HashMap<>();
        String [] resistance = resistances.trim().split("\\s*;\\s*");
        resistancesMap.put(AttackResistanceType.PHYSICAL, Double.parseDouble(resistance[0]));
        resistancesMap.put(AttackResistanceType.FIRE, Double.parseDouble(resistance[1]));
        resistancesMap.put(AttackResistanceType.WATER, Double.parseDouble(resistance[2]));
        resistancesMap.put(AttackResistanceType.EARTH, Double.parseDouble(resistance[3]));
        resistancesMap.put(AttackResistanceType.AIR, Double.parseDouble(resistance[4]));
        resistancesMap.put(AttackResistanceType.ENERGY, Double.parseDouble(resistance[5]));
        resistancesMap.put(AttackResistanceType.LIGHT, Double.parseDouble(resistance[6]));
        resistancesMap.put(AttackResistanceType.DARK, Double.parseDouble(resistance[7]));
        return resistancesMap;
    }

    private static ArrayList<Skill> getCharacterSkills(String skills) {
        ArrayList<Skill> skillsList = new ArrayList<>();
        String [] skill = skills.trim().split("\\s*;\\s*");
        for(String newSkill: skill){
            skillsList.add(getSkillByName(newSkill));
        }
        return skillsList;
    }

    private static Skill getSkillByName(String skillName) {
        for(Skill skill: allSkillsList){
            if(skill.getName().equals(skillName)){
                return skill;
            }
        }
        return null;
    }

    public static CharacterClass getCharacterClassByName(String characterClassName) {
        for(CharacterClass characterClass: charactersClassesList){
            if(characterClass.getCharacterClassName().equals(characterClassName)){
                return characterClass;
            }
        }
        return null;
    }
}
