package game;

import gui.GUI;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class BattleDataProvider {
    private static volatile BattleDataProvider instance;

    private Battle battle;
    private DataProvider dataProvider;

    static Map<String,String> battleMap = new HashMap<>();

    private BattleDataProvider() {
        importData();
    }

    public static BattleDataProvider getInstance() {
        if (instance == null) {
            synchronized (BattleDataProvider.class) {
                if (instance == null) {
                    instance = new BattleDataProvider();
                }
            }
        }
        return instance;
    }

    void importData() {
        BufferedReader reader;

        try {
            reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/battleData.txt")));
            String line = reader.readLine();

            while (line != null) {
                if(line !=null) {
                    System.out.println(line);
                    String[] s = line.trim().split("\\s*" + "-" + "\\s*");
                    battleMap.put(s[0], s[1]);
                }

                line = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //battleData.txt data setup:
    //Battle name then "-" example: LVL1BATTLE-
    //3 sections divided then by ":" are in order Hero units,Enemy units and last Items
    //each section is then divided by "," representing single objects
    //then the Hero and Enemy are subdivided by ";" representing the variables needed to construct them
    //Example from battleData.txt LVL1BATTLE:
    //LVL1BATTLE-Knight;knight;1:Skeleton;skeleton;1:Potion,Great potion,Elixir,Throwing dagger,Burning dagger

    public Battle setUpBattle(String battleName, GUI gui) {
        List<Hero> heroArrayList = new ArrayList<>();
        List<Enemy> enemyArrayList = new ArrayList<>();

        String [] battleData = battleMap.get(battleName).trim().split("\\s*" + ":" + "\\s*");
        System.out.println(battleData[0]);
        System.out.println(battleData[1]);
        System.out.println(battleData[2]);
        String [] heroData = battleData[0].trim().split("\\s*" + "," + "\\s*");
        String [] enemyData = battleData[1].trim().split("\\s*" + "," + "\\s*");
        String [] itemData = battleData[2].trim().split("\\s*" + "," + "\\s*");

        for(int i = 0;i < heroData.length;i++) {
            String [] singleHeroData = heroData[i].trim().split("\\s*" + ";" + "\\s*");
            heroArrayList.add(new Hero(dataProvider.getInstance().getCharacterClassByName(singleHeroData[0]),singleHeroData[1],Integer.valueOf(singleHeroData[2])));
        }

        for(int i = 0;i < enemyData.length;i++) {
            String [] singleEnemyData = enemyData[i].trim().split("\\s*" + ";" + "\\s*");
            enemyArrayList.add(new Enemy(dataProvider.getInstance().getCharacterClassByName(singleEnemyData[0]),singleEnemyData[1],Integer.valueOf(singleEnemyData[2])));
        }

        battle = new Battle(heroArrayList, enemyArrayList, gui.getBattlePanel());

        for(int i = 0;i < itemData.length;i++) {
            battle.addItemToList(dataProvider.getInstance().getItemByName(itemData[i]));
        }

        return battle;
    }
}
