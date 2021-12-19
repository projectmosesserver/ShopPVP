package info.ahaha.shoppvp;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DataManager {
    public static ShopPVP plugin;
    public static FileConfiguration drop = null,effect = null,entity = null,item = null,money = null,shop = null,skill = null;
    public static File dropfile = null,effectfile = null,entityfile = null,itemfile = null,moneyfile = null,shopfile = null,skillfile = null;

    public DataManager(ShopPVP plugins) {
        plugin = plugins;
        saveDefaultConfig();
    }

    public static void reloadConfig() {
        if (dropfile == null)
            dropfile = new File(plugin.getDataFolder(), "drop.yml");
        if (effectfile == null)
            effectfile = new File(plugin.getDataFolder(), "effect.yml");
        if (entityfile == null)
            entityfile = new File(plugin.getDataFolder(), "entity.yml");
        if (itemfile == null)
            itemfile = new File(plugin.getDataFolder(), "item.yml");
        if (moneyfile == null)
            moneyfile = new File(plugin.getDataFolder(), "money.yml");
        if (shopfile == null)
            shopfile = new File(plugin.getDataFolder(), "shop.yml");
        if (skillfile == null)
            shopfile = new File(plugin.getDataFolder(), "skill.yml");

        drop = YamlConfiguration.loadConfiguration(dropfile);
        effect = YamlConfiguration.loadConfiguration(effectfile);
        entity = YamlConfiguration.loadConfiguration(entityfile);
        item = YamlConfiguration.loadConfiguration(itemfile);
        money = YamlConfiguration.loadConfiguration(moneyfile);
        shop = YamlConfiguration.loadConfiguration(shopfile);
        skill = YamlConfiguration.loadConfiguration(skillfile);


        InputStream dropStream = plugin.getResource("drop.yml");
        InputStream effectStream = plugin.getResource("effect.yml");
        InputStream entityStream = plugin.getResource("entity.yml");
        InputStream itemStream = plugin.getResource("item.yml");
        InputStream moneyStream = plugin.getResource("money.yml");
        InputStream shopStream = plugin.getResource("shop.yml");
        InputStream skillStream = plugin.getResource("skill.yml");

        if (dropStream != null) {
            YamlConfiguration configs = YamlConfiguration.loadConfiguration(new InputStreamReader(dropStream));
            drop.setDefaults(configs);
        }
        if (effectStream != null) {
            YamlConfiguration configs = YamlConfiguration.loadConfiguration(new InputStreamReader(effectStream));
            effect.setDefaults(configs);
        }
        if (entityStream != null) {
            YamlConfiguration configs = YamlConfiguration.loadConfiguration(new InputStreamReader(entityStream));
            entity.setDefaults(configs);
        }
        if (itemStream != null) {
            YamlConfiguration configs = YamlConfiguration.loadConfiguration(new InputStreamReader(itemStream));
            item.setDefaults(configs);
        }
        if (moneyStream != null) {
            YamlConfiguration configs = YamlConfiguration.loadConfiguration(new InputStreamReader(moneyStream));
            money.setDefaults(configs);
        }
        if (shopStream != null) {
            YamlConfiguration configs = YamlConfiguration.loadConfiguration(new InputStreamReader(shopStream));
            shop.setDefaults(configs);
        }
        if (skillStream != null) {
            YamlConfiguration configs = YamlConfiguration.loadConfiguration(new InputStreamReader(skillStream));
            skill.setDefaults(configs);
        }

    }


    public FileConfiguration getDrop() {
        if (drop == null)
            reloadConfig();
        return drop;
    }
    public FileConfiguration getEffect() {
        if (effect == null)
            reloadConfig();
        return effect;
    }
    public FileConfiguration getEntity() {
        if (entity == null)
            reloadConfig();
        return entity;
    }
    public FileConfiguration getItem() {
        if (item == null)
            reloadConfig();
        return item;
    }
    public FileConfiguration getMoney() {
        if (money == null)
            reloadConfig();
        return money;
    }
    public FileConfiguration getShop() {
        if (shop == null)
            reloadConfig();
        return shop;
    }
    public FileConfiguration getSkill() {
        if (skill == null)
            reloadConfig();
        return skill;
    }


    public void saveDefaultConfig() {

        if (dropfile == null)
            dropfile = new File(plugin.getDataFolder(), "drop.yml");
        if (effectfile == null)
            effectfile = new File(plugin.getDataFolder(), "effect.yml");
        if (entityfile == null)
            entityfile = new File(plugin.getDataFolder(), "entity.yml");
        if (itemfile == null)
            itemfile = new File(plugin.getDataFolder(), "item.yml");
        if (moneyfile == null)
            moneyfile = new File(plugin.getDataFolder(), "money.yml");
        if (shopfile == null)
            shopfile = new File(plugin.getDataFolder(), "shop.yml");
        if (skillfile == null)
            skillfile = new File(plugin.getDataFolder(), "skill.yml");

        if (!dropfile.exists())
            plugin.saveResource("drop.yml", false);
        if (!effectfile.exists())
            plugin.saveResource("effect.yml", false);
        if (!entityfile.exists())
            plugin.saveResource("entity.yml", false);
        if (!itemfile.exists())
            plugin.saveResource("item.yml", false);
        if (!moneyfile.exists())
            plugin.saveResource("money.yml", false);
        if (!shopfile.exists())
            plugin.saveResource("shop.yml", false);
        if (!skillfile.exists())
            plugin.saveResource("skill.yml", false);

    }
}
