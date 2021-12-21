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
import info.ahaha.shoppvp.util.ConfigToData;
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
        ConfigToData configToData = new ConfigToData(manager);

        configToData.moneyToData();
        configToData.dropToData();
        configToData.effectToData();
        configToData.skillToData();
        configToData.itemToData();

        getCommand("shoppvp").setExecutor(new Cmd());
        getCommand("shoppvp").setTabCompleter(new Cmd());
        getServer().getPluginManager().registerEvents(new JoinListener(), this);
        getServer().getPluginManager().registerEvents(new DropListener(), this);
        getServer().getPluginManager().registerEvents(new EntityDeathListener(), this);
        getServer().getPluginManager().registerEvents(new EntityThrowTNTListener(), this);
        getServer().getPluginManager().registerEvents(new ItemUseListener(), this);
        getServer().getPluginManager().registerEvents(new EntityAddEffectListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
