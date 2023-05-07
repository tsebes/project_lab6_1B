package game;

public class Data {

    static final String[] charactersArray = {
            "Knight,Blade storm;Water Blade;Air Blade;Fire Blade;Earth Blade;Healing aura,0;0;0;0;0;0;0;0,8,5,6,7,20,3,1,2,2,5,PHYSICAL",
            "Skeleton,Undead charge,-10;-20;100;60;60;0;-80;100,7,5,6,2,15,4,2,2,2,4,PHYSICAL"
            // Creating character tutorial:
            // characterName,
            // names of character skills, divided by ';',
            // Resistances: divided by ';' and in order: Physical; Fire; Water; Earth; Air; Energy; Light; Dark (-100 double damage, 0 same damage, 100 no damage),
            // Stats in order divided by ',' first lvl1 then growth Strength, Intelligence, Speed, Luck, hp
            // Basic attack Type
    };

    static final String[] skillsArray = {
            "Blade storm,true,true,PHYSICAL,1.2,,,30.0",
            "Water Blade,false,true,WATER,1.2,,,15.0",
            "Fire Blade,false,true,FIRE,1.2,,,15.0",
            "Air Blade,false,true,AIR,1.2,,,15.0",
            "Earth Blade,false,true,EARTH,1.2,,,15.0",
            "Healing aura,true,false,ENERGY,1.0,,,20.0",
            "Undead charge,false,true,PHYSICAL,1.3,,,12.0",
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
