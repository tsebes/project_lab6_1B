package gui;

import game.AttackResistanceType;
import game.Character;
import game.CharacterClass;

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

    public AnalyzePanel(BattlePanel battle) {
        this.battlePanel = battle;
        //TODO rework menu graphics
        setBounds(200, 400, 600, 200);
        setBackground(Color.BLACK);
        setVisible(false);
        setLayout(null);
        addTargetTitle();
        addTargetLevel();
        addTargetHP();
        addResistanceLabel();
        addPhysicalResistance();
        addAirResistance();
        addEarthResistance();
        addFireResistance();
        addWaterResistance();
        addEnergyResistance();
        addDarkResistance();
        addLightResistance();
        //TODO add label with known chosen enemy information
    }

    private void addTargetTitle() {
        JLabel title = new JLabel("", SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 28));
        title.setForeground(Color.WHITE);
        title.setBounds(100, 0, 400, 40);
        targetTitle = title;
        add(title);
    }

    private void addTargetLevel() {
        JLabel level = new JLabel("", SwingConstants.CENTER);
        level.setFont(new Font("Serif", Font.BOLD, 20));
        level.setForeground(Color.WHITE);
        level.setBounds(10, 40, 100, 40);
        targetLevel = level;
        add(level);
    }

    private void addTargetHP() {
        JLabel hp = new JLabel("", SwingConstants.CENTER);
        hp.setFont(new Font("Serif", Font.BOLD, 20));
        hp.setForeground(Color.WHITE);
        hp.setBounds(120, 40, 200, 40);
        targetHP = hp;
        add(hp);
    }

    private void addResistanceLabel() {
        JLabel resistanceLabel = new JLabel("Known resistances:", SwingConstants.CENTER);
        resistanceLabel.setFont(new Font("Serif", Font.BOLD, 20));
        resistanceLabel.setForeground(Color.WHITE);
        resistanceLabel.setBounds(10, 70, 175, 40);
        add(resistanceLabel);
    }

    private void addPhysicalResistance() {
        JLabel physicalResistance = new JLabel("PHYSICAL: ???", SwingConstants.CENTER);
        physicalResistance.setFont(new Font("Serif", Font.BOLD, 20));
        physicalResistance.setForeground(Color.WHITE);
        physicalResistance.setBounds(10, 100, 200, 40);
        targetPhysicalResistance = physicalResistance;
        add(physicalResistance);
    }

    private void addAirResistance() {
        JLabel airResistance = new JLabel("AIR: ???", SwingConstants.CENTER);
        airResistance.setFont(new Font("Serif", Font.BOLD, 20));
        airResistance.setForeground(Color.WHITE);
        airResistance.setBounds(160, 100, 200, 40);
        targetAirResistance = airResistance;
        add(airResistance);
    }

    private void addEarthResistance() {
        JLabel earthResistance = new JLabel("EARTH: ???", SwingConstants.CENTER);
        earthResistance.setFont(new Font("Serif", Font.BOLD, 20));
        earthResistance.setForeground(Color.WHITE);
        earthResistance.setBounds(300, 100, 200, 40);
        targetEarthResistance = earthResistance;
        add(earthResistance);
    }

    private void addFireResistance() {
        JLabel fireResistance = new JLabel("FIRE: ???", SwingConstants.CENTER);
        fireResistance.setFont(new Font("Serif", Font.BOLD, 20));
        fireResistance.setForeground(Color.WHITE);
        fireResistance.setBounds(440, 100, 200, 40);
        targetFireResistance = fireResistance;
        add(fireResistance);
    }

    private void addWaterResistance() {
        JLabel waterResistance = new JLabel("WATER: ???", SwingConstants.CENTER);
        waterResistance.setFont(new Font("Serif", Font.BOLD, 20));
        waterResistance.setForeground(Color.WHITE);
        waterResistance.setBounds(0, 130, 200, 40);
        targetWaterResistance = waterResistance;
        add(waterResistance);
    }

    private void addEnergyResistance() {
        JLabel energyResistance = new JLabel("ENERGY: ???", SwingConstants.CENTER);
        energyResistance.setFont(new Font("Serif", Font.BOLD, 20));
        energyResistance.setForeground(Color.WHITE);
        energyResistance.setBounds(160, 130, 200, 40);
        targetEnergyResistance = energyResistance;
        add(energyResistance);
    }

    private void addDarkResistance() {
        JLabel darkResistance = new JLabel("DARK: ???", SwingConstants.CENTER);
        darkResistance.setFont(new Font("Serif", Font.BOLD, 20));
        darkResistance.setForeground(Color.WHITE);
        darkResistance.setBounds(300, 130, 200, 40);
        targetDarkResistance = darkResistance;
        add(darkResistance);
    }

    private void addLightResistance() {
        JLabel lightResistance = new JLabel("LIGHT: ???", SwingConstants.CENTER);
        lightResistance.setFont(new Font("Serif", Font.BOLD, 20));
        lightResistance.setForeground(Color.WHITE);
        lightResistance.setBounds(440, 130, 200, 40);
        targetLightResistance = lightResistance;
        add(lightResistance);
    }

    public void refresh() {
        target = battlePanel.getBattle().getTargetsArrayList().get(0);
        targetCharacterClass = target.getCharacterClass();

        Map<AttackResistanceType, Double> basicResistances = targetCharacterClass.getBasicResistances();
        Map<AttackResistanceType, Boolean> discoveredResistances = targetCharacterClass.getDiscoveredResistances();

        targetTitle.setText("Analyze: "+target.getName());

        targetLevel.setText("Level: "+target.getLevel());

        targetHP.setText("HP: "+target.getCurrentHealthPoints()+"/"+target.getMaxHealthPoints());

        for(Map.Entry<AttackResistanceType, Boolean> entry : discoveredResistances.entrySet()) {
            if(entry.getValue()) {
                switch(entry.getKey()) {
                    case PHYSICAL -> targetPhysicalResistance.setText(entry.getKey()+": "+basicResistances.get(entry.getKey())+"%");
                    case AIR -> targetAirResistance.setText(entry.getKey()+": "+basicResistances.get(entry.getKey())+"%");
                    case EARTH -> targetEarthResistance.setText(entry.getKey()+": "+basicResistances.get(entry.getKey())+"%");
                    case FIRE -> targetFireResistance.setText(entry.getKey()+": "+basicResistances.get(entry.getKey())+"%");
                    case WATER -> targetWaterResistance.setText(entry.getKey()+": "+basicResistances.get(entry.getKey())+"%");
                    case ENERGY -> targetEnergyResistance.setText(entry.getKey()+": "+basicResistances.get(entry.getKey())+"%");
                    case DARK -> targetDarkResistance.setText(entry.getKey()+": "+basicResistances.get(entry.getKey())+"%");
                    case LIGHT -> targetLightResistance.setText(entry.getKey()+": "+basicResistances.get(entry.getKey())+"%");
                }
            } else {
                switch(entry.getKey()) {
                    case PHYSICAL -> targetPhysicalResistance.setText(entry.getKey()+": ???");
                    case AIR -> targetAirResistance.setText(entry.getKey()+": ???");
                    case EARTH -> targetEarthResistance.setText(entry.getKey()+": ???");
                    case FIRE -> targetFireResistance.setText(entry.getKey()+": ???");
                    case WATER -> targetWaterResistance.setText(entry.getKey()+": ???");
                    case ENERGY -> targetEnergyResistance.setText(entry.getKey()+": ???");
                    case DARK -> targetDarkResistance.setText(entry.getKey()+": ???");
                    case LIGHT -> targetLightResistance.setText(entry.getKey()+": ???");
                }
            }
        }

        System.out.println(targetCharacterClass.getDiscoveredResistances());
        System.out.println(targetCharacterClass.getBasicResistances());
    }
}