package game;

public class Data {

    static final String[] charactersArray = {
            "Knight,Blade storm;Healing aura,100;100;100;100;100;100;100;100,2,2,2,2,PHYSICAL",
            "Skeleton,Undead charge,100;100;100;100;100;100;100;100,2,2,2,2,PHYSICAL"
            // Creating character tutorial:
            // characterName,
            // names of character skills, divided by ';',
            // Resistances: divided by ';' and in order: Physical; Fire; Water; Earth; Air; Energy; Light; Dark,
            // Stats in order divided by ',' Strength, Intelligence, Speed, Luck,
            // Basic attack Type
    };

    static final String[] skillsArray = {
            "Blade storm,true,true,PHYSICAL,1.2,,,30.0",
            "Healing aura,true,false,ENERGY,1.0,,,20.0",
            "Undead charge,false,true,PHYSICAL,1.3,,,21.0",
            // Creating skill tutorial:
            // String name,
            // Boolean isAOE,
            // Boolean targetingEnemies,
            // AttackResistanceType attackType,
            // double skillPoints,
            // Map<Buff,Integer> buffs (currently not working but will be divided by ; done like this: 'buff:duration'),
            // Map<DeBuff> deBuffs (currently not working but will be divided by ; done like this: 'debuff:duration'),
            // double coolDownTime
    };

}
