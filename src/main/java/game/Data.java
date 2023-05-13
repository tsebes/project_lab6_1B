package game;

import java.util.Map;

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
            "Blade storm,true,true,PHYSICAL,1.2,,,30.0,Charges in and starts spinning with sword dealing damage to all enemies",
            "Water Blade,false,true,WATER,1.2,,,15.0,Covers blade with water magic and attacks single enemy",
            "Fire Blade,false,true,FIRE,1.2,,,15.0,Covers blade with fire magic and attacks single enemy",
            "Air Blade,false,true,AIR,1.2,,,15.0,Covers blade with air magic and attacks single enemy",
            "Earth Blade,false,true,EARTH,1.2,,,15.0,Covers blade with earth magic and attacks single enemy",
            "Healing aura,true,false,ENERGY,1.0,,,20.0,Emits aura that heals all allies",
            "Undead charge,false,true,PHYSICAL,1.3,,,12.0,Skeleton charges at enemy",
            // Creating skill tutorial:
            // String name,
            // Boolean isAOE,
            // Boolean targetingEnemies,
            // AttackResistanceType attackType,
            // double skillPoints,
            // Map<Buff,Integer> buffs (currently not working but will be divided by ; done like this: 'buff:duration'),
            // Map<DeBuff> deBuffs (currently not working but will be divided by ; done like this: 'debuff:duration'),
            // double coolDownTime
            // String description
    };

    static final String[] itemsArray = {
            "Potion, false, false, PHYSICAL, 10,,,15",
            "Great potion, false, false, PHYSICAL, 20,,,15",
            // Creating item tutorial:
            // String name,
            // Boolean isAOE,
            // Boolean targetingEnemies,
            // AttackResistanceType attackType,
            // double itemPoints,
            // Map<Buff,Integer> buffs (currently not working but will be divided by ; done like this: 'buff:duration'),
            // Map<DeBuff> deBuffs (currently not working but will be divided by ; done like this: 'debuff:duration'),
            // double coolDownTime

    };

}
