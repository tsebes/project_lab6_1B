package gui;

import game.Buff;
import game.DeBuff;
import game.Skill;
import game.SpecialEffect;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class SkillsInfoPanel extends JPanel {

    private final BattlePanel battlePanel;
    private Skill skill;
    private JLabel skillTitle;
    private JLabel skillTarget;
    private JLabel skillModifier;
    private JLabel skillAttackType;
    private JLabel skillCooldown;
    private JLabel skillBuffsDebuffs;
    private JLabel skillSpecialEffect;
    private JLabel skillDescription;

    public SkillsInfoPanel(BattlePanel battle) {
        this.battlePanel = battle;
        setBounds(200, 400, 600, 200);
        setVisible(false);
        setLayout(null);
        addSkillTitle();
        addSkillTarget();
        addSkillModifier();
        addSkillAttackType();
        addSkillCooldown();
        addSkillBuffsDebuffs();
        addSkillDescription();
        addSkillSpecialEffects();
    }

    private void addSkillTitle() {
        JLabel title = new JLabel("", SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 20));
        title.setForeground(Color.BLACK);
        title.setBounds(100, 5, 400, 40);
        skillTitle = title;
        add(title);
    }

    private void addSkillTarget(){
        JLabel target = new JLabel("", SwingConstants.CENTER);
        target.setFont(new Font("Serif", Font.PLAIN, 20));
        target.setForeground(Color.BLACK);
        target.setBounds(25, 50, 250, 30);
        skillTarget = target;
        add(target);
    }

    private void addSkillModifier(){
        JLabel modifier = new JLabel("", SwingConstants.CENTER);
        modifier.setFont(new Font("Serif", Font.PLAIN, 20));
        modifier.setForeground(Color.BLACK);
        modifier.setBounds(325, 50, 250, 30);
        skillModifier = modifier;
        add(modifier);
    }

    private void addSkillAttackType(){
        JLabel attackType = new JLabel("", SwingConstants.CENTER);
        attackType.setFont(new Font("Serif", Font.PLAIN, 20));
        attackType.setForeground(Color.BLACK);
        attackType.setBounds(25, 85, 250, 30);
        skillAttackType = attackType;
        add(attackType);
    }

    private void addSkillCooldown(){
        JLabel cooldown = new JLabel("", SwingConstants.CENTER);
        cooldown.setFont(new Font("Serif", Font.PLAIN, 20));
        cooldown.setForeground(Color.BLACK);
        cooldown.setBounds(325, 85, 250, 30);
        skillCooldown = cooldown;
        add(cooldown);
    }

    private void addSkillBuffsDebuffs(){
        JLabel buffsDebuffs = new JLabel("", SwingConstants.CENTER);
        buffsDebuffs.setFont(new Font("Serif", Font.PLAIN, 20));
        buffsDebuffs.setForeground(Color.BLACK);
        buffsDebuffs.setBounds(25, 120, 250, 30);
        skillBuffsDebuffs = buffsDebuffs;
        add(buffsDebuffs);
    }

    private void addSkillSpecialEffects(){
        JLabel SpecialEffect = new JLabel("", SwingConstants.CENTER);
        SpecialEffect.setFont(new Font("Serif", Font.PLAIN, 20));
        SpecialEffect.setForeground(Color.BLACK);
        SpecialEffect.setBounds(325, 120, 250, 30);
        skillSpecialEffect = SpecialEffect;
        add(SpecialEffect);
    }

    private void addSkillDescription(){
        JLabel description = new JLabel("Description", SwingConstants.CENTER);
        description.setFont(new Font("Serif", Font.PLAIN, 15));
        description.setForeground(Color.BLACK);
        description.setBounds(25, 155, 550, 30);
        skillDescription = description;
        add(description);
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public void refresh(){
        skillTitle.setText(skill.getName());
        String target = "Target: ";
        if(skill.getAOE()){
            target += "all ";
            if(skill.getTargetingEnemies()){
                target += "enemies";
            }else{
                target += "allies";
            }
        }else{
            target += "single ";
            if(skill.getTargetingEnemies()){
                target += "enemy";
            }else{
                target += "ally";
            }
        }
        skillTarget.setText(target);
        skillModifier.setText("Modifier: " + skill.getSkillPoints());
        skillAttackType.setText("Type: " + skill.getAttackType().toString());
        skillCooldown.setText("Cooldown: " + skill.getCoolDownTime());
        String buffsDebuffs = "";
        if(skill.getTargetingEnemies()){
            if(skill.getDeBuffs().size() > 0){
                buffsDebuffs += "Debuffs: ";
                for(Map.Entry<DeBuff,Integer> entry: skill.getDeBuffs().entrySet()){
                    buffsDebuffs += entry.getKey().toString() + " ";
                }
            }
        }else{
            if(skill.getBuffs().size() > 0) {
                buffsDebuffs += "Buffs: ";
                for (Map.Entry<Buff, Integer> entry : skill.getBuffs().entrySet()) {
                    buffsDebuffs += entry.getKey().toString() + " ";
                }
            }
        }
        skillBuffsDebuffs.setText(buffsDebuffs);
        String specialEffects = "";
        if(skill.getSpecialEffects().size() > 0){
            specialEffects += "Special effects: ";
            for(SpecialEffect effect : skill.getSpecialEffects()){
                specialEffects += effect.toString();
            }
        }
        skillSpecialEffect.setText(specialEffects);
        skillDescription.setText(skill.getDescription());
    }
}