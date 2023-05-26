package game;

import java.util.Map;

public class Data {

    static final String[] charactersArray = {
            "Knight,Blade storm;Water Blade;Air Blade;Fire Blade;Earth Blade;Battle Focus,30;-10;0;0;0;0;-10;-10,5,5,6,4,20,3,1,2,2,5,PHYSICAL",
            "Mage,Fire storm;Flood;Typhoon;Earthquake;Energy Bolt;Energy storm;Healing aura,-20;50;10;10;10;30;-10;-10,2,8,3,4,13,1,3,2,2,3,FIRE",
            "Warlock,Dark Bolt;Dark Blast;Death Calling;Death Parade;Energy Bolt;Energy storm,-20;10;10;10;10;30;-50;90,1,9,6,1,15,1,4,2,2,4,DARK",
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
            "Fire storm,true,true,FIRE,1.4,,BURN:3,35.0,Uses fire magic burn all enemies",
            "Flood,true,true,WATER,1.1,,,25.0,Uses water magic to drown all enemies",
            "Typhoon,true,true,AIR,1.1,,,25.0,Uses air magic to summon typhoon damaging all enemies",
            "Earthquake,true,true,EARTH,1.1,,,25.0,Uses earth magic to summon earthquake damaging all enemies",
            "Energy storm,true,true,ENERGY,1.8,,,45.0,Focuses magic to attack all enemies",
            "Dark Blast,true,true,DARK,1.6,,,40.0,Uses dark magic damaging all enemies",
            "Death Parade,true,true,DARK,2.8,,CURSE:1,70.0,Uses powerful dark magic on all enemies",
            "Energy Bolt,false,true,ENERGY,1.5,,,20.0,Focuses magic energy creating powerful bolt damaging single enemy",
            "Dark Bolt,false,true,DARK,1.6,,,20.0,Focuses dark magic creating powerful bolt damaging single enemy",
            "Death Calling,false,true,DARK,2.8,,CURSE:1,40.0,Uses powerful dark magic on single enemy",
            "Water Blade,false,true,WATER,1.2,,,15.0,Covers blade with water magic and attacks single enemy",
            "Fire Blade,false,true,FIRE,1.2,,,15.0,Covers blade with fire magic and attacks single enemy",
            "Air Blade,false,true,AIR,1.2,,,15.0,Covers blade with air magic and attacks single enemy",
            "Earth Blade,false,true,EARTH,1.2,,,15.0,Covers blade with earth magic and attacks single enemy",
            "Healing aura,true,false,ENERGY,2.0,REGEN:3,,20.0,Emits aura that heals all allies",
            "Battle Focus,true,false,ENERGY,0.0,STR_UP:3;INT_UP:3,,20.0,Strengthens allies strength and intelligence",
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
            "Potion, false, false, PHYSICAL, 10,,,15,1",
            "Great potion, false, false, PHYSICAL, 20,,,15,1",
            "Elixir, false, false, PHYSICAL, 50,,,15,2",
            "Throwing dagger, false, true, PHYSICAL, 10,,,15,1",
            "Burning dagger, false, true, FIRE, 10,,,15,1",
            // Creating item tutorial:
            // String name,
            // Boolean isAOE,
            // Boolean targetingEnemies,
            // AttackResistanceType attackType,
            // double itemPoints,
            // Map<Buff,Integer> buffs (currently not working but will be divided by ; done like this: 'buff:duration'),
            // Map<DeBuff> deBuffs (currently not working but will be divided by ; done like this: 'debuff:duration'),
            // double coolDownTime
            // int amount
    };

}
