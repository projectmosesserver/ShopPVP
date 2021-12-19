package info.ahaha.shoppvp;

import info.ahaha.shoppvp.commands.Cmd;
import info.ahaha.shoppvp.data.DropData;
import info.ahaha.shoppvp.data.EffectData;
import info.ahaha.shoppvp.data.ItemData;
import info.ahaha.shoppvp.data.SkillData;
import info.ahaha.shoppvp.listener.*;
import info.ahaha.shoppvp.skill.CircleMagma;
import info.ahaha.shoppvp.skill.Flame;
import info.ahaha.shoppvp.skill.Revival;
import info.ahaha.shoppvp.skill.ThrowTNT;
import info.ahaha.shoppvp.util.MessageUtil;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.*;

public final class ShopPVP extends JavaPlugin {

    public static ShopPVP plugin;
    public static Map<UUID, Double> deathData = new HashMap<>();
    public static Map<Material, Double> pickupMoney = new HashMap<>();
    public DataManager manager;
    public MessageUtil message;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        manager = new DataManager(this);
        message = new MessageUtil(this);

        for (String s : manager.getMoney().getStringList("Material")) {
            pickupMoney.put(Material.valueOf(s), (double) manager.getMoney().getInt(s + ".Money"));
        }

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
                types.add(EntityType.valueOf(manager.getDrop().getString("EntityType")));
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
            EffectData.data.add(data);
        }


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

        for (String s : manager.getItem().getStringList("Items")) {
            if (manager.getItem().getString(s + ".Material") == null) continue;
            ItemStack item = new ItemStack(Material.valueOf(manager.getItem().getString(s + ".Material")));
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(ChatColor.valueOf(manager.getItem().getString(s + ".Color")) + manager.getItem().getString(s + ".Name"));
            meta.setLore(new ArrayList<>(Arrays.asList(manager.getItem().getString(s + ".Explanation"))));
            item.setItemMeta(meta);
            ItemData data = new ItemData(item, manager.getItem().getString(s + ".Type"), SkillType.EFFECT, manager.getItem().getInt(s + ".Range"));
            ItemData.data.add(data);
        }

        getCommand("shoppvp").setExecutor(new Cmd());
        getCommand("shoppvp").setTabCompleter(new Cmd());
        getServer().getPluginManager().registerEvents(new JoinListener(), this);
        getServer().getPluginManager().registerEvents(new DropListener(), this);
        getServer().getPluginManager().registerEvents(new EntityDeathListener(), this);
        getServer().getPluginManager().registerEvents(new EntityThrowTNTListener(), this);
        getServer().getPluginManager().registerEvents(new ItemUseListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
