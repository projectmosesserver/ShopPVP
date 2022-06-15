package info.ahaha.shoppvp;

import info.ahaha.shoppvp.commands.Cmd;
import info.ahaha.shoppvp.listener.*;
import info.ahaha.shoppvp.util.ConfigToData;
import info.ahaha.shoppvp.util.MessageUtil;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

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
        getServer().getPluginManager().registerEvents(new ExplodeCancelListener(), this);
        getServer().getPluginManager().registerEvents(new GhastShootListener(), this);
        getServer().getPluginManager().registerEvents(new EntitySpawnListener(), this);
        getServer().getPluginManager().registerEvents(new EntityTargetListener(), this);
        getServer().getPluginManager().registerEvents(new ItemUseListener(), this);
        getServer().getPluginManager().registerEvents(new EntityAddEffectListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
