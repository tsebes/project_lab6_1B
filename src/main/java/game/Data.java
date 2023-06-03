package game;

import java.util.Map;

public class Data {

    static final String[] charactersArray = {
            "Knight,Blade storm;Water Blade;Air Blade;Fire Blade;Earth Blade;Battle Focus,30;-10;0;0;0;0;-10;-10,5,5,6,4,20,3,1,2,2,5,PHYSICAL",
            "Mage,Fire storm;Flood;Typhoon;Earthquake;Energy Bolt;Energy storm;Healing aura;All seeing eye,-20;50;10;10;10;30;-10;-10,2,8,3,4,13,1,3,2,2,3,FIRE",
            "Warlock,Dark Bolt;Dark Blast;Death Calling;Death Parade;Energy Bolt;Energy storm,-20;10;10;10;10;30;-50;90,1,9,6,1,15,1,4,2,2,4,DARK",
            "Cleric,Bless;Grant regen;Healing aura;Light Blast;Battle Focus;Energy Bolt;Energy storm,0;10;10;10;10;30;100;-20,1,9,6,1,15,1,4,2,1,3,LIGHT",
            "Skeleton,Undead charge,-10;-20;100;60;60;0;-80;100,7,5,6,2,15,4,2,2,2,4,PHYSICAL",
            "Wolf,Earthquake,-20;-10;100;40;80;0;-60;100,7,5,6,2,15,5,4,2,3,2,PHYSICAL",
            "Daemon,Energy storm,10;20;50;50;100;50;50;100,4,6,6,2,15,3,5,2,4,2,ENERGY"
            "Slime,Devour,100;0;0;100;0;-100;0;0,5,5,5,5,5,3,3,2,2,5,PHYSICAL",
            "Demon Lord,Fire storm;Purgatory;Dark Blast;Demon Lord's Aura,50;100;50;50;50;50;-100;100,10,10,10,10,25,5,5,2,3,5,DARK"
            // Creating character tutorial:
            // characterName,
            // names of character skills, divided by ';',
            // Resistances: divided by ';' and in order: Physical; Fire; Water; Earth; Air; Energy; Light; Dark (-100 double damage, 0 same damage, 100 no damage),
            // Stats in order divided by ',' first lvl1 then growth Strength, Intelligence, Speed, Luck, hp
            // Basic attack Type
    };

    static final String[] skillsArray = {
            "Blade storm,true,true,PHYSICAL,1.2,,,30.0,,Charges in and starts spinning with sword dealing damage to all enemies",
            "Fire storm,true,true,FIRE,1.4,,BURN:3,35.0,,Uses fire magic burn all enemies",
            "Flood,true,true,WATER,1.1,,,25.0,,Uses water magic to drown all enemies",
            "Typhoon,true,true,AIR,1.1,,,25.0,,Uses air magic to summon typhoon damaging all enemies",
            "Earthquake,true,true,EARTH,1.1,,,25.0,,Uses earth magic to summon earthquake damaging all enemies",
            "Energy storm,true,true,ENERGY,1.8,,,45.0,,Focuses magic to attack all enemies",
            "Light Blast,true,true,LIGHT,1.6,,,40.0,,Uses light magic damaging all enemies",
            "Dark Blast,true,true,DARK,1.6,,,40.0,,Uses dark magic damaging all enemies",
            "Death Parade,true,true,DARK,2.8,,CURSE:1,70.0,,Uses powerful dark magic on all enemies",
            "Energy Bolt,false,true,ENERGY,1.5,,,20.0,,Focuses magic energy creating powerful bolt damaging single enemy",
            "Dark Bolt,false,true,DARK,1.6,,,20.0,,Focuses dark magic creating powerful bolt damaging single enemy",
            "Death Calling,false,true,DARK,2.8,,CURSE:1,40.0,,Uses powerful dark magic on single enemy",
            "Water Blade,false,true,WATER,1.2,,,15.0,,Covers blade with water magic and attacks single enemy",
            "Fire Blade,false,true,FIRE,1.2,,,15.0,,Covers blade with fire magic and attacks single enemy",
            "Air Blade,false,true,AIR,1.2,,,15.0,,Covers blade with air magic and attacks single enemy",
            "Earth Blade,false,true,EARTH,1.2,,,15.0,,Covers blade with earth magic and attacks single enemy",
            "Healing aura,true,false,ENERGY,2.0,REGEN:3,,20.0,,Emits aura that heals all allies",
            "Battle Focus,true,false,ENERGY,0.0,STR_UP:3;INT_UP:3,,20.0,,Strengthens allies strength and intelligence",
            "Bless,true,false,ENERGY,10.0,LUC_UP:9;,,40.0,,Heals all allies and grants them good luck",
            "Grant regen,true,false,ENERGY,0.5,REGEN:5;,,20.0,,Heals small amount of hp and grants regen to all allies",
            "All seeing eye,false,true,PHYSICAL,0.0,,,20.0,AnalyzeEnemy,Uses magic to see all enemy weaknesses",
            "Undead charge,false,true,PHYSICAL,1.3,,,12.0,,Skeleton charges at enemy",
            "Devour,false,true,EARTH,2.0,,SPD_DOWN:1,20.0,,Slime coats the enemy trying to devour it",
            "Purgatory,true,true,DARK,4.0,,CURSE:2,50.0,,Demon Lord engulfs the area in dark flames",
            "Demon Lord's Aura,true,false,ENERGY,0.0,STR_UP:3;INT_UP:3;REGEN:3,,20.0,,Dark aura strengthens allies strength and intelligence and provides healing"
            // Creating skill tutorial:
            // String name,
            // Boolean isAOE,
            // Boolean targetingEnemies,
            // AttackResistanceType attackType,
            // double skillPoints,
            // Map<Buff,Integer> buffs (divided by ; done like this: 'buff:duration'),
            // Map<DeBuff> deBuffs (divided by ; done like this: 'debuff:duration'),
            // double coolDownTime
            // String description
            // List<specialEffect>
    };

    static final String[] itemsArray = {
            "Potion, false, false, PHYSICAL, 10,,,15,1",
            "Great potion, false, false, PHYSICAL, 20,,,15,1",
            "Elixir, false, false, PHYSICAL, 50,,,15,2",
            "Throwing dagger, false, true, PHYSICAL, 10,,,15,1",
            "Burning dagger, false, true, FIRE, 10,,,15,1",
            "Kill switch, true, true, PHYSICAL, 10000,,,15,1",
            // Creating item tutorial:
            // String name,
            // Boolean isAOE,
            // Boolean targetingEnemies,
            // AttackResistanceType attackType,
            // double itemPoints,
            // Map<Buff,Integer> buffs (divided by ; done like this: 'buff:duration'),
            // Map<DeBuff> deBuffs (divided by ; done like this: 'debuff:duration'),
            // double coolDownTime
            // int amount
    };

}
