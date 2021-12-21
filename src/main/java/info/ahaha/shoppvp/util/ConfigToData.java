package info.ahaha.shoppvp.util;

import info.ahaha.shoppvp.ActiveType;
import info.ahaha.shoppvp.DataManager;
import info.ahaha.shoppvp.Skill;
import info.ahaha.shoppvp.SkillType;
import info.ahaha.shoppvp.data.DropData;
import info.ahaha.shoppvp.data.EffectData;
import info.ahaha.shoppvp.data.ItemData;
import info.ahaha.shoppvp.data.SkillData;
import info.ahaha.shoppvp.skill.CircleMagma;
import info.ahaha.shoppvp.skill.Flame;
import info.ahaha.shoppvp.skill.Revival;
import info.ahaha.shoppvp.skill.ThrowTNT;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static info.ahaha.shoppvp.ShopPVP.pickupMoney;

public class ConfigToData {

    DataManager manager;

    public ConfigToData(DataManager manager) {
        this.manager = manager;
    }

    public void moneyToData() {
        for (String s : manager.getMoney().getStringList("Material")) {
            pickupMoney.put(Material.valueOf(s), (double) manager.getMoney().getInt(s + ".Money"));
        }
    }

    public void dropToData() {
        for (int i = manager.getDrop().getInt("Level.min"); i <= manager.getDrop().getInt("Level.max"); i++) {

            if (manager.getDrop().getString(i + ".EntityType") == null) {
                continue;
            }
            List<EntityType> types = new ArrayList<>();
            List<ItemStack> drops = new ArrayList<>();
            if (manager.getDrop().getString(i + ".EntityType").equalsIgnoreCase("Default")) {
                for (String s : manager.getDrop().getStringList("Default")) {
                    types.add(EntityType.valueOf(s));
                }
            } else {
                types.add(EntityType.valueOf(manager.getDrop().getString(i + ".EntityType")));
            }
            for (String s : manager.getDrop().getStringList(i + ".Materials")) {
                ItemStack item = new ItemStack(Material.valueOf(s), manager.getDrop().getInt(i + "." + s + ".Amount"));
                drops.add(item);
            }
            for (EntityType type : types) {
                DropData data = new DropData(i, type, drops);
                DropData.data.add(data);
            }
        }
    }

    public void effectToData() {
        for (String s : manager.getEffect().getStringList("Effects")) {
            if (manager.getEffect().getString(s + ".Effect") == null) continue;
            List<PotionEffect> effects = new ArrayList<>();
            int rgb = 0;
            for (String effectData : manager.getEffect().getStringList(s + ".Effect")) {
                int sec = manager.getEffect().getInt(s + "." + effectData + ".Sec") * 20;
                int level = manager.getEffect().getInt(s + "." + effectData + ".Level") - 1;
                PotionEffectType effectType = PotionEffectType.getByName(effectData);
                if (effectType == null) continue;
                PotionEffect effect = new PotionEffect(effectType, sec, level, true, true, true);
                effects.add(effect);
                rgb = manager.getEffect().getInt(s + "." + effectData + ".Color");
            }
            ActiveType type = ActiveType.valueOf(manager.getEffect().getString(s + ".Active"));
            EffectData data;
            if (rgb != 0) {
                data = new EffectData(s, type, effects, Color.fromRGB(rgb));
            } else {
                data = new EffectData(s, type, effects);
            }
            if (manager.getEffect().getBoolean(s + ".Random")) {
                data.setRandom(true);
            }
            if (manager.getEffect().getStringList(s + ".KeepEffect").size() != 0) {
                List<PotionEffect> keepEffects = new ArrayList<>();
                for (String k : manager.getEffect().getStringList(s + ".KeepEffect")) {
                    PotionEffect effect = new PotionEffect(PotionEffectType.getByName(k), 9999999, manager.getEffect().getInt(s + "." + k + ".Level"), true, true, true);
                    keepEffects.add(effect);
                }
                data = new EffectData(s,type,effects,keepEffects);
            }
            EffectData.data.add(data);
        }
    }

    public void skillToData() {
        for (String s : manager.getSkill().getStringList("Skills")) {
            if (manager.getSkill().getStringList(s + ".Skill").size() == 0) continue;
            Skill skill = null;
            List<Skill> skills = new ArrayList<>();
            Color color = null;
            for (String skillData : manager.getSkill().getStringList(s + ".Skill")) {
                if (skillData.equalsIgnoreCase("CircleMagma")) {
                    skill = new CircleMagma(manager.getSkill().getInt(s + "." + skillData + ".Range"));
                } else if (skillData.equalsIgnoreCase("Flame")) {
                    skill = new Flame(manager.getSkill().getInt(s + "." + skillData + ".Range"), manager.getSkill().getInt(s + "." + skillData + ".Time") * 20);
                } else if (skillData.equalsIgnoreCase("DragonBreath")) {

                } else if (skillData.equalsIgnoreCase("LightningStar")) {

                } else if (skillData.equalsIgnoreCase("Revival")) {
                    skill = new Revival(manager.getSkill().getInt(s + "." + skillData + ".Num"));
                } else if (skillData.equalsIgnoreCase("ThrowTNT")) {
                    skill = new ThrowTNT(manager.getSkill().getDouble(s + "." + skillData + ".Damage"), manager.getSkill().getInt(s + "." + skillData + ".Time"));
                }
                if (skill == null) continue;
                skills.add(skill);
                if (manager.getSkill().getInt(s + "." + skillData + ".Color") != 0) {
                    color = Color.fromRGB(manager.getSkill().getInt(s + "." + skillData + ".Color"));
                }
            }
            SkillData data = new SkillData(s, skills);
            if (color != null)
                data.setColor(color);
            SkillData.data.add(data);

        }
    }

    public void itemToData() {
        for (String s : manager.getItem().getStringList("Items")) {
            if (manager.getItem().getString(s + ".Material") == null) continue;
            ItemStack item = new ItemStack(Material.valueOf(manager.getItem().getString(s + ".Material")));
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(manager.getItem().getString(s + ".Name"));
            meta.setLore(manager.getItem().getStringList(s + ".Explanation"));
            item.setItemMeta(meta);
            ItemData data = new ItemData(item, manager.getItem().getString(s + ".Type"), manager.getItem().getInt(s + ".Range"));
            ItemData.data.add(data);
        }
    }
}
