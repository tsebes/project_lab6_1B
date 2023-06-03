package gui;

import game.*;
import game.Character;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class AnalyzePanel extends JPanel {

    private final BattlePanel battlePanel;
    private Character target;
    private CharacterClass targetCharacterClass;
    private JLabel targetTitle;
    private JLabel targetLevel;
    private JLabel targetHP;
    private JLabel targetPhysicalResistance;
    private JLabel targetAirResistance;
    private JLabel targetEarthResistance;
    private JLabel targetFireResistance;
    private JLabel targetWaterResistance;
    private JLabel targetEnergyResistance;
    private JLabel targetDarkResistance;
    private JLabel targetLightResistance;
    private JLabel targetBuff1;
    private JLabel targetBuff2;
    private JLabel targetBuff3;
    private JLabel targetBuff4;
    private JLabel targetBuff5;
    private JLabel targetDebuff1;
    private JLabel targetDebuff2;
    private JLabel targetDebuff3;
    private JLabel targetDebuff4;
    private JLabel targetDebuff5;
    private JLabel targetDebuff6;

    public AnalyzePanel(BattlePanel battle) {
        this.battlePanel = battle;
        //TODO rework menu graphics
        setBounds(200, 400, 600, 200);
        setVisible(false);
        setLayout(null);
        addTargetTitle();
        addTargetLevel();
        addTargetHP();
        addBuffLabel();
        addDebuffLabel();
        addResistanceLabel();
        addPhysicalResistance();
        addAirResistance();
        addEarthResistance();
        addFireResistance();
        addWaterResistance();
        addEnergyResistance();
        addDarkResistance();
        addLightResistance();
    }

    private void addTargetTitle() {
        JLabel title = new JLabel("", SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 28));
        title.setForeground(Color.BLACK);
        title.setBounds(100, 0, 400, 40);
        targetTitle = title;
        add(title);
    }

    private void addTargetLevel() {
        JLabel level = new JLabel("", SwingConstants.CENTER);
        level.setFont(new Font("Serif", Font.BOLD, 20));
        level.setForeground(Color.BLACK);
        level.setBounds(50, 30, 100, 40);
        targetLevel = level;
        add(level);
    }

    private void addTargetHP() {
        JLabel hp = new JLabel("", SwingConstants.CENTER);
        hp.setFont(new Font("Serif", Font.BOLD, 20));
        hp.setForeground(Color.BLACK);
        hp.setBounds(160, 30, 200, 40);
        targetHP = hp;
        add(hp);
    }

    private void addResistanceLabel() {
        JLabel resistanceLabel = new JLabel("Known resistances:", SwingConstants.LEFT);
        resistanceLabel.setFont(new Font("Serif", Font.BOLD, 20));
        resistanceLabel.setForeground(Color.BLACK);
        resistanceLabel.setBounds(50, 70, 175, 20);
        add(resistanceLabel);
    }

    private void addPhysicalResistance() {
        JLabel physicalResistance = new JLabel("PHYSICAL: ???", SwingConstants.LEFT);
        physicalResistance.setFont(new Font("Serif", Font.BOLD, 18));
        physicalResistance.setForeground(Color.BLACK);
        physicalResistance.setBounds(50, 100, 200, 20);
        targetPhysicalResistance = physicalResistance;
        add(physicalResistance);
    }

    private void addAirResistance() {
        JLabel airResistance = new JLabel("AIR: ???", SwingConstants.LEFT);
        airResistance.setFont(new Font("Serif", Font.BOLD, 18));
        airResistance.setForeground(Color.BLACK);
        airResistance.setBounds(50, 125, 200, 20);
        targetAirResistance = airResistance;
        add(airResistance);
    }

    private void addEarthResistance() {
        JLabel earthResistance = new JLabel("EARTH: ???", SwingConstants.LEFT);
        earthResistance.setFont(new Font("Serif", Font.BOLD, 18));
        earthResistance.setForeground(Color.BLACK);
        earthResistance.setBounds(50, 150, 200, 20);
        targetEarthResistance = earthResistance;
        add(earthResistance);
    }

    private void addFireResistance() {
        JLabel fireResistance = new JLabel("FIRE: ???", SwingConstants.LEFT);
        fireResistance.setFont(new Font("Serif", Font.BOLD, 18));
        fireResistance.setForeground(Color.BLACK);
        fireResistance.setBounds(50, 175, 200, 20);
        targetFireResistance = fireResistance;
        add(fireResistance);
    }

    private void addWaterResistance() {
        JLabel waterResistance = new JLabel("WATER: ???", SwingConstants.LEFT);
        waterResistance.setFont(new Font("Serif", Font.BOLD, 18));
        waterResistance.setForeground(Color.BLACK);
        waterResistance.setBounds(220, 100, 200, 20);
        targetWaterResistance = waterResistance;
        add(waterResistance);
    }

    private void addEnergyResistance() {
        JLabel energyResistance = new JLabel("ENERGY: ???", SwingConstants.LEFT);
        energyResistance.setFont(new Font("Serif", Font.BOLD, 18));
        energyResistance.setForeground(Color.BLACK);
        energyResistance.setBounds(220, 125, 200, 20);
        targetEnergyResistance = energyResistance;
        add(energyResistance);
    }

    private void addDarkResistance() {
        JLabel darkResistance = new JLabel("DARK: ???", SwingConstants.LEFT);
        darkResistance.setFont(new Font("Serif", Font.BOLD, 18));
        darkResistance.setForeground(Color.BLACK);
        darkResistance.setBounds(220, 150, 200, 20);
        targetDarkResistance = darkResistance;
        add(darkResistance);
    }

    private void addLightResistance() {
        JLabel lightResistance = new JLabel("LIGHT: ???", SwingConstants.LEFT);
        lightResistance.setFont(new Font("Serif", Font.BOLD, 18));
        lightResistance.setForeground(Color.BLACK);
        lightResistance.setBounds(220, 175, 200, 20);
        targetLightResistance = lightResistance;
        add(lightResistance);
    }

    private void addBuffLabel() {
        JLabel buffLabel = new JLabel("Buffs:", SwingConstants.LEFT);
        buffLabel.setFont(new Font("Serif", Font.BOLD, 20));
        buffLabel.setForeground(Color.BLACK);
        buffLabel.setBounds(370, 40, 90, 20);
        add(buffLabel);

        JLabel buff1 = new JLabel("", SwingConstants.LEFT);
        buff1.setFont(new Font("Serif", Font.BOLD, 18));
        buff1.setForeground(Color.BLACK);
        buff1.setBounds(370, 70, 90, 18);
        add(buff1);
        targetBuff1 = buff1;

        JLabel buff2 = new JLabel("", SwingConstants.LEFT);
        buff2.setFont(new Font("Serif", Font.BOLD, 18));
        buff2.setForeground(Color.BLACK);
        buff2.setBounds(370, 90, 90, 18);
        add(buff2);
        targetBuff2 = buff2;

        JLabel buff3 = new JLabel("", SwingConstants.LEFT);
        buff3.setFont(new Font("Serif", Font.BOLD, 18));
        buff3.setForeground(Color.BLACK);
        buff3.setBounds(370, 110, 90, 18);
        add(buff3);
        targetBuff3 = buff3;

        JLabel buff4 = new JLabel("", SwingConstants.LEFT);
        buff4.setFont(new Font("Serif", Font.BOLD, 18));
        buff4.setForeground(Color.BLACK);
        buff4.setBounds(370, 130, 90, 18);
        add(buff4);
        targetBuff4 = buff4;

        JLabel buff5 = new JLabel("", SwingConstants.LEFT);
        buff5.setFont(new Font("Serif", Font.BOLD, 18));
        buff5.setForeground(Color.BLACK);
        buff5.setBounds(370, 150, 90, 18);
        add(buff5);
        targetBuff5 = buff5;
    }

    private void addDebuffLabel() {
        JLabel debuffLabel = new JLabel("Debuffs:", SwingConstants.LEFT);
        debuffLabel.setFont(new Font("Serif", Font.BOLD, 20));
        debuffLabel.setForeground(Color.BLACK);
        debuffLabel.setBounds(470, 40, 90, 20);
        add(debuffLabel);

        JLabel debuff1 = new JLabel("", SwingConstants.LEFT);
        debuff1.setFont(new Font("Serif", Font.BOLD, 18));
        debuff1.setForeground(Color.BLACK);
        debuff1.setBounds(470, 70, 90, 18);
        add(debuff1);
        targetDebuff1 = debuff1;

        JLabel debuff2 = new JLabel("", SwingConstants.LEFT);
        debuff2.setFont(new Font("Serif", Font.BOLD, 18));
        debuff2.setForeground(Color.BLACK);
        debuff2.setBounds(470, 90, 90, 18);
        add(debuff2);
        targetDebuff2 = debuff2;

        JLabel debuff3 = new JLabel("", SwingConstants.LEFT);
        debuff3.setFont(new Font("Serif", Font.BOLD, 18));
        debuff3.setForeground(Color.BLACK);
        debuff3.setBounds(470, 110, 90, 18);
        add(debuff3);
        targetDebuff3 = debuff3;

        JLabel debuff4 = new JLabel("", SwingConstants.LEFT);
        debuff4.setFont(new Font("Serif", Font.BOLD, 18));
        debuff4.setForeground(Color.BLACK);
        debuff4.setBounds(470, 130, 90, 18);
        add(debuff4);
        targetDebuff4 = debuff4;

        JLabel debuff5 = new JLabel("", SwingConstants.LEFT);
        debuff5.setFont(new Font("Serif", Font.BOLD, 18));
        debuff5.setForeground(Color.BLACK);
        debuff5.setBounds(470, 150, 90, 18);
        add(debuff5);
        targetDebuff5 = debuff5;

        JLabel debuff6 = new JLabel("", SwingConstants.LEFT);
        debuff6.setFont(new Font("Serif", Font.BOLD, 18));
        debuff6.setForeground(Color.BLACK);
        debuff6.setBounds(470, 170, 90, 18);
        add(debuff6);
        targetDebuff6 = debuff6;
    }

    public void refresh() {
        target = battlePanel.getBattle().getTargetsArrayList().get(0);
        targetCharacterClass = target.getCharacterClass();

        Map<AttackResistanceType, Double> basicResistances = targetCharacterClass.getBasicResistances();
        Map<AttackResistanceType, Boolean> discoveredResistances = targetCharacterClass.getDiscoveredResistances();

        targetTitle.setText("Analyze: " + target.getName());

        targetLevel.setText("Level: " + target.getLevel());

        targetHP.setText("HP: " + target.getCurrentHealthPoints() + "/" + target.getMaxHealthPoints());

        for (Map.Entry<AttackResistanceType, Boolean> entry : discoveredResistances.entrySet()) {
            if (entry.getValue()) {
                switch (entry.getKey()) {
                    case PHYSICAL -> targetPhysicalResistance.setText(entry.getKey() + ": " + basicResistances.get(entry.getKey()) + "%");
                    case AIR -> targetAirResistance.setText(entry.getKey() + ": " + basicResistances.get(entry.getKey()) + "%");
                    case EARTH -> targetEarthResistance.setText(entry.getKey() + ": " + basicResistances.get(entry.getKey()) + "%");
                    case FIRE -> targetFireResistance.setText(entry.getKey() + ": " + basicResistances.get(entry.getKey()) + "%");
                    case WATER -> targetWaterResistance.setText(entry.getKey() + ": " + basicResistances.get(entry.getKey()) + "%");
                    case ENERGY -> targetEnergyResistance.setText(entry.getKey() + ": " + basicResistances.get(entry.getKey()) + "%");
                    case DARK -> targetDarkResistance.setText(entry.getKey() + ": " + basicResistances.get(entry.getKey()) + "%");
                    case LIGHT -> targetLightResistance.setText(entry.getKey() + ": " + basicResistances.get(entry.getKey()) + "%");
                }
            } else {
                switch (entry.getKey()) {
                    case PHYSICAL -> targetPhysicalResistance.setText(entry.getKey() + ": ???");
                    case AIR -> targetAirResistance.setText(entry.getKey() + ": ???");
                    case EARTH -> targetEarthResistance.setText(entry.getKey() + ": ???");
                    case FIRE -> targetFireResistance.setText(entry.getKey() + ": ???");
                    case WATER -> targetWaterResistance.setText(entry.getKey() + ": ???");
                    case ENERGY -> targetEnergyResistance.setText(entry.getKey() + ": ???");
                    case DARK -> targetDarkResistance.setText(entry.getKey() + ": ???");
                    case LIGHT -> targetLightResistance.setText(entry.getKey() + ": ???");
                }
            }
        }

        Map<Buff, Integer> buffs = target.getBuffs();
        Map<DeBuff, Integer> deBuffs = target.getDeBuffs();

        targetBuff1.setText("");
        targetBuff2.setText("");
        targetBuff3.setText("");
        targetBuff4.setText("");
        targetBuff5.setText("");
        targetDebuff1.setText("");
        targetDebuff2.setText("");
        targetDebuff3.setText("");
        targetDebuff4.setText("");
        targetDebuff5.setText("");

        for (Map.Entry<Buff, Integer> entry : buffs.entrySet()) {
            if (entry.getValue() != 0) {
                switch (entry.getKey()) {
                    case STR_UP -> {
                        targetBuff1.setText(entry.getKey() + " " + entry.getValue());
                    }
                    case INT_UP -> {
                        targetBuff2.setText(entry.getKey() + " " + entry.getValue());
                    }
                    case SPD_UP -> {
                        targetBuff3.setText(entry.getKey() + " " + entry.getValue());
                    }
                    case LUC_UP -> {
                        targetBuff4.setText(entry.getKey() + " " + entry.getValue());
                    }
                    case REGEN -> {
                        targetBuff5.setText(entry.getKey() + " " + entry.getValue());
                    }
                }
            }
        }
        for(Map.Entry<DeBuff,Integer> entry : deBuffs.entrySet()) {
            if (entry.getValue() != 0) {
                switch (entry.getKey()) {
                    case STR_DOWN -> {
                        targetDebuff1.setText(entry.getKey() + " " + entry.getValue());
                    }
                    case INT_DOWN -> {
                        targetDebuff2.setText(entry.getKey() + " " + entry.getValue());
                    }
                    case SPD_DOWN -> {
                        targetDebuff3.setText(entry.getKey() + " " + entry.getValue());
                    }
                    case LUC_DOWN -> {
                        targetDebuff4.setText(entry.getKey() + " " + entry.getValue());
                    }
                    case BURN -> {
                        targetDebuff5.setText(entry.getKey() + " " + entry.getValue());
                    }
                    case CURSE -> {
                        targetDebuff6.setText(entry.getKey() + " " + entry.getValue());
                    }
                }
            }
        }
    }
}