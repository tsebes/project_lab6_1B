package gui;

import game.*;
import game.Item;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class ItemsInfoPanel extends JPanel {

    private final BattlePanel battlePanel;
    private Item item;
    private int amount;
    private JLabel itemTitle;
    private JLabel itemTarget;
    private JLabel itemModifier;
    private JLabel itemAttackType;
    private JLabel itemCooldown;
    private JLabel itemBuffsDebuffs;
    private JLabel itemAmount;

    public ItemsInfoPanel(BattlePanel battle) {
        this.battlePanel = battle;
        setBounds(200, 400, 600, 200);
        setVisible(false);
        setLayout(null);
        addItemTitle();
        addItemTarget();
        addItemModifier();
        addItemAttackType();
        addItemCooldown();
        addItemBuffsDebuffs();
        addItemAmount();
    }

    private void addItemTitle() {
        JLabel title = new JLabel("", SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 20));
        title.setForeground(Color.BLACK);
        title.setBounds(100, 5, 400, 40);
        itemTitle = title;
        add(title);
    }

    private void addItemTarget(){
        JLabel target = new JLabel("", SwingConstants.CENTER);
        target.setFont(new Font("Serif", Font.PLAIN, 20));
        target.setForeground(Color.BLACK);
        target.setBounds(25, 50, 250, 30);
        itemTarget = target;
        add(target);
    }

    private void addItemModifier(){
        JLabel modifier = new JLabel("", SwingConstants.CENTER);
        modifier.setFont(new Font("Serif", Font.PLAIN, 20));
        modifier.setForeground(Color.BLACK);
        modifier.setBounds(325, 50, 250, 30);
        itemModifier = modifier;
        add(modifier);
    }

    private void addItemAttackType(){
        JLabel attackType = new JLabel("", SwingConstants.CENTER);
        attackType.setFont(new Font("Serif", Font.PLAIN, 20));
        attackType.setForeground(Color.BLACK);
        attackType.setBounds(25, 85, 250, 30);
        itemAttackType = attackType;
        add(attackType);
    }

    private void addItemCooldown(){
        JLabel cooldown = new JLabel("", SwingConstants.CENTER);
        cooldown.setFont(new Font("Serif", Font.PLAIN, 20));
        cooldown.setForeground(Color.BLACK);
        cooldown.setBounds(325, 85, 250, 30);
        itemCooldown = cooldown;
        add(cooldown);
    }

    private void addItemBuffsDebuffs(){
        JLabel buffsDebuffs = new JLabel("", SwingConstants.CENTER);
        buffsDebuffs.setFont(new Font("Serif", Font.PLAIN, 20));
        buffsDebuffs.setForeground(Color.BLACK);
        buffsDebuffs.setBounds(25, 120, 250, 30);
        itemBuffsDebuffs = buffsDebuffs;
        add(buffsDebuffs);
    }

    private void addItemAmount(){
        JLabel amount = new JLabel("", SwingConstants.CENTER);
        amount.setFont(new Font("Serif", Font.PLAIN, 20));
        amount.setForeground(Color.BLACK);
        amount.setBounds(100, 155, 400, 30);
        itemAmount = amount;
        add(amount);
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item Item) {
        this.item = Item;
    }

    public int getItemAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void refresh(){
        itemTitle.setText(item.getName());
        String target = "Target: ";
        if(item.getAOE()){
            target += "all ";
            if(item.getTargetingEnemies()){
                target += "enemies";
            }else{
                target += "allies";
            }
        }else{
            target += "single ";
            if(item.getTargetingEnemies()){
                target += "enemy";
            }else{
                target += "ally";
            }
        }
        itemTarget.setText(target);
        itemModifier.setText("Modifier: " + item.getItemPoints());
        itemAttackType.setText("Type: " + item.getAttackType().toString());
        itemCooldown.setText("Cooldown: " + item.getCoolDownTime());
        String buffsDebuffs = "";
        if(item.getTargetingEnemies()){
            if(item.getDeBuffs().size() > 0){
                buffsDebuffs += "Debuffs: ";
                for(Map.Entry<DeBuff,Integer> entry: item.getDeBuffs().entrySet()){
                    buffsDebuffs += entry.getKey().toString() + " ";
                }
            }
        }else{
            if(item.getBuffs().size() > 0) {
                buffsDebuffs += "Buffs: ";
                for (Map.Entry<Buff, Integer> entry : item.getBuffs().entrySet()) {
                    buffsDebuffs += entry.getKey().toString() + " ";
                }
            }
        }
        itemBuffsDebuffs.setText(buffsDebuffs);
        itemAmount.setText("Amount: " + amount);
    }
}